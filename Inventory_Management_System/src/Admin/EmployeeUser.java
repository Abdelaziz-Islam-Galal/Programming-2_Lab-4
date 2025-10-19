package Admin;

import Database.ObjectInfo;
import MyUtilities.Validation;

public class EmployeeUser implements ObjectInfo {
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    public EmployeeUser(String employeeId, String name, String email, 
                        String address, String phoneNumber) {
        if (!Validation.isValidString(employeeId)) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        if (!Validation.isValidString(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (!Validation.isValidString(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!Validation.isValidString(address)) {
            throw new IllegalArgumentException("Invalid address");
        }
        if (!Validation.isValidString(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    // Setters with validation
    public void setEmployeeId(String employeeId) {
        if (!Validation.isValidString(employeeId)) {
            throw new IllegalArgumentException("Invalid employee ID");
        }
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        if (!Validation.isValidString(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (!Validation.isValidString(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public void setAddress(String address) {
        if (!Validation.isValidString(address)) {
            throw new IllegalArgumentException("Invalid address");
        }
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!Validation.isValidString(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String lineRepresentation() {
        return employeeId + "," + name + "," + email + "," + address + "," + phoneNumber;
    }
    
    @Override
    public String getSearchKey() {
        return employeeId;
    }
}
