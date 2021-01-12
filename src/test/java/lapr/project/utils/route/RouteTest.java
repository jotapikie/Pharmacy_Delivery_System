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
        Pathway p = new Pathway(or, dest, "Rua de Teste", 34, 5.6);
        instance = new Route(p);
       
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    /**
     * Test of getPaths method, of class Route.
     */
    @Test
    public void testGetPaths() {
        assertEquals(1, instance.getPaths().size());
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
        assertEquals(34, instance.getRouteDistance());
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
        Pathway p = new Pathway(dest, p2, "Rua de Teste", 24, 5.6);
        
        GeographicalPoint p3 = new GeographicalPoint(42.67, 45.23, 34.2);
        Pathway pp = new Pathway(p2, p3, "Ru de Teste", 56, 5.8);
        
        List<Pathway> list = new ArrayList<>();
        list.add(p);
        list.add(pp);
        
        instance.addPaths(list);
        assertEquals(3, instance.getPaths().size());
        
        
    }

    /**
     * Test of addPath method, of class Route.
     */
    @Test
    public void testAddPath() {
        
        GeographicalPoint p2 = new GeographicalPoint(43.533, 47.23, 35.2);
        Pathway p = new Pathway(dest, p2, "Rua de Teste", 24, 5.6);
        instance.addPath(p);
        assertEquals(2, instance.getPaths().size());
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
        
    }

    /**
     * Test of compareTo method, of class Route.
     */
    @Test
    public void testCompareTo() {
        GeographicalPoint ori = new GeographicalPoint(41.236, 44.23, 12.2);
        GeographicalPoint desti = new GeographicalPoint(41.539, 46.23, 25.2);
        Pathway p = new Pathway(ori, desti, "Rua de Teste", 24, 6.5);
        Route other = new Route(p);
        assertTrue(instance.compareTo(other)<0);
    }

    /**
     * Test of equals method, of class Route.
     */
    @Test
    public void testEquals() {
        GeographicalPoint ori = new GeographicalPoint(41.236, 44.23, 12.2);
        GeographicalPoint desti = new GeographicalPoint(41.539, 46.23, 25.2);
        Pathway p = new Pathway(ori, desti, "Rua de Teste", 24, 6.5);
        Route other = new Route(p);
        assertFalse(instance.equals(other));
    }

    /**
     * Test of hashCode method, of class Route.
     */
    @Test
    public void testHashCode() {

    }
    
}
