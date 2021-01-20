/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.OrderDB;
import lapr.project.model.Order;
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
public class NotifyReadyOrderControllerTest {
  
    private static NotifyReadyOrderController controller;
    private static OrderDB odb;
    private static Order ord1;
    private static Order ord2;
    private static Order ord3;
    private static Order ord4;
    @BeforeAll
    public static void setUpClass() throws SQLException {
                odb = mock(OrderDB.class);
        controller = new NotifyReadyOrderController(odb, 1);
        
        List<Order> orders = new ArrayList<>();

        ord1 = new Order();
        ord1.setPrice(35);
        ord1.setId(1);
        
        ord2 = new Order();
        ord2.setId(4);
        ord2.setPrice(18.99);
        
        ord3 = new Order();
        ord3.setId(7);
        ord3.setPrice(23.5);
        
        ord4 = new Order();
        ord4.setId(10);
        ord4.setPrice(23.5);

        orders.add(ord1);
        orders.add(ord2);
        orders.add(ord3);
        
        when(odb.getOrdersByStatus(1, "Preparing")).thenReturn(orders);
        when(odb.getOrder(1)).thenReturn(ord1);
        when(odb.getOrder(4)).thenReturn(ord2);
        when(odb.getOrder(7)).thenReturn(ord3);
        when(odb.getOrder(10)).thenReturn(ord4);
    }
    


    /**
     * Test of getPreparingOrders method, of class NotifyReadyOrderController.
     */
    @Test
    public void testGetPreparingOrders() throws Exception {
        List<String> res = controller.getPreparingOrders();
        assertEquals(3,res.size());
        assertTrue(res.contains(ord1.toString()));
        assertTrue(res.contains(ord2.toString()));
        assertTrue(res.contains(ord3.toString()));
    }

    /**
     * Test of getSelectedOrder method, of class NotifyReadyOrderController.
     */
    @Test
    public void testGetSelectedOrder() throws Exception {
        controller.getPreparingOrders();
        assertEquals(null, controller.getSelectedOrder(10));
        assertEquals(null, controller.getSelectedOrder(9));
        assertEquals(ord3.toString(), controller.getSelectedOrder(7));
        assertEquals(ord2.toString(), controller.getSelectedOrder(4));
        assertEquals(ord1.toString(), controller.getSelectedOrder(1));

    }

    /**
     * Test of setOrderToReady method, of class NotifyReadyOrderController.
     */
    @Test
    public void testSetOrderToReady() throws Exception {
        assertFalse(controller.setOrderToReady());
        
        controller.getPreparingOrders();
        controller.getSelectedOrder(10);
        assertFalse(controller.setOrderToReady());
        
        controller.getPreparingOrders();
        controller.getSelectedOrder(1);
        when(odb.setStatus(1, "Prepared", 1)).thenReturn(true);
        assertTrue(controller.setOrderToReady());
        
        controller.getPreparingOrders();
        controller.getSelectedOrder(1);
        when(odb.setStatus(1, "Prepared", 1)).thenReturn(false);
        assertFalse(controller.setOrderToReady());
    }
    
}
