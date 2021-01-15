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
 * @author Helder
 */
public class AddToCartControllerTest {
    
    private static AddToCartController atcc;
    private static String clientEmail;
    private static ProductDB pdb;
    private static ClientDB cdb;
    private static CartProductDB cpdb;
    private static Product p;
    private static Product p2;
    private static List<Product> prds = new ArrayList<>();
    
    public AddToCartControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        pdb=mock(ProductDB.class);
        cdb = mock(ClientDB.class);
        cpdb = mock(CartProductDB.class);
        atcc=new AddToCartController(clientEmail,pdb,cdb,cpdb);
        p=new Product(1,"brufen",5.0,5.0);
        p2=new Product(2,"benerun",5.0,5.0);
        prds.add(p);
        prds.add(p2);
        when(pdb.getProducts()).thenReturn(prds);
        when(pdb.getProduct(1)).thenReturn(p);
        when(pdb.getProduct(2)).thenReturn(p2);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAvailableProducts method, of class AddToCartController.
     */
    @Test
    public void testGetAvailableProducts() throws Exception {
        assertEquals(true, pdb.getProducts().contains(p));
        assertEquals(true, pdb.getProducts().contains(p2));
    }

    /**
     * Test of getSelectedProduct method, of class AddToCartController.
     */
    @Test
    public void testGetSelectedProduct() throws Exception {
        assertEquals(p.toString(), atcc.getSelectedProduct(1));
        assertEquals(p2.toString(), atcc.getSelectedProduct(2));
        assertEquals(null, atcc.getSelectedProduct(5));
    }

    
    
}
