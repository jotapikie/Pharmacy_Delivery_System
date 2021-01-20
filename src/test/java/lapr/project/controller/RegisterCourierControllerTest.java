/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.CourierDB;
import lapr.project.model.Courier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;

/**
 *
 * @author Diogo
 */
public class RegisterCourierControllerTest {
    
    private static RegisterCourierController controller;
    private static CourierDB cdb;
    private static Courier c1;
    private static Courier c2;
    private static Courier c3;
    
    private static int idPharmacy;
    private static Set<Courier> couriers;
    
    
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
 
        
        c1 = new Courier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4);
        c2 = new Courier("Tiago", "tiago@lapr.com", "123", 123496788, 26312, 4.6);
        c3 = new Courier("Mário", "mario@lapr.com", "123", 123406777, 21372, 4.9);
        
        idPharmacy = 1;
        couriers = new HashSet<>();

        
        
        
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        cdb = mock(CourierDB.class);
        controller = new RegisterCourierController(cdb, 1);
        
        when(cdb.newCourier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4)).thenReturn(c1);
        when(cdb.newCourier("Tiago", "tiago@lapr.com", "123", 123496788, 26312, 4.6)).thenReturn(c2);
        when(cdb.newCourier("Mário", "mario@lapr.com", "123", 123406777, 21372, 4.9)).thenReturn(c3);
        
        
 
    }
    


    /**
     * Test of newCourier method, of class RegisterCourierController.
     */
    @Test
    public void testNewCourier() {
        assertEquals(c1.toString(), controller.newCourier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4));
        assertEquals(c2.toString(), controller.newCourier("Tiago", "tiago@lapr.com", "123", 123496788, 26312, 4.6));
        assertEquals(c3.toString(), controller.newCourier("Mário", "mario@lapr.com", "123", 123406777, 21372, 4.9));
    }

    /**
     * Test of addToQueue method, of class RegisterCourierController.
     */
    @Test
    public void testAddToQueue() throws SQLException {
        assertFalse(controller.addToQueue());
        
        controller.newCourier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4);
        assertTrue(controller.addToQueue());
        
        controller.newCourier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4);
        assertFalse(controller.addToQueue());
    }

    /**
     * Test of registCouriers method, of class RegisterCourierController.
     */
    @Test
    public void testRegistCouriers() throws Exception {
        assertEquals(0, controller.registCouriers());
            
        controller.newCourier("Paulo", "paulo@lapr.com", "123", 123456789, 21312, 5.4);
        controller.addToQueue();
        couriers.add(c1);
        when(cdb.saveCouriers(couriers, idPharmacy)).thenReturn(1);
        assertEquals(1, controller.registCouriers());
        
        controller.newCourier("Tiago", "tiago@lapr.com", "123", 123496788, 26312, 4.6);
        controller.addToQueue();
        couriers.add(c2);
        when(cdb.saveCouriers(couriers, idPharmacy)).thenReturn(2);
        assertEquals(2, controller.registCouriers());
   
    }
    
}
