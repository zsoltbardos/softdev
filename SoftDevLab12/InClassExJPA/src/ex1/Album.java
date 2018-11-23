package ex1;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a"),
    @NamedQuery(name = "Album.findByPriceArtist", query = "SELECT a FROM Album a WHERE a.artist=:artist AND a.price > :price")})

@SequenceGenerator(name="a_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Album {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="a_seq")
    private int id;
    private String title;
    private String artist;
    private double price;

    public Album() {
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
        return "Album: " + "Album id: " + id + " Title: " + title + " Artist: " + artist + " Price: "+price;
    }
    
}
