import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Admin.AdminRole;
//import Database.ProductDatabase;
import User.EmployeeRole;

public class Lab4Main {
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("===================================================");
        System.out.println("  Welcome to [Quad A] Inventory Management System");
        System.out.println("===================================================");
        
        int choice;
        Scanner scan = new Scanner(System.in);
        
    do{
        do{
        System.out.println("\n*******System User ?*******");
        System.out.println("---Admin(1)\n---Employee(2)");
        System.out.println("...Choose User Type [EXIT(0)]");
        choice = scan.nextInt();
        if(choice == 0)
        System.exit(0);
        }while(choice != 1 && choice != 2);
       

        if(choice == 1)
        {
            System.out.println("+----------------------+");
            System.out.println("|   Welcome, Admin!    |");
            System.out.println("+----------------------+");
        
        adminloop:
        do{
            do{
            System.out.println("=======What you want yo do ?=======\n");
            System.out.println("---Add New Employee(1)");
            System.out.println("---Employees List(2)");
            System.out.println("---Remove Employee(3)");
            System.out.println("---LogOut(4)");
            System.out.println("...Choose Operation [EXIT(0)]");
            choice = scan.nextInt();
            if(choice == 0)
            System.exit(0);
            }while(choice < 1 || choice > 4);
            
            AdminRole ar = new AdminRole();
            String[] holdS = new String[5];
            switch(choice){

                case 1:
                    System.out.println("-------New Employee Info-------\n");
                    scan.nextLine();
                    boolean reEnter = false;
                    do{
                        if(reEnter)
                    System.out.println("Employee ID already exist, RE-ENTER!");
                    System.out.print("Employee ID: ");
                    holdS[0] = scan.nextLine();
                    reEnter = true;
                    }while (ar.checkDuplicate(holdS[0])); 
                    System.out.print("Name: ");
                    holdS[1] = scan.nextLine();   
                    System.out.print("E-mail: ");
                    holdS[2] = scan.nextLine();
                    System.out.print("Address: ");
                    holdS[3] = scan.nextLine();
                    System.out.print("Mobile Number: ");
                    holdS[4] = scan.nextLine();
                    ar.addEmployee(holdS[0], holdS[1], holdS[2], holdS[3], holdS[4]);
                    System.out.println("Employee Registered Successffully!");
                    break;

                case 2:
                    System.out.println("-------Employees List-------\n");
                    ar.getListOfEmployees();
                    System.out.println(ar.getListOfEmployees().length + " Employees");
                    for(int i = 0; i < ar.getListOfEmployees().length; i++)
                    {
                        holdS = ar.getListOfEmployees()[i].lineRepresentation().split(",");
                        System.out.println("---Employee [" + (i + 1) + "]");
                        System.out.println("Employee ID: " + holdS[0] + " | Name: " + holdS[1]
                        + " | E-Mail: "+ holdS[2] + " | Address: "+ holdS[3] + " | Mobile Number: " + holdS[4]);
                    }
                    break;

                case 3:
                    System.out.println("-------Remove Employee-------\n");
                    scan.nextLine();
                    System.out.print("Employee ID: ");
                    holdS[0] = scan.nextLine();
                    ar.removeEmployee(holdS[0]);
                    System.out.println("Employee " + holdS[0] + " Removed Successfully!");
                    break;

                case 4:
                    ar.logout();
                    System.out.println("....Saved and Logged Out Successfully!");
                    break adminloop;
                
                default:
                System.out.println("Re-Enter Operation Choice [1 - 4]");
            }
        
        System.out.println("\nNew Operation (1) | Exit Admin (0)");
        choice = scan.nextInt();
    
        }while(choice != 0);
        
        }
        

        else
        { 
            System.out.println("+----------------------+");
            System.out.println("|  Welcome, Employee!  |");
            System.out.println("+----------------------+");
        
        employeeloop:    
        do{    
            do{

            System.out.println("=======What you want yo do ?=======\n");
            System.out.println("---Add New Product(1)");
            System.out.println("---Products List(2)");
            System.out.println("---Purchasing Operations List(3)");
            System.out.println("---Purchase Product(4)");
            System.out.println("---Return Product(5)");
            System.out.println("---Apply Payment(6)");
            System.out.println("---LogOut(7)");
            System.out.println("...Choose Operation [EXIT(0)]");
            choice = scan.nextInt();
            if(choice == 0)
            System.exit(0);
            }while(choice < 1 || choice > 7);

            
            EmployeeRole er = new EmployeeRole();
            String[] holdS = new String[6];
            switch(choice){

                case 1:
                    System.out.println("-------Add New Product-------\n");
                    scan.nextLine();
                    boolean reEnter = false;
                    do{
                        if(reEnter)
                    System.out.println("Product ID already exist, RE-ENTER!");
                    System.out.print("Product ID: ");
                    holdS[0] = scan.nextLine(); 
                    reEnter = true;
                    }while(er.checkDuplicate(holdS[0])); 
                    System.out.print("Product Name: ");
                    holdS[1] = scan.nextLine();   
                    System.out.print("Manufacturer Name: ");
                    holdS[2] = scan.nextLine();
                    System.out.print("Supplier Name: ");
                    holdS[3] = scan.nextLine();
                    System.out.print("Qunatity: ");
                    holdS[4] = scan.nextLine();
                    System.out.print("Price: ");
                    holdS[5] = scan.nextLine();
                    er.addProduct(holdS[0], holdS[1], holdS[2], holdS[3], Integer.parseInt(holdS[4]));
                //setprice
                    System.out.println("Product Added Successfully!");
                    break; 

                case 2:
                    System.out.println("-------Products List-------\n");
                    er.getListOfProducts();
                    System.out.println(er.getListOfProducts().length + " Products");
                    for(int i = 0; i < er.getListOfProducts().length; i++)
                    {
                        holdS = er.getListOfProducts()[i].lineRepresentation().split(",");
                        System.out.println("---Product [" + (i + 1) + "]");
                        System.out.println("Product ID: " + holdS[0] + "|Product Name: " + holdS[1]
                        + "|Manufacturer Name: " + holdS[2] + "|supplier Name: " + holdS[3]
                        + "|Quantity: " + holdS[4] + "|Price: " + holdS[5]);
                    }
                    break;
                
                case 3:
                    System.out.println("-------Purchasing Operations-------\n");
                    er.getListOfPurchasingOperations();
                    System.out.println(er.getListOfPurchasingOperations().length + " Operations");
                    for(int i = 0; i < er.getListOfPurchasingOperations().length; i++)
                    {
                        holdS = er.getListOfPurchasingOperations()[i].lineRepresentation().split(",");
                        System.out.println("---Operation [" + (i + 1) + "]");
                        System.out.println("Customer SSN: " + holdS[0] + "|Product ID: " + holdS[1]
                        + "|Purchase Date: " + holdS[2] + "|Paid: " + holdS[3]);  
                    }
                    break;

                case 4:
                    System.out.println("-------Purchase Product-------\n");
                    scan.nextLine();
                    System.out.print("Customer SSN: ");
                    holdS[0] = scan.nextLine();
                    System.out.print("Product ID: ");
                    holdS[1] = scan.nextLine();   
                    System.out.print("Purchase Date: ");
                    holdS[2] = scan.nextLine();
                    if(er.purchaseProduct(holdS[0], holdS[1], 
                    LocalDate.parse(holdS[2], DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    
                    System.out.println("Product Purchased Successfully!");
                    
                    else
                    System.out.println("Product Purchase Failed!");
                    break;

                case 5:
                    System.out.println("-------Return Product-------\n");
                    scan.nextLine();
                    System.out.print("Customer SSN: ");
                    holdS[0] = scan.nextLine();
                    System.out.print("Product ID: ");
                    holdS[1] = scan.nextLine();   
                    System.out.print("Purchase Date: ");
                    holdS[2] = scan.nextLine();
                    System.out.print("Return Date: ");
                    holdS[3] = scan.nextLine();

                    System.out.println("Product Price: " 
                    + er.returnProduct(holdS[0], holdS[1], 
                    LocalDate.parse(holdS[2], DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                    LocalDate.parse(holdS[3], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                    System.out.println("Product Returned Successfully!");
                    break;

                case 6:
                    System.out.println("-------Apply payment-------\n");
                    scan.nextLine();
                    System.out.print("Customer SSN: ");
                    holdS[0] = scan.nextLine();
                    System.out.print("Purchase Date: ");
                    holdS[1] = scan.nextLine();
                    if(er.applyPayment(holdS[0], 
                    LocalDate.parse(holdS[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    
                    System.out.println("Payment Applied Successfully!");
                    
                    else
                    System.out.println("Payment Failed!");
                    break;
                
                case 7:
                er.logout();
                System.out.println("....Saved and Logged Out Successfully!");
                break employeeloop;  
                
                default:

                System.out.println("Re-Enter Operation Choice [1 - 7]");
            }
        
        System.out.println("\nNew Operation (1) | Exit Employee (0)");
        choice = scan.nextInt();
        
        }while(choice != 0);


        }
    
    System.out.println("\n....RE-LOGIN (1) | EXIT SYSTEM (0)");
    choice = scan.nextInt();    

    }while(choice != 0);
        
    System.out.println("Exiting....");
    System.out.println("===================================================");
    System.out.println("[Quad A] Inventory Management System Terminated");
    System.out.println("===================================================");
    scan.close();
    } 
}



    


