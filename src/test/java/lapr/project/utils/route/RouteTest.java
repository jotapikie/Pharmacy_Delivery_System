/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class RouteTest {
    
    Route instance;
     GeographicalPoint or;
     GeographicalPoint dest;
    
    @BeforeEach
    public void setUp() {
        or = new GeographicalPoint(41.233, 45.23, 34.2);
        dest = new GeographicalPoint(41.533, 47.23, 35.2);
        Pathway p = new Pathway(or, dest, 45, 5.6, 3.2, 3.4);
        instance = new Route(p);
        
        Route temp = new Route(instance);
        
        
       
    }
    


    /**
     * Test of getPaths method, of class Route.
     */
    @Test
    public void testGetPaths() {
        assertEquals(1, instance.getPaths().size());
        
        Pathway p = null;
        boolean flag = false;
        try{
        Route r = new Route(p);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
    }

    /**
     * Test of getRouteEnergy method, of class Route.
     */
    @Test
    public void testGetRouteEnergy() {
        assertEquals(5.6, instance.getRouteEnergy());
    }

    /**
     * Test of getRouteDistance method, of class Route.
     */
    @Test
    public void testGetRouteDistance() {
        assertEquals(45, instance.getRouteDistance());
    }

    /**
     * Test of getOrigin method, of class Route.
     */
    @Test
    public void testGetOrigin() {
        assertEquals(or, instance.getOrigin());
    }

    /**
     * Test of getDestination method, of class Route.
     */
    @Test
    public void testGetDestination() {
        assertEquals(dest, instance.getDestination());
    }

    /**
     * Test of getNumGeographicalPoints method, of class Route.
     */
    @Test
    public void testGetNumGeographicalPoints() {
        assertEquals(2, instance.getNumGeographicalPoints());
    }

    /**
     * Test of addPaths method, of class Route.
     */
    @Test
    public void testAddPaths() {
        GeographicalPoint p2 = new GeographicalPoint(43.533, 47.23, 35.2);
        Pathway p = new Pathway(dest, p2, 23, 4.5, 3.5, 2.6);
        
        GeographicalPoint p3 = new GeographicalPoint(42.67, 45.23, 34.2);
        Pathway pp = new Pathway(p2, p3, 23.4, 8.9, 2.5, 2.5);
        
        List<Pathway> list = new ArrayList<>();
        list.add(p);
        list.add(pp);
        
        instance.addPaths(list);
        assertEquals(3, instance.getPaths().size());
        
        list.clear();
        instance.addPaths(list);
        assertEquals(3, instance.getPaths().size());
        
        boolean flag = false;
        try{
        instance.addPaths(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        
        assertTrue(flag);
        
        
        
    }

    /**
     * Test of addPath method, of class Route.
     */
    @Test
    public void testAddPath() {
        
        GeographicalPoint p2 = new GeographicalPoint(43.533, 47.23, 35.2);
        Pathway p = new Pathway(dest, p2, 2.6, 9.6, 2.7, 2.4);
        instance.addPath(p);
        assertEquals(2, instance.getPaths().size());
        
        boolean flag = false;
        try{
        instance.addPath(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        
        assertTrue(flag);
    }

    /**
     * Test of contains method, of class Route.
     */
    @Test
    public void testContains() {
        GeographicalPoint p1 = new GeographicalPoint(42.233, 45.23, 34.2);
        
        Set<GeographicalPoint> lst = new HashSet<>();
        lst.add(or);
        lst.add(dest);
        assertTrue(instance.contains(lst));
        lst.add(p1);
        assertFalse(instance.contains(lst));
        
        lst.clear();
        assertTrue(instance.contains(lst));
        
        boolean flag = false;
        try{
        instance.contains(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        
    }

    /**
     * Test of compareTo method, of class Route.
     */
    @Test
    public void testCompareTo() {
        GeographicalPoint ori = new GeographicalPoint(41.236, 44.23, 12.2);
        GeographicalPoint desti = new GeographicalPoint(41.539, 46.23, 25.2);
        Pathway p = new Pathway(ori, desti, 3.4, 5.6, 24.5, 2.5);
        Route other = new Route(p);
        assertTrue(instance.compareTo(other)>0);
        
        boolean flag = false;
        try{
            instance.compareTo(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of equals method, of class Route.
     */
    @Test
    public void testEquals() {
        GeographicalPoint ori = new GeographicalPoint(41.236, 44.23, 12.2);
        GeographicalPoint desti = new GeographicalPoint(41.539, 46.23, 25.2);
        Pathway p = new Pathway(ori, desti, 7.4, 3.5, 2.7, 3.5);
        Route other = new Route(p);
        assertFalse(instance.equals(other));
        
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(p));
    }

 
    
}
