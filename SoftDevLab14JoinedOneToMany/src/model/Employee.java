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
@Table (name = "EMPLOYEE14")

@DiscriminatorColumn(name = "type" )

@SequenceGenerator (name = "eid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")
        
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eid_seq")
    private int empid;
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    
    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL)
    private List<Contact> clist = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, Calendar startDate) {
        this.name = name;
        this.startDate = startDate;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public String toString() {
        return String.format(" Employee Id: %2d Name: %-10s "
                + "Start Date: %3$8td %3$tB %3$tY   ",empid,name,startDate);
    }

}
