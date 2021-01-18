package lapr.project.controller;

import lapr.project.data.DeliveryRunDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EndDeliveryRunControllerTest {

    private DeliveryRunDB drdb;
    private EndDeliveryRunController controller;
    private static String courier;

    @BeforeEach
    void setUp() throws SQLException {
        this.drdb = mock(DeliveryRunDB.class);
        courier = "courier1@lapr3.com";
        this.controller= new EndDeliveryRunController();


    }
    @Test
    void getScooterCourierByDeliveryRun() throws SQLException {
        when(drdb.getCourierEmail(anyInt())).thenReturn(courier);
        String result=drdb.getCourierEmail(1);
        assertEquals(courier, result);
    }
    @Test
    void getScooterCourierByDeliveryRunFail() throws SQLException {
        when(drdb.getCourierEmail(anyInt())).thenReturn(null);
        String result=drdb.getCourierEmail(1);
        String exp= null;
        assertEquals(exp, result);
    }

    @Test
    void setEndDate() throws SQLException {
        when(drdb.endDeliveryRun(anyInt())).thenReturn(true);
        boolean result= drdb.endDeliveryRun(1);
        boolean exp= true;
        assertEquals(exp,result);
    }

    @Test
    void setEndDateFail() throws SQLException {
        when(drdb.endDeliveryRun(anyInt())).thenReturn(false);
        boolean result= drdb.endDeliveryRun(1);
        boolean exp= false;
        assertEquals(exp,result);
    }
}