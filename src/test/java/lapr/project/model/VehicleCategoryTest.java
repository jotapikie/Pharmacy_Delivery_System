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
 * @author Diogo
 */
public class VehicleCategoryTest {
    
    public VehicleCategoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of values method, of class VehicleCategory.
     */
    @Test
    public void testValues() {
        assertEquals(2, VehicleCategory.values().length);
    }



    /**
     * Test of getName method, of class VehicleCategory.
     */
    @Test
    public void testGetName() {
        assertEquals("Scooter", VehicleCategory.SCOOTER.getName());
         assertEquals("Drone", VehicleCategory.DRONE.getName());
    }

    /**
     * Test of fromString method, of class VehicleCategory.
     */
    @Test
    public void testFromString() {
        assertEquals(VehicleCategory.DRONE, VehicleCategory.fromString("Drone"));
        assertEquals(VehicleCategory.SCOOTER, VehicleCategory.fromString("Scooter"));
        assertNull(VehicleCategory.fromString("asasadf"));
    }
    
}
