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
        path = new Pathway(or, dest,StreetType.ASPHALT, 34, new Wind(1,1,1), "noName",VehicleCategory.SCOOTER);
    }
    
   
    

   
    
    @AfterEach
    public void tearDown() {
        path = new Pathway(or, dest,StreetType.ASPHALT, 34, new Wind(1,1,1), "noName",VehicleCategory.SCOOTER);
    }

    
    @Test
    public void testPathway() {
        Pathway temp = new Pathway(or, dest, StreetType.ASPHALT, 34, new Wind(1,1,1), "Street2",VehicleCategory.SCOOTER);
        assertEquals("Street2", temp.getStreet());
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
        assertEquals(0.5, path.getKineticCoef());
    }

    /**
     * Test of getWind method, of class Pathway.
     */
    @Test
    public void testGetWind() {

        assertEquals(1, path.getWind().vx);
        assertEquals(1, path.getWind().vy);
        assertEquals(1, path.getWind().vz);
    }

    /**
     * Test of getElevationDif method, of class Pathway.
     */
    @Test
    public void testGetAltitudeDif() {
        assertEquals(1, path.getElevationDif());
    }
    
    @Test
    public void testSetStreet() {
        path.setStreet("Street1");
        assertEquals("Street1", path.getStreet());
        
        boolean flag = false;
        try{
            path.setStreet("");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals("Street1", path.getStreet());
        
        path.setStreet(null);
        assertNull(path.getStreet());
  
        
        
    }
    
    @Test
    public void testSetStreetType() {
        path.setStreetType(StreetType.ASPHALT);
        assertEquals(StreetType.ASPHALT, path.getStreetType());
        
        path.setStreetType(StreetType.SIDEWALK);
        assertEquals(StreetType.SIDEWALK, path.getStreetType());
        
        path.setStreetType(StreetType.PARALELO);
        assertEquals(StreetType.PARALELO, path.getStreetType());
        
        path.setStreetType(StreetType.OFF_ROAD);
        assertEquals(StreetType.OFF_ROAD, path.getStreetType());
        
        path.setStreetType(null);
        assertNull(path.getStreetType());
        
        
        
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
        path.setKineticCoef(0.4);
        assertEquals(0.4, path.getKineticCoef());
        boolean flag = false;
        try{
            path.setKineticCoef(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(0.4, path.getKineticCoef());
        
        path.setKineticCoef(0);
        assertEquals(0, path.getKineticCoef());
    }

    /**
     * Test of setWind method, of class Pathway.
     */
    @Test
    public void testSetWind() {
        path.setWind(new Wind(2,3,4));
        assertEquals(2, path.getWind().vx);
        assertEquals(3, path.getWind().vy);
        assertEquals(4, path.getWind().vz);
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
        Pathway p3 = new Pathway(p1, p2,StreetType.ASPHALT, 34, new Wind(1,1,1), "noName",VehicleCategory.SCOOTER);
       
        
        p3 = new Pathway(p1, p2, StreetType.ASPHALT, 34, new Wind(2,1,1), "noName",VehicleCategory.SCOOTER);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(p1, p2, StreetType.ASPHALT, 35, new Wind(1,1,1), "noName",VehicleCategory.SCOOTER);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(or, dest,StreetType.SIDEWALK, 34, new Wind(1,1,1), "noName",VehicleCategory.SCOOTER);
        assertFalse(p3.equals(path));
        
        p3 = new Pathway(or, dest,StreetType.ASPHALT, 34, new Wind(1,2,1), "noName",VehicleCategory.SCOOTER);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(or, dest,StreetType.ASPHALT, 34, new Wind(1,1,2), "noName",VehicleCategory.SCOOTER);
        assertFalse(path.equals(p3));
        
        p3 = new Pathway(or, dest,StreetType.ASPHALT, 34, new Wind(1,1,1), "noName",VehicleCategory.DRONE);
        assertFalse(path.equals(p3));
        
        
    }
    
}
