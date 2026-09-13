package com.expense.service;

import com.expense.model.Expense;
import com.expense.model.Income;
import com.expense.model.Transaction;
import com.expense.storage.FileStorage;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseManager {
    private final List<Transaction> transactions;

    public ExpenseManager() {
        this.transactions = FileStorage.load();
    }

    public void addExpense(double amount, String category, LocalDate date, String description) {
        transactions.add(new Expense(amount, category, date, description));
    }

    public void addIncome(double amount, String category, LocalDate date, String description) {
        transactions.add(new Income(amount, category, date, description));
    }

    public boolean deleteById(int id) {
        return transactions.removeIf(t -> t.getId() == id);
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType().equals("INCOME"))
                .mapToDouble(Transaction::getAmount).sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .mapToDouble(Transaction::getAmount).sum();
    }

    public double getMonthlyExpenseByCategory(String category, YearMonth month) {
        return transactions.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .filter(t -> t.getCategory().equalsIgnoreCase(category))
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .mapToDouble(Transaction::getAmount).sum();
    }

    public List<Transaction> getByMonth(YearMonth month) {
        return transactions.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .collect(Collectors.toList());
    }

    public void save() {
        FileStorage.save(transactions);
    }
}