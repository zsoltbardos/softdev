/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part2;


/**
 *
 * @author X00157506
 */
abstract public class ThreeDShape extends Shape {
    private int d1, d2, d3;

    public ThreeDShape(int d1, int d2, int d3, int x, int y) {
        super(x, y);
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }

    public int getD3() {
        return d3;
    }
    
    abstract public int getArea();
    
    abstract public int getVolume();
    
    
}
