/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab3;

import java.text.NumberFormat;

/**
 *
 * @author x00157506
 */
abstract public class StaffMember {
    protected String name, address, phoneNumber;

    public StaffMember(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + ", " + address + ", " + phoneNumber;
    }
    
    abstract public double pay();
    
    
}
