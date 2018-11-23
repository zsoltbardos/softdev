/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab3;

/**
 *
 * @author x00157506
 */
public class Volunteer extends StaffMember {

    public Volunteer(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }
    
    @Override
    public double pay(){
        return 0;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + 
                "Address: " + address + "\n" + 
                "Phone: " + phoneNumber + "\n" +
                "Thanks!" + "\n" +
                "-----------------------------------";
    }
    
    
}
