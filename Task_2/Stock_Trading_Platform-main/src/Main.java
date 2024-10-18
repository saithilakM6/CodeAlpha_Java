package CodeAlpha_Stock_Trading_Platform;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Portfolio portfolio = new Portfolio();
        MarketData marketData = new MarketData();

        Scanner scanner = new Scanner(System.in);
        System.out.println("\t-----Welcome to the Stock Trading Platform-----");
        while (true) {
        	System.out.println("\n\n\t\t-----------------------");
            System.out.println("\t\t1. View Market Data");
            System.out.println("\t\t2. Buy Stock");
            System.out.println("\t\t3. Sell Stock");
            System.out.println("\t\t4. View Portfolio");
            System.out.println("\t\t5. Exit");
        	System.out.println("\t\t-----------------------\n");
        	System.out.print("-----Please choose an option:");
        	
            int choice = scanner.nextInt();
            System.out.println("\n\n");
            switch (choice) {
                case 1:
                    marketData.displayMarketData();
                    break;
                case 2:
                	System.out.println("\t\t-----Buying Stock-----\n\n");
                    System.out.println("-----Enter stock name:----- ");
                    String stockName = scanner.next();
                    System.out.println("-----Enter quantity:----- ");
                    int quantity = scanner.nextInt();
                    Stock stockToBuy = marketData.getStock(stockName.toUpperCase());
                    if (stockToBuy != null) {
                        portfolio.buyStock(stockToBuy, quantity, stockToBuy.getCurrentPrice());
                        System.out.println("\t\t-----Stock bought successfully!-----");
                    } else {
                        System.out.println("\t\t-----Stock not found.-----");
                    }
                    break;
                case 3:
                	System.out.println("\t\t-----Selling Stock-----\n\n");
                    System.out.println("-----Enter stock name:----- ");
                    String stockNameSell = scanner.next();
                    System.out.println("-----Enter quantity:----- ");
                    int quantitySell = scanner.nextInt();
                    Stock stockToSell = marketData.getStock(stockNameSell.toUpperCase());
                    if (stockToSell != null) {
                        portfolio.sellStock(stockToSell, quantitySell);
                        System.out.println("\t\t-----Stock sold successfully!-----");
                    } else {
                        System.out.println("\t\t-----Stock not found.-----");
                    }
                    break;
                case 4:
                	System.out.println("\t\t-----Your Portofolio-----\n\n");
                    for (PortfolioItem item : portfolio.getItems()) {
                        System.out.println(item);
                        System.out.println("-----Current Value: $" + item.getCurrentValue()+"-----");
                        System.out.println("-----Profit/Loss: $" + item.getProfitOrLoss()+"-----");
                    }
                    System.out.println("-----Total Portfolio Value: $" + portfolio.getTotalValue()+"-----");
                    System.out.println("-----Total Profit/Loss: $" + portfolio.getTotalProfitOrLoss()+"-----");
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("\t\t-----Invalid choice. Please try again.-----");
            }
        }
    }
}
