/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.StudentOperations;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class University {

    private String name;
    private ArrayList<Student> slist;
    private ResultSet rset;
    private StudentOperations eo;

    public University(String o, StudentOperations co) {
        this.eo = co;
        name = o;
        slist = new ArrayList<>();
    }

   

    public void removeStudent(int id) {
        int val = findStudent(id);
        if (val == -1) {
            System.out.println("Sorry record not found");
        } else {
            slist.remove(val);
            eo.deleteRecord(id);

        }
    }

    

    public Student getStudent(int i) {
        return slist.get(i);
    }


    public int findStudent(int id) {
        int index = -1;
        for (int i = 0; i < slist.size(); i++) {
            if (slist.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }

    public void showStudents() {
        System.out.printf("%10s%20s%25s%25s%n", "Student ID", "Name", "Date Of Birth", "Registration Date");
        for (int i = 0; i < slist.size(); i++) {
            System.out.println(slist.get(i));
        }
    }
    
    public void showStudentsAges(){
        System.out.printf("%20s%20s%n", "Student Name", "Student Age");
        for (int i = 0; i < slist.size(); i++) {
            int age = slist.get(i).calcAge(slist.get(i).getDob());
            System.out.printf("%20s%20s%n",slist.get(i).getName(),age);
        }
        
    }
    
    public void fillList(){
        rset = eo.getStudents();
        try {
            while (rset.next()){
                
                Calendar dob = Calendar.getInstance();
                dob.setTime(rset.getDate(3));
                
                Calendar regDate = Calendar.getInstance();
                regDate.setTime(rset.getDate(4));
                
                Student student = new Student(rset.getInt(1), rset.getString(2), dob, regDate);
                slist.add(student);
            }
        } catch (SQLException e){
            System.out.println("ERROR in fillList() method: " + e);
        }
    }
    
    public void addStudent() {
        rset = eo.getMaxStudent();

        try {
            if (rset.next()) {
                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(rset.getDate(3));
                Calendar regDate = Calendar.getInstance();
                regDate.setTime(rset.getDate(4));
                Student student = new Student(rset.getInt(1), rset.getString(2),
                        birthDate, regDate);
                slist.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error in addEmployee() method" + e.getMessage());
        }
    }
    
     public void showLateRegisteredStudents(){
        Calendar lastDayOfReg = Calendar.getInstance();
        lastDayOfReg.set(2018, 8, 14);
        
        System.out.printf("%20s%n", "Late Registrations");
        
        for (int i = 0; i < slist.size(); i++) {
            if (slist.get(i).getRdate().getTimeInMillis() > lastDayOfReg.getTimeInMillis()) {
                System.out.printf("%20s%n",slist.get(i).getName());
            }
            
        }
    }
    
  

}
