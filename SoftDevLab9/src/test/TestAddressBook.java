package test;

import db.ContactOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.AddressBook;

public class TestAddressBook {

    public static void main(String[] args) {
        ContactOperations co = new ContactOperations();
        co.openDB();
        co.dropContactSequence();
        co.dropAddressBookOwnerSequence();

        co.dropContactsAddressBookTable();
        co.dropAddressBookOwnersTable();
        co.dropContactsTable();

        co.createContactSequence();
        co.createAddressBookOwnersSequence();

        co.CreateContactsTable();
        co.createAddressBookOwnerstable();
        co.createContactsAddressBook();

        ArrayList<AddressBook> abList = new ArrayList<>();

        ResultSet rset = co.getOwners();
        try {
            while (rset.next()) {
                abList.add(new AddressBook(rset.getInt(1), rset.getString(2), co));
            }
        } catch (SQLException ex) {
            System.out.println("Problem with result set "+ex.getMessage());
        }
        for (int i = 0; i < abList.size(); i++) {
            abList.get(i).fillList(abList.get(i).getOwner());
        }

        displayMenu1(abList, co);
    }

    public static void displayMenu1(ArrayList<AddressBook> abList, ContactOperations co) {
        boolean found = false;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please press 1 to view address book owner contacts");
            System.out.println("Please press 2 to exit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Please enter the name of the address book owner whose contacts you wish to view");
                    String owner = in.nextLine();
                    for (int i = 0; i < abList.size(); i++) {
                        if (abList.get(i).getOwner().equals(owner)) {
                            displayMenu2(abList.get(i), co, abList, owner);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Address Book owner not found");
                    }

                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }

    public static void displayMenu2(AddressBook ab, ContactOperations co, ArrayList<AddressBook> abList, String owner) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Please press 1 to view contact details");
            System.out.println("Please press 2 if you want to update a phone number");
            System.out.println("Please press 3 to delete a contact");
            System.out.println("Please press 4 to add a new contact");
            System.out.println("Please press 5 to go back to main menu");
            System.out.println("Press 6 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    ab.showContacts();
                    break;
                case 2:
                    System.out.println("please enter the id of the contact you wish to update");
                    int id = in.nextInt();
                    in.nextLine();
                    if (ab.findContact(id) == -1) {
                        System.out.println("Record not found");
                    } else {
                        System.out.println("Please enter the new phone number for " + id);
                        String newNum = in.nextLine();
                        for (int i = 0; i < abList.size(); i++) {
                            abList.get(i).updateContact(id, newNum);
                        }
                        co.updateRecord(id, newNum);
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the contact you wish to delete");
                    int idDelete = in.nextInt();
                    if (ab.findContact(idDelete) == -1) {
                        System.out.println("Record not found");
                    } else {
                        for (int i = 0; i < abList.size(); i++) {
                            abList.get(i).removeContact(idDelete);
                        }
                        co.deleteRecord(idDelete);
                    }
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
                    co.add(nameAdd, addAdd, numAdd,emailAdd);
                    co.addToLinkTable(owner);
                    ab.addContact(ab.getOwner());
                    break;
                case 5:
                    displayMenu1(abList, co);
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