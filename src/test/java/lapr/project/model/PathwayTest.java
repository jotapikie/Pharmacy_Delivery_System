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
public class PathwayTest {
    
    private static GeographicalPoint or;
    static GeographicalPoint dest;
    static Pathway path;
    
    @BeforeAll
    public static void setUpClass() {
        or = new GeographicalPoint(41.233, 45.23, 34.2);
        dest = new GeographicalPoint(41.533, 47.23, 35.2);
        path = new Pathway(or, dest,3.4, 34, 3.5);
    }
    

   
    
    @AfterEach
    public void tearDown() {
        path = new Pathway(or, dest,3.4, 34, 3.5);
    }

    /**
     * Test of getOriginPoint method, of class Pathway.
     */
    @Test
    public void testGetOriginPoint() {
        assertEquals(or, path.getOriginPoint());
    }

    /**
     * Test of getDestinationPoint method, of class Pathway.
     */
    @Test
    public void testGetDestinationPoint() {
        assertEquals(dest, path.getDestinationPoint());
    }

    /**
     * Test of getDistance method, of class Pathway.
     */
    @Test
    public void testGetDistance() {
        assertEquals(34, path.getDistance());
    }

    /**
     * Test of getCost method, of class Pathway.
     */
    @Test
    public void testGetCost() {
        assertEquals(34, path.getCost());
    }

    /**
     * Test of getKineticCoef method, of class Pathway.
     */
    @Test
    public void testGetKineticCoef() {
        assertEquals(3.4, path.getKineticCoef());
    }

    /**
     * Test of getWind method, of class Pathway.
     */
    @Test
    public void testGetWind() {
        assertEquals(3.5, path.getWind());
    }

    /**
     * Test of getElevationDif method, of class Pathway.
     */
    @Test
    public void testGetAltitudeDif() {
        assertEquals(1, path.getElevationDif());
    }

    /**
     * Test of setOriginPoint method, of class Pathway.
     */
    @Test
    public void testSetOrigPoint() {
        boolean flag = false;
        try{
            path.setOriginPoint(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setDestinationPoint method, of class Pathway.
     */
    @Test
    public void testSetDestPoint() {
        boolean flag = false;
        try{
            path.setDestinationPoint(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setDistance method, of class Pathway.
     */
    @Test
    public void testSetDistance() {
        boolean flag = false;
        try{
            path.setDistance(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        flag = false;
        try{
            path.setDistance(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setKineticCoef method, of class Pathway.
     */
    @Test
    public void testSetKineticCoef() {
        boolean flag = false;
        try{
            path.setKineticCoef(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setWind method, of class Pathway.
     */
    @Test
    public void testSetWind() {
        path.setWind(2.3);
        assertEquals(2.3, path.getWind());
    }

    /**
     * Test of hashCode method, of class Pathway.
     */
    @Test
    public void testHashCode() {
        assertTrue(path.hashCode() != 0);
    }

    /**
     * Test of equals method, of class Pathway.
     */
    @Test
    public void testEquals() {
        assertTrue(path.equals(path));
        assertFalse(path.equals(null));
        assertFalse(path.equals(or));
        
        GeographicalPoint p1 = new GeographicalPoint(41.233, 45.23, 34.2);
        GeographicalPoint p2 = new GeographicalPoint(41.533, 47.23, 35.2);
        Pathway p3 = new Pathway(p1, p2,3.3, 34, 3.5);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(p1, p2, 3.4, 34, 3.2);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(p1, p2, 3.4, 35, 3.5);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(or, p2, 3.4, 34, 3.5);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(p1, dest, 3.4, 34, 3.5);
        assertFalse(path.equals(p3));
    }
    
}
