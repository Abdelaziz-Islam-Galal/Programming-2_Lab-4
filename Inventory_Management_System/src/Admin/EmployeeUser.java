package Admin;

import Database.ObjectInfo;
import MyUtilities.Validation;

public class EmployeeUser implements ObjectInfo {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber) {
        boolean validUser = true;
        validUser = setEmployeeId(employeeId);
        validUser = setName(name);
        validUser = setEmail(email);
        validUser = setAddress(address);
        validUser = setPhoneNumber(phoneNumber);

        if (!validUser) {
            System.out.println("due to invalid inputs, user is creation failed");
            this.employeeId = "Invalid";
        }
    }

    // setters:
    public boolean setEmployeeId(String employeeId) {
        if (Validation.isValidString(employeeId) == false) {
            System.out.println("Invalid employee ID; employee ID not set");
            return false;
        }

        this.employeeId = employeeId;
        return true;
    }

    public boolean setName(String name) {
        if (Validation.isValidString(name) == false) {
            System.out.println("Invalid name; name not set");
            return false;
        }

        this.name = name;
        return true;
    }

    public boolean setEmail(String email) {
        if (Validation.isValidString(email) == false) {
            System.out.println("Invalid email; email not set");
            return false;
        }

        this.email = email;
        return true;
    }

    public boolean setAddress(String address) {
        if (Validation.isValidString(address) == false) {
            System.out.println("Invalid address; address not set");
            return false;
        }

        this.address = address;
        return true;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (Validation.isValidString(phoneNumber) == false) {
            System.out.println("Invalid phone number; phone number not set");
            return false;
        }

        this.phoneNumber = phoneNumber;
        return true;
    }

    // getters:
    public String getEmployeeId() {
        if (isValidUser(this)) {
            return null;
        }

        return employeeId;
    }

    public String getName() {
        if (isValidUser(this)) {
            return null;
        }

        return name;
    }

    public String getEmail() {
        if (isValidUser(this)) {
            return null;
        }

        return email;
    }

    public String getAddress() {
        if (isValidUser(this)) {
            return null;
        }

        return address;
    }

    public String getPhoneNumber() {
        if (isValidUser(this)) {
            return null;
        }

        return phoneNumber;
    }

    // function to make sure user is valid
    public static boolean isValidUser(EmployeeUser x) {
        if (x.employeeId.equals("Invalid")) {
            System.out.println("This user was not created due to wrong construction");
            return true;
        }
        return false;
    }

    // methods:
    @Override
    public String lineRepresentation() {
        if (isValidUser(this)) {
            return null;
        }

        return (employeeId + "," + name + "," + email + "," + address + "," + phoneNumber);
    }
    
    @Override
    public String getSearchKey() {
        if (isValidUser(this)) {
            return null;
        }

        return employeeId;
    }

}
