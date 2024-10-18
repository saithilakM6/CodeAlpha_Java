package CodeAlpha_Stock_Trading_Platform;

import java.util.HashMap;
import java.util.Map;

public class MarketData {
    private Map<String, Stock> stocks = new HashMap<>();

    public MarketData() {
        // Initialise with some data
        stocks.put("APPLE", new Stock("APPLE", 150.0));
        stocks.put("GOOGLE", new Stock("GOOGLE", 2800.0));
        stocks.put("AMAZON", new Stock("AMAZON", 3400.0));
        stocks.put("MICROSOFT", new Stock("MICROSOFT", 2800.5));
        stocks.put("COCACOLA", new Stock("COCACOLA", 1370.35));

    }

    public Stock getStock(String name) {
        return stocks.get(name);
    }

    public void displayMarketData() {
    	System.out.println("\t\t-----Market Data-----\n");
        for (Stock stock : stocks.values()) {
        	System.out.println("\t\t");
            System.out.println(stock);
        }
    }

    public void updateStockPrice(String name, double newPrice) {
        Stock stock = stocks.get(name);
        if (stock != null) {
            stock.setCurrentPrice(newPrice);
        }
    }
}

