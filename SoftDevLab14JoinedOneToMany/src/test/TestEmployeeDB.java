/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.EmployeeOperations;
import db.PersistenceOperations;
import java.util.Calendar;
import java.util.Scanner;
import model.Contact;
import model.Employee;

/**
 *
 * @author x00157506
 */
public class TestEmployeeDB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        EmployeeOperations eo = new EmployeeOperations();
        eo.openDB();
        eo.dropEmployeeSequence();
        eo.dropFTTable();
        eo.dropPTTable();
        eo.dropContactSequence();
        eo.dropContactTable();
        eo.dropEmployeeTable();  
        eo.createEmployeeSequence();
        eo.createEmployeeTable();
        eo.createPTTable();
        eo.createFTTable();
        eo.createContactSequence();
        eo.createContactTable();
        
        eo.fillTables();
        eo.fillContactTable();

        PersistenceOperations po = new PersistenceOperations();

        while (true) {
            System.out.println("Please press 1 to add a new employee");
            System.out.println("Please press 2 to view all employees");
            System.out.println("Please press 3 to view all PT employees");
            System.out.println("Please press 4 to view all FT employees");
            System.out.println("Please press 5 to delete an employee using JPA method");
            System.out.println("Please press 6 to add a new contact to an employee");
            System.out.println("Please press 7 to view the contacts of an employee");
            System.out.println("Please press 8 to delete a contact");
            System.out.println("Press 9 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                     System.out.println("please enter the name of employee "
                            + "you wish to add"); 
                    String name=in.nextLine();
                    System.out.println("Please enter the start year of "
                            + "the employee");
                    int year = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the start month of "
                            + "the employee");
                    int month = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the start day of "
                            + "the employee");
                    int day = in.nextInt();
                    in.nextLine();
                    Calendar sdate = Calendar.getInstance();
                    sdate.set(year, month, day);
                   
                    System.out.println("please enter the employee type");
                    String type = in.nextLine();
                    if(type.equals("FT")){
                        System.out.println("Pleaes enter the salary for this employee");
                        double sal = in.nextDouble();
                        po.addFTEmployee(name, sdate,sal);
                    }else if(type.equals("PT")){
                        System.out.println("Pleaes enter the rate for this employee");
                        double rate = in.nextDouble();
                        System.out.println("Pleaes enter the hrs for this employee");
                        double hrs = in.nextDouble();
                        po.addpTEmployee(name, sdate,rate, hrs);
                    }
                    break;
                case 2:
                    po.showAllEmployees();
                    break;
                case 3:
                    po.showAllPT();
                    break;
                case 4:
                    po.showAllFT();
                    break;
                case 5:
                    System.out.println("please enter the id of the employee you "
                            + "wish to delete");
                    int eid = in.nextInt();
                    in.nextLine();
                    if (po.findEmployee(eid) != null) {
                        po.deleteEmployee(eid);
                    }
                    break;
                case 6:
                    System.out.println("Please enter the id of the Employee for "
                            + "who you wish to add a new contact");
                    int id = in.nextInt();
                    in.nextLine();
                    Employee e = po.findEmployee(id);
                    if(e!=null){ 
                        System.out.println(e.getName());
                        System.out.println("Please enter the name of the new contact");
                        String cname = in.nextLine();
                        System.out.println("Please enter the address of the new contact");
                        String caddress = in.nextLine();
                        System.out.println("Please enter the phone of the new contact");
                        String cphone = in.nextLine();
                        System.out.println("Please enter the email of the new contact");
                        String cemail = in.nextLine();
                        Contact c = new Contact(cname,caddress,cphone,cemail);
                        po.addContact(e,c);
                    }
                    break;  
                case 7:
                    System.out.println("Please enter the id of the Employee whose "
                            + "contacts you wish to view");
                    int employeeID = in.nextInt();
                    in.nextLine();
                    Employee employee = po.findEmployee(employeeID);
                    if(employee!=null){ 
                    po.showContacts(employeeID);
                    }
                    break;
                case 8:
                    System.out.println("Please enter the id of the staff member whose "
                            + "contacts you wish to delete");
                    eid = in.nextInt();
                    in.nextLine();
                    e = po.findEmployee(eid);
                    if(e!=null){ 
                    System.out.println("Please enter the id of the contact you wish "
                            + "to delete");
                    int cid = in.nextInt();
                    in.nextLine();
                    po.deleteContact(cid, eid);
                    }                    
                    break;
                case 9:
                    po.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
