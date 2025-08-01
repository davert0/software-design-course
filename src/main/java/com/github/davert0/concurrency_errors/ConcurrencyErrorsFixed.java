package com.github.davert0.concurrency_errors;

import java.util.concurrent.atomic.AtomicInteger;


public class ConcurrencyErrorsFixed {


    /**
    Если два потока одновременно обращаются к общей переменной, 
    они могут прочитать одно и то же старое значение, оба прибавить 1 и оба записать одинаковый результат.
    В итоге инкременты теряются
    */

    //Решение через атомики:

    private static final AtomicInteger counter = new AtomicInteger();

    public static void raceConditionFixed(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    counter.incrementAndGet();   // атомарный инкремент
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        System.out.println("Final counter value: " + counter.get()); // всегда 1 000 000
    }




    /**
    1 тред захватывает lock1, 2 тред захватывает lock2, 1 тред пытается захватить lock2, 2 тред пытается захватить lock1.
    В итоге оба потока ждут навсегда.
    */

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    // Решение: Использовать единый порядок захвата блокировок
    // Всегда захватываем сначала lock1, потом lock2
    private static void doWork() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " working");
            }
        }
    }

    public static void deadlockFixed(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ConcurrencyErrorsFixed::doWork, "Thread-1");
        Thread t2 = new Thread(ConcurrencyErrorsFixed::doWork, "Thread-2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Finished without deadlock");
    }
} 