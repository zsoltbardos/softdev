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
@Table(name = "CLASS")

@SequenceGenerator(name = "classid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classid_seq")
    private int class_id;
    private String class_name;
    private String class_day;
    private String class_time;
    
    @ManyToMany(mappedBy = "classList", cascade = CascadeType.PERSIST)
    private List<Member> memberList = new ArrayList<>();
    
    @ManyToOne()
    @JoinColumn(name="trainer_id")
    private Trainer trainer;

    public Class() {
    }
    

    public Class(String class_name, String class_day, String class_time) {
        this.class_name = class_name;
        this.class_day = class_day;
        this.class_time = class_time;
    }
       
    
    public void addMember(Member m) {
        memberList.add(m);
        m.getClassList().add(this);
    }


    public void removeMember(Member m) 
    {
        memberList.remove(m);
        m.getClassList().remove(this);

    }
    
    public void remove() {
        ArrayList<Member> temp = new ArrayList<>(memberList);
        for (int i = 0; i < temp.size(); i++) {
            removeMember(temp.get(i));
        }
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_day() {
        return class_day;
    }

    public void setClass_day(String class_day) {
        this.class_day = class_day;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    
    

    @Override
    public String toString() {
        return "Class{" + "class_id=" + class_id + ", class_name=" + class_name + ", class_day=" + class_day + ", class_time=" + class_time + ", memberList=" + memberList + '}';
    }
    
    
    
    

}
