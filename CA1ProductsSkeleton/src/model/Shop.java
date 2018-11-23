package model;

import db.ProductOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Shop {

    private String name;
    private ArrayList<Product> pList;
    private ProductOperations po;
    private ResultSet rset;

    public Shop(String name, ProductOperations po) {
        this.name = name;
        this.po = po;
        pList = new ArrayList<>();
    }

    public void fillList() {
        rset = po.getProducts();
        try {
            while (rset.next()) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(rset.getDate(8));
                Product p = new Product(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getDouble(4),
                        rset.getInt(5), rset.getString(6), rset.getString(7),cal);
                pList.add(p);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void showProducts() {
        System.out.printf("%15s%25s%15s%15s%20s%10s%20s%20s%n", "Product Number", 
                "Name", "Brand", "Price", "Quantity\t", "Category", "Branch","Release Date");
        for (int i = 0; i < pList.size(); i++) {
            System.out.println(pList.get(i));
        }
    }

    public int findProduct(int id) {
        int index = -1;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getProductID() == id) {
                index = i;
            }
        }
        return index;
    }
    

   
    public void removeProduct(int pid) {
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getProductID() == pid) {
                pList.remove(i);
                po.deleteDBProduct(pid);
            }
        }
    }
    
    public void updateProductPrice(int id, double newPrice){
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getProductID() == id) {
                pList.get(i).setPrice(newPrice);
                po.updateRecord(id, newPrice);
            }
        }
    }
    
    public double calculateDiscount(double price, double discount){
       return price - (price * (discount/100));
    }
    
    public void applyDiscount(String brand, double discount){
        double discountedPrice;
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getBrand().equals(brand)) {
                discountedPrice = calculateDiscount(pList.get(i).getPrice(), discount);
                pList.get(i).setPrice(discountedPrice);
                po.updateRecord(brand, discountedPrice);
            }
        }
    }
    
    public void searchForBrand(String brand) {
        System.out.printf("%15s%25s%15s%15s%20s%10s%20s%20s%n", "Product Number", 
                "Name", "Brand", "Price", "Quantity\t", "Category", "Branch","Release Date");
        for (int i = 0; i < pList.size(); i++) {
            if(pList.get(i).getBrand().equals(brand)){
                System.out.println(pList.get(i));
            }
        }
    }
    
    public void showTotalValue(){
        double totalValue = 0;
        for (int i = 0; i < pList.size(); i++) {
            totalValue += pList.get(i).getPrice() * pList.get(i).getQty();
        }
        System.out.printf("Total value of stock is: €%,.2f \n", totalValue);
    }
    
    public void showOldestProducts() {
        System.out.println("Oldest Product(s) in stock:");
        System.out.printf("%15s%25s%15s%15s%20s%10s%20s%20s%n", "Product Number", 
                "Name", "Brand", "Price", "Quantity\t", "Category", "Branch","Release Date");
        for (int i = 0; i < pList.size(); i++) {
            if(pList.get(i).getReleaseDate().getTimeInMillis() == calcOldestProductDate()){
                System.out.println(pList.get(i));
            }
        }
    }
   
    public long calcOldestProductDate() {
        long oldestDate = pList.get(0).getReleaseDate().getTimeInMillis();
        for (int i = 0; i < pList.size(); i++) {
            if (pList.get(i).getReleaseDate().getTimeInMillis() < oldestDate ){
                oldestDate = pList.get(i).getReleaseDate().getTimeInMillis();
            }        
        }
        return oldestDate;
    }



    
     
}
