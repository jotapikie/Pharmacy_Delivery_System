/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
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
public class NewVehicleControllerTest {
    
    private static NewVehicleController controller;
    private static VehicleDB vdb;
    
    private static int idPharmacy = 1;
    
    private static EScooter scooter;
    private static Drone drone;
    
    private static Set<Vehicle> vehicles;
    
    public NewVehicleControllerTest() {
        NewVehicleController c1 = new NewVehicleController(idPharmacy);
    }
    
    @BeforeAll
    public static void setUpClass() {
        scooter = new EScooter(1, State.LOCKED, 50, 50);
        drone = new Drone(2, State.LOCKED, 40, 40);
        vehicles = new HashSet<>();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        vdb = mock(VehicleDB.class);
        controller = new NewVehicleController(vdb, idPharmacy);
        
        when(vdb.newEScooter(1, State.LOCKED, 50, 50)).thenReturn(scooter);
        when(vdb.newEScooter(2, State.LOCKED, 50, 50)).thenReturn(scooter);
        when(vdb.newDrone(1, State.LOCKED, 40, 40)).thenReturn(drone);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of newEScooter method, of class NewVehicleController.
     */
    @Test
    public void testNewEScooter() {
        assertEquals(scooter.toString(), controller.newEScooter(50,50));
    }

    /**
     * Test of newDrone method, of class NewVehicleController.
     */
    @Test
    public void testNewDrone() {
        assertEquals(drone.toString(), controller.newDrone(40,40));
    }



    /**
     * Test of addVehicleToQueue method, of class NewVehicleController.
     */
    @Test
    public void testAddVehicleToQueue() {
        assertFalse(controller.addVehicleToQueue());
        
        controller.newEScooter(50,50);
        assertTrue(controller.addVehicleToQueue());
        
        controller.newEScooter(50,50);
        assertFalse(controller.addVehicleToQueue());
        
    }

    /**
     * Test of registerVehicles method, of class NewVehicleController.
     */
    @Test
    public void testRegistVehicles() throws Exception {
            assertEquals(0, controller.registerVehicles());
            
            controller.newEScooter(50,50);
            controller.addVehicleToQueue();
            vehicles.add(scooter);
            when(vdb.save(vehicles, idPharmacy)).thenReturn(1);
            assertEquals(1, controller.registerVehicles());
    }
    
}
