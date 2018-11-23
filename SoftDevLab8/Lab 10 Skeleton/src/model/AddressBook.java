package model;

import db.ContactOperations;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AddressBook {

    private String owner;
    private ArrayList<Contact> clist;
    private ResultSet rset;
    private ContactOperations co;

    public AddressBook(String o, ContactOperations co) {
        this.co = co;
        owner = o;
        clist = new ArrayList<>();
    }


    public Contact getContact(int i) {
        return clist.get(i);
    }

    public String getOwner() {
        return owner;
    }

    public int getNumContacts() {
        return clist.size();
    }


    
    

    public void showContacts() {
        for (int i = 0; i < clist.size(); i++) {
            System.out.printf("%-2d %-10s %-30s %-15s%-15s\n",
                    clist.get(i).getID(), clist.get(i).getName(), clist.get(i).getAddress(), 
                    clist.get(i).getPhoneNumber(), clist.get(i).getEmail());
        }
    }
}
