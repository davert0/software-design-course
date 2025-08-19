package com.github.davert0.changes_protection;

import com.github.davert0.storage.DBStorage;
import com.github.davert0.storage.InMemoryStorage;
import com.github.davert0.storage.Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;

public class DependencyInjectionExample {
    public static void main(String[] args) {
        String gateway = System.getProperty("gateway", "stripe");
        String store = System.getProperty("store", "memory");
        OrderProcessor processor = Container.create(gateway, store);
        processor.process("o-1", 1999);
        processor.process("o-2", 4599);
    }

    static class Container {
        static OrderProcessor create(String gateway, String store) {
            PaymentGateway base = gateway.equals("paypal") ? new PaypalGateway() : new StripeGateway();
            PaymentGateway withRetry = new RetryingPaymentGateway(base, 3);
            PaymentGateway withLogging = new LoggingPaymentGateway(withRetry);
            Storage storage = store.equals("db") ? createDbStorage() : new InMemoryStorage();
            ReceiptRepository receiptRepository = new StorageReceiptRepository(storage);
            TimeProvider timeProvider = new SystemTimeProvider(Clock.systemUTC());
            return new OrderProcessor(withLogging, receiptRepository, timeProvider);
        }

        static Storage createDbStorage() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:h2:mem:di;DB_CLOSE_DELAY=-1");
                return new DBStorage(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    interface PaymentGateway {
        void charge(String orderId, long amountCents);
    }

    static class StripeGateway implements PaymentGateway {
        @Override
        public void charge(String orderId, long amountCents) {
            System.out.println("stripe charge order=" + orderId + ", amount=" + amountCents);
        }
    }

    static class PaypalGateway implements PaymentGateway {
        @Override
        public void charge(String orderId, long amountCents) {
            System.out.println("paypal charge order=" + orderId + ", amount=" + amountCents);
        }
    }

    static class LoggingPaymentGateway implements PaymentGateway {
        private final PaymentGateway delegate;

        LoggingPaymentGateway(PaymentGateway delegate) {
            this.delegate = delegate;
        }

        @Override
        public void charge(String orderId, long amountCents) {
            System.out.println("log start");
            delegate.charge(orderId, amountCents);
            System.out.println("log done");
        }
    }

    static class RetryingPaymentGateway implements PaymentGateway {
        private final PaymentGateway delegate;
        private final int maxAttempts;

        RetryingPaymentGateway(PaymentGateway delegate, int maxAttempts) {
            this.delegate = delegate;
            this.maxAttempts = maxAttempts;
        }

        @Override
        public void charge(String orderId, long amountCents) {
            int attempt = 0;
            while (true) {
                try {
                    delegate.charge(orderId, amountCents);
                    return;
                } catch (RuntimeException e) {
                    attempt++;
                    if (attempt >= maxAttempts) {
                        throw e;
                    }
                }
            }
        }
    }

    interface ReceiptRepository {
        void save(String record);
    }

    static class StorageReceiptRepository implements ReceiptRepository {
        private final Storage storage;

        StorageReceiptRepository(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void save(String record) {
            storage.save(record);
        }
    }

    interface TimeProvider {
        Instant now();
    }

    static class SystemTimeProvider implements TimeProvider {
        private final Clock clock;

        SystemTimeProvider(Clock clock) {
            this.clock = clock;
        }

        @Override
        public Instant now() {
            return clock.instant();
        }
    }

    static class OrderProcessor {
        private final PaymentGateway paymentGateway;
        private final ReceiptRepository receiptRepository;
        private final TimeProvider timeProvider;

        OrderProcessor(PaymentGateway paymentGateway, ReceiptRepository receiptRepository, TimeProvider timeProvider) {
            this.paymentGateway = paymentGateway;
            this.receiptRepository = receiptRepository;
            this.timeProvider = timeProvider;
        }

        void process(String orderId, long amountCents) {
            paymentGateway.charge(orderId, amountCents);
            String record = timeProvider.now().toString() + "|" + orderId + "|" + amountCents;
            receiptRepository.save(record);
        }
    }
}
