# Project Statement — Smart Expense & Budget Manager

## Problem Statement

Managing personal finances manually is inefficient and error-prone. Most people lose track of where their money goes within a few weeks, especially when spending is spread across many categories like food, transport, and utilities. Spreadsheets help but require constant manual updating and give no immediate feedback when a category is close to or has exceeded its budget.

This project addresses that gap by providing a lightweight, terminal-based tool where a user can log transactions as they happen, set monthly budgets per category, and see at a glance whether they are within their limits. It works fully offline and stores data locally in plain CSV files.

## Scope of the Project

This project delivers a command-line Java application that lets a single user:

- Record income and expense entries with date, category, and description.
- Set a monthly budget for each spending category.
- View a monthly report showing budget vs. actual spend per category.
- Delete incorrectly entered transactions by ID.
- Persist all data between sessions using CSV files.

The scope is intentionally limited to a **single-user, local, offline** tool. No authentication, networking, or database is involved. The interface is entirely text-based so the project runs on any machine with a JDK installed.

## Target Users

- Students and young professionals who want a fast, lightweight way to track monthly spending.
- Anyone who prefers a terminal-based tool over a full GUI application.
- Users on low-resource machines where a browser-based finance app may be too heavy.

## High-Level Features

1. **Transaction Management** — add income, add expense, delete transaction by ID.
2. **Budget Management** — set a monthly limit for each category.
3. **Reporting & Analytics** — generate monthly reports with status indicators and progress bars.
4. **Persistent Storage** — CSV-based save/load with auto-created data folders.
5. **Input Validation** — safe parsing with re-prompts on invalid entry.
6. **Rich Console Output** — color-coded statuses and visual progress bars.

## Out of Scope

- Multi-user accounts and cloud sync.
- Recurring transactions.
- Graphical user interface.
- Currency conversion.
