/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab2;

import java.util.Scanner;
/**
 *
 * @author X00157506
 */
public class SoftDevLab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        
        String[] musicianNames = {"John", "Paul", "George", "Ringo"};
        String[] insturmentNames = {"Keyboards", "Guitar", "Guitar", "Drums"};
       
        // TODO code application logic here
        Rockband rockBand1 = new Rockband("The Beetles", "Apple", musicianNames, insturmentNames);       
        
        System.out.println("Please Enter new label: ");
        
        if (!rockBand1.changeRecordLabel(reader.nextLine())){
            System.out.println("Label not changed");
        } else {
            System.out.println("Rock Band:\n" + rockBand1.toString());
        }
    }
    
}
