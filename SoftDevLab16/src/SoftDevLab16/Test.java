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
public class Test {
    public static void main(String args[]) {
        IPayable[] items = new IPayable[5];
        
        items[0] = new Invoice("Printer", "12345", 375, 2);
        items[1] = new Invoice("Ink Cartridges", "56789", 79.95, 4);
        items[2] = new HourlyEmployee(60, 30, "John", "Smith", "6578431N");
        items[3] = new HourlyEmployee(20, 12, "Lisa", "Kelly", "9865431M");
        items[4] = new HourlyEmployee(20, 8, "Mary", "Reily", "9875431M");
        
        
        for(int i=0;i<items.length;i++){
            System.out.println("");
            System.out.printf(items[i].toString());
            if (items[i] instanceof Invoice){
                System.out.printf("Amount Due: €%.2f%n",items[i].getPaymentAmount());
            } else if (items[i] instanceof HourlyEmployee){
                System.out.printf("Weekly Salary: €%.2f%n",items[i].getPaymentAmount());
            }
        }
        System.out.println("");
        System.out.printf("The total number of employees processed is: %1$d %n", Employee.getTotalEmployees());
        System.out.printf("The total number of invoices processed is: %1$d %n", Invoice.getTotalInvoices());
        System.out.printf("The total amount of VAT paid is: €%1$.2f %n", Invoice.getTotalVatPaid());
        System.out.printf("The total amount of VAT paid is: €%1$.2f %n", HourlyEmployee.getTotalTaxPaid());

    }
}
