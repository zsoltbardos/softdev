/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;

public class Employee {
    
    private int id;
    private String name;
    private Calendar startdate;
    private double salary;

    public Employee(int id, String name, Calendar startdate, double sal) {
        this.id = id;
        this.name = name;
        this.startdate = startdate;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
    @Override
   public String toString() {
       
       String d = String.format("Employee Id: %1$d "
               + "Name: %2$10s, "
               + "Start Date: %3$tb %3$td %3$tY, "
               + "Salary: €%4$,.2f"
               ,id,name,startdate,salary);
        return d;
   }
}

    

