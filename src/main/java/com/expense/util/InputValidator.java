package com.expense.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputValidator {
    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static String prompt(String text) {
        while (true) {
            System.out.print(text);
            System.out.flush();
            try {
                String line = reader.readLine();
                if (line == null) {
                    System.out.println();
                    System.exit(0);
                }
                line = line.trim();
                if (!line.isEmpty()) return line;
            } catch (IOException e) {
                System.out.println(ConsoleFormatter.RED + "Input error. Try again." + ConsoleFormatter.RESET);
            }
        }
    }

    public static String readString(String promptText) {
        return prompt(promptText);
    }

    public static double readDouble(String promptText) {
        while (true) {
            String line = prompt(promptText);
            try {
                double value = Double.parseDouble(line);
                if (value < 0) {
                    System.out.println(ConsoleFormatter.RED + "Amount cannot be negative." + ConsoleFormatter.RESET);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(ConsoleFormatter.RED + "Invalid number. Try again." + ConsoleFormatter.RESET);
            }
        }
    }

    public static int readInt(String promptText) {
        while (true) {
            String line = prompt(promptText);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleFormatter.RED + "Invalid number. Try again." + ConsoleFormatter.RESET);
            }
        }
    }

    public static LocalDate readDate(String promptText) {
        while (true) {
            String line = prompt(promptText + " (yyyy-MM-dd): ");
            try {
                return LocalDate.parse(line, FORMATTER);
            } catch (Exception e) {
                System.out.println(ConsoleFormatter.RED + "Invalid date format. Try again." + ConsoleFormatter.RESET);
            }
        }
    }
}