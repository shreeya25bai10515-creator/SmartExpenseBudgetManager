package com.expense.model;

import java.time.YearMonth;

/**
 * Represents a monthly budget for a spending category.
 */
public class Budget {
    private final String category;
    private final double monthlyLimit;
    private final YearMonth month;

    public Budget(String category, double monthlyLimit, YearMonth month) {
        this.category = category;
        this.monthlyLimit = monthlyLimit;
        this.month = month;
    }

    public String getCategory() { return category; }
    public double getMonthlyLimit() { return monthlyLimit; }
    public YearMonth getMonth() { return month; }

    public double getRemaining(double spent) {
        return monthlyLimit - spent;
    }
}