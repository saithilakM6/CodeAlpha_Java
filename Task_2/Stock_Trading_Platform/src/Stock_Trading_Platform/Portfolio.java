package Stock_Trading_Platform;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<PortfolioItem> items = new ArrayList<>();

    public void buyStock(Stock stock, int quantity, double purchasePrice) {
        items.add(new PortfolioItem(stock, quantity, purchasePrice));
    }

    public void sellStock(Stock stock, int quantity) {
        for (PortfolioItem item : items) {
            if (item.getStock().getName().equals(stock.getName()) && item.getQuantity() >= quantity) {
                item.reduceQuantity(quantity);
                if (item.getQuantity() == 0) {
                    items.remove(item);
                }
                break;
            }
        }
    }

    public double getTotalValue() {
        double total = 0;
        for (PortfolioItem item : items) {
            total += item.getCurrentValue();
        }
        return total;
    }

    public double getTotalProfitOrLoss() {
        double total = 0;
        for (PortfolioItem item : items) {
            total += item.getProfitOrLoss();
        }
        return total;
    }

    public List<PortfolioItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Portfolio{items=" + items + "}";
    }
}
