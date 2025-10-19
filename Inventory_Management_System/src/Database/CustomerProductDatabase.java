package Database;

import java.time.LocalDate;

import User.CustomerProduct;


public class CustomerProductDatabase extends DatabaseTemplate <CustomerProduct> {

    public CustomerProductDatabase(String filename){
    super(filename);
}

    @Override
    public CustomerProduct createRecordFrom(String line){
        
         String[] fields = line.split(",");

        return new CustomerProduct(fields[0], fields[1], LocalDate.parse(fields[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
    

   

}
    

