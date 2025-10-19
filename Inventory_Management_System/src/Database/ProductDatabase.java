package Database;

import User.Product;

public class ProductDatabase extends DatabaseTemplate<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {

        String[] fields = line.split(",");

        return new Product(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]),
                Float.parseFloat(fields[5]));
    }

}
