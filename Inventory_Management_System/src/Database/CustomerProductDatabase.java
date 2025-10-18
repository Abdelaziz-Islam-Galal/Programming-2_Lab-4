package Database;

import Admin.CustomerProduct;


public class CustomerProductDatabase extends DatabaseTemplate <CustomerProduct> {

    public CustomerProductDatabase(String filename){
    super(filename);
}


    public CustomerProduct createRecordFrom(String line){
        
        return new CustomerProduct(line, line, null);
    }
    

   

}
    

