package Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import MyUtilities.Validation;

public abstract class DatabaseTemplate<T extends ObjectInfo> {

    private ArrayList<T> records = new ArrayList<>();
    private String filename;

    public DatabaseTemplate(String filename) { // Constructor
        if (Validation.isValidString(filename) == false) {
            System.out.println("Invalid filename; filename not set");
            throw new IllegalArgumentException("Invalid filename");
        } else {
            this.filename = filename;
        }
    }

    public abstract T createRecordFrom(String line); // abstract method implementation needed !!

    public void readFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.filename));
        String line;

        while ((line = br.readLine()) != null) { // done but depends on above abstract method
            T record = createRecordFrom(line);
            records.add(record);
        }
        br.close();
    }

    public ArrayList<T> returnAllRecords() {
        return records; // done
    }

    public boolean contains(String key) {

     for(int i = 0; i < records.size(); i++)
     {                                                          //done
      if(key == records.get(i).getSearchKey())
      return true;
     }
      return false;
    }


    public T getRecord(String key){

     for(int i = 0; i < records.size(); i++)
     {                                                          //DONE
      if(key == records.get(i).getSearchKey())
      return records.get(i);
     }
     System.out.println("RECORD (TO BE RETURNED) NOT FOUND!"); 
     return null;
    }



    public void insertRecord(T record){

      for(int i = 0; i < records.size(); i++)
      {                                                         //DONE
        if(record == records.get(i))
        {
        System.out.println("RECORD (TO BE INSERTED) ALREADY EXISTS!");
        return;
        }
      }
        records.add(record);
    }

    public void deleteRecord(String key){

     for(int i = 0; i < records.size(); i++)
     {                                                          //DONE
       if(key == records.get(i).getSearchKey())
       {
       records.remove(i);
       return;
       }
     } 
       System.out.println("RECORD (TO BE DELETED) NOT FOUND!"); 
    }

    public void saveToFile() {

    }

}