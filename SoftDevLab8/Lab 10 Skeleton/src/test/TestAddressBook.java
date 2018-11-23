package test;

import db.ContactOperations;
import java.util.Scanner;
import model.AddressBook;

public class TestAddressBook {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        ContactOperations co = new ContactOperations();
        co.openDB();
        co.dropContactsSequence();
        co.dropContactsTable();
        co.createContactsSequence();
        co.CreateContactsTable();

        AddressBook pb = new AddressBook("John", co);
        //pb.fillList();

        while (true) {
            System.out.println("Please press 1 to view contact details");
            System.out.println("Please press 2 if you want to update a phone number");
            System.out.println("Please press 3 to delete a contact");
            System.out.println("Please press 4 to add a new contact");
            System.out.println("Press 5 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    //pb.showContacts();
                    break;
                case 2:
                    System.out.println("please enter the id of the contact you wish to update");
                    int id = in.nextInt();
                    in.nextLine();
                    System.out.println("Please enter the new phone number for " + id);
                    String newNum = in.nextLine();
                    //pb.updateContact(id, newNum);
                    break;
                case 3:
                    System.out.println("please enter the id of the contact you wish to delete");
                    int idDelete = in.nextInt();
                    //pb.removeContact(idDelete);
                    break;
                case 4:
                    System.out.println("please enter the name of the contact you wish to add");
                    String nameAdd = in.nextLine();
                    System.out.println("please enter the address of the contact you wish to add");
                    String addAdd = in.nextLine();
                    System.out.println("please enter the email of the contact you wish to add");
                    String emailAdd = in.nextLine();
                    System.out.println("please enter the number of the contact you wish to add");
                    String numAdd = in.nextLine();
                    //co.add(nameAdd, addAdd, emailAdd, numAdd);
                    //pb.addContact();
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
