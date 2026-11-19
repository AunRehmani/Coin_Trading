# Coin Trading System (Virtual Trade Expert)

A Java console-based simulated coin trading application developed by Abdul Rehman (`se231082`), Aunali Karim (`se231077`), Sunny Lakhwani (`se231095`), and Rohit Motiani (`se231058`). It supports multi-user login validation, simulated buying and selling of coins, dynamic price and profit/tax updates, and account balance management in memory.

## Overview

The application is implemented in `Coin_tradingsys.java` and uses a console menu loop (`VIRTUAL TRADE EXPERT`) where authenticated users can deposit funds, buy or sell coins, inspect their portfolio and profit/tax figures, return to the login screen, or exit. All user accounts, coin prices, balances, share quantities, profits, and taxes are stored in memory while the program runs.

## Features

- **Login & Failed-Attempt Lockout:** Authenticates four predefined users (`usera`, `userb`, `userc`, `userd`) against their passwords (`passa`, `passb`, `passc`, `passd`) with up to 3 failed attempts before triggering a lockout alert.
- **Buy Coins:** Allows users to choose among four assets (`Etherium` at `$20`, `Dogecoin` at `$35`, `Binance` at `$88`, and `XRP` at `$101` initially), invest from their available balance, calculate shares acquired, update coin price/profit/tax values, and deduct from their balance.
- **Sell Coins:** Displays current coin holdings, lets users select a coin and quantity to sell, credits sale proceeds to their balance, and updates price, profit, and tax calculations.
- **Portfolio & Balance View:** Shows the active user's current cash balance and coin share quantities.
- **Profit & Tax Summary:** Displays accumulated user profit and company tax totals.
- **Add Balance & Session Navigation:** Lets users deposit additional funds into their balance, return to the login menu (option `6`), or exit the program (option `7`).

## Code Structure (`Coin_tradingsys.java`)

- **`Coin_tradingsys`**: Holds the `Scanner`, shared session/trading state variables, predefined user credentials (`usera`–`userd`), coin names and initial prices (`Etherium`, `Dogecoin`, `Binance`, `XRP`), user balances/profits/holdings arrays, and `clearConsole()`.
- **`login`**: Extends `Coin_tradingsys` and implements `login(Scanner)` with a 3-attempt retry loop and lockout warning.
- **`menu`**: Extends `login` and provides `showMainMenu(int, Scanner)` to print the main menu options (`1`–`7`) for the active user (`USER A`–`USER D`).
- **`my_mainmenu`**: Extends `login` and runs `login_chk()`, handling login flow, menu selection, purchasing, selling, balance display, profit/tax output, and balance deposits.
- **`main`**: Contains the `public static void main(String[] args)` entry point that instantiates `my_mainmenu` and starts `login_chk()`.

## Prerequisites & Dependencies

- **Java Development Kit (JDK):** Any standard JDK supporting `java.util.Scanner` and standard Java SE console I/O (no external libraries or Maven/Gradle dependencies required).
- **Terminal / Console Environment:** Required for interactive keyboard prompts and menu input[cite: 2].

## Installation & Running

1. **Clone the repository**
   ```bash
   git clone <your-github-repo-url>
   cd <your-repo-directory>
