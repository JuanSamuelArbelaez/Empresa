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
    public void printProduct() {
        System.out.println("Product: "+this.name+
                "\n\tCode: " + this.code+
                "\n\tPrice: " + this.price+
                "\n\tDescription: "+this.desc+
                "\n\tCategory: "+((Category)this.parent).name+
                "\n\tURL: "+this.url+
                "\n--------------------------------------------------------");
    }
}