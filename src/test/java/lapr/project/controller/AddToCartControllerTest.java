/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import lapr.project.utils.Utils;
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
public class AddToCartControllerTest {
    
    private static AddToCartController controller;
    private static String email = "client1@lapr3.com";
    private static List<Product> products;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new Product(1, "Product 1", 0.56, 23);
        p2 = new Product(2, "Product 2", 4, 3.99);
        p3 = new Product(3, "Product 3", 1.2, 9.5);
        products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        
        

    }

    
    @BeforeEach
    public void setUp() throws SQLException {
        ProductDB pdb = mock(ProductDB.class);
        ClientDB cdb = mock(ClientDB.class);
        CartProductDB cpdb = mock(CartProductDB.class);
        
        when(pdb.getProducts()).thenReturn(products);
        when(pdb.getProduct(1)).thenReturn(p1);
        when(pdb.getProduct(2)).thenReturn(p2);
        when(pdb.getProduct(3)).thenReturn(p3);
        controller = new AddToCartController(email, pdb, cdb, cpdb);
    }
    


    /**
     * Test of getAvailableProducts method, of class AddToCartController.
     */
    @Test
    public void testGetAvailableProducts() throws Exception {
        assertEquals(Utils.listToString(products), controller.getAvailableProducts());
    }

    /**
     * Test of getSelectedProduct method, of class AddToCartController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
        assertEquals(p1.toString(), controller.getSelectedProduct(1));
        assertEquals(p2.toString(), controller.getSelectedProduct(2));
        assertEquals(p3.toString(), controller.getSelectedProduct(3));
        assertEquals(null, controller.getSelectedProduct(4));
    }

   

    /**
     * Test of addToCart method, of class AddToCartController.
     */
    @Test
    public void testAddToCart() throws Exception {
        controller.getSelectedProduct(1);
        controller.addToQueue(4);
        controller.addToCart();
    }
    
}
