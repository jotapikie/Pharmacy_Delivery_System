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
    static VehiclePath path;
    
    @BeforeAll
    public static void setUpClass() {
        or = new GeographicalPoint(41.233, 45.23, 34.2);
        dest = new GeographicalPoint(41.533, 47.23, 35.2);
        path = new VehiclePath(or, dest, 34, StreetType.ASFALTO, 3.4, 56.7, 2.3,"Street1");
    }
    
    @AfterEach
    public void tearDown() {
        path = new VehiclePath(or, dest, 34, StreetType.ASFALTO, 3.4, 56.7, 2.3, "Street1");
    }

    /**
     * Test of getTotalWeight method, of class VehiclePath.
     */
    @Test
    public void testGetTotalWeight() {
        assertEquals(56.7, path.getTotalWeight());
    }

    /**
     * Test of getVehicleAerodynamicCoef method, of class VehiclePath.
     */
    @Test
    public void testGetVehicleAerodynamicCoef() {
        assertEquals(2.3, path.getVehicleAerodynamicCoef());
    }

    /**
     * Test of setTotalWeight method, of class VehiclePath.
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
     * Test of setVehicleAerodynamicCoef method, of class VehiclePath.
     */
    @Test
    public void testSetVehicleAerodynamicCoef() {
        path.setVehicleAerodynamicCoef(4.5);
        assertEquals(4.5, path.getVehicleAerodynamicCoef());
        
        boolean flag = false;
        try{
        path.setVehicleAerodynamicCoef(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        flag = false;
        try{
        path.setVehicleAerodynamicCoef(-5);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of getCost method, of class VehiclePath.
     */
    @Test
    public void testGetCost() {
        assertEquals(1.1, path.getCost(), 0.1);
    }
    
}
