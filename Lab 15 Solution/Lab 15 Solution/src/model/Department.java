/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 
    @OneToMany(mappedBy = "dep",cascade = CascadeType.ALL)
    private List<Student> slist = new ArrayList<>();

    public Department() {
    }

    public Department(int did, String depName) {
        this.did = did;
        this.depName = depName;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public List<Student> getSlist() {
        return slist;
    }

    public void setSlist(List<Student> slist) {
        this.slist = slist;
    }
    public void addStudent(Student s)
    {        
        slist.add(s);
        s.setDep(this);
    }


        @Override
    public String toString() {
        String s = " ";
        s+= "Dep ID: " + did + " Dep Name: " + depName +"\n";
        for (int i = 0; i < slist.size(); i++) {
            s += "\n" + slist.get(i) + "\n";
        }
        return s;
    }  

}
