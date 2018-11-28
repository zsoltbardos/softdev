package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author x00157506
 */
@Entity
@Table(name = "Contact")
@SequenceGenerator(name="cid_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")

public class Contact {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cid_seq")   
    private int cid;
    private String cname;
    private String address;
    private String phone;
    private String email;
    @ManyToOne()
    @JoinColumn(name="emp_id")
    private Employee emp;

    
    public Contact() {       
    }

    public Contact(String cname, String address, String phone, String email) {
        this.cname = cname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    
    @Override
    public String toString() {
        return String.format("Name: %10s Contact ID: %2d Address: %-15s "
                + "Phone Number: %15s Email: %10s ",cname,cid,address,phone,email);
    }
    

    
}
