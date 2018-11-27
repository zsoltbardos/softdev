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

@Entity
@Table(name = "STUDENT")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")

@SequenceGenerator(name = "sid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sid_seq")
    private int sid;
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar dob;
    private String course;

    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STUDENTMODULE",
            joinColumns = @JoinColumn(name = "SID"),
            inverseJoinColumns = @JoinColumn(name = "MID"))
    private List<Module> mlist = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="addressID")
    private Address address;
    
    
    @ManyToOne()
    @JoinColumn(name="DEPID")
    private Department dep;



    public Student() {
    }

    public Student(String name, Calendar dob, String course) {
        this.name = name;
        this.dob = dob;
        this.course = course;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Module> getMlist() {
        return mlist;
    }

    public void setMlist(List<Module> mlist) {
        this.mlist = mlist;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }
    
    
    @Override
    public String toString() {
        String s = String.format(" Student Id: %1$10d"
                + "Name: %2$-10s "
                + "DOB: %3$8tb %3$td %3$tY"
                + "%4$4s", 
                sid, name, dob, course);

        return s;
    }
    
    public void printModules(){
        System.out.println("Modules for: "+name);
        for (int i = 0; i < mlist.size(); i++) {
            System.out.println( mlist.get(i));
        }

    }

}
