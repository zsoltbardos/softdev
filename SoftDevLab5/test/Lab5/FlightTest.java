/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author x00157506
 */
public class FlightTest {
        public Flight testFlight;
        Flight.Passenger testPassenger;
    public FlightTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testFlight = new Flight(200, 3.5);
        testPassenger = testFlight.new Passenger("John", 52, new double[]{12, 16, 22}, new char[]{'M', 'M', 'L'});
    }
    
    @After
    public void tearDown() {
        testFlight = null;
    }

    /**
     * Test of fillList method, of class Flight.
     */
    @Test
    public void testFillList() {
        //Arrange
        
        //Act
        testFlight.fillList(testPassenger);
        //Assert
        assertTrue(testFlight.getPassengers().size() == 1);
    }

    /**
     * Test of print method, of class Flight.
     */
    @Test
    public void testPrint() {
        System.out.println("print");
        Flight instance = null;
        instance.print();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAvailability method, of class Flight.
     */
    @Test
    public void testCheckAvailability() {
        System.out.println("checkAvailability");
        Flight instance = null;
        int expResult = 0;
        int result = instance.checkAvailability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcOldestPassenger method, of class Flight.
     */
    @Test
    public void testCalcOldestPassenger() {
        System.out.println("calcOldestPassenger");
        Flight instance = null;
        String expResult = "";
        String result = instance.calcOldestPassenger();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
