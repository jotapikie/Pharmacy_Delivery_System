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
public class ParkSlotTest {
    
    
    private static ParkSlot p1;
    private static ParkSlot p2;
    private static EScooter scooter;
    
    public ParkSlotTest() {
        ParkSlot temp = new ParkSlot(45, true);
        assertEquals(45, temp.getId());
        assertTrue(temp.isAbleToCharge());
        assertNull(temp.getVehicle());
        
        temp = new ParkSlot(38, false);
        assertEquals(38, temp.getId());
        assertFalse(temp.isAbleToCharge());
        assertNull(temp.getVehicle());
        
        ParkSlot temp1 = new ParkSlot(25, true, scooter);
        assertEquals(25, temp1.getId());
        assertTrue(temp1.isAbleToCharge());
        assertEquals(scooter, temp1.getVehicle());
        
        temp1 = new ParkSlot(27, false, scooter);
        assertEquals(27, temp1.getId());
        assertFalse(temp1.isAbleToCharge());
        assertEquals(scooter, temp1.getVehicle());
        
        
    }
    
    @BeforeAll
    public static void setUpClass() {
        scooter = new EScooter(1, State.CHARGING, 67, 43);
        
    }
    

    
    @BeforeEach
    public void setUp() {
         p1 = new ParkSlot(1, true);
         p2 = new ParkSlot(2, true, scooter);
    }


    /**
     * Test of getId method, of class ParkSlot.
     */
    @Test
    public void testGetId() {
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }

    /**
     * Test of isAbleToCharge method, of class ParkSlot.
     */
    @Test
    public void testIsAbleToCharge() {
        assertTrue(p1.isAbleToCharge());
        assertTrue(p2.isAbleToCharge());
    }

    /**
     * Test of getScooter method, of class ParkSlot.
     */
    @Test
    public void testGetVehicle() {
        assertNull(p1.getVehicle());
        assertEquals(scooter, p2.getVehicle());
    }

    /**
     * Test of setId method, of class ParkSlot.
     */
    @Test
    public void testSetId() {
        p1.setId(3);
        assertEquals(3, p1.getId());
        
        p2.setId(4);
        assertEquals(4, p2.getId());
        
        boolean flag = false;
        try{
            p1.setId(-5);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(3, p1.getId());
        
        flag = false;
        try{
            p2.setId(-5);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(4, p2.getId());
    }

    /**
     * Test of setAbleToCharge method, of class ParkSlot.
     */
    @Test
    public void testSetAbleToCharge() {
        p1.setAbleToCharge(false);
        assertFalse(p1.isAbleToCharge());
        
        p2.setAbleToCharge(false);
        assertFalse(p1.isAbleToCharge());
    }

    /**
     * Test of setScooter method, of class ParkSlot.
     */
    @Test
    public void testSetScooter() {
        p1.setVehicle(scooter);
        assertEquals(scooter, p1.getVehicle());
        p1.setVehicle(null);
        assertNull(p1.getVehicle());
        
        p2.setVehicle(null);
        assertNull(p2.getVehicle());
        p2.setVehicle(scooter);
        assertEquals(scooter, p2.getVehicle());
    }
    
}
