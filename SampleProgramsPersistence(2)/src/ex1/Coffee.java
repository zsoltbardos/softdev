package ex1;
import javax.persistence.*;

@NamedQueries({
    @NamedQuery(name = "Coffee.findAll", query = "SELECT c FROM Coffee c"),
    @NamedQuery(name = "Coffee.findByPrice", query = "SELECT c FROM Coffee c WHERE c.price = :price")})

@Entity
@Table(name = "COFFEE")
@SuppressWarnings("SerializableClass")
public class Coffee {
    private String description;
    @Id
    private String prodnum;
    private double price;

    public Coffee() {
    }

    public Coffee(String prodnum) {
        this.prodnum = prodnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProdnum() {
        return prodnum;
    }

    public void setProdnum(String prodnum) {
        this.prodnum = prodnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
   public String toString() {
        return String.format("Description: %-25s Product Number: %-15s Price: %,.2f",description,prodnum,price);
    }
    
}
