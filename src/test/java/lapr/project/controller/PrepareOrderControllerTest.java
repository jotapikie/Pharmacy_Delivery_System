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

        ord1 = new Order();
        ord1.setPrice(35);
        ord1.setId(1);
        
        ord2 = new Order();
        ord2.setId(4);
        ord2.setPrice(18.99);
        ord3 = new Order();
        ord3.setId(7);
        ord3.setPrice(23.5);

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
