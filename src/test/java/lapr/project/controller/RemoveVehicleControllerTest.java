package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.VehicleDB;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class RemoveVehicleControllerTest {

    private static RemoveVehicleController rvc2;
    private static VehicleDB vdb;

    @BeforeAll
    public static void setUpClass() {
        vdb = mock(VehicleDB.class);
        rvc2 = new RemoveVehicleController(vdb, 1);
        RemoveVehicleController test = new RemoveVehicleController(1);
    }

    public RemoveVehicleControllerTest() {
    }

    /**
     * Test of remove method, of class RemoveVehicleController.
     * @throws java.sql.SQLException
     */
    @Test
    public void testRemove() throws SQLException {
        when(vdb.remove(123, 1)).thenReturn(true);

        boolean expResult = true;
        boolean result = rvc2.remove(123);
        assertEquals(expResult, result);
        
        when(vdb.remove(123, 1)).thenReturn(false);
        assertFalse(rvc2.remove(2));
    }

}
