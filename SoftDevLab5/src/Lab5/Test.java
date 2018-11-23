/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

/**
 *
 * @author Zsolti
 */
public class Test {
    public static void main(String [ ] args){

        Flight flight = new Flight(200, 3.5);
        Flight.Passenger passenger1 = flight.new Passenger("John", 52, new double[]{12, 16, 22}, new char[]{'M', 'M', 'L'});
        flight.fillList(passenger1);
        
        Flight.Passenger passenger2 = flight.new Passenger("Ken", 26, new double[]{12, 13}, new char[]{'M', 'M'});
        flight.fillList(passenger2);
        
        Flight.Passenger passenger3 = flight.new Passenger("Kate", 89, new double[]{22, 23, 24}, new char[]{'L', 'L', 'L'});
        flight.fillList(passenger3);
        
        Flight.Passenger passenger4 = flight.new Passenger("Peter", 40, new double[]{16, 18, 23}, new char[]{'M', 'M', 'L'});
        flight.fillList(passenger4);
        
        
        flight.print();
        
        System.out.println("Number of seats available: " + flight.checkAvailability());
        
        System.out.println(flight.calcOldestPassenger());
    }
}
