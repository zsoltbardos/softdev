/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;
import java.util.ArrayList;

/**
 *
 * @author Zsolti
 */
public class Flight {
    
    private int capacity;
    private double duration;
    private ArrayList<Passenger> passengers;

    public Flight(int capacity, double duration) {
        this.capacity = capacity;
        this.duration = duration;   
        passengers = new ArrayList<>();
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }
    
    


  
    public void fillList(Passenger passenger) {
        this.passengers.add(passenger);
    }
    
    public void print() {
        for (Passenger passenger : passengers){
            System.out.println("Name: " + passenger.name + ", Age: " + passenger.age +
                                ", Baggage charge: " + passenger.calcBaggageCharge());
        }
    }
    
    public int checkAvailability(){
        return this.capacity - this.passengers.size();
    }
    
    public String calcOldestPassenger() {
        String oldestPassenger = "Null";
        int oldestPassengerAge = 0;
        for (Passenger passenger : passengers) {
            if (passenger.age > oldestPassengerAge){
                oldestPassengerAge = passenger.age;
                oldestPassenger = passenger.name + " age: " + oldestPassengerAge;
            }
        
        }
        return "Oldest Person on the flight is: " + oldestPassenger;
    }

    public class Passenger {
        private String name;
        private int age;
        private double[] weightOfBags;
        private char[] sizeOfBags;

        public Passenger(String name, int age, double[] weightOfBags, char[] sizeOfBags) {
            this.name = name;
            this.age = age;
            this.weightOfBags = weightOfBags;
            this.sizeOfBags = sizeOfBags;
        }
        
        public int calcBaggageCharge() {
            int baggageCharge = 0;
            
            for (int i = 0; i < sizeOfBags.length; i++) {
                if (sizeOfBags[i] == 'M' ){
                    baggageCharge += 30;
                    if (weightOfBags[i] > 15){
                        baggageCharge += (Math.round(weightOfBags[i]) - 15) * 10;
                    }
                } else if (sizeOfBags[i] == 'L') {
                    baggageCharge += 40;
                    if (weightOfBags[i] > 20){
                        baggageCharge += (Math.round(weightOfBags[i]) - 20) * 10;
                    }
                }
  
            }            

            return baggageCharge;
            
        }
    }   
    
    
}
