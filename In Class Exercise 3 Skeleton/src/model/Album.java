package model;

public class Album {

    private int id;
    private String title;
    private String artist;
    private double price;

    public Album() {
        this.id = 0;
        this.title = "";
        this.artist = "";
        this.price = 0.0;
    }

    public Album(int id, String title, String artist, double price) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Album Details: \n" + "ID: " + id + ", Title: " + title + ", Artist: " + artist
                + ", Price: " + price + '}';
    }

}
