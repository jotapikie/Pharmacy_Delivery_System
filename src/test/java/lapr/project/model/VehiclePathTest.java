/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class VehiclePathTest {
    
    private static GeographicalPoint or;
    static GeographicalPoint dest;
    static ScooterPath path;
    
    @BeforeAll
    public static void setUpClass() {
        or = new GeographicalPoint(41.233, 45.23, 34.2);
        dest = new GeographicalPoint(41.533, 47.23, 35.2);
        path = new ScooterPath(or, dest, 34, StreetType.ASPHALT, new Wind(1,1,1), 56.7,"Street1");
    }
    
    @AfterEach
    public void tearDown() {
        path = new ScooterPath(or, dest, 34, StreetType.ASPHALT, new Wind(1,1,1), 56.7, "Street1");
    }

    /**
     * Test of getTotalWeight method, of class ScooterPath.
     */
    @Test
    public void testGetTotalWeight() {
        assertEquals(56.7, path.getTotalWeight());
    }



    /**
     * Test of setTotalWeight method, of class ScooterPath.
     */
    @Test
    public void testSetTotalWeight() {
        path.setTotalWeight(4.5);
        assertEquals(4.5, path.getTotalWeight());
        
        boolean flag = false;
        try{
        path.setTotalWeight(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        flag = false;
        try{
        path.setTotalWeight(-5);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    
    }



    /**
     * Test of getCost method, of class ScooterPath.
     */
    @Test
    public void testGetCost() {
        assertEquals(0.0012, path.getCost(), 0.001);
    }
    
}
