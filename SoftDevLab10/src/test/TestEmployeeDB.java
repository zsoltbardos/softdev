/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.EmployeeOperations;
import java.util.Calendar;
import java.util.Scanner;
import model.Company;

public class TestEmployeeDB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        EmployeeOperations eo = new EmployeeOperations();
        eo.openDB();
        
        eo.dropEmployee1Sequence();
        eo.dropEmployee1Table();
        
        eo.createEmployee1Sequence();
        eo.createEmployee1table();
        eo.fillEmployee1Table();


        Company co = new Company("SAP", eo);
        co.fillList();

        while (true) {
            System.out.println("Please press 1 to view employee details");
            System.out.println("Please press 2 if you want to update a salary");
            System.out.println("Please press 3 to delete an employee");
            System.out.println("Please press 4 to add a new employee");
            System.out.println("Press 5 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    co.showEmployees();
                    break;
                case 2:
                    System.out.println("please enter the id of the employee you wish to update");
                    int id = in.nextInt();
                    in.nextLine();
                    if (co.findEmployee(id)==-1) {
                        System.out.println("Record not found");
                    } else {
                    System.out.println("Please enter the new salary for " + id);
                    double newSal = in.nextDouble();
                    co.updateEmployee(id, newSal);
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the employee you "
                            + "wish to delete");
                    int idDelete = in.nextInt();
                    co.removeEmployee(idDelete);
                    break;
                case 4:
                    System.out.println("please enter the name of the employee "
                            + "you wish to add");
                    String nameAdd = in.nextLine();
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
                    Calendar dob = Calendar.getInstance();
                    dob.set(year, month, day);
                    System.out.println("please enter the salary of "
                            + "the employee you wish to add");
                    double salary = in.nextDouble();
                    eo.add(nameAdd, dob, salary);
                    co.addEmployee();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
   }
}
        

