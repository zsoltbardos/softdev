/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author furry
 */
public class TestIMeasurable {
    public static void main(String args[]) {
        IMeasurable[] measurableObjects = new IMeasurable[4];
        
        measurableObjects[0] = new Circle(5);
        measurableObjects[1] = new Circle(8);
        measurableObjects[2] = new Square(7);
        measurableObjects[3] = new Square(5);
        
        
        for(int i=0;i<measurableObjects.length;i++){
            System.out.printf("Area is: %.2f%n",measurableObjects[i].area());
        }
        
        
        
    }
    
}
