package com.expense.model;

import java.time.LocalDate;

/**
 * Represents an income transaction.
 */
public class Income extends Transaction {
    public Income(double amount, String category, LocalDate date, String description) {
        super(amount, category, date, description, "INCOME");
    }
}