/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author x00157506
 */

@Entity
@Table (name = "Employee13EX1")
@Inheritance( strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type" )

@SequenceGenerator (name = "eid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")
        
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eid_seq")
    private int id;
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    public Employee() {
    }

    public Employee(String name, Calendar startDate) {
        this.name = name;
        this.startDate = startDate;
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
                + "Start Date: %3$8td %3$tB %3$tY   ",id,name,startDate);
    }

}
