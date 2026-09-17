# Sample Inputs and Outputs — Walkthrough

This file walks through the Smart Expense & Budget Manager with the exact inputs typed during a real test session and the corresponding outputs. It shows what the application does without requiring the reader to run it first.

All test data uses dates from September 2026.

---

## 1. Application Startup

**Input:**

```
$ java -cp out com.expense.Main
```

**Output (main menu appears):**

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

---

## 2. Adding Income (Option 1)

**Input:**

```
Enter choice: 1
Amount: 30000
Category: Salary
Date (yyyy-MM-dd): 2026-09-01
Description: Monthly Salary
```

**Output:**

```
============================================================
                        ADD INCOME
============================================================
Amount: 30000
Category: Salary
Date (yyyy-MM-dd): 2026-09-01
Description: Monthly Salary
Income added.
```

The program returns to the main menu.

---

## 3. Adding an Expense (Option 2)

**Input:**

```
Enter choice: 2
Amount: 4500
Category: Food
Date (yyyy-MM-dd): 2026-09-05
Description: Groceries
```

**Output:**

```
============================================================
                        ADD EXPENSE
============================================================
Amount: 4500
Category: Food
Date (yyyy-MM-dd): 2026-09-05
Description: Groceries
Expense added.
```

---

## 4. Adding a Second Expense (Option 2)

**Input:**

```
Enter choice: 2
Amount: 2200
Category: Food
Date (yyyy-MM-dd): 2026-09-12
Description: Restaurant
```

**Output:**

```
Expense added.
```

---

## 5. Setting a Budget (Option 3)

**Input:**

```
Enter choice: 3
Category: Food
Monthly limit: 5000
Month (any date in that month) (yyyy-MM-dd): 2026-09-01
```

**Output:**

```
============================================================
                         SET BUDGET
============================================================
Category: Food
Monthly limit: 5000
Month (any date in that month) (yyyy-MM-dd): 2026-09-01
Budget set.
```

---

## 6. Viewing the Monthly Report (Option 4)

**Input:**

```
Enter choice: 4
Enter month (any date) (yyyy-MM-dd): 2026-09-15
```

**Output:**

```
============================================================
              MONTHLY REPORT — 2026-09
============================================================
Category       Budget     Spent      Status       Progress
------------------------------------------------------------
Food           5000.00    6700.00    OVER 1700.00 ████████████████████
------------------------------------------------------------
Total Income : 30000.00
Total Expense: 6700.00
Savings      : 23300.00
------------------------------------------------------------
```

The `OVER 1700.00` status appears in red, and the progress bar is fully filled and red — indicating the Food budget was exceeded by 1700.00.

---

## 7. Attempting to Delete a Non-Existent Transaction (Option 5)

**Input:**

```
Enter choice: 5
Enter transaction ID to delete: 4
```

**Output:**

```
============================================================
                     DELETE TRANSACTION
============================================================
Enter transaction ID to delete: 4
No transaction found with ID 4.
```

The program handles the case where the user requests deletion of an ID that does not exist, without crashing.

---

## 8. Save & Exit (Option 6)

**Input:**

```
Enter choice: 6
```

**Output:**

```
Data saved. Goodbye!
```

The program writes all transactions and budgets to disk and exits.

---

## 9. Recompiling

After the session, the project was recompiled from the terminal to confirm the code still builds cleanly:

**Input:**

```
$ javac -d out (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
```

**Output:** No output — the compiler runs silently for two to three seconds and returns to the prompt. No errors, no warnings.

---

## 10. Data Files Written

After save & exit, the CSV files look like this:

**`data/transactions.csv`**
```
1,INCOME,30000.0,Salary,2026-09-01,Monthly Salary
2,EXPENSE,4500.0,Food,2026-09-05,Groceries
3,EXPENSE,2200.0,Food,2026-09-12,Restaurant
```

**`data/budgets.csv`**
```
Food,5000.0,2026-09
```

Both files are human-readable and can be inspected or edited in any text editor.

---

## 11. Invalid Input Handling

The application re-prompts on invalid input rather than crashing.

### Non-Numeric Amount

**Input:** `Amount: abc`
**Output:** `Invalid number. Try again.` then re-prompts.

### Wrong Date Format

**Input:** `Date (yyyy-MM-dd): 15-09-2026`
**Output:** `Invalid date format. Try again.` then re-prompts.

### Empty Category

**Input:** `Category:` (blank line)
**Output:** `Input cannot be empty.` then re-prompts.

### Invalid Menu Choice

**Input:** `Enter choice: 99`
**Output:** `Invalid choice.` then the menu is redrawn.

---

## 12. Persistence Check

After saving and exiting, restarting the application and viewing the report for the same month shows the same transactions and budgets — confirming CSV persistence works correctly.

**Input:**
```
$ java -cp out com.expense.Main
Enter choice: 4
Enter month (any date) (yyyy-MM-dd): 2026-09-15
```

**Output:** The same report is displayed, with the same budget, spent amount, and status.

---

## 13. Status Indicators Reference

| Status | Meaning | Colour |
|--------|---------|--------|
| `OK` | Spending is less than 80% of budget | Green |
| `WARN` | Spending is 80% or more but not over budget | Yellow |
| `OVER X` | Spending exceeded the budget by X | Red |

The progress bar is 20 characters wide, using `█` (filled) and `░` (empty).
