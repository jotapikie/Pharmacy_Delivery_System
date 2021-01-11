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
import static org.mockito.Mockito.*;

/**
 *
 * @author Diogo
 */
public class RegisterCourierControllerTest {
    
    private static RegisterCourierController controller;
    private static RegisterCourierController controller1;
    private static CourierDB cdb;
    private static Courier c1;
    private static Courier c2;
    private static Courier c3;
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        cdb = mock(CourierDB.class);
        controller = new RegisterCourierController(cdb, 1);
        controller1 = new RegisterCourierController(cdb, 1);
        
        c1 = new Courier("Paulo", "paulo@lapr.com", "123", 12345673, 21312, 5.4);
        c2 = new Courier("Tiago", "tiago@lapr.com", "123", 12349673, 26312, 4.6);
        c3 = new Courier("Mário", "mario@lapr.com", "123", 12340673, 21372, 4.9);
        
        when(cdb.newCourier("Paulo", "paulo@lapr.com", "123", 12345673, 21312, 5.4)).thenReturn(c1);
        when(cdb.newCourier("Tiago", "tiago@lapr.com", "123", 12349673, 26312, 4.6)).thenReturn(c2);
        when(cdb.newCourier("Mário", "mario@lapr.com", "123", 12340673, 21372, 4.9)).thenReturn(c3);

        
        when(cdb.saveCouriers(controller1.getCouriersList(), 1)).thenReturn(controller1.getCouriersList().size());
        
    }
    


    /**
     * Test of newCourier method, of class RegisterCourierController.
     */
    @Test
    public void testNewCourier() {
        assertEquals(c1.toString(), controller.newCourier("Paulo", "paulo@lapr.com", "123", 12345673, 21312, 5.4));
        assertEquals(c2.toString(), controller.newCourier("Tiago", "tiago@lapr.com", "123", 12349673, 26312, 4.6));
        assertEquals(c3.toString(), controller.newCourier("Mário", "mario@lapr.com", "123", 12340673, 21372, 4.9));
    }

    /**
     * Test of addToQueue method, of class RegisterCourierController.
     */
    @Test
    public void testAddToQueue() throws SQLException {
        controller.newCourier("Paulo", "paulo@lapr.com", "123", 12345673, 21312, 5.4);
        
        assertEquals(0, controller.getCouriersList().size());
        
        controller.addToQueue();
        assertEquals(1, controller.getCouriersList().size());
        assertEquals(true, controller.getCouriersList().contains(c1));
        
        controller.newCourier("Tiago", "tiago@lapr.com", "123", 12349673, 26312, 4.6);
        controller.addToQueue();
        assertEquals(2, controller.getCouriersList().size());
        assertEquals(true, controller.getCouriersList().contains(c2));
      
        
        

    }

    /**
     * Test of registCouriers method, of class RegisterCourierController.
     */
    @Test
    public void testRegistCouriers() throws Exception {
          
          assertEquals(0, controller.registCouriers());
          
          controller1.newCourier("Paulo", "paulo@lapr.com", "123", 12345673, 21312, 5.4);
          controller1.addToQueue();
          when(cdb.saveCouriers(controller1.getCouriersList(), 1)).thenReturn(controller1.getCouriersList().size());
          assertEquals(1, controller1.registCouriers());
          
//          controller1.newCourier("Tiago", "tiago@lapr.com", "123", 12349673, 26312, 4.6);
//          controller1.addToQueue();
//          when(cdb.saveCouriers(controller1.getCouriersList(), 1)).thenReturn(controller1.getCouriersList().size());
//          assertEquals(2, controller1.registCouriers());
          

          
        
          
        
    }
    
}
