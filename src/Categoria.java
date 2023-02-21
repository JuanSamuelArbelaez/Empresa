import java.util.ArrayList;

public class Categoria {
    public String code;
    public String name;
    public String desc;
    public int tabs;

    public Categoria(Categoria parent, String name, String code, String desc, int tabs) {
        this.parent = parent;
        this.code = code.replaceAll("\t", "");
        this.name = name.replaceAll("\t", "");
        this.desc = desc.replaceAll("\t", "");
        this.tabs = tabs;
    }
    public Categoria(){};
    public ArrayList<Producto> productos = new  ArrayList<Producto>();
    public ArrayList<Categoria> subCategorias = new ArrayList<Categoria>();

    public Categoria parent;
}
