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
public class Square extends TwoDShapeClass{

    public Square(int d1, int d2, int x, int y) {
        super(d1, d2, x, y);
        increment();
    }
    
    @Override
    public String getName(){
        return "Square";
    }
    
    public int getSide(){
        return getD1();
    }
    
    @Override
    public int getArea(){
        return getD1() * getD2();
    }
    
    @Override
    public String toString() {
        return "x: " + getX() + ", y: " + getY() + ", side: " + getSide() + "\n" + "Are of Square is: " + getArea();
    }
}
