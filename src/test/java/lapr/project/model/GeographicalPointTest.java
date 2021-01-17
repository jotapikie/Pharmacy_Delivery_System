/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class GeographicalPointTest {
    

    private GeographicalPoint instance;
    

    
    @BeforeEach
    public void setUp() {
        instance = new GeographicalPoint(45.7, 23.5, 200);
    }
    


    /**
     * Test of setLongitude method, of class GeographicalPoint.
     */
    @Test
    public void testSetLongitude() {
        
        instance.setLongitude(23.9);
        assertEquals(23.9, instance.getLongitude());
        
        boolean flag = false;
        try{
            instance.setLongitude(94);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
    }

    /**
     * Test of setLatitude method, of class GeographicalPoint.
     */
    @Test
    public void testSetLatitude() {
        instance.setLatitude(23.9);
        assertEquals(23.9, instance.getLatitude());
        
        boolean flag = false;
        try{
            instance.setLatitude(190);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setElevation method, of class GeographicalPoint.
     */
    @Test
    public void testSetElevation() {
        instance.setElevation(23);
        assertEquals(23, instance.getElevation());
    }
    
    /**
     * Test of setDescprition method, of class GeographicalPoint.
     */
    @Test
    public void testSetDescprition() {
        instance.setDescription("Aliados");
        assertEquals("Aliados", instance.getDescription());
        
        boolean flag = false;
        try{
            instance.setDescription("");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
       flag = false;
        try{
            instance.setDescription(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }
    
    

    /**
     * Test of getLongitude method, of class GeographicalPoint.
     */
    @Test
    public void testGetLongitude() {
        assertEquals(45.7, instance.getLongitude());
    }

    /**
     * Test of getLatitude method, of class GeographicalPoint.
     */
    @Test
    public void testGetLatitude() {
        assertEquals(23.5, instance.getLatitude());
    }

    /**
     * Test of getElevation method, of class GeographicalPoint.
     */
    @Test
    public void testGetElevation() {
        assertEquals(200, instance.getElevation());
    }

    /**
     * Test of getCounter method, of class GeographicalPoint.
     */
    @Test
    public void testGetCounter() {
        assertEquals(0, instance.getCounter());
    }

    /**
     * Test of incrementCounter method, of class GeographicalPoint.
     */
    @Test
    public void testIncrementCounter() {
        assertEquals(0, instance.getCounter());
        instance.incrementCounter();
        instance.incrementCounter();
        assertEquals(2, instance.getCounter());
    }

    /**
     * Test of resetCounter method, of class GeographicalPoint.
     */
    @Test
    public void testResetCounter() {
        assertEquals(0, instance.getCounter());
        instance.incrementCounter();
        instance.incrementCounter();
        assertEquals(2, instance.getCounter());
        instance.resetCounter();
        assertEquals(0, instance.getCounter());
    }
    
}
