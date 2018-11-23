/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author X00157506
 */
public class Sphere extends ThreeDShape {

    public Sphere(int d1, int d2, int d3, int x, int y) {
        super(d1, d2, d3, x, y);
                increment();
    }
    
    @Override
    public String getName(){
        return "Sphere";
    }
    
    public int getRadius(){
        return getD1();
    }
    
    @Override
    public int getArea(){
        return (int) (4 * Math.PI * getRadius() * getRadius());
    }
    
    @Override
    public int getVolume(){
        return (int) ((4 / 3) * Math.PI * Math.pow(getRadius(), 3));
    }
    
    @Override
    public String toString() {
        return "x: " + getX() + ", y: " + getY() + ", radius: " + getRadius() + "\n" + "Area of sphere is: " + getArea() + "\n" + "Volume of Sphere is: " + getVolume();
    }
}

