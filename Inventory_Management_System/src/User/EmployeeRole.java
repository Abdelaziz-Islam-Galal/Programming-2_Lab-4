package User;

import java.time.LocalDate;

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

    public void addProduct(String productID, String productName, String manufacturerName, String supplierName,
            int quantity) {
        Product product = new Product(productID, productName, manufacturerName, supplierName, quantity, 0.0f);
        productsDatabase.insertRecord(product);

        try {
            productsDatabase.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving product to file: " + e.getMessage());
        }
    }

    public Product[] getListOfProducts() {
        int size = productsDatabase.numberOfRecords();

        Product[] products = new Product[size];
        return productsDatabase.returnAllRecords().toArray(products);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        int size = customerProductDatabase.numberOfRecords();

        CustomerProduct[] customerProducts = new CustomerProduct[size];
        return customerProductDatabase.returnAllRecords().toArray(customerProducts);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (productsDatabase.contains(productID)) {
            Product product = productsDatabase.getRecord(productID); // return refrence
            if (product.getQuantity() <= 0) {
                return false;
            }

            CustomerProduct customerProduct = new CustomerProduct(customerSSN, productID, purchaseDate);
            customerProductDatabase.insertRecord(customerProduct);
            try {
                customerProductDatabase.saveToFile();
            } catch (Exception e) {
                System.err.println("Error saving purchasing operation to file: " + e.getMessage());
            }

            product.setQuantity(product.getQuantity() - 1); // since reference is returned, then data changed will take
                                                            // effect in the array
            try {
                productsDatabase.saveToFile();
            } catch (Exception e) {
                System.err.println("Error saving product to file after purchase: " + e.getMessage());
            }

            return true;
        } else {
            return false;
        }
    }

    
}
