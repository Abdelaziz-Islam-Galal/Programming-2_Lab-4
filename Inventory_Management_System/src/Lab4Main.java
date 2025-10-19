import java.util.Scanner;

import Admin.AdminRole;

public class Lab4Main {
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("===================================================");
        System.out.println("  Welcome to [Quad A] Inventory Management System");
        System.out.println("===================================================");
        
        int choice;
        Scanner scan = new Scanner(System.in);
        
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
        
        do{
            do{
            System.out.println("+----------------------+");
            System.out.println("|   Welcome, Admin!    |");
            System.out.println("+----------------------+");

            System.out.println("*******What you want yo do ?*******");
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
                    System.out.println("-------New Employee Info-------");
                    System.out.print("Employee ID: ");
                    holdS[0] = scan.nextLine();
                    System.out.print("Name: ");
                    holdS[1] = scan.nextLine();   
                    System.out.print("E-mail: ");
                    holdS[2] = scan.nextLine();
                    System.out.print("Address: ");
                    holdS[3] = scan.nextLine();
                    System.out.print("Mobile Number: ");
                    holdS[4] = scan.nextLine();
                    ar.addEmployee(holdS[0], holdS[1], holdS[2], holdS[3], holdS[4]);
                    break;

                case 2:
                    System.out.println("-------Employees List-------");
                    ar.getListOfEmployees();
                    System.out.println(ar.getListOfEmployees().length + "Employees");
                    for(int i = 0; i < ar.getListOfEmployees().length; i++)
                    {
                        holdS = ar.getListOfEmployees()[i].lineRepresentation().split(",");
                        System.out.println("---Employee [" + (i + 1) + "]");
                        System.out.println("Employee ID: " + holdS[0] + "Name: " + holdS[1]
                        + "E-Mail: "+ holdS[2] + "Address: "+ holdS[3] + " Mobile Number: " + holdS[4]);
                    }



               case 3:
               case 4:
               default:
            
            }
        
    
        }while(choice != 0);
        
        }
        
        else
        { 
            System.out.println("+----------------------+");
            System.out.println("|  Welcome, Employee!  |");
            System.out.println("+----------------------+");





        }
        
        
        scan.close();







    }
}

    


