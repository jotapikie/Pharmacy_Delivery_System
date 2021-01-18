package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import lapr.project.model.EScooter;
import lapr.project.model.State;
import lapr.project.model.Vehicle;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpdateVehicleControllerTest {

    private static UpdateVehicleController uvc2;
    private static VehicleDB vdb;

    @BeforeAll
    public static void setUpClass() {
        vdb = mock(VehicleDB.class);
        uvc2 = new UpdateVehicleController(vdb);
        UpdateVehicleController test = new UpdateVehicleController();
    }

    public UpdateVehicleControllerTest() {
    }


    /**
     * Test of updateScooterData method, of class UpdateVehicleController.
     */
    @Test
    public void testUpdateScooterData() throws SQLException {
        int id = 2;
        int weight = 40;
        double aeroCoef = 2.0;
        double frontalArea = 2.0;
        State state = State.ACTIVE;
        int maxBat = 5;
        int actualBat = 1;
        int motor = 5;

        EScooter value = new EScooter(id, weight, state, maxBat, actualBat, motor, aeroCoef, frontalArea);
        when(vdb.getVehicle(anyInt())).thenReturn(value);


        EScooter expResult = new EScooter(2, 8, State.ACTIVE, 5, actualBat, 9, 9, 9);
        EScooter result = uvc2.updateScooterData(id, 8, State.ACTIVE, 5, 9, 9, 9);
        assertEquals(expResult, result);
    }

    /**
     * Test of update method, of class UpdateVehicleController.
     */
    @Test
    public void testUpdate() throws SQLException {
        when(vdb.update(any(Vehicle.class))).thenReturn(true);

        boolean expResult = true;
        boolean result = uvc2.update();
        assertEquals(expResult, result);

    }

    /**
     * Test of update method, of class UpdateVehicleController.
     */
    @Test
    public void testUpdate2() throws SQLException {
        when(vdb.update(any(Vehicle.class))).thenReturn(false);

        boolean expResult = false;
        boolean result = uvc2.update();
        assertEquals(expResult, result);

    }

    @Test
    public void testUpdateState() throws SQLException{
        when(vdb.update(any(Vehicle.class))).thenReturn(true);
        when(vdb.getVehicle(anyInt())).thenReturn(new EScooter());
        boolean expResult = true;
        EScooter scooter = uvc2.updateScooterState(1, State.LOCKED);
        boolean result= uvc2.update();
        assertEquals(expResult, result);

    }

    @Test
    public void testUpdateStateFail() throws SQLException{
        when(vdb.update(any(Vehicle.class))).thenReturn(false);
        boolean expResult = false;
        EScooter scooter = uvc2.updateScooterState(1, State.LOCKED);
        boolean result= uvc2.update();
        assertEquals(expResult, result);

    }

}
