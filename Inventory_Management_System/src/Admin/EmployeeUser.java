package Admin;

import Database.ObjectInfo;

public class EmployeeUser implements ObjectInfo{
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
        if (employeeId == null) {
            System.out.println("Invalid employee id; id not set");
            return false;
        } else if (employeeId.trim().isEmpty() || employeeId.matches(".*\\s.*")) {
            System.out.println("Invalid employee id; id not set");
            return false;
        }

        this.employeeId = employeeId;
        return true;
    }

    public boolean setName(String name) {
        if (name == null) {
            System.out.println("Invalid name; name not set");
            return false;
        }
        if (name.trim().isEmpty()) {
            System.out.println("Invalid name; name not setd");
            return false;
        }

        this.name = name;
        return true;
    }

    public boolean setEmail(String email) {
        if (email == null) {
            System.out.println("Invalid email; email not set");
            return false;
        }
        if (email.trim().isEmpty() || !email.contains("@")) {
            System.out.println("Invalid email; email not set");
            return false;
        }

        this.email = email;
        return true;
    }

    public boolean setAddress(String address) {
        if (address == null) {
            System.out.println("Invalid address; address not set");
            return false;
        }
        if (address.trim().isEmpty()) {
            System.out.println("Invalid address; address not set");
            return false;
        }

        this.address = address;
        return true;
    }

    public boolean setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            System.out.println("Invalid phone number; phone number not set");
            return false;
        }
        if (phoneNumber.trim().isEmpty() || !phoneNumber.matches("\\d{11}")) { // \\d{11} = have 11 numbers only
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
    public String lineRepresentation() {
        if (isValidUser(this)) {
            return null;
        }

        return (employeeId + "," + name + "," + email + "," + address + "," + phoneNumber);
    }

    public String getSearchKey() {
        if (isValidUser(this)) {
            return null;
        }

        return employeeId;
    }

}
