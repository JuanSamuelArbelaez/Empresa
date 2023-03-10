import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Business {
    public final String name;
    public final String nit;
    public ArrayList<Category> categories = new ArrayList<>();
    public Business(String name, String nit) {
        this.name = name;
        this.nit = nit;
        createTree();
        ArrayList<Product> products=getProducts(0, new ArrayList<>());
        System.out.println("Todos los productos: \n--------------------------------------------------------");
        printProducts(0, products);
        products.clear();

        String catName = "RopaHombre";
        System.out.println("Todos los productos de categoría '"+catName+"': \n--------------------------------------------------------");
        products=findProducts_Category(0, new ArrayList<>(), catName);
        printProducts(0, products);
        products.clear();

        double min=0.0, max=22000.0;
        System.out.println("Todos los productos con precio entre "+min+" y "+max+": \n--------------------------------------------------------");
        products=getProducts_PriceRange(min, max,0, new ArrayList<>());
        printProducts(0, products);
        products.clear();
    }
    private ArrayList<Product> getProducts_PriceRange(double min, double max, int count, ArrayList<Product> products) {
        if(count<this.categories.size()){
            Category auxCat = this.categories.get(count);
            auxCat.getProducts_PriceRange(min, max, products);
            getProducts_PriceRange(min, max, count+1, products);
        }
        return products;
    }
    private ArrayList<Product> findProducts_Category(int count, ArrayList<Product> products, String catName) {
        if(count< this.categories.size()) {
            Category auxCat = this.categories.get(count);
            try {
                Category aux2 =new Category().findCategory_Name(auxCat, catName);
                aux2.getProducts(products);
            }catch(Exception e) {
                findProducts_Category(count+1, products, catName);
            }
        }
        return products;
    }
    private ArrayList<Product> getProducts(int count,ArrayList<Product> products){
        if(count<this.categories.size()){
            Category auxCat = this.categories.get(count);
            auxCat.getProducts(products);
            getProducts(count+1,products);
        }
        return products;
    }
    private ArrayList<Category> createCategories(Scanner scanner, Node<?> parent) {
        String line = scanner.nextLine();

        if(line.contains("<Category>")){
            Category auxCategory = new Category();
            ((Category) parent).subCategories.add(auxCategory);
            auxCategory.parent = parent;
            return createCategories(scanner, auxCategory);
        }
        if(line.contains("</Category>")){
            return createCategories(scanner, (Category) parent.parent);
        }
        if(line.contains("<Product>")){
            Product auxProduct = new Product();
            ((Category) parent).products.add(auxProduct);
            auxProduct.parent = parent;
            return createCategories(scanner,  auxProduct);
        }
        if(line.contains("</Product>")){
            return createCategories(scanner, (Category) parent.parent);
        }
        if(line.contains("<Name>")){
            parent.name = readText(line);
            return createCategories(scanner, parent);
        }
        if(line.contains("<Description>")){
            parent.desc = readText(line);
            return createCategories(scanner, parent);
        }
        if(line.contains("<Code>")){
            parent.code = readText(line);
            return createCategories(scanner, parent);
        }
        if(line.contains("<Price>")){
            ((Product)parent).setPrice(Double.parseDouble(readText(line)));
            return createCategories(scanner, parent);
        }
        if(line.contains("<Url>")){
            return createCategories(scanner, parent);
        }
        return ((Category) parent).subCategories;
    }
    public void createTree(){
        try {
            File file = new File("src/records.txt");
            Scanner input = new Scanner(file);
            categories = createCategories(input, new Category());
        } catch (Exception ex) {
            System.out.println("File Sintax Error");
        }
    }
    private String readText(String line) {
        return line.split(">")[1].split("<")[0];
    }
    private void printProducts(int count, ArrayList<Product> products) {
        if(count<products.size()) {
            Product auxProduct = products.get(count);
            auxProduct.printProduct();
            printProducts(count+1, products);
        }
    }
}
