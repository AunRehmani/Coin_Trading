/* Coin Trading System
Authors: Abdul Rehman(se231082)
         Aunali karim(se231077)
         Sunny Lakhwani(se231095)
         Rohit Motiani(se231058)
 */
import java.util.Scanner;

public class Coin_tradingsys {
    Scanner scanner = new Scanner(System.in);
    int currentLogin, choice, product;
    float userBuyed;
    float userSell;
    int yesNo;
    float tax = 0;
    float companyProfit;

    static final int NUM_USERS = 4;
    static final int COINS = 4;

    static String[] userNames = {"usera", "userb", "userc", "userd"};
    static String[] passwords = {"passa", "passb", "passc", "passd"};

    static float[] balances = new float[NUM_USERS];
    static float[] profit = new float[NUM_USERS];
    static float[][] share = new float[NUM_USERS][COINS];

    static String[] products = {"Etherium", "Dogecoin", "Binance", "XRP"};
    static float[] prices = {20, 35, 88, 101};
    static float[][] productShares = new float[NUM_USERS][COINS];
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class login extends Coin_tradingsys{

    int login (Scanner scanner){
        int currentLogin = -1;
        int count = 0;
        boolean validUser = false;
        boolean validPassword = false;
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                               VIRTUAL TRADE EXPERT                                                                       ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Hint: Username: usera");
        System.out.println("Hint: Password: passa");
        do {
            System.out.println("-------------------------LOGIN---------------------");
            System.out.println("Username:");
            String inputUsername = scanner.next();

            for (currentLogin = 0; currentLogin < NUM_USERS; ++currentLogin) {
                if (inputUsername.equals(userNames[currentLogin])) {
                    validUser = true;
                    break;
                }
            }

            if (validUser) {
                System.out.print("Password:");
                String inputPassword = scanner.next();

                if (inputPassword.length() >= 5 && inputPassword.equals(passwords[currentLogin])) {
                    validPassword = true;
                }
            }

            if (validUser && validPassword) {
                System.out.println("-------------Login Successful!------------");
                break;
            } else {
                System.out.println("Login Unsuccessful! Press any key to login again.");
                count++;
                System.out.println("Press any Key");
                scanner.nextLine(); // Wait for any key
            }
        } while (count < 3);

        if (count >= 3) {
            System.out.println("    WRONG ID PASSWORD ENTERED FOR 3 TIMES!!!    ALARM HAS BEEN INITIATED");
            System.out.println("Beep!");
            System.out.println("Press any key");
            scanner.nextLine(); // Wait for any key
            currentLogin = -1; // Indicate login failure
        }


        return currentLogin;
    }
}
class menu extends login{

    public static void showMainMenu(int currentLogin, Scanner scanner) {

        System.out.println("~~MAINMENU");
        System.out.printf("USER %c\n", 'A' + currentLogin);

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("SELECT AN OPTION\n1. Purchase\n2. Sell\n3. My Balance\n4. Profit\n5. Add balance\n6.Return To Login \n7. Exit");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

        System.out.print("Enter your choice: ");
    }
}
class my_mainmenu extends login {
    void login_chk() {
        do {
            currentLogin = login(scanner);

            if (currentLogin != -1) {
                System.out.println("Press Enter to continue");
                scanner.nextLine();

                do {
                    menu.showMainMenu(currentLogin, scanner);

                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.println("Press Enter to continue!");
                    scanner.nextLine();


                    switch (choice) {
                        case 1: // Purchase menu
                            System.out.println("What do you want to buy :");
                            for (int i = 0; i < COINS; ++i) {
                                System.out.printf("\n\n%d. %s(%.2f$)", i + 1, products[i], prices[i]);
                            }

                            System.out.println("\n\nYou want to buy:");
                            product = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline
                            System.out.println("Press Enter to continue!");
                            scanner.nextLine();

                            if (product >= 1 && product <= COINS) {
                                System.out.printf("\nHow much money do you want to invest in %s:", products[product - 1]);
                                userBuyed = scanner.nextFloat();


                                if (userBuyed <= balances[currentLogin]) {
                                    float sharesBought = userBuyed / prices[product - 1];
                                    productShares[currentLogin][product - 1] += sharesBought;
                                    share[currentLogin][product - 1] += sharesBought;

                                    float currentProfit = prices[product - 1] / 50 * sharesBought;
                                    profit[currentLogin] += currentProfit;
                                    balances[currentLogin] -= (userBuyed - currentProfit);
                                    prices[product - 1] += (prices[product - 1] / 50) * sharesBought;
                                    companyProfit = currentProfit / 10;
                                    tax += companyProfit;
                                    profit[currentLogin] -= companyProfit;
                                    balances[currentLogin] -= companyProfit;

                                    System.out.printf("You have purchased %.2f share of %s i.e cost= $ %.2f\nDiscount=%.2f\nCompany tax: %.2f\nRemaining balance:%.2f\n",
                                            sharesBought, products[product - 1], userBuyed, currentProfit, companyProfit, balances[currentLogin]);
                                } else {
                                    System.out.println("SORRY FOR YOUR TRANSACTION! YOU HAVE INSUFFICIENT BALANCE OR NO BALANCE.");
                                }
                            } else {
                                System.out.println("Invalid product");
                            }
                            break;
                        case 2: // Selling product
                            System.out.println("\nYou have the following share in hands:\nPRODUCT\t\t\t\t\tQUANTITY");
                            for (int i = 0; i < COINS; ++i) {
                                System.out.printf("%s\t\t\t\t\t%.2f\n", products[i], productShares[currentLogin][i]);
                            }

                            System.out.println("Do you want to sell? (1 for yes, 2 for No.)\n1. YES.\n2. NO");
                           System.out.println("Your Choice:");
                            yesNo = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline

                            if (yesNo == 1) {
                                System.out.println("Which product do you want to sell?");
                                System.out.println("1.Etherium");
                                System.out.println("2.Dogecoin");
                                System.out.println("3.Binance");
                                System.out.println("4.XRP");
                                product = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("How much do you want to sell?\nOnly Enter quantity to sell: ");
                                userSell = scanner.nextFloat();
                                scanner.nextLine();

                                if (product >= 1 && product <= COINS && userSell <= productShares[currentLogin][product - 1]) {
                                    float unitPrice = prices[product - 1];
                                    String productName = products[product - 1];

                                    productShares[currentLogin][product - 1] -= userSell;
                                    balances[currentLogin] += userSell * unitPrice;
                                    prices[product - 1] -= unitPrice / 50 * userSell;
                                    profit[currentLogin] += unitPrice / 50 * userSell;
                                    float a = (unitPrice / 50 * userSell) / 10;
                                    tax += a;
                                    profit[currentLogin] -= a;

                                    System.out.printf("You have sold %.2f share of %s for $ %.2f\nRemaining share: %.2f\nRemaining balance: $ %.2f\n",
                                            userSell, productName, userSell * unitPrice, share[currentLogin][product - 1], balances[currentLogin]);
                                } else {
                                    System.out.println("\tSORRY FOR YOUR TRANSACTION!\n\tYOU HAVE INSUFFICIENT SHARES TO SELL.");
                                }
                            } else if (yesNo == 2) {
                                System.out.println("OK, no sale made.");
                            } else {
                                System.out.println("Invalid choice");
                            }
                            break;
                        case 3: // My Balance
                            System.out.printf("Your current balance:%.2f\nPRODUCT\t\t\t\tQUANTITY\n", balances[currentLogin]);
                            for (int i = 0; i < COINS; ++i) {
                                System.out.printf("%s\t\t\t%.2f\n", products[i], productShares[currentLogin][i]);
                            }
                            break;
                        case 4: // Profit
                            System.out.printf("PROFIT:%.2f", profit[currentLogin]);
                            System.out.printf("TAX: %.2f", tax);
                            break;
                        case 5: // Add Balance
                            System.out.println("Add balance:");
                            userBuyed = scanner.nextFloat();
                            scanner.nextLine();
                            balances[currentLogin] += userBuyed;
                            System.out.printf("Your current balance is: $%.2f", balances[currentLogin]);
                            break;
                        case 6:
                            break;
                        case 7:
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice");
                            System.out.println("\t\tPress Enter to go back to the main menu\n");
                            scanner.nextLine();

                            break;
                    }

                    if (choice == 6) {
                        break;
                    }
                } while (true);
            }
        } while (currentLogin != -1);

        scanner.close();

    }
}
class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        login login = new login();
//    login.login(scanner);
        my_mainmenu mymenu = new my_mainmenu();
        mymenu.login_chk();
    }
}







