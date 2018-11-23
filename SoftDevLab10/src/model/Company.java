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
                Calendar cal = Calendar.getInstance();
                cal.setTime(rset.getDate(3));
                Employee e = new Employee(rset.getInt(1), rset.getString(2),
                        cal, rset.getDouble(4));
                elist.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error in addContact() method" + e.getMessage());
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
        for (int i = 0; i < elist.size(); i++) {
            System.out.println(elist.get(i));
        }
    }
    
    public void fillList() {
        rset = eo.getEmployees();
        
        try {
            while(rset.next()){
                Calendar cal = Calendar.getInstance();
                cal.setTime(rset.getDate(3));
                Employee e = new Employee(rset.getInt(1), rset.getString(2), cal, rset.getDouble(4));
                elist.add(e);
            }
        } catch (SQLException e){
            System.out.println("Error in fillList() method: " + e.getMessage());
        }
    }

}
