/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;

/**
 *
 * @author x00157506
 */
public class Paint {
    private double coverage;

    public Paint(double coverage) {
        this.coverage = coverage;
    }
    
    public double calcAmount(Shape s){
        return s.getArea() / coverage;
    }
}
