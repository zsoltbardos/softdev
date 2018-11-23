package ex1;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByID", query = "SELECT c FROM Contact c WHERE c.id <=:id")})

@SequenceGenerator(name="c_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Contact {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="c_seq")
    private int id;
    private String name;
    private String address;
    private String pnumber;
    private String email;

    public Contact() {
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPnumber() {
        return pnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    



    @Override
    public String toString() {
        return "Contact: " + "Contact id: " + id + " Name: " + name + " Address: " + address + " Phone: "+ pnumber + " Email: " + email;
    }
    
}
