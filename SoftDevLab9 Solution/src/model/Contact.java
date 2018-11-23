package model;

public class Contact {

    private int id;
    private String name;         // Person's name
    private String address;
    private String phoneNumber;  // Person's phone number
    private String email;

    public Contact(String n, String a, String pn, String e) {
        name = n;
        address = a;
        phoneNumber = pn;
        email = e;
    }

    public Contact(int id, String n, String a, String pn, String e) {
        this.id = id;
        name = n;
        address = a;
        phoneNumber = pn;
        email = e;
    }

    public void setName(String n) {
        name = n;
    }

    public void setAddress(String a) {
        address = a;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String pn) {
        phoneNumber = pn;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
