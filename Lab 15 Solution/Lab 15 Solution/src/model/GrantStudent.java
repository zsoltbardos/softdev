/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import javax.persistence.*;

@Entity
@DiscriminatorValue( value="GS" )
@PrimaryKeyJoinColumn(referencedColumnName="sid")

@SuppressWarnings("SerializableClass")
public class GrantStudent extends Student {

    private double grantAmount;

    public GrantStudent() {
    }

    public GrantStudent(String name, Calendar dob, String course, double grantAmount) {
        super(name, dob, course);
        this.grantAmount = grantAmount;
    }

    public double getGrantAmount() {
        return grantAmount;
    }

    public void setGrantAmount(double grantAmount) {
        this.grantAmount = grantAmount;
    }

    @Override
    public String toString() {
        return super.toString() + "  Grant Student " + "Grant: " + grantAmount;
    }
}
