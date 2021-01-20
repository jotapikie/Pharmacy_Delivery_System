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
public class StateTest {
    
    public StateTest() {
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
        assertEquals(4,State.values().length);
    }
    
    @Test
    public void testGetName() {
        assertEquals("Active",State.ACTIVE.getName());
        assertEquals("Charging",State.CHARGING.getName());
        assertEquals("Inactive",State.INACTIVE.getName());
        assertEquals("Locked",State.LOCKED.getName());
    }
    
    @Test
    public void testFromString() {
        assertEquals(State.ACTIVE, State.fromString("Active"));
        assertEquals(State.CHARGING, State.fromString("Charging"));
        assertEquals(State.INACTIVE, State.fromString("Inactive"));
        assertEquals(State.LOCKED, State.fromString("Locked"));
        assertNull(State.fromString("sadfsd"));
    }
    
}
