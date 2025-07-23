package com.github.davert0.bank_account;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        this.balance = this.balance + amount;
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance - amount;
    }

    public double getBalance() {
        return this.balance;
    }
}