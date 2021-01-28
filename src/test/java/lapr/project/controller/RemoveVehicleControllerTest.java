/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import lapr.project.utils.Utils;
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
public class RemoveVehicleControllerTest {
    
    private static RemoveVehicleController controller;
    private static VehicleDB vdb;
    private static int id = 1;
    
    private static EScooter v1;
    private static EScooter v2;
    private static Drone v3;
    private static Drone v4;
    private static List<Vehicle> vehicles;
    
    
    public RemoveVehicleControllerTest() {
        RemoveVehicleController c1 = new RemoveVehicleController(id);
    }
    
    @BeforeAll
    public static void setUpClass() {
        vehicles = new ArrayList<>();
        v1 = new EScooter(1, State.ACTIVE, 2.3, 1.8);
        v2 = new EScooter(2, State.ACTIVE, 2.6, 1.5);
        v3 = new Drone(3, State.LOCKED, 3.4, 1.2);
        v4 = new Drone(4, State.LOCKED, 3.2, 1.3);
        vehicles.add(v1);
        vehicles.add(v2);
        vehicles.add(v3);
        vehicles.add(v4);
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        vdb = mock(VehicleDB.class);
        controller = new RemoveVehicleController(vdb, id);
        when(vdb.getVehicles(id)).thenReturn(vehicles);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getVehicles method, of class RemoveVehicleController.
     */
    @Test
    public void testGetVehicles() throws Exception {
        assertEquals(Utils.listToString(vehicles), controller.getVehicles());
    }

    /**
     * Test of selectVehicle method, of class RemoveVehicleController.
     */
    @Test
    public void testSelectVehicle() throws SQLException {
        controller.getVehicles();
        assertNull(controller.selectVehicle(7));
        assertEquals(v1.toString(), controller.selectVehicle(1));
        assertEquals(v2.toString(), controller.selectVehicle(2));
        assertEquals(v3.toString(), controller.selectVehicle(3));
        assertEquals(v4.toString(), controller.selectVehicle(4));
    }

    /**
     * Test of remove method, of class RemoveVehicleController.
     */
    @Test
    public void testRemove() throws Exception {
        assertFalse(controller.remove());
        controller.getVehicles();
        controller.selectVehicle(1);
        
        when(vdb.remove(1, id)).thenReturn(true);
        assertTrue(controller.remove());
        
        controller.selectVehicle(1);
        when(vdb.remove(1, id)).thenReturn(false);
        assertFalse(controller.remove());
    }
    
}
