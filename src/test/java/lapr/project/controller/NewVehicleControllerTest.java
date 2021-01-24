package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.VehicleDB;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class NewVehicleControllerTest {

    private static NewVehicleController nvc2;
    private static NewVehicleController nvc;
    private static NewVehicleController controller;
    private static VehicleDB vdb;
    private static int pharmID= 123;

    @BeforeAll
    public static void setUpClass() {
        vdb = mock(VehicleDB.class);
        nvc = new NewVehicleController();
        nvc2 = new NewVehicleController(vdb, pharmID);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        controller = new NewVehicleController(vdb, pharmID);
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    public NewVehicleControllerTest() {
    }

    /**
     * Test of newEScooter method, of class NewVehicleController.
     */
    @Test
    public void testNewEScooter() {
        int id = 27;
        State state= State.ACTIVE;
        int maxBat = 8;
        int actualBat = 5;

        EScooter value = new EScooter(id,state,maxBat,actualBat);
        when(vdb.newEScooter(anyInt(),any(),anyInt(),anyInt())).thenReturn(value);

        EScooter expResult = new EScooter(id, state, maxBat, actualBat);
        EScooter result = nvc2.newEScooter(id, maxBat);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerVehicle method, of class NewVehicleController.
     */
    @Test
    public void testRegisterVehicle() throws SQLException {
        NewVehicleController nvc3 = new NewVehicleController(vdb, pharmID);
        int id = 3;
        int maxBat = 8;
        nvc3.newEScooter(id,maxBat);
        when(vdb.save(any(Vehicle.class), anyInt())).thenReturn(true);

        boolean expResult = true;
        boolean result = nvc3.registerVehicle();
        assertEquals(expResult, result);
        
        when(vdb.save(any(Vehicle.class), anyInt())).thenReturn(false);
        assertFalse(nvc3.registerVehicle());

    }

    /**
     * Test of addVehicleToQueue method, of class NewVehicleController.
     */
    @Test
    public void testAddVehicleToQueue() {
        NewVehicleController nvc2 = new NewVehicleController();
        EScooter b = nvc2.newEScooter(1, 3);
        nvc2.addVehicleToQueue();
        assertEquals(1, nvc2.getVehicles().size());
    }

    /**
     * Test of insertVehiclesBatchOp method, of class NewVehicleController.
     */
    @Test
    public void testInsertVehiclesBatchOp() throws Exception {
        int numInserts = nvc2.insertVehiclesBatchOp();

        when(vdb.save(nvc2.getVehicles(),pharmID)).thenReturn(numInserts);

        assertEquals(0, nvc2.getVehicles().size());
        assertEquals(numInserts, numInserts);
        
        assertEquals(0, controller.insertVehiclesBatchOp());
  
        
      }

    /**
     * Test of getVehicles method, of class NewVehicleController.
     */
    @Test
    public void testGetVehicles() {
        int id = 3;
        int maxBat = 8;

        EScooter b = nvc.newEScooter(id,maxBat);
        HashSet<Vehicle> vehicles = new HashSet<>();
        vehicles.add(b);
        nvc.addVehicleToQueue();
        assertEquals(vehicles, nvc.getVehicles());
    }

    /**
     * Test of newDrone method, of class NewVehicleController.
     */
    @Test
    public void testNewDrone() {
        int id = 27;
        State state= State.ACTIVE;
        int maxBat = 8;
        int actualBat = 5;

        Drone value = new Drone(id,state,maxBat,actualBat);
        when(vdb.newDrone(anyInt(),any(),anyInt(),anyInt())).thenReturn(value);

        Drone expResult = new Drone(id, state, maxBat, actualBat);
        Drone result = nvc2.newDrone(id, maxBat);
        assertEquals(expResult, result);
  
    }

}
