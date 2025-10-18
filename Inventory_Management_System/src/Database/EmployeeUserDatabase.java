package Database;

import Admin.EmployeeUser;

public class EmployeeUserDatabase extends DatabaseTemplate <EmployeeUser> {
    
public EmployeeUserDatabase(String filename){
    super(filename);
}


public EmployeeUser createRecordFrom(String line){

    String[] fields = line.split(",");
    
    return new EmployeeUser(fields[0], fields[1], fields[2], fields[3], fields[4]);
}

}
