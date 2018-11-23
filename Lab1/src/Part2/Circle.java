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
public class Circle extends TwoDShapeClass {
    
    private String nameOfShape = "Circle";

    public Circle(int d1, int d2, int x, int y) {
        super(d1, d2, x, y);
                increment();
    }
    
    @Override
    public String getName(){
        return nameOfShape;
    }
    
    public int getRadius(){
        return getD1() * getD1();
    }
    
    @Override
    public int getArea(){
        return (int) (Math.PI * getRadius());
    }

    @Override
    public String toString() {
        return "x: " + getX() + ", y: " + getY() + ", radius: " + getRadius() + "\n" + "Are of Circle is: " + getArea();
    }
    
    
}

