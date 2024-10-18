package CodeAlpha_Stock_Trading_Platform;

public class PortfolioItem {
    private Stock stock;
    private int quantity;
    private double purchasePrice;

    public PortfolioItem(Stock stock, int quantity, double purchasePrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }
    
    public void setStock(Stock stock) {
		this.stock = stock;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getCurrentValue() {
        return stock.getCurrentPrice() * quantity;
    }

    public double getProfitOrLoss() {
        return getCurrentValue() - (purchasePrice * quantity);
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    @Override
    public String toString() {
        return "PortfolioItem{stock=" + stock + ", quantity=" + quantity + ", purchasePrice=" + purchasePrice + "}";
    }
}
