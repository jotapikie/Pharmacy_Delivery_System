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
import java.util.Map;
import lapr.project.data.PharmacyStockDB;
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
public class UpdateStockControllerTest {
    
    private static UpdateStockController controller;
    private static PharmacyStockDB ppdb;
    private static int id = 1;
    private static List<Product> products;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    
    private static Map<Product, Integer> queue;
    
    public UpdateStockControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new Product(1, "Product 1", 0.56, 23);
        p2 = new Product(2, "Product 2", 4, 3.99);
        p3 = new Product(3, "Product 3", 1.2, 9.5);
        products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        UpdateStockController controller = new UpdateStockController(id);
        queue = new HashMap<>();
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        ProductDB pdb = mock(ProductDB.class);
        ppdb = mock(PharmacyStockDB.class);

        
        when(pdb.getProducts()).thenReturn(products);
        when(pdb.getProduct(1)).thenReturn(p1);
        when(pdb.getProduct(2)).thenReturn(p2);
        when(pdb.getProduct(3)).thenReturn(p3);
        
        controller = new UpdateStockController(pdb, ppdb, id);
    }


    /**
     * Test of getProducts method, of class UpdateStockController.
     */
    @Test
    public void testGetProducts() throws Exception {
        assertEquals(Utils.listToString(products), controller.getProducts());
    }

    /**
     * Test of getSelectedProduct method, of class UpdateStockController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
        assertEquals(p1.toString(), controller.getSelectedProduct(1));
        assertEquals(p2.toString(), controller.getSelectedProduct(2));
        assertEquals(p3.toString(), controller.getSelectedProduct(3));
        assertEquals(null, controller.getSelectedProduct(4));
    }

    /**
     * Test of addToQueue method, of class UpdateStockController.
     */
    @Test
    public void testAddToQueue() throws SQLException {
        assertFalse(controller.addToQueue(5));
        
        controller.getSelectedProduct(1);
        assertTrue(controller.addToQueue(1));
        
        controller.getSelectedProduct(1);
        assertFalse(controller.addToQueue(1));
        
    }

    /**
     * Test of updateStock method, of class UpdateStockController.
     */
    @Test
    public void testUpdateStock() throws Exception {
        assertFalse(controller.updateStock());
        
        controller.getSelectedProduct(1);
        controller.addToQueue(4);
        queue.put(p1, 4);
        when(ppdb.updateStock(id, queue)).thenReturn(true);
        assertTrue(controller.updateStock());
        
        controller.getSelectedProduct(1);
        controller.addToQueue(4);
        when(ppdb.updateStock(id, queue)).thenReturn(false);
        assertFalse(controller.updateStock());
        
    }
    
}
