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
public class Comission extends Hourly {
    private double totalSales;
    private double commissionRate;

    public Comission(String name, String address, String phoneNumber, String rsiNumber, double payRate, double commissionRate) {
        super(name, address, phoneNumber, rsiNumber, payRate);
        this.totalSales = 0;
        this.commissionRate = commissionRate;
    }


    
    public void addSales(double sale){
        this.totalSales += sale;
    }
    
    @Override
    public double pay(){
        return super.pay() + (totalSales * commissionRate);
    }


    @Override
    public String toString() {
        return "Name: " + name + "\n" + 
                "Address: " + address + "\n" + 
                "Phone: " + phoneNumber + "\n" +
                "RSI Number: " + getRsiNumber() + "\n" +
                "Current hours: " + getHoursWorked() + "\n" +
                "Total sales: €" + totalSales + "\n" +
                "Paid: €" + pay() + "\n" +
                "-----------------------------------";
    }
    
    
    
    
    
}


