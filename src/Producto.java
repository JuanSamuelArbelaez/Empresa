public class Producto{
    public String code;
    public String name;
    public String desc;
    public String url;
    public double price;
    public Categoria parent;
    public int tabs;
    public Producto (Categoria parent, String name, double price, String code, String desc, String url, int tabs) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.parent = parent;
        this.url = url;
        this.tabs = tabs;
    }
}