package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RemoveVehicleControllerTest {

    private static RemoveVehicleController rvc2;
    private static VehicleDB vdb;

    @BeforeAll
    public static void setUpClass() {
        vdb = mock(VehicleDB.class);
        rvc2 = new RemoveVehicleController(vdb);
        RemoveVehicleController test = new RemoveVehicleController();
    }

    public RemoveVehicleControllerTest() {
    }

    /**
     * Test of remove method, of class RemoveVehicleController.
     */
    @Test
    public void testRemove() throws SQLException {
        when(vdb.remove(123)).thenReturn(true);

        boolean expResult = true;
        boolean result = rvc2.remove(123);
        assertEquals(expResult, result);
    }

}
