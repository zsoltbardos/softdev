package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@DiscriminatorValue("PT")
@PrimaryKeyJoinColumn(referencedColumnName = "empid")

@SuppressWarnings("SerializableClass")

public class PartTimeEmployee extends Employee {

    private double hours;
    private double rate;

    public PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, Calendar startdate,double rate,double hours) {
        super(name, startdate);
        this.hours = hours;
        this.rate = rate;
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
