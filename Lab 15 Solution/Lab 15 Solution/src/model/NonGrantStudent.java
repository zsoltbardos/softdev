/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@DiscriminatorValue("NG")
@PrimaryKeyJoinColumn(referencedColumnName = "sid")

@SuppressWarnings("SerializableClass")

public class NonGrantStudent extends Student {
    private double feesDue;

    public double getFeeAmount() {
        return feesDue;
    }

    public void setFeeAmount(double feeAmount) {
        this.feesDue = feeAmount;
    }

    

    public NonGrantStudent() {
    }

    public NonGrantStudent(double feeAmount) {
        this.feesDue = feeAmount;
    }

    public NonGrantStudent(String name, Calendar dob, String course, double feeAmount) {
        super(name, dob, course);
        this.feesDue = feeAmount;
    }

    @Override
    public String toString() {
        return super.toString() + "  Non Grant Student " + feesDue;
    }
}
