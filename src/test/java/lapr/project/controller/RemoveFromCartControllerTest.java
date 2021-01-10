/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Client;
import lapr.project.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Diogo
 */
public class RemoveFromCartControllerTest {
    

    private static RemoveFromCartController controller;
    private static ClientDB cdb;
    private static ProductDB pdb;
    private static Client c;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        
        cdb = mock(ClientDB.class);
        pdb = mock(ProductDB.class);
        controller = new RemoveFromCartController("client1@lapr.com", cdb, pdb);
        
        c = new Client("u1", "Hugo", "123", "client1@lapr.com", 123456789, 0, null, null);
        when(cdb.getClient("client1@lapr.com")).thenReturn(c);
        
        p1 = new Product(1, "Brufen", 0.65, 5);
        p2 = new Product(2, "Benuron", 1.23, 3.5);
        p3 = new Product(3, "Nasla", 0.75, 8.3);
        
        when(pdb.getProduct(1)).thenReturn(p1);
        when(pdb.getProduct(2)).thenReturn(p2);
        when(pdb.getProduct(3)).thenReturn(p3);
        
        c.getCart().updateCart(p1, 2);
        c.getCart().updateCart(p2, 3);
        
        controller.getProductsInCart(); // intialize client

        
        
    }
    


    /**
     * Test of getProductsInCart method, of class RemoveFromCartController.
     */
    @Test
    public void testGetProductsInCart() {
        
        assertEquals(2, c.getCart().getItems().size());
        assertEquals(true, c.getCart().getItems().keySet().contains(p1));
        assertEquals(true, c.getCart().getItems().keySet().contains(p2));
        assertNotNull(controller.getProductsInCart());

    }

    /**
     * Test of getSelectedProduct method, of class RemoveFromCartController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
        
        assertEquals(p1.toString(),controller.getSelectedProduct(1));
        assertEquals(p2.toString(), controller.getSelectedProduct(2));
        assertEquals(null, controller.getSelectedProduct(3)); // not exists in cart
        assertEquals(null, controller.getSelectedProduct(4)); // not exists
    }

    /**
     * Test of removeFromCart method, of class RemoveFromCartController.
     */
    @Test
    public void testRemoveFromCart() throws SQLException {
        
        assertEquals(true, c.getCart().getItems().keySet().contains(p1) && c.getCart().getItems().keySet().contains(p2));
        
        controller.getSelectedProduct(1);
        controller.removeFromCart();
        assertEquals(true,!c.getCart().getItems().keySet().contains(p1) && c.getCart().getItems().keySet().contains(p2));
        
        controller.getSelectedProduct(3);
        controller.removeFromCart();
        assertEquals(true,!c.getCart().getItems().keySet().contains(p1) && c.getCart().getItems().keySet().contains(p2));
        
        controller.getSelectedProduct(2);
        controller.removeFromCart();
        assertEquals(0, c.getCart().getItems().size());

        c.getCart().updateCart(p1, 2);
        c.getCart().updateCart(p2, 3);


    }
    
}
