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
import lapr.project.data.CartProductDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
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
public class RemoveFromCartControllerTest {
    private static RemoveFromCartController controller;
    private static CartProductDB cpdb;
    private static String email = "client1@lapr3.com";
    private static ShoppingCart cart;
    private static HashMap<Product, Integer> products;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    private static Product p4;
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new Product(1, "Product 1", 0.56, 23);
        p2 = new Product(2, "Product 2", 4, 3.99);
        p3 = new Product(3, "Product 3", 1.2, 9.5);
        p4 = new Product(4, "Product 4", 3.5, 19.3);
        products = new HashMap<>();
        products.put(p1, 2);
        products.put(p2, 1);
        products.put(p3, 1);
        cart = new ShoppingCart(products);
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        ProductDB pdb = mock(ProductDB.class);
        cpdb = mock(CartProductDB.class);
        
        when(cpdb.getCart(email)).thenReturn(cart);
        when(pdb.getProduct(1)).thenReturn(p1);
        when(pdb.getProduct(2)).thenReturn(p2);
        when(pdb.getProduct(3)).thenReturn(p3);
        when(pdb.getProduct(4)).thenReturn(p4);
        controller = new RemoveFromCartController(email, cpdb, pdb);
    }
    


    /**
     * Test of getProductsInCart method, of class RemoveFromCartController.
     */
    @Test
    public void testGetProductsInCart() throws Exception {
        assertEquals(cart.toString(), controller.getProductsInCart());
        
        when(cpdb.getCart(email)).thenReturn(new ShoppingCart(new HashMap<>()));
        assertNull(controller.getProductsInCart());
    }

    /**
     * Test of getSelectedProduct method, of class RemoveFromCartController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
            controller.getProductsInCart();
            assertNull(controller.getSelectedProduct(6));
            assertNull(controller.getSelectedProduct(4));
            assertEquals(p1.toString(),controller.getSelectedProduct(1));
            assertEquals(p2.toString(),controller.getSelectedProduct(2));
            assertEquals(p3.toString(),controller.getSelectedProduct(3));
            
    }

    /**
     * Test of removeFromCart method, of class RemoveFromCartController.
     */
    @Test
    public void testRemoveFromCart() throws Exception {
            assertFalse(controller.removeFromCart());
            
            
            controller.getProductsInCart();
            controller.getSelectedProduct(4);
            assertFalse(controller.removeFromCart());
            
            controller.getProductsInCart();
            controller.getSelectedProduct(1);
            when(cpdb.removeProduct(p1, email)).thenReturn(true);
            assertTrue(controller.removeFromCart());
            
            controller.getProductsInCart();
            controller.getSelectedProduct(1);
            when(cpdb.removeProduct(p1, email)).thenReturn(false);
            assertFalse(controller.removeFromCart());
    }
    
}
