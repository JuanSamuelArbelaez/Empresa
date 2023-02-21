import java.util.ArrayList;

public class Categoria {
    private String code;
    private String name;
    private String desc;

    public Categoria(Categoria parentCategoria, String name, String code, String desc){
        this.parentCategoria = parentCategoria;
        this.code = code.replaceAll("\t", "");
        this.name = name.replaceAll("\t", "");
        this.desc = desc.replaceAll("\t", "");
    }
    public Categoria(){};
    public ArrayList<Producto> productos;
    public ArrayList<Categoria> subCategorias;

    private Categoria parentCategoria;
}
