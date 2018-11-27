/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")

@SequenceGenerator(name = "adid_seq", initialValue = 1, allocationSize = 1)
@SuppressWarnings("SerializableClass")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adid_seq")
    private int addId;
    private String addName;
    private String eirCode;

    //Constructors
    public Address() {

    }

    public Address(String addName, String eirCode) {
        this.addName = addName;
        this.eirCode = eirCode;
    }

    public int getAddId() {
        return addId;
    }

    public void setAddId(int addId) {
        this.addId = addId;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }

    public String getEirCode() {
        return eirCode;
    }

    public void setEirCode(String eirCode) {
        this.eirCode = eirCode;
    }
        @Override
    public String toString() {
        return  addName + ",  Eir Code= " + eirCode ;
    }


}
