package test;

import db.AlbumOperations;
import java.util.Scanner;
import model.AlbumCollection;

public class TestAlbum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        AlbumOperations ao = new AlbumOperations();
        ao.openDB();
        ao.dropSequence();
        ao.dropTable();
        ao.CreateAlbumTable();

        AlbumCollection ac = new AlbumCollection("John", ao);
        ac.fillList();

        while (true) {
            System.out.println("Please press 1 to view album details");
            System.out.println("Please press 2 if you want to update an album price");
            System.out.println("Please press 3 to delete an album");
            System.out.println("Please press 4 to add a new album");
            System.out.println("Press 5 to quit");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    ac.showAlbums();
                    break;
                case 2:
                    System.out.println("please enter the id of the album you wish to update");
                    int id = in.nextInt();
                    in.nextLine();
                    if (ac.findAlbum(id)) {
                    System.out.println("Please enter the new price for album number:  " + id);
                    double newPrice = in.nextDouble();
                    ac.updateAlbum(id, newPrice);
                    } else {
                        System.out.println("Sorry record not found");
                    }
                    break;
                case 3:
                    System.out.println("please enter the id of the album you wish to delete");
                    int idDelete = in.nextInt();
                     if (ac.findAlbum(idDelete)) {
                    ac.removeAlbum(idDelete);
                     } else {
                         System.out.println("Sorry record not found");
                     }
                    break;
                case 4:
                    System.out.println("please enter the title of the album you wish to add");
                    String title = in.nextLine();
                    System.out.println("please enter the artist of the album you wish to add");
                    String artist = in.nextLine();
                    System.out.println("please enter the price of the album you wish to add");
                    double price = in.nextDouble();
                    ao.add(title, artist, price);
                    ac.addAlbum();
                    break;
                case 5:
                    ao.closeDB();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Option entered");
                    break;
            }
        }
    }
}
