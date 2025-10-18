package product;

public class Product {
    // Constants for validation
    public static final double MIN_PRICE = 0.0;
    public static final int MIN_QUANTITY = 0;
    
    private String productID;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;

    public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        // Input validation
        if (productID == null || productID.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (manufacturerName == null || manufacturerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer name cannot be null or empty");
        }
        if (supplierName == null || supplierName.trim().isEmpty()) {
            throw new IllegalArgumentException("Supplier name cannot be null or empty");
        }
        
        this.productID = productID;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        setQuantity(quantity);  //to validate quantity input  before initializing
        setPrice(price);        /*to validate price input before initializing 
                                  (عشان لما حد يستعمل 
                                  addproduct 
                                  ميقدرش يدخل قيمة غلط) */

    }

    public String lineRepresentation() {
        return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price;
    }

    public String getSearchKey() {
        return productID;
    }

    
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

    
    public void setQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
            throw new IllegalArgumentException("Quantity cannot be negative. Provided: " + quantity);
        }
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException("Price cannot be negative. Provided: " + price);
        }
        this.price = price;
    }
}

  