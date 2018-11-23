package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@DiscriminatorValue( value="FT" )
@PrimaryKeyJoinColumn(referencedColumnName="empid")

@SuppressWarnings("SerializableClass")
public class FullTimeEmployee extends Employee {

    private double salary;

    public FullTimeEmployee() {
       
    }
    public double getSalary() {
        return salary;
    }
    public FullTimeEmployee(String name, Calendar startdate,double salary) {
        super(name, startdate);
        this.salary = salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString()+ "  FullTimeEmployee " + "Salary: " + salary;
    }



}
