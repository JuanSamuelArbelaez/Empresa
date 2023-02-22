public class Product extends Node {
    public String url;
    private double price;
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean priceInRange(double min, double max) {
        return price >= min && price <= max;
    }
}