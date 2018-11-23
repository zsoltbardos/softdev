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
public class Employee extends StaffMember {
    private String rsiNumber;
    protected double payRate;

    public Employee(String name, String address, String phoneNumber, String rsiNumber, double payRate) {
        super(name, address, phoneNumber);
        this.rsiNumber = rsiNumber;
        this.payRate = payRate;
    }

    public String getRsiNumber() {
        return rsiNumber;
    }
    
    

    

    @Override
    public String toString() {
        return "Name: " + name + "\n" + 
                "Address: " + address + "\n" + 
                "Phone: " + phoneNumber + "\n" +
                "RSI Number: " + rsiNumber + "\n" +
                "Paid: €" + pay() + "\n" +
                "-----------------------------------";
    }
    
    @Override
    public double pay(){
        return payRate;
    }
    
    
}
