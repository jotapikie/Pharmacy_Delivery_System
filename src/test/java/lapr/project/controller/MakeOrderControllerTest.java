/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import lapr.project.data.CartProductDB;
import lapr.project.data.ClientDB;
import lapr.project.data.InvoiceDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PharmacyDB;
import lapr.project.data.PharmacyStockDB;
import lapr.project.model.Address;
import lapr.project.model.Administrator;
import lapr.project.model.Client;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Invoice;
import lapr.project.model.Order;
import lapr.project.model.Pharmacy;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Diogo
 */
public class MakeOrderControllerTest {
    
    private  MakeOrderController controller;
    private ClientDB cdb;
    private  CartProductDB cpdb;
    private  OrderDB odb;
    private  InvoiceDB idb;
    private  PharmacyStockDB ppdb;
    private  PharmacyDB phardb;
    private static final String email = "client1@lapr3.com";
    
    private  Client cli;
    private  Product p1;
    private  Product p2;
    private  ShoppingCart cart;
    private  Order o1;
    private  Invoice inv;
    private  Pharmacy pha1;
    private  Pharmacy pha2;
    
    private  List<Pharmacy> pharmacies;
    
    
    
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
      MakeOrderController c1 = new MakeOrderController(email);

        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        cdb = mock(ClientDB.class);
        cpdb = mock(CartProductDB.class);
        odb = mock(OrderDB.class);
        idb = mock(InvoiceDB.class);
        ppdb = mock(PharmacyStockDB.class);
        phardb = mock(PharmacyDB.class);

        cli = new Client("Paulo", "123", email, 914673846, 43, 43245, new Address("Rua Teste", new GeographicalPoint(43.56, 68.90, 4.6), "Porto",45, "4563-456"));
        p1 = new Product(1, "Brufen", 0.89,10);
        p2 = new Product(2, "Asprina", 0.2, 5);

        //1297 km
        pha1 = new Pharmacy(1, 912456839, "Farmacia 1", new Administrator("Hugo", "admin1@lapr.com", "as"), new Address("Rua 2", new GeographicalPoint(41.7, 57.9, 3.4), "Cidade 1", 3, "3452-232"), new HashSet<>());
        
        //1959 km
        pha2 = new Pharmacy(2, 912456838, "Farmacia 2", new Administrator("Miguel", "admin2@lapr.com", "as"), new Address("Rua 3", new GeographicalPoint(56.9, 52.4, 2.1), "Cidade 2", 8, "4552-232"), new HashSet<>());
        pharmacies = new ArrayList<>();
        pharmacies.add(pha2); pharmacies.add(pha1);

        
        HashMap<Product, Integer> pInCart = new HashMap<>();
        pInCart.put(p1, 2);
        pInCart.put(p2, 1);
        o1 = new Order();
        o1.setId(1);
        o1.setPrice(28.5);
        o1.setProducts(pInCart);
        inv = new Invoice(cli, cli.getAddress(), pInCart, 28.5, 28.5, 5, 0, 111111111);

        
        cart = new ShoppingCart(pInCart);
        
        
        when(odb.newOrder(28.5, pInCart)).thenReturn(o1);
        when(cpdb.getCart(email)).thenReturn(cart);
        when(cdb.getClient(email)).thenReturn(cli);
        when(idb.newInvoice(cli, cli.getAddress(), pInCart, 28.5, 28.5, 5, 0, 23345)).thenReturn(inv);
        when(phardb.getPharmacies()).thenReturn(pharmacies);
        controller = new MakeOrderController(cdb, cpdb, odb, idb, ppdb, phardb, email);
      
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    /**
     * Test of getCart method, of class MakeOrderController.
     */
    @Test
    public void testGetCart() throws Exception {
          
           assertTrue(controller.getCart().length()> 100);
           HashMap<Product, Integer> produ = new HashMap<>();
           produ.put(p1, 200);
           cart = new ShoppingCart(produ);
           when(cpdb.getCart(email)).thenReturn(cart);
           assertNull(controller.getCart());
    }

    /**
     * Test of getAddress method, of class MakeOrderController.
     */
    @Test
    public void testGetAddress() throws SQLException {
        controller.getCart();
        assertEquals(cli.getAddress().toString(), controller.getAddress());
    }

    /**
     * Test of getDefaultNif method, of class MakeOrderController.
     */
    @Test
    public void testGetDefaultNif() throws SQLException {
        controller.getCart();
        controller.getAddress();
        assertEquals(43245, controller.getDefaultNif());
    }

    /**
     * Test of getCredits method, of class MakeOrderController.
     */
    @Test
    public void testGetCredits() throws SQLException {
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        assertEquals(43, controller.getCredits());
    }

    /**
     * Test of discount method, of class MakeOrderController.
     */
    @Test
    public void testDiscount() throws SQLException {
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        controller.discount();
        assertEquals(25.6, controller.getFinalPrice(), 0.1);
    }

    /**
     * Test of getFinalPrice method, of class MakeOrderController.
     */
    @Test
    public void testGetFinalPrice() throws SQLException {
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        assertEquals(28.5, controller.getFinalPrice());
    }


    
    @Test
    public void makeOrder_WithoutStock() throws SQLException{
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        controller.getFinalPrice();
        when(ppdb.getQuantity(1, 1)).thenReturn(1);
        when(ppdb.getQuantity(1, 2)).thenReturn(1);
        HashMap<Product, Integer> missing = new HashMap<>();
        missing.put(p1, 1);
        when(ppdb.hasProducts(missing, 2)).thenReturn(true);

        assertTrue(controller.makeOrder(35323));
    }
    
    
   @Test
    public void makeOrder_NoPharmacies() throws SQLException{
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        controller.getFinalPrice();
        when(phardb.getPharmacies()).thenReturn(new ArrayList<>());
        assertFalse(controller.makeOrder(1111));
        when(phardb.getPharmacies()).thenReturn(pharmacies);
    }
    
       @Test
    public void makeOrder_AssignedWithStock() throws SQLException{
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        controller.getFinalPrice();
        when(ppdb.getQuantity(1, 1)).thenReturn(20);
        when(ppdb.getQuantity(1, 2)).thenReturn(20);
        assertTrue(controller.makeOrder(2222));
        
    }
    
    @Test
    public void makeOrder_NoPharmacyWithStock() throws SQLException{
        controller.getCart();
        controller.getAddress();
        controller.getDefaultNif();
        controller.getCredits();
        controller.getFinalPrice();
        when(ppdb.getQuantity(1, 1)).thenReturn(1);
        when(ppdb.getQuantity(1, 2)).thenReturn(1);
        HashMap<Product, Integer> missing = new HashMap<>();
        missing.put(p1, 1);
        when(ppdb.hasProducts(missing, 2)).thenReturn(false);
        assertFalse(controller.makeOrder(2222));
    }
    
}
