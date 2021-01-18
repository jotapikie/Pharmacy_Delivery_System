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
import static org.mockito.Mockito.*;

public class NewVehicleControllerTest {

    private static NewVehicleController nvc2;
    private static NewVehicleController nvc;
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
        int weight = 5;
        State state= State.ACTIVE;
        double aeroCoef = 5.0;
        double frontalArea = 2.0;
        int maxBat = 8;
        int actualBat = 5;
        int motor = 5;

        EScooter value = new EScooter(id,state,maxBat,actualBat);
        when(vdb.newEScooter(anyInt(),any(),anyInt(),anyInt())).thenReturn(value);

        EScooter expResult = new EScooter(id, state, maxBat, actualBat);
        EScooter result = nvc2.newEScooter(id, maxBat, actualBat);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerVehicle method, of class NewVehicleController.
     */
    @Test
    public void testRegisterVehicle() throws SQLException {
        NewVehicleController nvc3 = new NewVehicleController(vdb, pharmID);
        int id = 3;
        int weight = 5;
        State state= State.INACTIVE;
        double aeroCoef = 5.0;
        double frontalArea = 2.0;
        int maxBat = 8;
        int actualBat = 100;
        int motor = 5;
        nvc3.newEScooter(id,maxBat, actualBat);
        when(vdb.save(any(Vehicle.class), anyInt())).thenReturn(true);

        boolean expResult = true;
        boolean result = nvc3.registerVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of addVehicleToQueue method, of class NewVehicleController.
     */
    @Test
    public void testAddVehicleToQueue() {
        NewVehicleController nvc2 = new NewVehicleController();
        EScooter b = nvc2.newEScooter(1, 3,  3);
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
    }

    /**
     * Test of getVehicles method, of class NewVehicleController.
     */
    @Test
    public void testGetVehicles() {
        int id = 3;
        int weight = 5;
        State state= State.INACTIVE;
        double aeroCoef = 5.0;
        int actualBat = 5;
        double frontalArea = 2.0;
        int maxBat = 8;
        int motor = 5;

        EScooter b = nvc.newEScooter(id,maxBat,actualBat);
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
        int weight = 5;
        State state= State.ACTIVE;
        int maxBat = 8;
        int actualBat = 5;
        int motor = 5;
        Drone value = new Drone(id,weight,state,maxBat,actualBat,motor);
        when(vdb.newDrone(anyInt(), anyInt(), any(),anyInt(),anyInt(),anyInt())).thenReturn(value);

        Drone expResult = new Drone(id, weight, state, maxBat, actualBat, motor);
        Drone result = nvc2.newDrone(id, weight, maxBat, actualBat, motor);
        assertEquals(expResult, result);
    }

}
