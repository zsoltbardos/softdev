/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Inheritance( strategy = InheritanceType.JOINED)
@SequenceGenerator(name="did_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")

public class Contact {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cid_seq")
    
    private int cid;
    private String cname;
    private String address;
    private String phone;
    private String email;
    private int eid;
    @ManyToOne()
    @JoinColumn(name="empid")
    private Employee emp;

    
    public Contact() {       
    }

    public Contact(int cid, String cname, String address, String phone, String email, int eid) {
        this.cid = cid;
        this.cname = cname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.eid = eid;
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

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Contact{" + "cid=" + cid + ", cname=" + cname + ", address=" + address + ", phone=" + phone + ", email=" + email + ", eid=" + eid + ", emp=" + emp + '}';
    }
    

    
}
