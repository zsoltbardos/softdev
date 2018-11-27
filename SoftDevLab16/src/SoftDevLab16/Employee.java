/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftDevLab16;

/**
 *
 * @author Zsolti
 */
public class Employee implements IPayable {
    private String firstName;
    private String lastName;
    private String rsiNum;
    

    
    private static int totalEmployees = 0;

    public Employee(String firstName, String lastName, String rsiNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rsiNum = rsiNum;
        Employee.totalEmployees += 1;
    }

    public Employee() {
    }
    
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRsiNum() {
        return rsiNum;
    }

    public void setRsiNum(String rsiNum) {
        this.rsiNum = rsiNum;
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }    
    

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + ", rsiNum=" + rsiNum + '}';
    }
    
    @Override
    public double getPaymentAmount(){
        return 0;
    }
    
    
}
