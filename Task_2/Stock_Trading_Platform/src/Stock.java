package Stock_Trading_Platform;

public class Stock {
    private String name;
    private double currentPrice;

    public Stock(String name, double currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return "Stock{name='" + name + "', currentPrice=" + currentPrice + "}";
    }
}
