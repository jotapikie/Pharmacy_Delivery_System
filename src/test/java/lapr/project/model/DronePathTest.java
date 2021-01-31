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
public class DronePathTest {
    
    private DronePath test;
    private GeographicalPoint p = new GeographicalPoint(34, 23, 3.4, "Point 5");
    private GeographicalPoint p1 = new GeographicalPoint(4, 3, 1.4, "Point 2");
    
    public DronePathTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        test=new DronePath(6.0,p,p1,200.0,new Wind(1,1,1));
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTotalWeight method, of class DronePath.
     */
    @Test
    public void testGetTotalWeight() {
        System.out.println("getTotalWeight");
        double expResult = 6.0;
        double result = test.getTotalWeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotalWeight method, of class DronePath.
     */
    @Test
    public void testSetTotalWeight() {
        System.out.println("setTotalWeight");
        double totalWeight = 5.0;
        test.setTotalWeight(totalWeight);
        assertEquals(test.getTotalWeight(), totalWeight, 0.0);
        
        boolean flag = false;
        try{
            test.setTotalWeight(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(totalWeight, test.getTotalWeight());
        
        flag = false;
        try{
            test.setTotalWeight(-6);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(totalWeight, test.getTotalWeight());
    }

    /**
     * Test of getCost method, of class DronePath.
     */
    @Test
    public void testGetCost() {
        double expResult = 819.05;
        double result = test.getCost();
        assertEquals(expResult, result, 0.01);
    }
    
}
