package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "STAFF")
@SequenceGenerator(name="sid_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Staff {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sid_seq")
    private int sid;
    private String sName;
    @OneToMany(mappedBy = "stf",cascade = CascadeType.ALL)  
    private List<Contact> clist = new ArrayList<>();

    public Staff() {

    }

    public void addContact(Contact c) {
        this.clist.add(c);
        c.setS(this);
    }

    public int getId() {
        return sid;
    }

    public void setId(int id) {
        this.sid = id;
    }

    public String getName() {
        return sName;
    }

    public void setName(String name) {
        this.sName = name;
    }

    public List<Contact> getClist() {
        return clist;
    }

    @Override
    public String toString() {
        String s = "";
        s += "Staff " + "Id: " + sid + ", Name: " + sName + ", Contacts: ";
        for (int i = 0; i < clist.size(); i++) {
            s += "\n" + clist.get(i) + "\n";
        }
        return s;
    }
}
