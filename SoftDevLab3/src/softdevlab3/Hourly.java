/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab3;

/**
 *
 * @author x00157506
 */
public class Hourly extends Employee{
    private int hoursWorked;

    public Hourly(String name, String address, String phoneNumber, String rsiNumber, double payRate) {
        super(name, address, phoneNumber, rsiNumber, payRate);
        this.hoursWorked = 0;
    }
    
    public void addHours(int hours){
        this.hoursWorked += hours;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
    
    
    
    @Override
    public double pay() {
        if (hoursWorked <= 40){
            return hoursWorked * payRate;
        } else {
            return 40 * payRate + ((hoursWorked - 40) * payRate * 1.5);
        }

    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + 
                "Address: " + address + "\n" + 
                "Phone: " + phoneNumber + "\n" +
                "RSI Number: " + getRsiNumber() + "\n" +
                "Current hours: " + hoursWorked + "\n" +
                "Paid: €" + pay() + "\n" +
                "-----------------------------------";
    }
    
    
    
}
