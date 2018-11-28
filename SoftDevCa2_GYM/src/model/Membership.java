/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;


/**
 *
 * @author x00157506
 */

@Entity
@Table(name = "MEMBERSHIP")

@SequenceGenerator(name="mshipid_seq", initialValue=1, allocationSize=1)
@SuppressWarnings("SerializableClass")
public class Membership {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mshipid_seq")
    private int mship_id;
    private String mship_type;
    private double mship_price;
    private double mship_duration;

    @OneToMany(mappedBy = "mship",cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();
    
    public Membership() {
    }

    
    
    public Membership(String mship_type, double mship_price, double mship_duration) {
        this.mship_type = mship_type;
        this.mship_price = mship_price;
        this.mship_duration = mship_duration;
    }

    public int getMship_id() {
        return mship_id;
    }

    public void setMship_id(int mship_id) {
        this.mship_id = mship_id;
    }

    public String getMship_type() {
        return mship_type;
    }

    public void setMship_type(String mship_type) {
        this.mship_type = mship_type;
    }

    public double getMship_price() {
        return mship_price;
    }

    public void setMship_price(double mship_price) {
        this.mship_price = mship_price;
    }

    public double getMship_duration() {
        return mship_duration;
    }

    public void setMship_duration(double mship_duration) {
        this.mship_duration = mship_duration;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
    
    public void addMember(Member m)
    {        
        memberList.add(m);
        m.setMship(this);
    }

    @Override
    public String toString() {
        return "Membership{" + "mship_id=" + mship_id + ", mship_type=" + mship_type + ", mship_price=" + mship_price + ", mship_duration=" + mship_duration + ", memberList=" + memberList + '}';
    }
    
    
    
    
}
