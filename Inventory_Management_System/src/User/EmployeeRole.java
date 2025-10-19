package User;

import Database.*;

public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        productsDatabase = new ProductDatabase("Products.txt");
        try {
            productsDatabase.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading Products.txt: " + e.getMessage());
        }

        customerProductDatabase = new CustomerProductDatabase("CustomerProducts.txt");
        try {
            customerProductDatabase.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading CustomerProducts.txt: " + e.getMessage());
        }
    }

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName, int quantity) {
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
        productsDatabase.insertRecord(product);

        try {
            productsDatabase.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving product to file: " + e.getMessage());
        }
    }

    public Product[] getListOfProducts(){
    }
}
