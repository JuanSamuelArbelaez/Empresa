import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Empresa {
    public final String name;
    public final String nit;
    public ArrayList<Categoria> categorias = new ArrayList<>();

    public Empresa(String name, String nit) {
        this.name = name;
        this.nit = nit;
        try {
            File file = new File("src/records.txt");
            Scanner input = new Scanner(file);
            Categoria aux = new Categoria();
            crearProductos(input, aux);
            categorias=aux.subCategorias;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String crearProductos(Scanner scanner, Categoria parent) {
        if(scanner.hasNextLine()){
            String nextLine = scanner.nextLine();
            if(nextLine.endsWith(".cat")){
                parent.subCategorias.add(new Categoria(parent, nextLine.split(".")[1].replaceAll("\t", ""),
                        crearProductos(scanner,  null),
                        crearProductos(scanner,  null)));
            }
            if(nextLine.endsWith(".pct")){
                parent.productos.add(new Producto(parent, nextLine.split(".")[1].replaceAll("\t", ""),
                        Double.parseDouble(crearProductos(scanner,  null)),
                        crearProductos(scanner,  null),
                        crearProductos(scanner,  null),
                        crearProductos(scanner,  null)));
            }
            return nextLine;
        }
        return "";
    }

    private int countTabs(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\t') {
                count++;
            }
        }
        return count;
    }
}
