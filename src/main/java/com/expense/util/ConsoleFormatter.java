package com.expense.util;

public class ConsoleFormatter {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";

    public static void printBanner() {
        String[] banner = {
            "  ____                       _     _____                     _        ",
            " / ___| _ __ ___   __ _ _ __| |_  | ____|_  ___ __   ___ _ __(_) ___   ",
            " \\___ \\| '_ ` _ \\ / _` | '__| __| |  _| \\ \\/ / '_ \\ / _ \\ '__| |/ _ \\  ",
            "  ___) | | | | | | (_| | |  | |_  | |___ >  <| |_) |  __/ |  | |  __/  ",
            " |____/|_| |_| |_|\\__,_|_|   \\__| |_____/_/\\_\\ .__/ \\___|_|  |_|\\___|  ",
            "                                             |_|                       "
        };
        System.out.println(CYAN + BOLD);
        for (String line : banner) System.out.println(line);
        System.out.println(RESET + CYAN + "           --- Smart Expense & Budget Manager ---" + RESET);
        System.out.println();
    }

    public static void printHeader(String title) {
        String line = "=".repeat(60);
        System.out.println(CYAN + line + RESET);
        System.out.println(BOLD + center(title, 60) + RESET);
        System.out.println(CYAN + line + RESET);
    }

    public static void printSeparator() {
        System.out.println(DIM + "-".repeat(60) + RESET);
    }

    public static String center(String text, int width) {
        if (text.length() >= width) return text;
        int pad = (width - text.length()) / 2;
        return " ".repeat(pad) + text;
    }

    public static String formatCurrency(double amount) {
        return String.format("%.2f", amount);
    }

    public static String bar(double fraction, int width) {
        int filled = (int) Math.round(fraction * width);
        if (filled < 0) filled = 0;
        if (filled > width) filled = width;
        return "\u2588".repeat(filled) + "\u2591".repeat(width - filled);
    }
}