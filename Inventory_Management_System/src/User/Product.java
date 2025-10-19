package User;

import Database.ObjectInfo;
import MyUtilities.Validation;

public class Product implements ObjectInfo {
    public static final double MIN_PRICE = 0.0;
    public static final int MIN_QUANTITY = 0;
    
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, 
                   String supplierName, int quantity, float price) {
        // Input validation using Validation utility
        if (!Validation.isValidString(productID)) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (!Validation.isValidString(productName)) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (!Validation.isValidString(manufacturerName)) {
            throw new IllegalArgumentException("Manufacturer name cannot be null or empty");
        }
        if (!Validation.isValidString(supplierName)) {
            throw new IllegalArgumentException("Supplier name cannot be null or empty");
        }
        
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        setQuantity(quantity);
        setPrice(price);
    }

    @Override
    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," + 
               supplierName + "," + quantity + "," + price;
    }

    @Override
    public String getSearchKey() {
        return productID;
    }

    // Getters
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    // Setters with validation
    public void setQuantity(int quantity) {
        if (!Validation.isValidPositiveInteger(quantity)) {
            throw new IllegalArgumentException("Quantity cannot be negative. Provided: " + quantity);
        }
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        if (!Validation.isValidPositiveNumber(price)) {
            throw new IllegalArgumentException("Price cannot be negative. Provided: " + price);
        }
        this.price = price;
    }
}
