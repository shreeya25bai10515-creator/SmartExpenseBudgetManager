# Smart Expense & Budget Manager

A command-line Java application for tracking income, expenses, and monthly budgets. Built as my Evaluated Course Project for the Flipped course at VIT.

---

## Table of Contents

1. [Problem Statement](#problem-statement)
2. [Objectives](#objectives)
3. [Approach](#approach)
4. [Features](#features)
5. [Tech Stack](#tech-stack)
6. [Project Structure](#project-structure)
7. [Setup Instructions](#setup-instructions)
   - [Step 1: Install the JDK](#step-1-install-the-jdk)
   - [Step 2: Verify the installation](#step-2-verify-the-installation)
   - [Step 3: Download the project](#step-3-download-the-project)
   - [Step 4: Compile the project](#step-4-compile-the-project)
   - [Step 5: Run the application](#step-5-run-the-application)
   - [Step 6: First-time usage](#step-6-first-time-usage)
   - [Troubleshooting](#troubleshooting)
8. [Usage](#usage)
9. [How Data is Stored](#how-data-is-stored)
10. [Testing](#testing)
11. [Possible Improvements](#possible-improvements)
12. [Author](#author)

---

## Problem Statement

Tracking personal spending is something most of us do badly. We either forget to note things down, or we note them in a notes app where we never look again. By the end of the month, we have no clear picture of where the money actually went.

Spreadsheets solve this to some extent, but they require constant manual updating and give no immediate feedback. There is no easy way to know whether you're close to your food budget for the month until it is already too late.

This project tries to address that gap. It gives users a fast terminal tool to log transactions as they happen, set a monthly budget for each category, and see at a glance whether they are within limits. Everything works offline, no sign-up is needed, and all data is stored locally in plain CSV files.

---

## Objectives

The main goals I set for this project were:

- To record income and expense entries with amount, category, date, and a short description.
- To allow a monthly budget limit to be set per spending category.
- To generate a monthly report that shows, for each category, how much was budgeted against how much was actually spent.
- To visually indicate whether a category is under, near, or over its budget.
- To keep all data between sessions using simple file storage.
- To build the entire thing using only the Java Standard Library.
- To keep the interface clean and readable, even though it lives in a terminal.

---

## Approach

The project follows a layered structure to keep the code organised and easy to reason about:

- **Model layer** — plain data classes representing the domain: `Transaction` (abstract base), `Expense`, `Income`, and `Budget`.
- **Service layer** — business logic. `ExpenseManager` handles transactions, `BudgetManager` handles budgets, and `ReportGenerator` renders monthly reports.
- **Storage layer** — `FileStorage` reads and writes CSV files for both transactions and budgets.
- **Utility layer** — `ConsoleFormatter` handles all output styling (colors, banners, progress bars) and `InputValidator` handles safe user input.
- **UI layer** — `Menu` presents the console menu and dispatches actions.
- **Entry point** — `Main` sets up the data folders and starts the menu.

Development was done bottom-up: models first, then services, then the UI on top. This made it easier to test each layer independently before wiring everything together.

---

## Features

- Add income and expense entries with date, category, and description.
- Set a monthly budget for any category.
- View a monthly report that shows budget vs. actual spending per category.
- Status indicators per category — green "OK", yellow "WARN" at 80% usage, red "OVER" when exceeded.
- A simple text-based progress bar for each category so the report is easy to scan.
- Delete a transaction by its ID.
- All data persists between sessions.
- The `data/` and `docs/` folders are created automatically on the first run.

---

## Tech Stack

- **Java 21**
- Plain `javac` for compilation — no Maven or Gradle.
- CSV files for storage.
- ANSI escape codes for terminal colours.
- No external dependencies — only the Java Standard Library.

---

## Project Structure

```
SmartExpenseBudgetManager/
├── src/main/java/com/expense/
│   ├── Main.java
│   ├── model/
│   │   ├── Transaction.java
│   │   ├── Expense.java
│   │   ├── Income.java
│   │   └── Budget.java
│   ├── service/
│   │   ├── ExpenseManager.java
│   │   ├── BudgetManager.java
│   │   └── ReportGenerator.java
│   ├── storage/
│   │   └── FileStorage.java
│   ├── util/
│   │   ├── ConsoleFormatter.java
│   │   └── InputValidator.java
│   └── ui/
│       └── Menu.java
├── data/
└── docs/
```

---

## Setup Instructions

This section assumes you have never run a Java program before. Follow the steps in order and you should have the application running within about ten minutes.

### Step 1: Install the JDK

Java programs need the **Java Development Kit (JDK)** — not just the runtime. The JDK includes the compiler (`javac`) that turns source code into runnable bytecode.

**Option A — Download from Adoptium (recommended)**

1. Go to [https://adoptium.net](https://adoptium.net).
2. Select **Temurin 21 (LTS)**.
3. Choose your operating system and architecture (Windows x64 for most laptops).
4. Download the **.msi** (Windows), **.pkg** (macOS), or **.tar.gz** (Linux) installer.
5. Run the installer. On Windows, **tick the box that says "Set JAVA_HOME variable"** and **"Add to PATH"** during installation. These make Java available from the terminal.
6. Finish the installer.

**Option B — Download from Oracle**

1. Go to [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/).
2. Pick **Java 21** → your OS → download the **JDK** (not the JRE).
3. Run the installer.

Once installed, **restart any open terminals or editors**. Java only becomes visible to programs started after installation.

### Step 2: Verify the installation

Open a terminal. On Windows, you can use **PowerShell** (search "PowerShell" in the Start menu) or **Command Prompt**. On macOS or Linux, use your default terminal.

Run these two commands:

```
java -version
javac -version
```

Both should print something like:

```
java version "21.0.4" 2024-07-16 LTS
javac 21.0.4
```

The exact minor version does not matter, but **both commands must work** and the version must start with **21** (or higher).

**If either command says "not recognized" or "command not found":**

- Java was not added to your system PATH during installation.
- On Windows, you can fix this by re-running the installer and ticking the "Add to PATH" option, or by manually adding the JDK's `bin` folder to your PATH (search "Environment Variables" in the Start menu).
- After changing PATH, **close and reopen the terminal** — the change only applies to new sessions.

### Step 3: Download the project

You have two options.

**Option A — Clone with Git (if Git is installed):**

```
git clone https://github.com/shreeya25bai10515-creator/SmartExpenseBudgetManager.git
cd SmartExpenseBudgetManager
```

**Option B — Download as a ZIP (no Git needed):**

1. Open [https://github.com/shreeya25bai10515-creator/SmartExpenseBudgetManager](https://github.com/shreeya25bai10515-creator/SmartExpenseBudgetManager) in your browser.
2. Click the green **Code** button.
3. Click **Download ZIP**.
4. Extract the ZIP somewhere convenient (for example, your Desktop).
5. Open a terminal and navigate into the extracted folder:

```
cd path/to/SmartExpenseBudgetManager
```

On Windows, you can also open the folder in File Explorer, right-click in the empty space, and choose **"Open in Terminal"**.

### Step 4: Compile the project

Compilation turns the `.java` source files into `.class` bytecode files. All the `.class` files will be written to an `out/` folder.

The exact command depends on your terminal.

**If you are on PowerShell (Windows):**

```
javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
```

PowerShell does not expand wildcards the way CMD or Bash does, so the parenthesised `Get-ChildItem` part is needed to gather all `.java` files first.

**If you are on CMD, Bash, macOS, or Linux:**

```
javac -d out src/main/java/com/expense/*.java src/main/java/com/expense/model/*.java src/main/java/com/expense/service/*.java src/main/java/com/expense/storage/*.java src/main/java/com/expense/util/*.java src/main/java/com/expense/ui/*.java
```

**Expected result:** the compiler runs silently for two to three seconds, and the prompt returns. A new folder called `out/` appears in your project directory.

**If you see errors:** double-check that you are inside the project root (the folder containing `src/`) when running the command. Run `dir` (Windows) or `ls` (macOS/Linux) to confirm.

### Step 5: Run the application

Once compilation succeeds, run:

```
java -cp out com.expense.Main
```

**What each part means:**

- `java` — the runtime that launches a Java program.
- `-cp out` — tells Java where to look for the compiled `.class` files.
- `com.expense.Main` — the fully qualified name of the class with the `main` method.

If everything is correct, you will see a banner followed by the main menu:

```
============================================================
                        MAIN MENU
============================================================
1. Add Income
2. Add Expense
3. Set Budget
4. View Monthly Report
5. Delete Transaction by ID
6. Save & Exit
------------------------------------------------------------
Enter choice:
```

The program is now waiting for you to type a number and press Enter.

### Step 6: First-time usage

Because no data exists yet, the first thing to do is add some entries. A suggested sequence:

1. Choose **1** to add income. Enter `30000` for the amount, `Salary` for the category, today's date in `yyyy-MM-dd` format, and a short description.
2. Choose **2** to add an expense. Enter something like `1500`, `Food`, `2026-09-05`, `Lunch`.
3. Choose **3** to set a budget. Enter `Food`, `5000`, and any date within September 2026.
4. Choose **4** to view the report for September 2026. You will see the Food category with a green status.
5. Add a few more expenses in the same category to watch the status change to yellow at 80% usage, then red when the budget is exceeded.
6. Choose **6** to save and exit.

Now run the program again (`java -cp out com.expense.Main`). Your data will still be there — this is what the CSV persistence is for.

### Troubleshooting

| Problem | Likely cause | Fix |
|---------|--------------|-----|
| `javac: command not found` | JDK not on PATH | Reinstall JDK with "Add to PATH" option, or add it manually |
| `error: package com.expense.model does not exist` | Compiling from the wrong folder | Make sure you are in `SmartExpenseBudgetManager/` (the folder with `src/` in it) |
| `Could not find or load main class com.expense.Main` | `out/` folder is missing or stale | Re-run the compile step; check that `out/com/expense/Main.class` exists |
| Menu appears but typing has no effect | Terminal focus issue | Click inside the terminal window first, then type |
| Colors do not show, only escape codes | Terminal doesn't support ANSI | Use VS Code's integrated terminal or Windows Terminal; CMD may need adjustment |
| Data does not persist | You exited with Ctrl+C instead of choosing 6 | Exit using menu option 6, which triggers the save |

---

## Usage

A typical session looks like this:

1. Add income — choose `1`, then enter the amount, category, date in `yyyy-MM-dd`, and a short description.
2. Add an expense — choose `2` and do the same.
3. Set a budget — choose `3`, enter the category, a monthly limit, and any date within the target month.
4. View the report — choose `4`, then enter a date. The report for that month appears.
5. Delete a transaction — choose `5` and enter the ID shown in a previous report.
6. Save and exit — choose `6`. This writes both transactions and budgets to disk.

The report looks like this:

```
============================================================
              MONTHLY REPORT — 2026-09
============================================================
Category       Budget     Spent      Status       Progress
------------------------------------------------------------
Food           5000.00    2200.00    OK           ████████░░░░░░░░░░░░
------------------------------------------------------------
Total Income : 30000.00
Total Expense: 2200.00
Savings      : 27800.00
------------------------------------------------------------
```

The status column turns yellow when a category has used 80% or more of its budget, and red when it has gone over. The progress bar reflects the same information visually.

---

## How Data is Stored

Data is stored as plain CSV, so it can be inspected in any text editor.

**`data/transactions.csv`**

```
1,INCOME,30000.0,Salary,2026-09-01,Monthly salary
2,EXPENSE,4500.0,Food,2026-09-05,Groceries
```

Each line has six fields: `id`, `type`, `amount`, `category`, `date`, and `description`.

**`data/budgets.csv`**

```
Food,5000.0,2026-09
```

Each line has three fields: `category`, `monthly limit`, and `month` in `yyyy-MM` format.

Both files are auto-created the first time the program runs. If you delete them, the program simply starts with a clean slate.

---

## Testing

I tested the following scenarios before finalising the project:

- Adding income, expenses, and budgets through the menu.
- Entering empty input, invalid numbers, and malformed dates — the program re-prompts each time.
- Going over budget in a category — the report correctly shows a red OVER status.
- Reaching 80% of a budget — the report shows a yellow WARN status.
- Deleting a transaction by ID, including the case where the ID does not exist.
- Restarting the program to confirm that transactions and budgets persist correctly.
- Confirming the `data/` and `docs/` folders are created automatically on the first run.
- Running the project on both PowerShell and CMD to make sure the compile commands work in either shell.

---

## Possible Improvements

If I continued working on this project, I would consider:

- Exporting reports to PDF or a text file.
- Adding support for recurring transactions (rent, salary).
- A month-over-month comparison view.
- Loading budgets from a config file rather than setting them interactively.
- Unit tests using JUnit.

---

## Author

**Shreeya Raj** — Reg. No. 25BAI10515
VIT — Flipped Course Project, 2026
Programming in Java
