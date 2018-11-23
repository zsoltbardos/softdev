/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticnestedclass;

import java.util.Random;

/**
 *
 * @author x00157506
 */
public class MagicHat {
    private static final int maxRabbits = 4;
    private static Random randomNumber = new Random();
    private String hatName;
    private Rabbit[] rabbitList;
    
    static class Rabbit{
        private static String[] rabbitNames = {"Joe", "Jack", "Fred", "Bunny"};
        private static int[] numbers = new int[rabbitNames.length];
        private String rabbitName;
        
        public Rabbit(){
            int index = randomNumber.nextInt(rabbitNames.length-1);
            rabbitName = rabbitNames[index] + (++numbers[index]);
        }
        
        @Override
        public String toString(){
            return rabbitName;
        }
    }
    
    public MagicHat(String hatName){
        this.hatName = hatName;
        int arraySize = 1 + randomNumber.nextInt(maxRabbits);
        rabbitList = new Rabbit[arraySize];
        
        for(int i=0;i<rabbitList.length;i++){
            rabbitList[i] = new Rabbit();
        }
    }
    
    @Override
    public String toString(){
        String s = hatName + " contains: \n";
        for(int i=0;i<rabbitList.length;i++){
            s += " " + rabbitList[i];
        }
        return s;
    }
}
