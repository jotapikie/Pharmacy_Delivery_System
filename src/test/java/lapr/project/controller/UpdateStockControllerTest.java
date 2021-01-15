/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.PharmacyStockDB;
import lapr.project.data.ProductDB;
import lapr.project.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Helder
 */
public class UpdateStockControllerTest {
    
    private static UpdateStockController usc;
    private static ProductDB pdb;
    private static final int idPharmacy=1;
    private static Product pro;
    private static List<Product> prds = new ArrayList<>();
    private static Product p;
    private static Product p2;
    
    public UpdateStockControllerTest() {
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
        usc = new UpdateStockController(idPharmacy);
        p=new Product(1,"brufen",5.0,5.0);
        p2=new Product(2,"benerun",5.0,5.0);
        prds.add(p);
        prds.add(p2);
        when(pdb.getProducts()).thenReturn(prds);
        when(pdb.getProduct(1)).thenReturn(p);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getProducts method, of class UpdateStockController.
     */
    @Test
    public void testGetProducts() throws Exception {
        assertEquals(true, pdb.getProducts().contains(p));
        assertEquals(true, pdb.getProducts().contains(p2));
        assertEquals(2, pdb.getProducts().size());
    }

    
}
