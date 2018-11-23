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
public class Executive extends Employee {
    private double bonus;

    public Executive(String name, String address, String phoneNumber, String rsiNumber, double payRate) {
        super(name, address, phoneNumber, rsiNumber, payRate);
        this.bonus = 0;
    }
    
    public void awardBonus(double bonus){
        this.bonus = bonus;
    }
    
    @Override
    public double pay() {
        return super.pay() + this.bonus;
    }
    
    
}
