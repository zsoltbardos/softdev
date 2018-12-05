/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.MemberOperations;
import db.PersistenceOperations;
import java.util.Calendar;
import java.util.Scanner;
import model.Member;
import model.Class;
import model.Membership;
import model.Trainer;

/**
 *
 * @author x00157506
 */
public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        MemberOperations mo = new MemberOperations();
        mo.openDB();

        mo.dropClassSequence();
        mo.dropMemberSequence();
        mo.dropMembershipSequence();
        mo.dropTrainerSequence();

        mo.createClassSequence();
        mo.createMemberSequence();
        mo.createMembershipSequence();
        mo.createTrainerSequence();

        mo.dropClassTable();
        mo.dropMemberTable();
        mo.dropMembershipTable();
        mo.dropTrainerTable();
        mo.dropMember_ClassTable();

        mo.createTrainerTable();
        mo.createClassTable();
        mo.createMembershipTable();
        mo.createMemberTable();
        mo.createMember_ClassTable();
        
        mo.fillMembershipTable();
        mo.fillMemberTable();
        mo.fillTrainerTable();
        mo.fillClassTable();



        PersistenceOperations po = new PersistenceOperations();
        
        po.enrolClass(1, 1);
        
         while (true) {
            System.out.println("Please press 1 to add a new member");
            System.out.println("Please press 2 to add a new class");
            System.out.println("Please press 3 to add a new trainer");
            System.out.println("Please press 4 to add a new membership");
             System.out.println("");
            System.out.println("Please press 5 to enrol a member in a class");
            System.out.println("Please press 6 to unenrol a member from a class");
             System.out.println("");
            System.out.println("Please press 7 to view all membership details");
            System.out.println("Please press 8 to view all members");
            System.out.println("Please press 9 to view all trainers");
            System.out.println("Please press 10 to view all classes");

            System.out.println("Please press 11 to view all members in a class");
            System.out.println("Please press 12 to view all classes for a member");
            System.out.println("Please press 13 to view all classes for a trainer");
             System.out.println("");
            System.out.println("Please press 14 to delete a member ");
            System.out.println("Please press 15 to delete a class");
            System.out.println("Please press 16 to delete a trainer");
            System.out.println("Please press 17 to delete a membership");

            System.out.println("Please press 18 too search classes by day");
            
            System.out.println("Press 19 to quit");

            int choice = in.nextInt();
            in.nextLine();
            
             switch (choice) {
                case 1:
                    System.out.println("please enter the first name of the member "
                            + "you wish to add");
                    String firstName = in.nextLine();
                    
                    System.out.println("please enter the last name of the member "
                            + "you wish to add");
                    String lastName = in.nextLine();
                    
                    System.out.println("please enter the gender of the member "
                            + "you wish to add");
                    String gender = in.nextLine();
                    
                    System.out.println("please enter the phone of the member "
                            + "you wish to add");
                    String phone = in.nextLine();
                    
                    System.out.println("please enter the email of the member "
                            + "you wish to add");
                    String email = in.nextLine();                 
                    
                    System.out.println("please enter the address of the member "
                            + "you wish to add");
                    String address = in.nextLine();               
                    
                    System.out.println("please enter the year of birth of the member "
                            + "you wish to add");
                    int year = in.nextInt();
                    in.nextLine();
                    
                    System.out.println("please enter the month of birth of the member "
                            + "you wish to add");
                    int month = in.nextInt();
                    in.nextLine();                  
                    
                    System.out.println("please enter the day birth of the member "
                            + "you wish to add");
                    int day = in.nextInt();
                    in.nextLine();

                    System.out.println("please enter the medical conditions of the member "
                            + "you wish to add");
                    String medicalConditions = in.nextLine();
                    
                    System.out.println("Please enter the id of the membership type for the member");
                    System.out.println("Gym only ID: 0");
                    int mshipID = in.nextInt();
                    in.nextLine();

                    Calendar dob = Calendar.getInstance();
                    dob.set(year, month, day);
                    
                    po.addMember(firstName, lastName, gender, phone, email, address, dob, medicalConditions, mshipID);
                          
                    
                    break;
                case 2:
                    System.out.println("please enter the name of the class you wish to add");
                    String name = in.nextLine();
                    System.out.println("Please enter the day of the class is running on");
                    String classDay = in.nextLine();
                    System.out.println("Please enter the time of the class");
                    String classTime = in.nextLine();
                    System.out.println("Please enter the id of the trainer for this class");
                    int trainerID = in.nextInt();
                    in.nextLine();
                    
                    po.addClass(name, classDay, classTime, trainerID);
                    
                    break;
                case 3:
                    System.out.println("please enter the first name of the trainer you wish to add");
                     firstName = in.nextLine();
                     
                    System.out.println("please enter the last name of the trainer you wish to add");
                     lastName = in.nextLine();
                    
                    System.out.println("please enter the phone of the trainer you wish to add");
                     phone = in.nextLine();
                    
                    System.out.println("please enter the email of the trainer you wish to add");
                     email = in.nextLine();
                    
                    po.addTrainer(firstName, lastName, phone, email);
                    break;
                case 4:
                    System.out.println("please enter the type of the membership you wish to add");
                     String mshipType = in.nextLine();
                     
                    System.out.println("please enter the price of the membership you wish to add");
                     double price = in.nextDouble();
                    in.nextLine();
                    
                    System.out.println("please enter the duration of the membership you wish to add");
                     String duration = in.nextLine();
                    
                    po.addMembership(mshipType, price, duration);
                    break;
                case 5:
                    System.out.println("Please enter the member id");
                    int memberID = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the class id");
                    int classID = in.nextInt();
                    in.nextLine();
                    po.enrolClass(memberID, classID);
                    break;
                case 6:
                    System.out.println("Please enter the member id");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the class id");
                    classID = in.nextInt();
                    in.nextLine();
                    po.unenrolClass(id, classID);
                    break;
                case 7:
                    po.viewMemberships();
                    break;
                case 8:
                    po.viewMembers();
                    break;
                case 9:
                    po.viewTrainers();
                    break;
                case 10:
                    po.viewClasses();
                    break;
                case 11:
                    System.out.println("please enter the id of the class to view the"
                            + " list of members enrolled");
                    classID = in.nextInt();
                    in.nextLine();
                    po.viewClassMembers(classID);
                    break;
                case 12:
                    System.out.println("please enter the id of the member whose "
                            + "classes you wish to view");
                    memberID = in.nextInt();
                    in.nextLine();
                    po.viewMemberClasses(memberID);
                    break;
                case 13:
                    System.out.println("please enter the id of the trainer whose "
                            + "classes you wish to view");
                    trainerID = in.nextInt();
                    in.nextLine();
                    po.viewTrainerClasses(trainerID);
                    break;
                case 14:
                    System.out.println("Please enter the id of the member you wish to delete");
                    id = in.nextInt();
                    in.nextLine();
                    Member member = po.findMember(id);
                    if (member != null) {
                        po.deleteMember(id);
                    }
                    break;
                case 15:
                    System.out.println("Please enter the id of the class you wish to delete");
                    classID = in.nextInt();
                    in.nextLine();
                    Class c = po.findClass(classID);
                    if (c != null) {
                        po.deleteClass(classID);
                    }
                    break;
                case 16:
                    System.out.println("Please enter the id of the trainer you wish to delete");
                    trainerID = in.nextInt();
                    in.nextLine();
                    Trainer t = po.findTrainer(trainerID);
                    if (t != null) {
                        po.deleteTrainer(trainerID);
                    }
                    break;
                case 17:
                    System.out.println("Please enter the id of the membership you wish to delete");
                    mshipID = in.nextInt();
                    in.nextLine();
                    Membership m = po.findMembership(mshipID);
                    if (m != null) {
                        po.deleteMembership(mshipID);
                    }
                    break;

                case 18:
                    System.out.println("Please enter the name of the day you wish to view the classes on");
                    classDay = in.nextLine();
                    po.whichClassWhichDay(classDay);
                    break;
                    
                case 19:
                    po.close();
                    mo.closeDB();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
         
    }
    
    
}
