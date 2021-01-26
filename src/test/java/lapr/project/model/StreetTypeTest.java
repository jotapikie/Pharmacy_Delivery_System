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
public class StreetTypeTest {
    
    public StreetTypeTest() {
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
     * Test of values method, of class State.
     */
    @Test
    public void testValues() {
        assertEquals(4,StreetType.values().length);
    }

    @Test
    public void testGetName() {
        assertEquals("Asphalt",StreetType.ASPHALT.getName());
        assertEquals("Off-Road",StreetType.OFF_ROAD.getName());
        assertEquals("Paralelo",StreetType.PARALELO.getName());
        assertEquals("Sidewalk",StreetType.SIDEWALK.getName());
    }

    

    /**
     * Test of fromString method, of class StreetType.
     */
    @Test
    public void testFromString() {
        assertEquals(StreetType.ASPHALT, StreetType.fromString("Asphalt"));
        assertEquals(StreetType.OFF_ROAD, StreetType.fromString("Off-Road"));
        assertEquals(StreetType.PARALELO, StreetType.fromString("Paralelo"));
        assertEquals(StreetType.SIDEWALK, StreetType.fromString("Sidewalk"));
        assertNull(StreetType.fromString("sadfsd"));
    }
    
}
