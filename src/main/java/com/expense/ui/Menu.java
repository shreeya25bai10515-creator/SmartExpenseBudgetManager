package com.expense.ui;

import com.expense.service.BudgetManager;
import com.expense.service.ExpenseManager;
import com.expense.service.ReportGenerator;
import com.expense.util.ConsoleFormatter;
import com.expense.util.InputValidator;

import java.time.YearMonth;

public class Menu {
    private final ExpenseManager expenseManager = new ExpenseManager();
    private final BudgetManager budgetManager = new BudgetManager();
    private final ReportGenerator reportGenerator = new ReportGenerator(expenseManager, budgetManager);

    public void start() {
        ConsoleFormatter.printBanner();
        while (true) {
            ConsoleFormatter.printHeader("MAIN MENU");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. Set Budget");
            System.out.println("4. View Monthly Report");
            System.out.println("5. Delete Transaction by ID");
            System.out.println("6. Save & Exit");
            ConsoleFormatter.printSeparator();

            int choice = InputValidator.readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addIncome();
                    break;
                case 2:
                    addExpense();
                    break;
                case 3:
                    setBudget();
                    break;
                case 4:
                    monthlyReport();
                    break;
                case 5:
                    deleteTransaction();
                    break;
                case 6:
                    expenseManager.save();
                    budgetManager.save();
                    System.out.println(ConsoleFormatter.GREEN + "Data saved. Goodbye!" + ConsoleFormatter.RESET);
                    return;
                default:
                    System.out.println(ConsoleFormatter.RED + "Invalid choice." + ConsoleFormatter.RESET);
            }
        }
    }

    private void addIncome() {
        ConsoleFormatter.printHeader("ADD INCOME");
        double amount = InputValidator.readDouble("Amount: ");
        String category = InputValidator.readString("Category: ");
        var date = InputValidator.readDate("Date");
        String desc = InputValidator.readString("Description: ");
        expenseManager.addIncome(amount, category, date, desc);
        System.out.println(ConsoleFormatter.GREEN + "Income added." + ConsoleFormatter.RESET);
    }

    private void addExpense() {
        ConsoleFormatter.printHeader("ADD EXPENSE");
        double amount = InputValidator.readDouble("Amount: ");
        String category = InputValidator.readString("Category: ");
        var date = InputValidator.readDate("Date");
        String desc = InputValidator.readString("Description: ");
        expenseManager.addExpense(amount, category, date, desc);
        System.out.println(ConsoleFormatter.GREEN + "Expense added." + ConsoleFormatter.RESET);
    }

    private void setBudget() {
        ConsoleFormatter.printHeader("SET BUDGET");
        String category = InputValidator.readString("Category: ");
        double limit = InputValidator.readDouble("Monthly limit: ");
        var date = InputValidator.readDate("Month (any date in that month)");
        budgetManager.setBudget(category, limit, YearMonth.from(date));
        System.out.println(ConsoleFormatter.GREEN + "Budget set." + ConsoleFormatter.RESET);
    }

    private void monthlyReport() {
        var date = InputValidator.readDate("Enter month (any date)");
        reportGenerator.printMonthlyReport(YearMonth.from(date));
    }

    private void deleteTransaction() {
        ConsoleFormatter.printHeader("DELETE TRANSACTION");
        int id = InputValidator.readInt("Enter transaction ID to delete: ");
        boolean removed = expenseManager.deleteById(id);
        if (removed) {
            System.out.println(ConsoleFormatter.GREEN + "Transaction #" + id + " deleted." + ConsoleFormatter.RESET);
        } else {
            System.out.println(ConsoleFormatter.RED + "No transaction found with ID " + id + "." + ConsoleFormatter.RESET);
        }
    }
}