package com.github.davert0;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.github.davert0.bank_account.BankAccount;


public class BankAccountTest {
    
    private BankAccount account;
    
    @BeforeEach
    void setUp() {
        account = new BankAccount(1000.0);
    }
    
    @Test
    void testInitialBalance() {
        assertEquals(1000.0, account.getBalance(), 0.001, 
            "Начальный баланс должен быть равен 1000 рублей");
    }
    
    @Test
    void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.001, 
            "После пополнения на 500 рублей баланс должен быть 1500 рублей");
        
        account.deposit(250.75);
        assertEquals(1750.75, account.getBalance(), 0.001, 
            "После дополнительного пополнения на 250.75 рублей баланс должен быть 1750.75 рублей");
    }
    
    @Test
    void testWithdraw() {
        account.withdraw(300.0);
        assertEquals(700.0, account.getBalance(), 0.001, 
            "После снятия 300 рублей баланс должен быть 700 рублей");
        
        account.withdraw(150.25);
        assertEquals(549.75, account.getBalance(), 0.001, 
            "После дополнительного снятия 150.25 рублей баланс должен быть 549.75 рублей");
    }
    
    @Test
    void testMultipleOperations() {
        // Пополнение
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.001, 
            "После пополнения на 500 рублей");
        
        // Снятие
        account.withdraw(300.0);
        assertEquals(1200.0, account.getBalance(), 0.001, 
            "После снятия 300 рублей");
        
        // Еще пополнение
        account.deposit(1000.0);
        assertEquals(2200.0, account.getBalance(), 0.001, 
            "После пополнения на 1000 рублей");
        
        // Еще снятие
        account.withdraw(500.0);
        assertEquals(1700.0, account.getBalance(), 0.001, 
            "После снятия 500 рублей");
        
        // Финальное пополнение
        account.deposit(75.50);
        assertEquals(1775.50, account.getBalance(), 0.001, 
            "После финального пополнения на 75.50 рублей");
    }
    
    @Test
    @DisplayName("Тест операций с дробными числами")
    void testFractionalOperations() {
        account.deposit(123.45);
        assertEquals(1123.45, account.getBalance(), 0.001, 
            "Пополнение дробной суммы");
        
        account.withdraw(67.89);
        assertEquals(1055.56, account.getBalance(), 0.001, 
            "Снятие дробной суммы");
        
        account.deposit(0.01);
        assertEquals(1055.57, account.getBalance(), 0.001, 
            "Пополнение минимальной суммы");
    }
    
    @Test
    void testZeroBalance() {
        BankAccount zeroAccount = new BankAccount(0.0);
        assertEquals(0.0, zeroAccount.getBalance(), 0.001, 
            "Счет с нулевым балансом должен иметь баланс 0");
        
        zeroAccount.deposit(100.0);
        assertEquals(100.0, zeroAccount.getBalance(), 0.001, 
            "После пополнения нулевого счета баланс должен быть 100");
    }
    
    @Test
    void testLargeAmounts() {
        account.deposit(1000000.0);
        assertEquals(1001000.0, account.getBalance(), 0.001, 
            "Пополнение на миллион рублей");
        
        account.withdraw(500000.0);
        assertEquals(501000.0, account.getBalance(), 0.001, 
            "Снятие 500 тысяч рублей");
    }
} 