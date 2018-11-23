package model;

import db.ContactOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddressBook {

    private int id;
    private String owner;
    private ArrayList<Contact> clist;
    private ResultSet rset;
    private ContactOperations co;

    public AddressBook(int id,String o, ContactOperations co) {       
        this.id = id;
        owner = o;     
        this.co = co;
        clist = new ArrayList<>();
    }

    public void fillList(String owner) {
        rset = co.getContacts(owner);
        try {
            while (rset.next()) {
                Contact c = new Contact(rset.getInt(1), rset.getString(2),
                        rset.getString(3), rset.getString(4), rset.getString(5));
                clist.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Problem in fillList() method "+e.getMessage());
        } 
    }

    public void addContact(String owner)
    {
        rset = co.getContacts(owner);

        try {
            if (rset.last()) {
                Contact c = new Contact(rset.getInt(1), rset.getString(2),
                        rset.getString(3), rset.getString(4), rset.getString(5));
                clist.add(c);
                
            }

        } catch (SQLException e) {
            System.out.println("Problem in addContact() method "+e.getMessage());
        } 
    }
    public Contact getContact(int i) {
        return clist.get(i);
    }

    public String getOwner() {
        return owner;
    }


    public int findContact(int id) {
        int index = -1;
        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).getID() == id) {
                index = i;
            }
        }
        return index;
    }

    public void updateContact(int id, String newnum) {
        int val = findContact(id);
        if (val != -1) {
            clist.get(val).setPhoneNumber(newnum);
        }
    }

    public void removeContact(int id) {
        int val = findContact(id);
        if (val != -1) {
            clist.remove(val);
        }
    }

    public void showContacts() {
        for (int i = 0; i < clist.size(); i++) {
            System.out.printf("%-2d %-10s %-30s %-15s%-15s\n",
                    clist.get(i).getID(), clist.get(i).getName(), 
                    clist.get(i).getAddress(), clist.get(i).getPhoneNumber(), 
                    clist.get(i).getEmail());
        }
    }
}