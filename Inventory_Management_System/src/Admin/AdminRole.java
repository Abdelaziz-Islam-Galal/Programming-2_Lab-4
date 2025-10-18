package Admin;

import Database.EmployeeUserDatabase;
import java.util.ArrayList;

public class AdminRole {

    private EmployeeUserDatabase database;

    public AdminRole()
    {
        database= new EmployeeUserDatabase("Employees.txt");

        try {
            database.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading Employees.txt: "+e.getMessage());
        }
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber)
    {
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(employee);

        try {
            database.saveToFile();
        }
        catch (Exception e) { 
            System.err.println("Error saving employee to file: "+e.getMessage());
        }
    }

    public EmployeeUser[] getListOfEmployees()
    {
        ArrayList<EmployeeUser> list = new ArrayList<>();

        list=database.returnAllRecords();

        EmployeeUser[] array = list.toArray(new EmployeeUser[0]);
        return array;
    }

    public void removeEmployee(String key) 
    {
        database.deleteRecord(key);
    }
    
    public void logout()
    {
        try {
            database.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving employee to file: "+e.getMessage());
        }
    }


}


