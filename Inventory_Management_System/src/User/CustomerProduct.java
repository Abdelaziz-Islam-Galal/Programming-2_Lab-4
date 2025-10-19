package User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Database.ObjectInfo;
import MyUtilities.Validation;

public class CustomerProduct implements ObjectInfo {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        if (!Validation.isValidString(customerSSN)) {
            throw new IllegalArgumentException("Invalid customer SSN.");
        }
        if (!Validation.isValidString(productID)) {
            throw new IllegalArgumentException("Invalid product ID.");
        }
        if (purchaseDate == null) {
            throw new IllegalArgumentException("Invalid purchase date.");
        } 

        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    } 
    
    public String getCustomerSSN() {
        return customerSSN;
    } 
    
    public String getProductID() {
        return productID;
    } 
    
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    
    public boolean isPaid() {
        return paid;
    }
    
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
    
    @Override
    public String lineRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return (customerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid);
    }
    
    @Override
    public String getSearchKey() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return (customerSSN + "," + productID + "," + purchaseDate.format(formatter));
    }

   /*  public static void main(String[] args) {
        CustomerProduct c = new CustomerProduct("null", "P2568", LocalDate.of(2022, 2, 12));
        c.setPaid(true);
        System.out.println(c.lineRepresentation());
        System.out.println(c.getSearchKey());
    }  
        */
}
