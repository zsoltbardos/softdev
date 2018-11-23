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
import model.Department;

public class TestEmployeeDB {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        EmployeeOperations eo = new EmployeeOperations();
        eo.openDB();
        eo.dropEmployeeSequence();
        eo.dropDepartmentSequence();

        eo.dropFTTable();
        eo.dropPTTable();
        eo.dropEmployeeTable();
        eo.dropDEPTable();
        
        eo.createDepartmentSequence();
        eo.createEmployeeSequence();
        eo.createDEPtable();
        eo.createEmployeetable();
        eo.createFTtable();
        eo.createPTtable();
        
        eo.fillTables();

        

        PersistenceOperations po = new PersistenceOperations();

        while (true) {
            System.out.println("Please press 1 to add a new department");
            System.out.println("Please press 2 to add a new employee");
            System.out.println("Please press 3 to view all department details");
            System.out.println("Please press 4 to view all employees");
            System.out.println("Please press 5 to view all PT employees");
            System.out.println("Please press 6 to view all FT employees");
            System.out.println("Please press 7 to delete an employee using JPA method");
            System.out.println("Please press 8 to delete a department");
            System.out.println("Press 9 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("please enter the name of the depeartment "
                            + "you wish to add");
                    String name = in.nextLine();
                    po.addDep(name);
                    break;
                case 2:
                     System.out.println("please enter the name of employee "
                            + "you wish to add"); 
                     name=in.nextLine();
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

                    System.out.println("Please enter the department ID where this employee will work");
                    int did = in.nextInt();
                    in.nextLine();                   
                    System.out.println("please enter the employee type");
                    String type = in.nextLine();
                    if(type.equals("FT")){
                        System.out.println("Pleaes enter the salary for this employee");
                        double sal = in.nextDouble();
                        po.addFTEmployee(name, sdate,did,sal);
                    }else if(type.equals("PT")){
                        System.out.println("Pleaes enter the rate for this employee");
                        double rate = in.nextDouble();
                        System.out.println("Pleaes enter the hrs for this employee");
                        double hrs = in.nextDouble();
                        po.addpTEmployee(name, sdate, did, rate, hrs);
                    }
                    break;
                case 3:
                    po.viewDepartments();
                    break;
                case 4:
                    po.showAllEmployees();
                    break;
                case 5:
                    po.showAllPT();
                    break;
                case 6:
                    po.showAllFT();
                    break;
                case 7:
                    System.out.println("please enter the id of the department where "
                            + "you wish to delete an employee");
                    did = in.nextInt();
                    in.nextLine();
                    System.out.println("please enter the id of the employee you "
                            + "wish to delete");
                    int eid = in.nextInt();
                    in.nextLine();
                    po.removeEmployee(eid,did);
                    break;
                case 8:
                    System.out.println("Please enter the id of the department you wish to delete");
                    did = in.nextInt();
                    in.nextLine();
                    Department d = po.findDepartment(did);
                    if(d!=null){ 
                    po.deleteDepartment(did);
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
