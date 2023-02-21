public class Producto{
    public String code;
    public String name;
    public String desc;
    public String url;
    public double price;
    public Categoria parent;
    public Producto (Categoria parent, String name, double price, String code, String desc, String url) {
        this.code = code.replaceAll("\t", "");
        this.name = name.replaceAll("\t", "");
        this.desc = desc.replaceAll("\t", "");
        this.price = price;
        this.parent = parent;
    }
}