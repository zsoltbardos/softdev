/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.StudentOperations;
import java.util.Calendar;
import java.util.Scanner;
import model.University;

public class TestStudentDB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        StudentOperations so = new StudentOperations();
        so.openDB();
        
        so.dropStudentSequence();
        so.dropEmployeeTable();
        
        so.createStudentSequence();
        so.createStudenttable();
        so.fillStudentTable();


        University u = new University("TUD", so);
        u.fillList();

        while (true) {
            System.out.println("Please press 1 to view student details");
            System.out.println("Please press 2 to view student ages");
            System.out.println("Please press 3 to delete a student");
            System.out.println("Please press 4 to add a new student");
            System.out.println("Please press 5 to view students who registered late");
            System.out.println("Press 6 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    u.showStudents();
                    break;
                case 2:
                    u.showStudentsAges();
                   break;
                case 3:
                    System.out.println("please enter the id of the student you "
                            + "wish to delete");
                    int idDelete = in.nextInt();
                    u.removeStudent(idDelete);
                    break;
                case 4:
                    System.out.println("please enter the name of the student "
                            + "you wish to add");
                    String name = in.nextLine();
                    System.out.println("Please enter the birth year of "
                            + "the student");
                    int birthYear = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the birth month of "
                            + "the student");
                    int birthMonth = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the birth day of "
                            + "the student");
                    int birthDay = in.nextInt();
                    in.nextLine();
                    Calendar birthDate = Calendar.getInstance();
                    birthDate.set(birthYear, birthMonth, birthDay);
                    System.out.println("Please enter the registration year of "
                            + "the student");
                    int regYear = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the registration month of "
                            + "the student");
                    int regMonth = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the registration day of "
                            + "the student");
                    int regDay = in.nextInt();
                    in.nextLine();
                    Calendar regDate = Calendar.getInstance();
                    regDate.set(regYear, regMonth, regDay);

                    so.add(name,birthDate,regDate);
                    u.addStudent();
                    break;
                case 5:
                    u.showLateRegisteredStudents();
                    break;
                case 6:
                    System.exit(0);                    
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
   }
}
        

