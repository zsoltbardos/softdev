/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import db.PersistenceOperations;
import db.SCOperations;
import java.util.Scanner;
import model.Contact;
import model.Staff;

/**
 *
 * @author furry
 */
public class TestStaff {
    public static void main(String[] args) {
         Scanner in = new Scanner(System.in);

        SCOperations sco = new SCOperations();
        sco.openDB();
        sco.dropContactSequence();
        sco.dropStaffSequence();
        sco.dropContactTable();
        sco.dropStaffTable();
        
        sco.createContactSequence();
        sco.createStaffSequence();
        sco.createStafftable();
        sco.createContactstable();
        
        

        PersistenceOperations po = new PersistenceOperations();

        while (true) {
            System.out.println("Please press 1 to add a new staff member");
            System.out.println("Please press 2 to add a new contact for a staff member");
            System.out.println("Please press 3 to view contacts of a staff member");
            System.out.println("Please press 4 to delete a contact");
            System.out.println("Please press 5 to delete a staff member");
            System.out.println("Press 6 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Please enter the name of the staff member");
                    String name = in.nextLine();
                    po.addStaff(name);
                    break;
                case 2:
                    System.out.println("Please enter the id of the staff member for "
                            + "who you wish to add a new contact");
                    int id = in.nextInt();
                    in.nextLine();
                    Staff s = po.findStaff(id);
                    if(s!=null){ 
                        System.out.println(s.getName());
                        System.out.println("Please enter the name of the new contact");
                        String cname = in.nextLine();
                        System.out.println("Please enter the address of the new contact");
                        String caddress = in.nextLine();
                        System.out.println("Please enter the phone of the new contact");
                        String cphone = in.nextLine();
                        System.out.println("Please enter the email of the new contact");
                        String cemail = in.nextLine();
                        Contact c = new Contact(cname,caddress,cphone,cemail);
                        po.addContact(s,c);
                    }
                    break;                   
                case 3:
                    System.out.println("Please enter the id of the staff member whose "
                            + "contacts you wish to view");
                    int sid = in.nextInt();
                    in.nextLine();
                    s = po.findStaff(sid);
                    if(s!=null){ 
                    po.showContacts(sid);
                    }
                    break;
                case 4:
                    System.out.println("Please enter the id of the staff member whose "
                            + "contacts you wish to delete");
                    sid = in.nextInt();
                    in.nextLine();
                    s = po.findStaff(sid);
                    if(s!=null){ 
                    System.out.println("Please enter the id of the contact you wish "
                            + "to delete");
                    int cid = in.nextInt();
                    in.nextLine();
                    po.deleteContact(cid, sid);
                    }                    
                    break;
                case 5:
                    System.out.println("Please enter the id of the staff member you wish to delete");
                    sid = in.nextInt();
                    in.nextLine();
                    s = po.findStaff(sid);
                    if(s!=null){ 
                    po.deleteStaff(sid);
                    }                   
                    break;
                case 6:
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
