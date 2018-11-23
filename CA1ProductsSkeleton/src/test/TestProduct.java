package test;

import db.ProductOperations;
import java.util.Scanner;
import model.Shop;

public class TestProduct {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int pid;
        ProductOperations po = new ProductOperations();
        po.openDB();
        po.dropTables();
        po.createTables();
        Shop s = new Shop("PC World", po);
        s.fillList();
        
        while (true) {
            System.out.println("Please press 1 to view all products");
            System.out.println("Please press 2 if you want to change a product price");
            System.out.println("Please press 3 if you want to delete a product");
            System.out.println("Please press 4 to apply a sales discount on a particular brand of products");
            System.out.println("Please press 5 to search for products by brand");
            System.out.println("Please press 6 to show the total value of all products in stock");
            System.out.println("Please press 7 to view the oldest product(s) in stock");
            System.out.println("Press 8 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    s.showProducts();
                    break;
                case 2:
                    System.out.println("please enter the product id in order to change the price");
                    pid = in.nextInt();
                    in.nextLine();
                    if (s.findProduct(pid) == -1) {
                        System.out.println("Product not found");
                    } else {
                        System.out.println("Please enter the new price for product number " + pid);
                        double newPrice = in.nextDouble();
                        in.nextLine();
                        s.updateProductPrice(pid, newPrice);
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the product you wish to delete");
                    pid = in.nextInt();
                    s.removeProduct(pid);
                    break;
                case 4:
                    System.out.println("Please enter the brand of the product you wish to put on sale");
                    String brand = in.nextLine().toUpperCase();
                    System.out.println("Please enter the % discount you wish to apply to the " + brand + " brand of products");
                    double discount = in.nextDouble();
                    in.nextLine();
                    s.applyDiscount(brand, discount);
                    break;
                case 5:
                    System.out.println("Please enter the brand of the product you wish to view");
                    brand = in.nextLine().toUpperCase();
                    s.searchForBrand(brand);
                    break;
                case 6:
                    s.showTotalValue();
                    break;
                case 7:
                    s.showOldestProducts();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}

