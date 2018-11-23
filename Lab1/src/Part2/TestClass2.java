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
public class TestClass2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Shape[] shapes = new Shape[4];
       
        shapes[0] = new Circle(4, 4, 22, 88);
        shapes[1] = new Square(10, 10, 71, 96);
        shapes[2] = new Sphere(2, 2, 2, 8, 89);
        shapes[3] = new Cube(8, 8 , 8, 79, 61);
        
        Paint paint = new Paint(70);
        
        System.out.println("Number of shapes created: " + Shape.shapeCounter);
        for (int i=0;i<shapes.length;i++){
            System.out.println(shapes[i]);
            System.out.println("\nAmount of paint required: " + String.format("%.2f", paint.calcAmount(shapes[i])) + "\n");
        }
        
        
    }
    
    
    
}
