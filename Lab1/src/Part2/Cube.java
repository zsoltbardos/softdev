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
public class Cube extends ThreeDShape {

    public Cube(int d1, int d2, int d3, int x, int y) {
        super(d1, d2, d3, x, y);
                increment();
    }
    
    @Override
    public String getName(){
        return "Cube";
    }
    
    public int getSide(){
        return getD1();
    }
    
    @Override
    public int getArea(){
        return 6 * (getSide() * getSide());
    }
    
    @Override
    public int getVolume(){
        return getSide() * getSide() * getSide();
    }
    
    @Override
    public String toString() {
        return "x: " + getX() + ", y: " + getY() + ", Side: " + getSide() + "\n" + "Area of cube is: " + getArea() + "\n" + "Volume of Cube is: " + getVolume();
    }
}
