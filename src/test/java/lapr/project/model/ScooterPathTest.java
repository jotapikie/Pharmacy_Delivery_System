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
public class ScooterPathTest {
    
    private ScooterPath test;
    private GeographicalPoint p = new GeographicalPoint(34, 23, 3.4, "Point 5");
    private GeographicalPoint p1 = new GeographicalPoint(4, 3, 1.4, "Point 2");
    
    public ScooterPathTest() {       
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        test=new ScooterPath(p,p1,10.0,StreetType.ASPHALT,new Wind(1,1,1),100.0,"rua");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTotalWeight method, of class ScooterPath.
     */
    @Test
    public void testGetTotalWeight() {
        double expResult = 100.0;
        double result = test.getTotalWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotalWeight method, of class ScooterPath.
     */
    @Test
    public void testSetTotalWeight() {
        double totalWeight = 15.0;
        test.setTotalWeight(totalWeight);
        test.setTotalWeight(totalWeight);
        assertEquals(test.getTotalWeight(), totalWeight, 0.0);
    }

    /**
     * Test of equals method, of class ScooterPath.
     */
    @Test
    public void testEquals() {
        Object obj = null;
        boolean expResult = false;
        boolean result = test.equals(obj);
        assertEquals(expResult, result);
        
        expResult = true;
        result = test.equals(test);
        assertEquals(expResult, result);
        
        ScooterPath sc=new ScooterPath(p,p1,10.0,StreetType.ASPHALT,new Wind(1,1,1),100.0,"rua");
        expResult = true;
        result = test.equals(sc);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class ScooterPath.
     */
    @Test
    public void testHashCode() {
        assertTrue(test.hashCode() > 0);
    }
    
}
