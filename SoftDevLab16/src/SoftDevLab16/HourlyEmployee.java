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
public class HourlyEmployee extends Employee{
    private double hoursWorked;
    private double rateOfPay;
    
    private static double totalTaxPaid;

    public HourlyEmployee(double hoursWorked, double rateOfPay, String firstName, String lastName, String rsiNum) {
        super(firstName, lastName, rsiNum);
        this.hoursWorked = hoursWorked;
        this.rateOfPay = rateOfPay;
        HourlyEmployee.totalTaxPaid += calculateTaxPaid();
    }

    public HourlyEmployee(double hoursWorked, double rateOfPay) {
        this.hoursWorked = hoursWorked;
        this.rateOfPay = rateOfPay;
    }

    public static double getTotalTaxPaid() {
        return totalTaxPaid;
    }
    
    

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getRateOfPay() {
        return rateOfPay;
    }

    public void setRateOfPay(double rateOfPay) {
        this.rateOfPay = rateOfPay;
    }

    @Override
    public String toString() {
       
        String formattedString = String.format("Employee %n First Name: %1$s%n "
                + "Last Name: %2$s%n "
                + "RSI Number: %3$s%n "
                + "%1$s %2$s is an hourly employee %n "
                ,getFirstName(),getLastName(),getRsiNum());
        return formattedString;
    }
    
    public final double calculateTaxPaid(){
        double payment = hoursWorked * rateOfPay;
        double taxPaid = 0;
        double first300tax;
        double remainderTax;
        if ((payment > 200) & (payment <= 300)){
            taxPaid = (payment * (lowTaxRate * 0.01));
        } else if (payment > 300) {
            first300tax = (300 * (lowTaxRate * 0.01));
            
            remainderTax = ((payment - 300) * (highTaxRate * 0.01));
            taxPaid = first300tax + remainderTax;
        }
        return taxPaid;
    }
    
    @Override
    public double getPaymentAmount(){
        double payment = hoursWorked * rateOfPay;
        if ((payment > 200) & (payment <= 300)){
            payment = payment - calculateTaxPaid();
        } else if (payment > 300) {
            payment = payment - calculateTaxPaid();
        }
        return payment;
    }
}
