package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table (name= "EMPLOYEEJ")
@Inheritance( strategy = InheritanceType.JOINED)
@DiscriminatorColumn( name = "type" )

@SequenceGenerator(name="eid_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="eid_seq")
    private int empid;
    private String name;
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
    @ManyToOne()
    @JoinColumn(name="DEPID")
    private Department dep;

    public Employee() {
    }
    
    public Employee(String name, Calendar startdate) {
        this.name = name;
        this.startDate = startdate;
    }

    public Employee(int id) {
        this.empid = id;
    }

    public int getId() {
        return empid;
    }

    public void setId(int id) {
        this.empid = id;
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

    public Department getDep() {
        return dep;
    }

    public void setDep(Department dep) {
        this.dep = dep;
    }
    

    @Override
    public String toString() {
        return String.format(" Employee Id: %2d Name: %-10s "
                + "Start Date: %3$8td %3$tB %3$td   ",empid,name,startDate);
    }
}
