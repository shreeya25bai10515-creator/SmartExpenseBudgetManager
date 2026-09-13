package com.expense;

import com.expense.storage.FileStorage;
import com.expense.ui.Menu;

/**
 * Entry point of Smart Expense & Budget Manager.
 */
public class Main {
    public static void main(String[] args) {
        FileStorage.ensureDataDir();
        Menu menu = new Menu();
        menu.start();
    }
}