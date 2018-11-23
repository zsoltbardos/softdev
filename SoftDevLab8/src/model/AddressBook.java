package model;

import db.ContactOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void fillList() {
        ResultSet rs = co.getContacts();
        try {
            while (rs.next()) {
                Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                clist.add(contact);
            }
        } catch (SQLException e){
            System.out.println("Error in fillList() method: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    public void updateContact(int id, String newNum) {
        if (findContact(id)) {
            for (int i = 0; i < clist.size(); i++) {
                if (clist.get(i).getID()==id){
                    clist.get(i).setPhoneNumber(newNum);
                    co.updateRecord(id, newNum);
                }
            }
        }
    }


    
    
    

    public void removeContact(int id) {
        if (findContact(id)) {
            for (int i = 0; i < clist.size(); i++) {
                if (clist.get(i).getID()==id){
                    clist.remove(i);
                    co.deleteRecord(id);
                }
            }           
        }
    }

    
    
    
    
    
    public void addContact() {
        ResultSet rs = co.getMaxContact();
        try {
            if (rs.next()) {
                Contact contact = new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                clist.add(contact);
            }
        } catch (SQLException e){
            System.out.println("ERROR in addContact() method: " + e.getMessage());
        }
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
                    clist.get(i).getID(), 
                    clist.get(i).getName(), 
                    clist.get(i).getAddress(), 
                    clist.get(i).getPhoneNumber(), 
                    clist.get(i).getEmail());
        }
    }
    
    public boolean findContact(int id) {
        boolean found = false;
        for (int i = 0; i < clist.size(); i++) {
            if (clist.get(i).getID() == id) {
                found = true;
            }
        }
        return found;
    }
}
