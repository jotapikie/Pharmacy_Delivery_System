/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lapr.project.data.OrderDB;
import lapr.project.model.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author Diogo
 */
public class PrepareOrderControllerTest {
    
   private static PrepareOrderController controller;
    private static OrderDB odb;
    private static Order ord1;
    private static Order ord2;
    private static Order ord3;
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        odb = mock(OrderDB.class);
        controller = new PrepareOrderController(odb, 1);
        
        List<Order> orders = new ArrayList<>();
        ord1 = new Order(1, 35, new HashMap<>());
        ord2 = new Order(4, 18.99, new HashMap<>());
        ord3 = new Order(7, 23.50, new HashMap<>());
        orders.add(ord1);
        orders.add(ord2);
        orders.add(ord3);
        
        when(odb.getOrdersByStatus(1, "Processed")).thenReturn(orders);
        when(odb.getOrder(1)).thenReturn(ord1);
        when(odb.getOrder(4)).thenReturn(ord2);
        when(odb.getOrder(7)).thenReturn(ord3);
    }
    


    /**
     * Test of getReadyToPrepareOrders method, of class PrepareOrderController.
     */
    @Test
    public void testGetReadyToPrepareOrders() throws Exception {
        List<String> res = controller.getReadyToPrepareOrders();
        
        assertEquals(3, res.size());
        assertEquals(true, res.contains(ord1.toString()));
        assertEquals(true, res.contains(ord2.toString()));
        assertEquals(true, res.contains(ord3.toString()));
    }

    /**
     * Test of getSelectedOrder method, of class PrepareOrderController.
     */
    @Test
    public void testGetSelectedOrder() throws Exception {
        assertEquals(ord1.toString(), controller.getSelectedOrder(1));
        assertEquals(ord2.toString(), controller.getSelectedOrder(4));
        assertEquals(ord3.toString(), controller.getSelectedOrder(7));
        assertEquals(null, controller.getSelectedOrder(5));
    }

    /**
     * Test of prepareOrder method, of class PrepareOrderController.
     */
    @Test
    public void testPrepareOrder() throws Exception {
        // TO DO
    }
    
}
