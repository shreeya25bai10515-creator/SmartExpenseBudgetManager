package com.expense.service;

import com.expense.model.Budget;
import com.expense.storage.FileStorage;

import java.time.YearMonth;
import java.util.Map;

public class BudgetManager {
    private final Map<String, Budget> budgets;

    public BudgetManager() {
        this.budgets = FileStorage.loadBudgets();
    }

    private String key(String category, YearMonth month) {
        return category.toLowerCase() + "|" + month;
    }

    public void setBudget(String category, double limit, YearMonth month) {
        budgets.put(key(category, month), new Budget(category, limit, month));
    }

    public Budget getBudget(String category, YearMonth month) {
        return budgets.get(key(category, month));
    }

    public Map<String, Budget> getAll() {
        return budgets;
    }

    public void save() {
        FileStorage.saveBudgets(budgets);
    }
}