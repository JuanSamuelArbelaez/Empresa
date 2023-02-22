import java.util.ArrayList;

public class Category extends Node {
    public ArrayList<Product> products = new  ArrayList<>();
    public ArrayList<Category> subCategories = new ArrayList<>();
    public ArrayList<Product> getProducts(ArrayList<Product> products) {
        products.addAll(this.products);
        for(Category auxCategory :this.subCategories){
            auxCategory.getProducts(products);
        }
        return products;
    }
    public ArrayList<Product> getProducts_PriceRange(double min, double max, ArrayList<Product> products) {
        for(Category cat:this.subCategories){
            cat.getProducts_PriceRange(min, max, products);
        }
        return addProduct_InRange(min, max, products);
    }
    private ArrayList<Product> addProduct_InRange(double min, double max, ArrayList<Product> products) {
        for(Product product:this.products){
            if(product.priceInRange(min, max)) products.add(product);
        }
        return products;
    }
    public Category findCategory_Name(Category cat, String name){
        Category auxCat=null;
        if(cat.name.equalsIgnoreCase(name)) auxCat=cat;
        if(auxCat==null) {
            for (Category auxCategory : cat.subCategories) {
                auxCat = findCategory_Name(auxCategory, name);
                if (auxCat != null) return auxCat;
            }
        }
        return auxCat;
    }
    public Category findCategory_Code(Category cat, String code){
        Category auxCat=null;
        if(cat.code.equalsIgnoreCase(code)) auxCat=cat;
        if(auxCat==null) {
            for (Category auxCategory : cat.subCategories) {
                auxCat = findCategory_Code(auxCategory, code);
                if (auxCat != null) return auxCat;
            }
        }
        return auxCat;
    }
    public void printProducts(Category category){
        if(category!=null) {
            for (Category auxCategory : category.subCategories) {
                if (auxCategory != null) printProducts(auxCategory);
            }
            for (Product auxProduct : category.products) {
                System.out.println(auxProduct.name);
            }
        }
    }
}
