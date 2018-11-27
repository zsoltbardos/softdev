package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author x00157506
 */

@Entity
@Table (name = "EMPLOYEE14")
@Inheritance( strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type" )

@SequenceGenerator (name = "eid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")
        
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eid_seq")
    private int empid;
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    
    @OneToMany(mappedBy = "emp", cascade = CascadeType.ALL)
    private List<Contact> clist = new ArrayList<>();

    public Employee() {
    }

    public Employee(String name, Calendar startDate) {
        this.name = name;
        this.startDate = startDate;
    }
    
    public void addContact(Contact c) {
        this.clist.add(c);
        c.setEmp(this);
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public List<Contact> getClist() {
        return clist;
    }

    public void setClist(List<Contact> clist) {
        this.clist = clist;
    }
    
    
    
    @Override
    public String toString() {
        return String.format(" Employee Id: %2d Name: %-10s "
                + "Start Date: %3$8td %3$tB %3$tY   ",empid,name,startDate);
    }
    

    public String toString_contacts() {
        String e = "";
        e += "Employee " + "Id: " + empid + ", Name: " + name + ", Contacts: ";
        for (int i = 0; i < clist.size(); i++) {
            e += "\n" + clist.get(i) + "\n";
        }
        return e;
    }

}
