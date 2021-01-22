/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.ParkDB;
import lapr.project.model.Park;
import lapr.project.model.ParkSlot;
import lapr.project.model.VehicleCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Diogo
 */
public class AddParkControllerTest {
    
    private static AddParkController controller;
    private static ParkDB pdb;
    private static int idPharmacy ;
    
    private static Park p1;
    private static Park p2;
    
    private static Set<Park> parks;
    
    @BeforeAll
    public static void setUpClass() {
        parks = new HashSet<>();
        idPharmacy = 1;
        Set<ParkSlot> slots = new HashSet<>();
        slots.add(new ParkSlot(1, true));
        slots.add(new ParkSlot(2, true));
        slots.add(new ParkSlot(3, false));
        p1 = new Park(1, 3, VehicleCategory.SCOOTER, 65, slots);
        
        Set<ParkSlot> slots1 = new HashSet<>();
        slots1.add(new ParkSlot(4, true));
        slots1.add(new ParkSlot(5, false));
        p2 = new Park(2, 2, VehicleCategory.DRONE, 45, slots1);
                
    }
    

    
    @BeforeEach
    public void setUp() {
        pdb = mock(ParkDB.class);
        controller = new AddParkController(pdb, idPharmacy);
        
        when(pdb.newPark(1,3, 2, "Scooter", 65)).thenReturn(p1);
        when(pdb.newPark(2,3, 2, "Scooter", 65)).thenReturn(p1);
        when(pdb.newPark(1,2, 1, "Drone", 45)).thenReturn(p2);
        when(pdb.newPark(2,2, 1, "Drone", 45)).thenReturn(p2);
        when(pdb.newPark(3,6, 4, "Scooter", 6)).thenReturn(null);
    }
    
   

    /**
     * Test of newPark method, of class AddParkController.
     */
    @Test
    public void testNewPark() {
        assertEquals(p1.toString(), controller.newPark(3, 2, "Scooter", 65));
        assertEquals(p2.toString(), controller.newPark(2, 1, "Drone", 45));
        assertNull(controller.newPark(6, 4, "Scooter", 6));
    }

    /**
     * Test of addToQueue method, of class AddParkController.
     */
    @Test
    public void testAddToQueue() {
        assertFalse(controller.addToQueue());
        
        controller.newPark(3, 2, "Scooter", 65);
        assertTrue(controller.addToQueue());
        
        controller.newPark(3, 2, "Scooter", 65);
        assertFalse(controller.addToQueue());
    }

    /**
     * Test of saveParks method, of class AddParkController.
     */
    @Test
    public void testSaveParks() throws Exception {
         assertEquals(0, controller.saveParks());
         
         controller.newPark(3, 2, "Scooter", 65);
         controller.addToQueue();
         parks.add(p1);
         when(pdb.saveParks(parks, idPharmacy)).thenReturn(1);
         assertEquals(1, controller.saveParks());
         
         controller.newPark(2, 1, "Drone", 45);
         controller.addToQueue();
         parks.add(p2);
         when(pdb.saveParks(parks, idPharmacy)).thenReturn(2);
         assertEquals(2, controller.saveParks());
         
         
    }
    
}
