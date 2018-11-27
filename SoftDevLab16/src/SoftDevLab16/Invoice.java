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
public class Invoice implements IPayable {
    private String description;
    private String invoiceNum;
    private double price;
    private int quantity;
    private static int totalInvoices = 0;
    private static double totalVatPaid = 0;

    public Invoice(String description, String invoiceNum, double price, int quantity) {
        this.description = description;
        this.invoiceNum = invoiceNum;
        this.price = price;
        this.quantity = quantity;
        Invoice.totalInvoices += 1;
        Invoice.totalVatPaid += calculateVatPaid();
    }

    public Invoice() {
    }

    public static double getTotalVatPaid() {
        return totalVatPaid;
    }
    
    

    public static int getTotalInvoices() {
        return totalInvoices;
    }
    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public String toString() {
       
        String formattedString = String.format("Invoice %n Quantity: %1$d%n "
                + "Price Per Item: €%2$.2f%n "
                + "Part Number: %3$s%n "
                + "Description: %4$s%n "
                ,quantity,price,invoiceNum,description);
        return formattedString;
    }
    
    public final double calculateVatPaid(){
        double sum = price * quantity;
        double vatPaid = (vatRate * 0.01) * sum;
        
        return vatPaid;
    }
    
    public double getPaymentAmount(){
        double sum = price * quantity;
        double vatPaid = (vatRate * 0.01) * sum;
        
        return sum + vatPaid;
    }
}
