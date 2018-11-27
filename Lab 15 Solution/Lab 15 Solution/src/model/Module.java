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
@Table(name = "MODULE15")

@SequenceGenerator(name = "mid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mid_seq")
    private int mid;
    private String name;
    private int hours;

    @ManyToMany(mappedBy = "mlist", cascade = CascadeType.PERSIST)
    private List<Student> slist = new ArrayList<>();

    public Module() {
    }

    public Module(String mname, int hours) {
        this.name = mname;
        this.hours = hours;
    }

    public void addStudent(Student s) {
        slist.add(s);
        s.getMlist().add(this);
    }


    public void removeStudent(Student s) 
    {
        slist.remove(s);
        s.getMlist().remove(this);

    }
    
    public void remove() {
        ArrayList<Student> temp = new ArrayList<>(slist);
        for (int i = 0; i < temp.size(); i++) {
            removeStudent(temp.get(i));
        }
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return name;
    }

    public void setMname(String mname) {
        this.name = mname;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Student> getSlist() {
        return slist;
    }

    public void setSlist(List<Student> slist) {
        this.slist = slist;
    }

    @Override
    public String toString() {
        return String.format("Module ID: %2d Name: %-15s "
                + "Number Hours: %15s ", mid, name, hours);
    }
    public void printStudents(){
        System.out.println("Students enrolled in module "+name);
        for (int i = 0; i < slist.size(); i++) {
            System.out.println( slist.get(i));
        }

    }

}
