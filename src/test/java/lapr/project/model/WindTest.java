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
public class WindTest {
    
    private Wind test;
    
    public WindTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        test= new Wind(1,1,0.5);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of direction method, of class Wind.
     */
    @Test
    public void testDirection() {
        int expResult = 0;
        int result = test.direction();
        assertEquals(expResult, result);
    }

    /**
     * Test of speed method, of class Wind.
     */
    @Test
    public void testSpeed() {
        double expResult = 1.0;
        double result = test.speed();
        assertEquals(expResult, result, 0.0);
    }
    
}
