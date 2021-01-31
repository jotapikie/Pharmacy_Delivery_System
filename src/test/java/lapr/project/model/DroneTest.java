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
        droneTest= new Drone(1, State.ACTIVE, 100, 100);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getEletronicalConsumer method, of class Drone.
     */
    @Test
    public void testGetEletronicalConsumer() {
        System.out.println("getEletronicalConsumer");
        double expResult = 2000.0;
        double result = Drone.getEletronicalConsumer();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFrontalArea method, of class Drone.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        double expResult = 1.0;
        double result = Drone.getFrontalArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTopArea method, of class Drone.
     */
    @Test
    public void testGetTopArea() {
        System.out.println("getTopArea");
        double expResult = 1.0;
        double result = Drone.getTopArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getPowerTransfer method, of class Drone.
     */
    @Test
    public void testGetPowerTransfer() {
        System.out.println("getPowerTransfer");
        double expResult = 2.0;
        double result = Drone.getPowerTransfer();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLiftDrag method, of class Drone.
     */
    @Test
    public void testGetLiftDrag() {
        System.out.println("getLiftDrag");
        double expResult = 1.0;
        double result = Drone.getLiftDrag();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getAeroCoef method, of class Drone.
     */
    @Test
    public void testGetAeroCoef() {
        System.out.println("getAeroCoef");
        double expResult =0.5;
        double result = Drone.getAeroCoef();
        assertEquals(expResult, result, 0.0);
    }
    
}
