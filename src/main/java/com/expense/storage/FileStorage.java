package com.expense.storage;

import com.expense.model.Budget;
import com.expense.model.Expense;
import com.expense.model.Income;
import com.expense.model.Transaction;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles CSV persistence for transactions and budgets.
 */
public class FileStorage {
    private static final String DATA_DIR = "data";
    private static final String TX_FILE = DATA_DIR + "/transactions.csv";
    private static final String BUDGET_FILE = DATA_DIR + "/budgets.csv";

    public static void ensureDataDir() {
        try {
            Files.createDirectories(Paths.get(DATA_DIR));
            Files.createDirectories(Paths.get("docs"));
        } catch (IOException e) {
            System.out.println("Could not create data/docs directories: " + e.getMessage());
        }
    }

    public static void save(List<Transaction> transactions) {
        ensureDataDir();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TX_FILE))) {
            for (Transaction t : transactions) {
                writer.write(t.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public static List<Transaction> load() {
        List<Transaction> list = new ArrayList<>();
        Path path = Paths.get(TX_FILE);
        if (!Files.exists(path)) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(TX_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", 6);
                if (p.length < 6) continue;
                double amount = Double.parseDouble(p[2]);
                String category = p[3];
                LocalDate date = LocalDate.parse(p[4]);
                String desc = p[5];

                if ("EXPENSE".equals(p[1])) {
                    list.add(new Expense(amount, category, date, desc));
                } else {
                    list.add(new Income(amount, category, date, desc));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        return list;
    }

    public static void saveBudgets(Map<String, Budget> budgets) {
        ensureDataDir();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BUDGET_FILE))) {
            for (Budget b : budgets.values()) {
                writer.write(b.getCategory() + "," + b.getMonthlyLimit() + "," + b.getMonth());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving budgets: " + e.getMessage());
        }
    }

    public static Map<String, Budget> loadBudgets() {
        Map<String, Budget> budgets = new HashMap<>();
        Path path = Paths.get(BUDGET_FILE);
        if (!Files.exists(path)) return budgets;

        try (BufferedReader reader = new BufferedReader(new FileReader(BUDGET_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",", 3);
                if (p.length < 3) continue;
                String category = p[0];
                double limit = Double.parseDouble(p[1]);
                YearMonth month = YearMonth.parse(p[2]);
                budgets.put(category.toLowerCase() + "|" + month,
                        new Budget(category, limit, month));
            }
        } catch (IOException e) {
            System.out.println("Error loading budgets: " + e.getMessage());
        }
        return budgets;
    }
}