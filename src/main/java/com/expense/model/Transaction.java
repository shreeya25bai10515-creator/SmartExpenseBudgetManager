package com.expense.model;

import java.time.LocalDate;

/**
 * Base class representing a financial transaction.
 */
public abstract class Transaction {
    private static int counter = 1;

    private final int id;
    private final double amount;
    private final String category;
    private final LocalDate date;
    private final String description;
    private final String type;

    public Transaction(double amount, String category, LocalDate date, String description, String type) {
        this.id = counter++;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public String getType() { return type; }

    /** CSV format: id,type,amount,category,date,description */
    public String toCsv() {
        return id + "," + type + "," + amount + "," + category + "," + date + "," + description;
    }

    @Override
    public String toString() {
        return String.format("%-4d | %-8s | %10.2f | %-12s | %s | %s",
                id, type, amount, category, date, description);
    }
}