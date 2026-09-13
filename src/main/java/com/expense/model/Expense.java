package com.expense.model;

import java.time.LocalDate;

/**
 * Represents an expense transaction.
 */
public class Expense extends Transaction {
    public Expense(double amount, String category, LocalDate date, String description) {
        super(amount, category, date, description, "EXPENSE");
    }
}