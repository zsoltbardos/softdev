/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class Employee {
    
    private int id;
    private String name;
    private Calendar startdate;
    private Calendar dob;
    private double salary;

    public Employee(int id, String name, Calendar startdate, 
            Calendar dob,double sal) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
        this.dob = dob;
        this.salary = sal;
    }

    public Employee(int id) {
        this.id = id;
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

    public Calendar getStartdate() {
        return startdate;
    }

    public void setStartdate(Calendar startdate) {
        this.startdate = startdate;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
    @Override
   public String toString() {
       
       String d = String.format("%1$10d "
               + "%2$20s"
               + "%3$15tb %3$td %3$tY "
               + "%4$12tb %4$td %4$tY "
               + "%5$,12.2f€"
               ,id,name,startdate,dob,salary);
        return d;
   }
    public int calcAge(Calendar dob) {
        int age;
        LocalDate birthday = LocalDate.of(dob.get(Calendar.YEAR), 
                Calendar.MONTH, Calendar.DAY_OF_MONTH);
        LocalDate now = LocalDate.now();

        Period period = Period.between(birthday, now);
        age = period.getYears();
        return age;
    }

}

    

