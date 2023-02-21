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
        System.out.println(countTabs("--"));
        try {
            File file = new File("src/records.txt");
            Scanner input = new Scanner(file);
            Categoria aux = new Categoria();
            aux=crearProductos(input, aux, 0);
            categorias.stream();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Categoria crearProductos(Scanner scanner, Categoria parent, int tabs) {
        if(scanner.hasNextLine()){
            String nextLine = scanner.nextLine();
            System.out.println(nextLine);

            if(nextLine.endsWith("/pct")){
                if(countTabs(nextLine)<=tabs) {
                    for (int i = tabs; i>=countTabs(nextLine); i--) {
                        parent = parent.parent;
                    }
                }
                parent.productos.add(new Producto(parent, nextLine.split("/")[0].replaceAll("-", ""),
                        Double.parseDouble(scanner.nextLine().split("/")[0].replaceAll("-", "")),
                        scanner.nextLine().split("/")[0].replaceAll("-", ""),
                        scanner.nextLine().split("/")[0].replaceAll("-", ""),
                        scanner.nextLine().split("/")[0].replaceAll("-", ""),
                        countTabs(nextLine)));
                return crearProductos(scanner, parent, parent.tabs);
            }
            if(nextLine.endsWith("/ctg")){
                Categoria aux = new Categoria();
                aux.name = nextLine.split("/")[0].replaceAll("-", "");
                aux.code = scanner.nextLine().split("/")[0].replaceAll("-", "");
                aux.desc = scanner.nextLine().split("/")[0].replaceAll("-", "");
                aux.parent = parent;
                parent.subCategorias.add(aux);
                crearProductos(scanner ,aux, countTabs(nextLine));
                return crearProductos(scanner, parent, tabs);
            }
            else if(nextLine.endsWith(".ctg")){
                Categoria aux = new Categoria();
                aux.name = nextLine.trim();
                aux.code = scanner.nextLine().trim();
                aux.desc = scanner.nextLine().trim();
                if(countTabs(nextLine)<=tabs) {
                    aux.parent = parent;

                    categorias.add(aux);
                }
                crearProductos(scanner,aux, countTabs(nextLine));
            }


        }
        return null;
    }

    private int countTabs(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                count++;
            }
        }
        return count;
    }
}
