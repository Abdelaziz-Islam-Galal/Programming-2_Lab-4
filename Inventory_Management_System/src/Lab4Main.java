import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Admin.AdminRole;
import Admin.EmployeeUser;
import User.EmployeeRole;
import User.Product;
import User.CustomerProduct;

public class Lab4Main {
    
    public static void main(String[] args) {
        
        System.out.println("===================================================");
        System.out.println("  Welcome to [Quad A] Inventory Management System");
        System.out.println("===================================================");
        
        int choice;
        Scanner scan = new Scanner(System.in);
        
        do {
           
            do {
                try {
                    System.out.println("\n*******System User?*******");
                    System.out.println("---Admin(1)\n---Employee(2)");
                    System.out.println("...Choose User Type [EXIT(0)]");
                    choice = scan.nextInt();
                    
                    if (choice == 0) {
                        System.out.println("\nExiting....");
                        System.out.println("===================================================");
                        System.out.println("[Quad A] Inventory Management System Terminated");
                        System.out.println("===================================================");
                        scan.close();
                        return;
                    }
                    
                    if (choice != 1 && choice != 2) {
                        System.out.println("Invalid choice! Please enter 1 or 2.");
                    }
                    
                } catch (InputMismatchException e) {
                    System.out.println("Error: Please enter a valid number (0, 1, or 2)");
                    scan.nextLine(); // Clear invalid input
                    choice = -1; // Force re-entry
                }
            } while (choice != 1 && choice != 2);

            // ADMIN SECTION
            if (choice == 1) {
                System.out.println("+----------------------+");
                System.out.println("|   Welcome, Admin!    |");
                System.out.println("+----------------------+");
                
                // Create AdminRole ONCE outside loop to prevent data loss on crash
                AdminRole ar = new AdminRole();
                
                adminloop:
                do {
                   
                    do {
                        try {
                            System.out.println("=======What do you want to do?=======\n");
                            System.out.println("---Add New Employee(1)");
                            System.out.println("---Employees List(2)");
                            System.out.println("---Remove Employee(3)");
                            System.out.println("---LogOut(4)");
                            System.out.println("...Choose Operation [EXIT(0)]");
                            choice = scan.nextInt();
                            
                            if (choice == 0) {
                                ar.logout();
                                System.out.println("....Saved and Exiting....");
                                scan.close();
                                System.exit(0);
                            }
                            
                            if (choice < 1 || choice > 4) {
                                System.out.println("Invalid choice! Please enter 1-4.");
                            }
                            
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Please enter a valid number");
                            scan.nextLine();
                            choice = -1;
                        }
                    } while (choice < 1 || choice > 4);
                    
                    String[] holdS = new String[5];
                    
                    switch (choice) {
                        case 1: 
                            System.out.println("-------New Employee Info-------\n");
                            scan.nextLine();
                            
                           
                            boolean reEnter = false;
                            do {
                                if (reEnter)
                                    System.out.println("Employee ID already exists, RE-ENTER!");
                                System.out.print("Employee ID: ");
                                holdS[0] = scan.nextLine();
                                reEnter = true;
                            } while (ar.checkDuplicate(holdS[0]));
                            
                            System.out.print("Name: ");
                            holdS[1] = scan.nextLine();
                            System.out.print("E-mail: ");
                            holdS[2] = scan.nextLine();
                            System.out.print("Address: ");
                            holdS[3] = scan.nextLine();
                            System.out.print("Mobile Number: ");
                            holdS[4] = scan.nextLine();
                            
                           
                            try {
                                ar.addEmployee(holdS[0], holdS[1], holdS[2], holdS[3], holdS[4]);
                                System.out.println("Employee Registered Successfully!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                            }
                            break;

                        case 2:
                            System.out.println("-------Employees List-------\n");
                            try {
                                EmployeeUser[] employees = ar.getListOfEmployees();
                                System.out.println(employees.length + " Employees");
                                for (int i = 0; i < employees.length; i++) {
                                    holdS = employees[i].lineRepresentation().split(",");
                                    System.out.println("---Employee [" + (i + 1) + "]");
                                    System.out.println("Employee ID: " + holdS[0] + " | Name: " + holdS[1]
                                            + " | E-Mail: " + holdS[2] + " | Address: " + holdS[3] 
                                            + " | Mobile Number: " + holdS[4]);
                                }
                            } catch (Exception e) {
                                System.out.println("Error listing employees: " + e.getMessage());
                            }
                            break;

                        case 3: 
                            System.out.println("-------Remove Employee-------\n");
                            scan.nextLine();
                            System.out.print("Employee ID: ");
                            holdS[0] = scan.nextLine();
                            
                            
                            try {
                                ar.removeEmployee(holdS[0]);
                                System.out.println("Employee " + holdS[0] + " Removed Successfully!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error removing employee: " + e.getMessage());
                            }
                            break;

                        case 4: 
                            ar.logout();
                            System.out.println("....Saved and Logged Out Successfully!");
                            break adminloop;
                    }
                    
                    System.out.println("\nNew Operation (1) | Exit Admin (0)");
                    try {
                        choice = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Returning to menu...");
                        scan.nextLine();
                        choice = 1;
                    }
                    
                } while (choice != 0);
            }
            
            
            else {
                System.out.println("+----------------------+");
                System.out.println("|  Welcome, Employee!  |");
                System.out.println("+----------------------+");
                
             
                EmployeeRole er = new EmployeeRole();
                
                employeeloop:
                do {
                   
                    do {
                        try {
                            System.out.println("=======What do you want to do?=======\n");
                            System.out.println("---Add New Product(1)");
                            System.out.println("---Products List(2)");
                            System.out.println("---Purchasing Operations List(3)");
                            System.out.println("---Purchase Product(4)");
                            System.out.println("---Return Product(5)");
                            System.out.println("---Apply Payment(6)");
                            System.out.println("---LogOut(7)");
                            System.out.println("...Choose Operation [EXIT(0)]");
                            choice = scan.nextInt();
                            
                            if (choice == 0) {
                                er.logout();
                                System.out.println("....Saved and Exiting....");
                                scan.close();
                                System.exit(0);
                            }
                            
                            if (choice < 1 || choice > 7) {
                                System.out.println("Invalid choice! Please enter 1-7.");
                            }
                            
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Please enter a valid number");
                            scan.nextLine();
                            choice = -1;
                        }
                    } while (choice < 1 || choice > 7);

                    String[] holdS = new String[6];
                    
                    switch (choice) {
                        case 1: 
                            System.out.println("-------Add New Product-------\n");
                            scan.nextLine();
                            
                            
                            boolean reEnter = false;
                            do {
                                if (reEnter)
                                    System.out.println("Product ID already exists, RE-ENTER!");
                                System.out.print("Product ID: ");
                                holdS[0] = scan.nextLine();
                                reEnter = true;
                            } while (er.checkDuplicate(holdS[0]));
                            
                            System.out.print("Product Name: ");
                            holdS[1] = scan.nextLine();
                            System.out.print("Manufacturer Name: ");
                            holdS[2] = scan.nextLine();
                            System.out.print("Supplier Name: ");
                            holdS[3] = scan.nextLine();
                            
                            // Get Quantity with validation
                            boolean validQuantity = false;
                            do {
                                try {
                                    System.out.print("Quantity: ");
                                    holdS[4] = scan.nextLine();
                                    int qty = Integer.parseInt(holdS[4]);
                                    if (qty < 0) {
                                        System.out.println("Error: Quantity cannot be negative.");
                                    } else {
                                        validQuantity = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Please enter a valid integer.");
                                }
                            } while (!validQuantity);
                            
                            // Get Price with validation in the same exact way
                            boolean validPrice = false;
                            do {
                                try {
                                    System.out.print("Price: ");
                                    holdS[5] = scan.nextLine();
                                    float priceValue = Float.parseFloat(holdS[5]);
                                    if (priceValue < 0) {
                                        System.out.println("Error: Price cannot be negative.");
                                    } else {
                                        validPrice = true;
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Please enter a valid price.");
                                }
                            } while (!validPrice);
                            
                            
                            try {
                                er.addProduct(holdS[0], holdS[1], holdS[2], holdS[3], 
                                            Integer.parseInt(holdS[4]));
                                er.setProductPrice(holdS[0], Float.parseFloat(holdS[5]));
                                System.out.println("Product Added Successfully!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                            }
                            break;

                        case 2: 
                            System.out.println("-------Products List-------\n");
                            try {
                                Product[] products = er.getListOfProducts();
                                System.out.println(products.length + " Products");
                                for (int i = 0; i < products.length; i++) {
                                    holdS = products[i].lineRepresentation().split(",");
                                    System.out.println("---Product [" + (i + 1) + "]");
                                    System.out.println("Product ID: " + holdS[0] + " | Product Name: " + holdS[1]
                                            + " | Manufacturer: " + holdS[2] + " | Supplier: " + holdS[3]
                                            + " | Quantity: " + holdS[4] + " | Price: " + holdS[5]);
                                }
                            } catch (Exception e) {
                                System.out.println("Error listing products: " + e.getMessage());
                            }
                            break;

                        case 3: 
                            System.out.println("-------Purchasing Operations-------\n");
                            try {
                                CustomerProduct[] operations = er.getListOfPurchasingOperations();
                                System.out.println(operations.length + " Operations");
                                for (int i = 0; i < operations.length; i++) {
                                    holdS = operations[i].lineRepresentation().split(",");
                                    System.out.println("---Operation [" + (i + 1) + "]");
                                    System.out.println("Customer SSN: " + holdS[0] + " | Product ID: " + holdS[1]
                                            + " | Purchase Date: " + holdS[2] + " | Paid: " + holdS[3]);
                                }
                            } catch (Exception e) {
                                System.out.println("Error listing operations: " + e.getMessage());
                            }
                            break;

                        case 4:
                            System.out.println("-------Purchase Product-------\n");
                            scan.nextLine();
                            System.out.print("Customer SSN: ");
                            holdS[0] = scan.nextLine();
                            System.out.print("Product ID: ");
                            holdS[1] = scan.nextLine();
                            System.out.print("Purchase Date (dd-MM-yyyy): ");
                            holdS[2] = scan.nextLine();
                            
                            //  try-catch for date parsing
                            try {
                                LocalDate purchaseDate = LocalDate.parse(holdS[2], 
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                
                                if (er.purchaseProduct(holdS[0], holdS[1], purchaseDate)) {
                                    System.out.println("Product Purchased Successfully!");
                                } else {
                                    System.out.println("Product Purchase Failed! (Out of stock or not found)");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid date format. Use dd-MM-yyyy (e.g., 20-10-2025)");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                            }
                            break;

                        case 5: 
                            System.out.println("-------Return Product-------\n");
                            scan.nextLine();
                            System.out.print("Customer SSN: ");
                            holdS[0] = scan.nextLine();
                            System.out.print("Product ID: ");
                            holdS[1] = scan.nextLine();
                            System.out.print("Purchase Date (dd-MM-yyyy): ");
                            holdS[2] = scan.nextLine();
                            System.out.print("Return Date (dd-MM-yyyy): ");
                            holdS[3] = scan.nextLine();
                            
                           
                            try {
                                LocalDate purchaseDate = LocalDate.parse(holdS[2], 
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                LocalDate returnDate = LocalDate.parse(holdS[3], 
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                
                                double price = er.returnProduct(holdS[0], holdS[1], 
                                                               purchaseDate, returnDate);
                                
                                if (price == -1) {
                                    System.out.println("Return Failed! (Invalid dates, product not found, or return period expired)");
                                } else {
                                    System.out.println("Product Returned Successfully!");
                                    System.out.println("Refund Amount: " + price);
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid date format. Use dd-MM-yyyy");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                            }
                            break;

                        case 6: 
                            System.out.println("-------Apply Payment-------\n");
                            scan.nextLine();
                            System.out.print("Customer SSN: ");
                            holdS[0] = scan.nextLine();
                            System.out.print("Purchase Date (dd-MM-yyyy): ");
                            holdS[1] = scan.nextLine();
                            
                          
                            try {
                                LocalDate purchaseDate = LocalDate.parse(holdS[1], 
                                    DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                                
                                if (er.applyPayment(holdS[0], purchaseDate)) {
                                    System.out.println("Payment Applied Successfully!");
                                } else {
                                    System.out.println("Payment Failed! (Purchase not found or already paid)");
                                }
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Invalid date format. Use dd-MM-yyyy");
                            } catch (Exception e) {
                                System.out.println("Unexpected error: " + e.getMessage());
                            }
                            break;

                        case 7: 
                            er.logout();
                            System.out.println("....Saved and Logged Out Successfully!");
                            break employeeloop;
                    }
                    
                    System.out.println("\nNew Operation (1) | Exit Employee (0)");
                    try {
                        choice = scan.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Returning to menu...");
                        scan.nextLine();
                        choice = 1;
                    }
                    
                } while (choice != 0);
            }
            
          
            System.out.println("\n....RE-LOGIN (1) | EXIT SYSTEM (0)");
            try {
                choice = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Exiting...");
                scan.nextLine();
                choice = 0;
            }
            
        } while (choice != 0);
        
        System.out.println("\nExiting....");
        System.out.println("===================================================");
        System.out.println("[Quad A] Inventory Management System Terminated");
        System.out.println("===================================================");
        scan.close();
    }
}
