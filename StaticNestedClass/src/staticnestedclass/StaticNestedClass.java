/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticnestedclass;

/**
 *
 * @author x00157506
 */
public class StaticNestedClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MagicHat hat1 = new MagicHat("Gray Hat");
        System.out.println(hat1);
        
        MagicHat hat2 = new MagicHat("Black Hat");
        System.out.println(hat2);
        
        MagicHat hat3 = new MagicHat("Baseball Hat");
        System.out.println(hat3);
        
        MagicHat.Rabbit rabbit1 = new MagicHat.Rabbit();
        System.out.println("\nRabbit outside of the hat is: " + rabbit1);
        
    }
    
}
