/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;
import java.util.Set;
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
public class ParkTest {
    
    private static Park p1;
    private static Park p2;
    
    private static Set<ParkSlot> slots1;
    private static Set<ParkSlot> slots2;
    
    
    public ParkTest() {
        Park temp = new Park(23, 12, VehicleCategory.DRONE, 35, slots1);
        assertEquals(23, temp.getId());
        assertEquals(12, temp.getMaxVehicles());
        assertEquals(VehicleCategory.DRONE, temp.getType());
        assertEquals(35, temp.getMaxEnergy());
        assertEquals(35, temp.getCurrentEnergy());
        assertEquals(slots1, temp.getSlots());
    }
    
    @BeforeAll
    public static void setUpClass() {
        slots1 = new HashSet<>();
        slots1.add(new ParkSlot(1, true));
        slots1.add(new ParkSlot(2, true));
        slots1.add(new ParkSlot(3, false));
        
        
        slots2 = new HashSet<>();
        slots2.add(new ParkSlot(4, true));
        slots2.add(new ParkSlot(5, true));
        slots2.add(new ParkSlot(6, false));
        slots2.add(new ParkSlot(7, false));
    }
    

    
    @BeforeEach
    public void setUp() {
        p1 = new Park(1, 3, VehicleCategory.SCOOTER, 65, slots1);
        p2 = new Park(2, 4, VehicleCategory.DRONE, 43, slots2);
    }
    


    /**
     * Test of getId method, of class Park.
     */
    @Test
    public void testGetId() {
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }

    /**
     * Test of getnMaxVehicles method, of class Park.
     */
    @Test
    public void testGetnMaxVehicles() {
        assertEquals(3, p1.getMaxVehicles());
        assertEquals(4, p2.getMaxVehicles());
    }

    /**
     * Test of getMaxEnergy method, of class Park.
     */
    @Test
    public void testGetMaxEnergy() {
        assertEquals(65, p1.getMaxEnergy());
        assertEquals(43, p2.getMaxEnergy());
    }

    /**
     * Test of getCurrentEnergy method, of class Park.
     */
    @Test
    public void testGetCurrentEnergy() {
        assertEquals(65, p1.getCurrentEnergy());
        assertEquals(43, p2.getCurrentEnergy());
    }

    /**
     * Test of getType method, of class Park.
     */
    @Test
    public void testGetType() {
        assertEquals(VehicleCategory.SCOOTER, p1.getType());
        assertEquals(VehicleCategory.DRONE, p2.getType());
    }

    /**
     * Test of getSlots method, of class Park.
     */
    @Test
    public void testGetSlots() {
        assertEquals(slots1, p1.getSlots());
        assertEquals(slots2, p2.getSlots());
    }

    /**
     * Test of setId method, of class Park.
     */
    @Test
    public void testSetId() {
        p1.setId(35);
        assertEquals(35, p1.getId());
        
        p2.setId(56);
        assertEquals(56, p2.getId());
        
        boolean flag = false;
        try{
            p1.setId(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(35, p1.getId());
        
        flag = false;
        try{
            p2.setId(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(56, p2.getId());
        
        
    }

    /**
     * Test of setnMaxVehicles method, of class Park.
     */
    @Test
    public void testSetnMaxVehicles() {
        p1.setMaxVehicles(12);
        assertEquals(12, p1.getMaxVehicles());
        
        p2.setMaxVehicles(8);
        assertEquals(8, p2.getMaxVehicles());
        
        boolean flag = false;
        try{
            p1.setMaxVehicles(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(12, p1.getMaxVehicles());
        
         flag = false;
        try{
            p2.setMaxVehicles(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(8, p2.getMaxVehicles());
 
    }

    /**
     * Test of setMaxEnergy method, of class Park.
     */
    @Test
    public void testSetMaxEnergy() {
        p1.setMaxEnergy(23);
        assertEquals(23, p1.getMaxEnergy());
        
        p2.setMaxEnergy(89);
        assertEquals(89, p2.getMaxEnergy());
        
        boolean flag = false;
        try{
            p1.setMaxEnergy(-3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(23, p1.getMaxEnergy());
        
        flag = false;
        try{
            p2.setMaxEnergy(-5);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(89, p2.getMaxEnergy());
        
        
    }

    /**
     * Test of setType method, of class Park.
     */
    @Test
    public void testSetType() {
           p1.setType(VehicleCategory.DRONE);
           assertEquals(VehicleCategory.DRONE, p1.getType());
           
           p2.setType(VehicleCategory.SCOOTER);
           assertEquals(VehicleCategory.SCOOTER, p2.getType());
           
           boolean flag = false;
           try{
               p1.setType(null);
           }catch(IllegalArgumentException e){
               flag = true;
           }
           assertTrue(flag);
           assertEquals(VehicleCategory.DRONE, p1.getType());
    }

    /**
     * Test of setCurrentEnergy method, of class Park.
     */
    @Test
    public void testSetCurrentEnergy() {
        p1.setCurrentEnergy(22);
        assertEquals(22, p1.getCurrentEnergy());
        
        p2.setCurrentEnergy(0);
        assertEquals(0, p2.getCurrentEnergy());
        
        boolean flag = false;
        try{
            p1.setCurrentEnergy(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(22, p1.getCurrentEnergy());
        
        flag = false;
        try{
            p1.setCurrentEnergy(300);
        }catch(IllegalArgumentException e){
           flag = true;
        }
        assertTrue(flag);
        assertEquals(22, p1.getCurrentEnergy());
        
        
    }

    /**
     * Test of setSlots method, of class Park.
     */
    @Test
    public void testSetSlots() {
            p2.setSlots(slots1);
            assertEquals(slots1, p2.getSlots());
            
           boolean flag = false;
           try{
               p2.setSlots(null);
           }catch(IllegalArgumentException e){
               flag = true;
           }
           assertTrue(flag);
           assertEquals(slots1, p2.getSlots());
           
           flag = false;
           try{
               p1.setSlots(slots2);
           }catch(IllegalArgumentException e){
               flag = true;
           }
           assertTrue(flag);
           assertEquals(slots1, p1.getSlots());
    }

    /**
     * Test of equals method, of class Park.
     */
    @Test
    public void testEquals() {
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(null));
        assertFalse(p1.equals(slots1));
        assertFalse(p1.equals(p2));
        p1 = new Park(1, 3, VehicleCategory.SCOOTER, 65, slots1);
        p2 = new Park(1, 3, VehicleCategory.SCOOTER, 65, slots1);
        assertTrue(p1.equals(p2));
    }

    /**
     * Test of hashCode method, of class Park.
     */
    @Test
    public void testHashCode() {
        assertEquals(94, p1.hashCode());
        assertEquals(95, p2.hashCode());
    }

    /**
     * Test of toString method, of class Park.
     */
    @Test
    public void testToString() {
        assertNotNull(p1.toString());
        assertFalse(p2.toString().isEmpty());
    }
    
}
