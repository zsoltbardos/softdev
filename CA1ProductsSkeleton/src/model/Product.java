package model;

import java.util.Calendar;

public class Product {

    private int productID;
    private String pName;
    private String brand;
    private double price;
    private int qty;
    private String category;
    private String branch;
    private Calendar releaseDate;

    public Product(int productID, String pName, String brand, double price, int qty, String category, String branch, Calendar rDate) {
        this.productID = productID;
        this.pName = pName;
        this.brand = brand;
        this.price = price;
        this.qty = qty;
        this.category = category;
        this.branch = branch;
        this.releaseDate = rDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Calendar releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("%1$15d %2$25s %3$12s %4$15.2f€ %5$15d %6$20s %7$20s %8$8tb %8$td %8$tY", productID, pName, brand, price, qty, category, branch, releaseDate);
    }

}
