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
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.DeliveryRun;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.MainGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
import lapr.project.utils.Utils;
import lapr.project.utils.route.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunControllerTest {
    
    private StartDeliveryRunController controller;
    private DeliveryRunDB drdb;
    private GeographicalPointDB gpdb;
    private PathwayDB pdb;
    private ClientDB cdb;
    private static String courier;
    private static double weight;
    private static int idPharmacy;
    private static List<DeliveryRun> runs;
    private static DeliveryRun dr1;
    private static DeliveryRun dr2;
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    private static GeographicalPoint p4;
    private static GeographicalPoint p5;
    private static List<GeographicalPoint> allPoints;
    
    private static Pathway path1;
    private static Pathway path2;
    private static Pathway path3;
    private static Pathway path4;
    private static Pathway path5;
    private static Pathway path6;
    private static Pathway path7;
    private static Pathway path8;
    private static Pathway path9;
    private static Pathway path10;
    private static List<Pathway> allPaths;
    
    private static List<GeographicalPoint> pointsDR1;
    private static List<GeographicalPoint> pointsDR2;
    
    private static Client c1;
    private static Client c2;
    
    private static List<Client> clients;

    
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        courier = "courier1@lapr2.com";
        weight = 67.4;
        idPharmacy = 1;
        
        Map<Product, Integer> products1 = new HashMap<>();
        products1.put(new Product(1, "Brufen", 0.78, 4.99), 5);
        products1.put(new Product(2, "Aspirin", 0.25, 2.3), 1);
        
        Map<Product, Integer> products2 = new HashMap<>();
        products2.put(new Product(3, "Covid19", 1.2, 19.99), 1);
        
        Order ord1 = new Order();
        ord1.setId(1);
        ord1.setStatus("Prepared");
        ord1.setPrice(23.90);
        ord1.setProducts(products1);
        
        Order ord2 = new Order();
        ord2.setId(2);
        ord2.setStatus("Prepared");
        ord2.setPrice(38.1);
        ord2.setProducts(products2);
        
        List<Order> orders1 = new ArrayList<>();
        orders1.add(ord1);orders1.add(ord2);
        dr1 = new DeliveryRun(1, orders1);
        
        Map<Product, Integer> products3 = new HashMap<>();
        products1.put(new Product(1, "Brufen", 0.78, 4.99), 2);
        products1.put(new Product(3, "Covid19", 1.2, 19.99),2);
        
        Map<Product, Integer> products4 = new HashMap<>();
        products2.put(new Product(3, "Covid19", 1.2, 19.99), 1);
        
        Order ord3 = new Order();
        ord1.setId(3);
        ord1.setStatus("Prepared");
        ord1.setPrice(50.02);
        ord1.setProducts(products3);
        
        Order ord4 = new Order();
        ord2.setId(4);
        ord2.setStatus("Prepared");
        ord2.setPrice(19.99);
        ord2.setProducts(products4);
        
        List<Order> orders2 = new ArrayList<>();
        orders2.add(ord3);orders2.add(ord4);
        dr2 = new DeliveryRun(2, orders2);
        
        runs = new ArrayList<>();
        runs.add(dr1);runs.add(dr2);
        
        p1 = new GeographicalPoint(42.45, 23.4, 0.2);p1.setDescription("p1");
        p2 = new GeographicalPoint(41.78, 36.7, 0.2);p2.setDescription("p2");         
        p3 = new GeographicalPoint(84.5, -23.3, 0.2);p3.setDescription("p3");
        p4 = new GeographicalPoint(-4.53, 1.32, 0.2);p4.setDescription("p4");
        p5 = new GeographicalPoint(89.2, -57.2, 0.2);p5.setDescription("p5");
        allPoints = new ArrayList<>();
        allPoints.add(p1);allPoints.add(p2);allPoints.add(p3);allPoints.add(p4);allPoints.add(p5);
        
        path1 = new Pathway(p1, p2, 0.5, 300, 0.6);
        //path2 = new Pathway(p1, p5, 0.23, 100, 0.2);
        path3 = new Pathway(p2, p3, 0.5, 300, 0.6);
        path5 = new Pathway(p3, p2, 0.89, 42.2, 0.2);
        path6 = new Pathway(p2, p1, 0.89, 42.2, 0.2);
        path7 = new Pathway(p2, p4, 0.23, 120.3, 0.28);
        path8 = new Pathway(p3, p4, 0.72, 94.7, 0.59);
        //path9 = new Pathway(p4, p5, 0.25, 23, 0.439);
        allPaths = new ArrayList<>();
        allPaths.add(path1);allPaths.add(path3);;allPaths.add(path5);
        allPaths.add(path6);allPaths.add(path7);allPaths.add(path8);
        
        pointsDR1 = new ArrayList<>();
        pointsDR1.add(p2);pointsDR1.add(p3);
        
        pointsDR2 = new ArrayList<>();
        pointsDR2.add(p3);pointsDR2.add(p5);
        
        c1 = new Client("Hugo", "123", "client1@lapr.com", 912523456, 23, 232456784, new Address("Teste", p3, "Porto", 1, "4525-354"));
        clients = new ArrayList<>();
        clients.add(c1);
        

    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        drdb = mock(DeliveryRunDB.class);
        gpdb = mock(GeographicalPointDB.class);
        cdb = mock(ClientDB.class);
        pdb = mock(PathwayDB.class);
        MainGraph.setup(gpdb, pdb);
        when(drdb.getDeliveryRuns(1)).thenReturn(runs);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints);
        when(gpdb.getPointsByDeliveryRun(1)).thenReturn(pointsDR1);
        when(gpdb.getPointsByDeliveryRun(2)).thenReturn(pointsDR2);
        when(pdb.getPaths()).thenReturn(allPaths);
        when(gpdb.getGeographicalPointByPharmacy(1)).thenReturn(p1);
        when(cdb.getClientsByDeliveryRun(1)).thenReturn(clients);
        when(cdb.getClientsByDeliveryRun(2)).thenReturn(clients);
        when(drdb.startDelivery(1, courier, null)).thenReturn(1);
        when(drdb.startDelivery(2, courier, null)).thenReturn(1);
        controller = new StartDeliveryRunController(drdb, gpdb, cdb, idPharmacy, weight, courier);
        
        
    }
    
    @AfterEach
    public void tearDown() {

        
    }

    /**
     * Test of getDeliveryRuns method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetDeliveryRuns() throws Exception {
        assertEquals(Utils.listToString(runs), controller.getDeliveryRuns());
    }

    /**
     * Test of selectDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testSelectDeliveryRun() throws SQLException {
         controller.getDeliveryRuns();
         assertNull(controller.selectDeliveryRun(3));
         assertEquals(dr1.toString(), controller.selectDeliveryRun(1));
         assertEquals(dr2.toString(), controller.selectDeliveryRun(2));
    }

    /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun_Succesfull() throws Exception {
            // ALL POINTS ARE CONNECTED
            controller.getDeliveryRuns();
            controller.selectDeliveryRun(1);
            controller.startDeliveryRun();
            assertNotNull(controller.getRoute());
  
    }
    
    @Test
    public void testStartDeliveryRun_Erro() throws SQLException{
        // ONE CLIENT ADDRESS HAS NO CONNECTIONS YET
            controller.getDeliveryRuns();
            controller.selectDeliveryRun(2);
            controller.startDeliveryRun();
            assertNull(controller.getRoute());
    }
    
    @Test
    public void testStartDeliveryRun_Exception() throws SQLException{
            when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
            controller.getDeliveryRuns();
            controller.selectDeliveryRun(2);
            assertTrue(controller.startDeliveryRun());

    }

    /**
     * Test of getRoute method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetRoute() {
        assertNull(controller.getRoute());
    }

    /**
     * Test of getVehicle method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetVehicle() {
        assertEquals(-1,controller.getVehicle());
    }
    
}
