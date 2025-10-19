package User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Database.*;
import MyUtilities.Validation;

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

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        // validations:
        // variable values validation:
        if (!Validation.isValidString(customerSSN)) {
            throw new IllegalArgumentException("Invalid customer SSN.");
        }
        if (!Validation.isValidString(productID)) {
            throw new IllegalArgumentException("Invalid product ID.");
        }
        if (purchaseDate == null) {
            throw new IllegalArgumentException("Invalid purchase date.");
        }
        if (returnDate == null) {
            throw new IllegalArgumentException("Invalid return date.");
        }

        // logical validations:
        if (returnDate.isBefore(purchaseDate) || returnDate.isAfter(purchaseDate.plusDays(14))) {
            return -1;
        }
        if (productsDatabase.contains(productID) == false) {
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        product.setQuantity(product.getQuantity() + 1);

        customerProductDatabase.deleteRecord(
                customerSSN + "," + productID + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        try {
            productsDatabase.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving product to file after purchase: " + e.getMessage());
        }

        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        // since I have no productId, I will need to implement a search over the records
        // instead of using getRecord
        int size = customerProductDatabase.numberOfRecords();
        CustomerProduct records[] = new CustomerProduct[size];
        records = customerProductDatabase.returnAllRecords().toArray(records);
        String key = customerSSN + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // any unpaid product will be marked as paid
        boolean state = false;
        for (int i = 0; i < size; i++) {
            String recordKey = records[i].getCustomerSSN() + ","
                    + records[i].getPurchaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            if (key.equals(recordKey) && records[i].isPaid() == false) {
                records[i].setPaid(true);
                try {
                    customerProductDatabase.saveToFile();
                } catch (Exception e) {
                    System.err.println("Error saving payment status to file: " + e.getMessage());
                }
                state = true;
            }
        }
        if (state) {
            return true;
        } else {
            System.out.println("RECORD (TO BE RETURNED) NOT FOUND!");
            return false;
        }
    }

    public void logout() {
        try {
            productsDatabase.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading Products.txt: " + e.getMessage());
        }

        try {
            customerProductDatabase.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading CustomerProducts.txt: " + e.getMessage());
        }
    }
}
