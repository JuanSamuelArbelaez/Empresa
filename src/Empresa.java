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
            categorias=crearProductos(input, new Categoria());
        } catch (Exception ex) {

        }
        System.out.println(categorias.size());

    }

    private ArrayList<Categoria> crearProductos(Scanner scanner, Node<?> parent) {
        String line = scanner.nextLine();

        if(line.contains("<Category>")){
            Categoria auxCat = new Categoria();
            ((Categoria) parent).subCategorias.add(auxCat);
            auxCat.parent = parent;
            return crearProductos(scanner, auxCat);
        }
        else if(line.contains("</Category>")){
            return crearProductos(scanner, (Categoria) parent.parent);
        }
        else if(line.contains("<Product>")){
            Producto auxProd = new Producto();
            ((Categoria) parent).productos.add(auxProd);
            auxProd.parent = parent;
            return crearProductos(scanner,  auxProd);
        }
        else if(line.contains("</Product>")){
            return crearProductos(scanner, (Categoria) parent.parent);
        }
        else if(line.contains("<Name>")){
            parent.name = readText(line);
            return crearProductos(scanner, parent);
        }
        else if(line.contains("<Description>")){
            parent.desc = readText(line);
            return crearProductos(scanner, parent);
        }
        else if(line.contains("<Code>")){
            parent.code = readText(line);
            return crearProductos(scanner, parent);
        }
        else if(line.contains("<Price>")){
            ((Producto)parent).price = Double.parseDouble(readText(line));
            return crearProductos(scanner, parent);
        }
        else if(line.contains("<Url>")){
            return crearProductos(scanner, parent);
        }
        else {
            return ((Categoria) parent).subCategorias;
        }
    }

    private String readText(String line) {
        return line.split(">")[1].split("<")[0];
    }
}
