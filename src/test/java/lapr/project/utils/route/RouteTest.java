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
import lapr.project.model.StreetType;
import lapr.project.model.ScooterPath;
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
     
     Route instance3;
     GeographicalPoint or3;
     GeographicalPoint dest3;
     
     
    
    @BeforeEach
    public void setUp() {
        or = new GeographicalPoint(41.233, 45.23, 34.2);
        dest = new GeographicalPoint(41.533, 47.23, 35.2);
        Pathway p = new Pathway(or, dest, StreetType.ASPHALT, 45, 2.3, "rua1");
        instance = new Route(p);
        
        Route temp = new Route(instance);
        
        or3 = new GeographicalPoint(41.233, 45.23, 34.2);
        dest3 = new GeographicalPoint(41.533, 47.23, 35.2);
        ScooterPath p3 = new ScooterPath(or, dest3, 34, StreetType.ASPHALT, 2.2, 56.8, 3.4,"Street1");
        instance3 = new Route(p3);
        
        
       
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
        
        assertEquals(1, instance3.getPaths().size());
        
        
    }

    /**
     * Test of getRouteCost method, of class Route.
     */
    @Test
    public void testGetRouteCost() {
        assertEquals(45, instance.getTotalEnergy());
        assertEquals(45, instance.getMinimumEnergy());
    }

    /**
     * Test of getRouteDistance method, of class Route.
     */
    @Test
    public void testGetRouteDistance() {
        assertEquals(45, instance.getTotalDistance());
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
        Pathway p = new Pathway(dest, p2, StreetType.ASPHALT, 34,45, "street1");
        
        GeographicalPoint p3 = new GeographicalPoint(42.67, 45.23, 34.2);
        Pathway pp = new Pathway(p2, p3, StreetType.ASPHALT, 45, 56, "street1");
        
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
        Pathway p = new Pathway(dest, p2, StreetType.ASPHALT, 9.6, 2.7, "RuaSemNome");
        instance.addPath(p);
        assertEquals(2, instance.getPaths().size());
        assertEquals(instance.getTotalEnergy(), instance.getEnergyToReachChargingPoint());
        assertEquals(54.6, instance.getMinimumEnergy());
        
        boolean flag = false;
        try{
        instance.addPath(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        p = new Pathway(or, or, StreetType.ASPHALT, 9.5, 2.6, "Street");
        try{
        instance.addPath(p);
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
        Pathway p = new Pathway(ori, desti, StreetType.ASPHALT, 5.6, 24.5, "rua Sesamo");
        Route other = new Route(p);
        assertTrue(instance.compareTo(other)>0);
        
        boolean flag = false;
        try{
            instance.compareTo(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        GeographicalPoint p1 = new GeographicalPoint(41.233, 45.23, 34.2);
        GeographicalPoint p2 = new GeographicalPoint(41.533, 47.23, 35.2);
        ScooterPath p3 = new ScooterPath(p1, p2, 45, StreetType.ASPHALT, 3.2, 5.6, 56.5,"Street1");
        Route t = new Route(p3);
        System.out.println(t.getTotalEnergy()+ " | " + instance3.getTotalEnergy() + " | " +instance.getTotalEnergy());
        assertTrue(instance3.compareTo(t) > 0);
        assertTrue(t.compareTo(instance3)<0);
        
        assertTrue(t.compareTo(t)==0);
        
        p3 = new ScooterPath(p1, p2, 22, StreetType.ASPHALT, 3.2, 5.6, 56.5,"Street1");
        t = new Route(p3);
        Pathway p4 = new Pathway(p1, p2, StreetType.ASPHALT, t.getTotalEnergy(), 23, "NoName");
        Route t1 = new Route(p4);
        assertTrue(t1.compareTo(t) <0);
        assertTrue(t.compareTo(t1)> 0);
        
        Pathway p8 = new Pathway(p1, p2, StreetType.ASPHALT, 50, 0.3, "Rua2");
        Pathway p9 = new Pathway(p1, p2, StreetType.ASPHALT, 25, 0.3, "Rua3");
        Pathway p10 = new Pathway(p2, dest, StreetType.ASPHALT,25, 0.3, "Rua4");
        t = new Route(p8);
        t1 = new Route(p9);
        t1.addPath(p10);
        assertTrue(t1.compareTo(t)>0);
        assertTrue(t.compareTo(t1)<0);
        
        assertEquals(0,t.compareTo(t));
        


        
        
        
        
        
        
    }

    /**
     * Test of equals method, of class Route.
     */
    @Test
    public void testEquals() {
        GeographicalPoint ori = new GeographicalPoint(41.236, 44.23, 12.2);
        GeographicalPoint desti = new GeographicalPoint(41.539, 46.23, 25.2);
        Pathway p = new Pathway(ori, desti, StreetType.ASPHALT, 3.5, 2.7, "Rua5");
        Route other = new Route(p);
        assertFalse(instance.equals(other));
        
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(null));
        assertFalse(instance.equals(p));
    }
    
    @Test
    public void testHash(){
        assertTrue(instance.hashCode() != 0);
    }

 
    
}
