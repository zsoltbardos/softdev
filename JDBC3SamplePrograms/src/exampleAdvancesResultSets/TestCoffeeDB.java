package exampleAdvancesResultSets;

import java.util.Scanner;

public class TestCoffeeDB {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        CoffeeOperations cdb = new CoffeeOperations();
        cdb.openDB();
        cdb.dropTables();
        cdb.buildCoffeeTable();
        cdb.advancedRSMethods();

        while (true) {
            System.out.println("Please press 1 to view coffee details");
            System.out.println("Please press 2 if you want to update a price");
            System.out.println("Please press 3 to delete a coffee record");
            System.out.println("Please press 4 to add a new coffee record");
            System.out.println("Press 5 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    cdb.queryDB();
                    break;
                case 2:
                    System.out.println("please enter the id of the coffee you wish to update");
                    String id = in.nextLine();
                    if (cdb.findCoffeeRecord(id)) {
                        System.out.println("Please enter the new price for " + id);
                        double newPrice = in.nextDouble();
                        cdb.updateRecord(id, newPrice);
                    } else {
                        System.out.println("Record not found");
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the coffee you wish to delete");
                    String idDelete = in.nextLine();
                    if (cdb.findCoffeeRecord(idDelete)) {
                        cdb.deleteRecord(idDelete);
                    } else {
                        System.out.println("Record not found");
                    }
                    break;
                case 4:
                    System.out.println("please enter the name of the coffee you wish to add");
                    String nameAdd = in.nextLine();
                    System.out.println("please enter the prod ID of the coffee you wish to add");
                    String idAdd = in.nextLine();
                    System.out.println("please enter the price of the coffee you wish to add");
                    double priceAdd = in.nextDouble();
                    cdb.add(nameAdd, idAdd, priceAdd);
                    break;
                case 5: 
                    cdb.closeDB();
                    System.exit(0);
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
