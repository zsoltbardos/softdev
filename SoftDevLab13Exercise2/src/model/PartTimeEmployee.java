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
@DiscriminatorValue (value ="PT")
@PrimaryKeyJoinColumn(referencedColumnName="empid")

@SuppressWarnings("SerializableClass")
public class PartTimeEmployee extends Employee {
    private double hours;
    private double rate;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, Calendar startDate, double rate, double hours) {
        super(name, startDate);
        this.rate = rate;
        this.hours = hours;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
    
    @Override
    public String toString() {
        return super.toString() + "  PartTimeEmployee hours: " + hours + ", Rate: " + rate;
    }

}

