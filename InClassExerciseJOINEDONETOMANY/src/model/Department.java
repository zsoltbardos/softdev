package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT")

@SequenceGenerator(name="did_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Department {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="did_seq")
    private int did;
    private String depName;   
 
    @OneToMany(mappedBy = "dep", cascade = CascadeType.ALL)
    private List<Employee> elist = new ArrayList<>();

    public Department() {
    }

    public Department(int deptID, String deptName) {
        this.did = deptID;
        this.depName = deptName;
    }

    public String getDeptName() {
        return depName;
    }

    public void setDeptName(String deptName) {
        this.depName = deptName;
    }

    public int getDeptID() {
        return did;
    }

    public void setDeptID(int deptID) {
        this.did = deptID;
    }

    public List<Employee> getElist() {
        return elist;
    }

    public void setElist(List<Department> ulist) {
        this.elist = elist;
    }

    public void addEmp(Employee e)
    {
        elist.add(e);
        e.setDep(this);
    }

    @Override
    public String toString() {
        return "Dep ID: " + did + " Dep Name: " + depName +"\n";
    }  
}
