/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.EmployeeOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Company {

    private String name;
    private ArrayList<Employee> elist;
    private ResultSet rset;
    private EmployeeOperations eo;

    public Company(String o, EmployeeOperations co) {
        this.eo = co;
        name = o;
        elist = new ArrayList<>();
    }

    public void fillList() {
        rset = eo.getEmployees();

        try {
            while (rset.next()) {
                Calendar sdate = Calendar.getInstance();
                sdate.setTime(rset.getDate(3));
                Calendar dob = Calendar.getInstance();
                dob.setTime(rset.getDate(4));
                Employee e = new Employee(rset.getInt(1), rset.getString(2),
                        sdate,dob,rset.getDouble(5));
                elist.add(e);

            }

        } catch (SQLException e) {
            System.out.println("Error in fillList() method" + e.getMessage());
        }
    }

    public void updateEmployee(int id, double newSal) {
        for (int i = 0; i < elist.size(); i++) {
            if (elist.get(i).getId() == id) {
                elist.get(i).setSalary(newSal);
                eo.updateRecord(id, newSal);
            }
        }
    }

    public void removeEmployee(int id) {
        int val = findEmployee(id);
        if (val == -1) {
            System.out.println("Sorry record not found");
        } else {
            elist.remove(val);
            eo.deleteRecord(id);

        }
    }

    public void addEmployee() {
        rset = eo.getMaxEmployee();

        try {
            if (rset.next()) {
                Calendar sdate = Calendar.getInstance();
                sdate.setTime(rset.getDate(3));
                Calendar dob = Calendar.getInstance();
                dob.setTime(rset.getDate(4));
                Employee e = new Employee(rset.getInt(1), rset.getString(2),
                        sdate, dob,rset.getDouble(5));
                elist.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error in addEmployee() method" + e.getMessage());
        }
    }

    public Employee getEmployee(int i) {
        return elist.get(i);
    }


    public int findEmployee(int id) {
        int index = -1;
        for (int i = 0; i < elist.size(); i++) {
            if (elist.get(i).getId() == id) {
                index = i;
            }
        }
        return index;
    }

    public void showEmployees() {
        System.out.printf("%10s%20s%20s%25s%10s%n", "Employee ID", "Name", 
                "Start Date", "Date Of Birth", "Salary");
        for (int i = 0; i < elist.size(); i++) {
            System.out.println(elist.get(i));
        }
    }
    public void showEmployeeAges(){
        System.out.printf("%20s%20s%n", "Employee Name", "Employee Age");
        for (int i = 0; i < elist.size(); i++) {
            int age = elist.get(i).calcAge(elist.get(i).getDob());
            System.out.printf("%20s%20s%n",elist.get(i).getName(),age);
        }
        
    }
     public void showLongestServingEmployee(){ 
        int minIndex=0;
        long min = Long.MAX_VALUE; 
        for (int i = 0; i < elist.size(); i++) {
            long millisRelease = elist.get(i).getStartdate().getTimeInMillis();
            if (millisRelease < min) {
                min = elist.get(i).getStartdate().getTimeInMillis();
                minIndex = i;
            }
        }
        System.out.printf("%20s%20s%n", "Employee Name", "Start Date");
        System.out.printf("%1$20s %2$12tb %2$td %2$tY%n",
                elist.get(minIndex).getName(),
                elist.get(minIndex).getStartdate());
    }

}
