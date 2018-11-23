/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author X00157506
 */
public class Employee {
    private String name;
    private int yearsService;
    private int salary;
    
    public void Employee(String name, int yearsService, int salary) {
        this.name = name;
        this.yearsService = yearsService;
        this.salary = salary;
    }
    
    public void print(Employee a) {
        System.out.println(a.name + " " + a.yearsService + " " + a.salary);
    }
    
}
