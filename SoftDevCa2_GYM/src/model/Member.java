/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author x00157506
 */

@Entity
@Table(name = "MEMBER")

@SequenceGenerator(name = "memid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memid_seq")
    private int mem_id;
    private String mem_first_name;
    private String mem_last_name;
    private String mem_gender;
    private String mem_phone;
    private String mem_email;
    private String mem_address;
    @Temporal(TemporalType.DATE)
    private Calendar mem_dob;
    private String mem_medical_con;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "MEMBER_CLASS",
            joinColumns = @JoinColumn(name = "mem_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id"))
    private List<Class> classList = new ArrayList<>();
    
    @ManyToOne()
    @JoinColumn(name="mship_id")
    private Membership mship;

    public Member() {
    }
    

    public Member(String mem_first_name, String mem_last_name, String mem_gender, String mem_phone, String mem_email, String mem_address, Calendar mem_dob, String mem_medical_con) {
        this.mem_first_name = mem_first_name;
        this.mem_last_name = mem_last_name;
        this.mem_gender = mem_gender;
        this.mem_phone = mem_phone;
        this.mem_email = mem_email;
        this.mem_address = mem_address;
        this.mem_dob = mem_dob;
        this.mem_medical_con = mem_medical_con;
    }

    public int getMem_id() {
        return mem_id;
    }

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_first_name() {
        return mem_first_name;
    }

    public void setMem_first_name(String mem_first_name) {
        this.mem_first_name = mem_first_name;
    }

    public String getMem_last_name() {
        return mem_last_name;
    }

    public void setMem_last_name(String mem_last_name) {
        this.mem_last_name = mem_last_name;
    }

    public String getMem_gender() {
        return mem_gender;
    }

    public void setMem_gender(String mem_gender) {
        this.mem_gender = mem_gender;
    }

    public String getMem_phone() {
        return mem_phone;
    }

    public void setMem_phone(String mem_phone) {
        this.mem_phone = mem_phone;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_address() {
        return mem_address;
    }

    public void setMem_address(String mem_address) {
        this.mem_address = mem_address;
    }

    public Calendar getMem_dob() {
        return mem_dob;
    }

    public void setMem_dob(Calendar mem_dob) {
        this.mem_dob = mem_dob;
    }

    public String getMem_medical_con() {
        return mem_medical_con;
    }

    public void setMem_medical_con(String mem_medical_con) {
        this.mem_medical_con = mem_medical_con;
    }

    public List<Class> getClassList() {
        return classList;
    }

    public void setMlist(List<Class> classList) {
        this.classList = classList;
    }

    public Membership getMship() {
        return mship;
    }

    public void setMship(Membership mship) {
        this.mship = mship;
    }
    
    public void printClasses(){
        System.out.println("Classes for: "+ mem_first_name + " " + mem_last_name);
        for (int i = 0; i < classList.size(); i++) {
            System.out.println( classList.get(i));
        }

    }
    
    
    @Override
    public String toString() {
        String s = String.format(" ID: %1$d, "
                + "Name: %2$s %3$s, "
                + "Gender: %4$s, "
                + "Phone: %5$s, "
                + "Email: %6$s, "
                + "Address: %7$s, "
                + "Date of birth: %8$tb %8$td %8$tY, "
                + "Medical conditions: %9$s %n"
                + "Membership type: %10$s %n"
                + "---------------------------- %n", 
                mem_id, mem_first_name, mem_last_name, mem_gender, mem_phone, mem_email, mem_address, mem_dob, mem_medical_con, mship.getMship_type());

        return s;
    }
    
    
    
    
    
}
