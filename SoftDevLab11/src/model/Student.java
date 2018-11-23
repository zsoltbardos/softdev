/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.time.*;
import java.util.Calendar;

public class Student {

    private int id;
    private String name;
    private Calendar dob;
    private Calendar rdate;

    public Student(int id, String name, Calendar startdate, Calendar rdate) {
        this.id = id;
        this.name = name;
        this.dob = startdate;
        this.rdate = rdate;
    }

    public Student(int id) {
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

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public Calendar getRdate() {
        return rdate;
    }

    public void setRdate(Calendar rdate) {
        this.rdate = rdate;
    }

    @Override
    public String toString() {

        String d = String.format("%1$10d "
                + "%2$20s "
                + "%3$15tb %3$td %3$tY "
                + "%4$15tb %4$td %4$tY ",
                 id, name, dob, rdate);
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
    

