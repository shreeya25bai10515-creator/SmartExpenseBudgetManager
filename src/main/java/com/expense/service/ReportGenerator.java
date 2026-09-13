package com.expense.service;

import com.expense.model.Budget;
import com.expense.util.ConsoleFormatter;

import java.time.YearMonth;
import java.util.Map;

public class ReportGenerator {
    private final ExpenseManager expenseManager;
    private final BudgetManager budgetManager;

    public ReportGenerator(ExpenseManager em, BudgetManager bm) {
        this.expenseManager = em;
        this.budgetManager = bm;
    }

    public void printMonthlyReport(YearMonth month) {
        ConsoleFormatter.printHeader("MONTHLY REPORT — " + month);

        System.out.printf("%-14s %-10s %-10s %-12s %s%n",
                "Category", "Budget", "Spent", "Status", "Progress");
        ConsoleFormatter.printSeparator();

        boolean anyBudget = false;

        for (Map.Entry<String, Budget> e : budgetManager.getAll().entrySet()) {
            Budget b = e.getValue();
            if (!b.getMonth().equals(month)) continue;
            anyBudget = true;

            double spent = expenseManager.getMonthlyExpenseByCategory(b.getCategory(), month);
            double remaining = b.getRemaining(spent);
            double fraction = b.getMonthlyLimit() > 0 ? spent / b.getMonthlyLimit() : 0;

            String status;
            String barColor;
            if (remaining < 0) {
                status = ConsoleFormatter.RED + "OVER " +
                        ConsoleFormatter.formatCurrency(Math.abs(remaining)) +
                        ConsoleFormatter.RESET;
                barColor = ConsoleFormatter.RED;
            } else if (fraction >= 0.8) {
                status = ConsoleFormatter.YELLOW + "WARN" + ConsoleFormatter.RESET;
                barColor = ConsoleFormatter.YELLOW;
            } else {
                status = ConsoleFormatter.GREEN + "OK" + ConsoleFormatter.RESET;
                barColor = ConsoleFormatter.GREEN;
            }

            String bar = barColor + ConsoleFormatter.bar(fraction, 20) + ConsoleFormatter.RESET;

            System.out.printf("%-14s %-10s %-10s %-12s %s%n",
                    b.getCategory(),
                    ConsoleFormatter.formatCurrency(b.getMonthlyLimit()),
                    ConsoleFormatter.formatCurrency(spent),
                    status,
                    bar);
        }

        if (!anyBudget) {
            System.out.println(ConsoleFormatter.DIM + "No budgets set for this month." + ConsoleFormatter.RESET);
        }

        ConsoleFormatter.printSeparator();

        double income = expenseManager.getTotalIncome();
        double expense = expenseManager.getTotalExpense();
        double savings = income - expense;

        System.out.println("Total Income : " + ConsoleFormatter.GREEN +
                ConsoleFormatter.formatCurrency(income) + ConsoleFormatter.RESET);
        System.out.println("Total Expense: " + ConsoleFormatter.RED +
                ConsoleFormatter.formatCurrency(expense) + ConsoleFormatter.RESET);

        String savingsColor = savings >= 0 ? ConsoleFormatter.GREEN : ConsoleFormatter.RED;
        System.out.println(savingsColor + "Savings      : " +
                ConsoleFormatter.formatCurrency(savings) + ConsoleFormatter.RESET);

        ConsoleFormatter.printSeparator();
    }
}