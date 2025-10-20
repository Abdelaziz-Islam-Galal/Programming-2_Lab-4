package Admin;

import Database.EmployeeUserDatabase;
import MyUtilities.Validation;

import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        database = new EmployeeUserDatabase("Employees.txt");

        try {
            database.readFromFile();
        } catch (Exception e) {
            System.err.println("Error reading Employees.txt: " + e.getMessage());
        }
    }

    public void addEmployee(String employeeId, String name, String email, 
                           String address, String phoneNumber) {
        
        EmployeeUser employee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(employee);

        try {
            database.saveToFile();
        } catch (Exception e) { 
            System.err.println("Error saving employee to file: " + e.getMessage());
        }
    }

    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> list = database.returnAllRecords();
        return list.toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key) {
        if (!Validation.isValidString(key)) {
            throw new IllegalArgumentException("Invalid employee key");
        }
        database.deleteRecord(key);
        
        try {
            database.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving to file after deletion: " + e.getMessage());
        }
    }
    
    public void logout() {
        try {
            database.saveToFile();
        } catch (Exception e) {
            System.err.println("Error saving employee to file: " + e.getMessage());
        }
    }

    public boolean checkDuplicate(String key){
        return database.contains(key);
    }
}
