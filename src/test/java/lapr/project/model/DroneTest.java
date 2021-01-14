/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Helder
 */
public class DroneTest {
    private static Drone droneTest;
    
    public DroneTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        droneTest = new Drone(20, 20, State.ACTIVE, 2, 2, 50);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of updateScooterData method, of class Drone.
     */
    @Test
    public void testUpdateScooterData() {
        Drone droneTest2 = new Drone(123, 20, State.ACTIVE, 10, 8, 508);
        droneTest2.updateDroneData( 8.0, State.INACTIVE, 5, 5);
        assertEquals(droneTest2.getWeight(), 8);
        assertEquals(droneTest2.getId(), 123);
        assertEquals(droneTest2.getMaxBat(), 5, 0.0);
        assertEquals(droneTest2.getMotor(), 5);
        assertEquals(droneTest2.getState(), State.INACTIVE);
        assertNotEquals(droneTest.toString(), droneTest2.toString());
    }

    /**
     * Test of getId method, of class Drone.
     */
    @Test
    public void testGetId() {
        int expResult = 20;
        int result = droneTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Drone.
     */
    @Test
    public void testGetState() {
        State expResult = State.ACTIVE;
        State result = droneTest.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActualBat method, of class Drone.
     */
    @Test
    public void testGetActualBat() {
        double expResult = 2.0;
        double result = droneTest.getActualBat();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setState method, of class Drone.
     */
    @Test
    public void testSetState() {
        State state = State.INACTIVE;
        droneTest.setState(state);
        assertEquals(droneTest.getState(), state);
    }
    
}
