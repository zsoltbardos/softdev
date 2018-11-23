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
abstract public class Shape {
    
    static int shapeCounter = 0;
    
    public void increment()
    {
        shapeCounter++;
    }
    
    public int getShapeCounter() {
        return shapeCounter;
    }
    
    private int x, y;

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Shape{" + "x=" + x + ", y=" + y + '}';
    }
    
    abstract public String getName();
    
}
