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
import java.util.Map;
import java.util.Set;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.OrderDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.Address;
import lapr.project.model.DeliveryRun;
import lapr.project.model.DronePath;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.MainGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
import lapr.project.model.ScooterPath;
import lapr.project.model.StreetType;
import lapr.project.model.VehicleCategory;
import lapr.project.model.Wind;
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
public class AssignOrderControllerTest {
    
    private static AssignOrderController controller;
    private static OrderDB odb;
    private static DeliveryRunDB drdb;
    private static GeographicalPointDB gpdb;
    private static PathwayDB pdb;
    
    private static Order o1;
    private static Order o2;
    private static Order o3;
    private static Order o4;
    private static List<Order> orders;
    
    private static Product p1;
    private static Product p2;
    private static Product p3;

    private static int idPharmacy = 1;
    
    private static DeliveryRun run;
    private static Set<DeliveryRun> runs;
    
    private static Address aClient;
    private static GeographicalPoint point1;
    
    private static List<GeographicalPoint> points;
    private static List<Pathway> paths;
    
    public AssignOrderControllerTest() {
        AssignOrderController c1 = new AssignOrderController(idPharmacy);
    }
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new Product(1, "Product 1", 0.9, 23.2);
        p2 = new Product(2, "Product 2", 0.7, 12.3);
        p3 = new Product(3, "Product 3", 0.5, 10.2);
        Map<Product, Integer> pr = new HashMap<Product, Integer>();
        pr.put(p1, 2);
        pr.put(p3, 1);
        aClient = new Address("Street1", new GeographicalPoint(21,21,0.4),"City1", 45, "4526-234");
        o1 = new Order();o1.setId(1);o1.setStatus("Prepared");o1.setProducts(pr);o1.setAddress(aClient);
        
        pr.clear();
        pr.put(p2, 3);
        Address aClient2 = new Address("Street2", new GeographicalPoint(22,22,0.4),"City2", 45, "4526-234");
        o2 = new Order();o2.setId(2);o2.setStatus("Prepared");o2.setProducts(pr);o2.setAddress(aClient2);
        
        pr.clear();
        pr.put(p1, 8);
        pr.put(p2, 2);
        o3 = new Order();o3.setId(2);o3.setStatus("Prepared");o3.setProducts(pr);
        
        o4 = new Order();o4.setId(8);
        orders = new ArrayList<>();
        orders.add(o1);orders.add(o2);orders.add(o3);
        
        run = new DeliveryRun(1, orders, VehicleCategory.SCOOTER);
        runs = new HashSet<>();
        
        
        point1 = new GeographicalPoint(21.4, 21.3, 0.4, "Pharmacy");
        
        points = new ArrayList<>();
        points.add(point1);points.add(aClient.getGeographicalPoint());points.add(aClient2.getGeographicalPoint());
        
        paths = new ArrayList<>();
        paths.add(new ScooterPath(point1, aClient.getGeographicalPoint(), 200, StreetType.ASPHALT, new Wind(1,1,1), 0.4, "Street2"));
        paths.add(new ScooterPath(aClient.getGeographicalPoint(),point1, 200, StreetType.ASPHALT, new Wind(1,1,1), 0.4, "Street2"));
        paths.add(new DronePath(0.4, point1, aClient.getGeographicalPoint(),200, new Wind(1,1,1)));
        paths.add(new DronePath(0.4, aClient.getGeographicalPoint(), point1,200, new Wind(1,1,1)));
       
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        odb = mock(OrderDB.class);
        drdb = mock(DeliveryRunDB.class);
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        controller = new AssignOrderController(odb, drdb,gpdb, idPharmacy);
        
        when(odb.getOrdersByStatus(idPharmacy, "Prepared")).thenReturn(orders);
        when(odb.getOrder(1)).thenReturn(o1);
        when(odb.getOrder(2)).thenReturn(o2);
        when(odb.getOrder(3)).thenReturn(o3);
        when(odb.getOrder(4)).thenReturn(o4);
        when(odb.getOrder(5)).thenReturn(null);
        
        MainGraph.setup(gpdb, pdb);
        when(gpdb.getGeographicalPoints()).thenReturn(points);
        when(pdb.getPaths()).thenReturn(paths);
        when(gpdb.getGeographicalPointByPharmacy(idPharmacy)).thenReturn(point1);
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAvailableOrders method, of class AssignOrderController.
     */
    @Test
    public void testGetAvailableOrders() throws Exception {
        assertEquals(Utils.listToString(orders), controller.getAvailableOrders());
        assertNull(controller.newDeliveryRun("Scooter"));
    }

    /**
     * Test of selectOrder method, of class AssignOrderController.
     */
    @Test
    public void testSelectOrder() throws Exception {
        controller.getAvailableOrders();
        assertNull(controller.selectOrder(4));
        assertNull(controller.selectOrder(5));
        assertEquals(o1.toString(), controller.selectOrder(1));
        assertEquals(o2.toString(), controller.selectOrder(2));
        assertEquals(o3.toString(), controller.selectOrder(3));
    }

    /**
     * Test of addOrder method, of class AssignOrderController.
     */
    @Test
    public void testAddOrder() throws SQLException {
          controller.getAvailableOrders();
          controller.selectOrder(5);
          assertFalse(controller.addOrder());
          
          controller.selectOrder(1);
          assertTrue(controller.addOrder());
          
          controller.selectOrder(3);
          assertFalse(controller.addOrder());
    }
    
      /**
     * Test of addOrder method, of class AssignOrderController.
     */
    @Test
    public void testAddOrder_2() throws SQLException {
          controller.getAvailableOrders();
          controller.selectOrder(3);
          assertTrue(controller.addOrder());
          
    }

    /**
     * Test of getAirRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetAirRoute() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        controller.getLandRoute();
        String air = controller.getAirRoute();
        assertNotNull(air);
        assertFalse(air.isEmpty());
        
    }
    
        /**
     * Test of getAirRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetAirRoute_NoPath() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(2);
        controller.addOrder();
        assertNull(controller.getLandRoute());
        String air = controller.getAirRoute();
        assertNull(air);
        
    }
    
              /**
     * Test of getLandRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetAirRoute_NoPaths() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(2);
        controller.addOrder();
        when(pdb.getPaths()).thenReturn(new ArrayList<>());
        when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
        assertNull(controller.getLandRoute());
        assertNull(controller.getAirRoute());
    }

    /**
     * Test of getLandRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetLandRoute() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        String land = controller.getLandRoute();
        assertNotNull(land);
        assertFalse(land.isEmpty());

    }
    
        /**
     * Test of getLandRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetLandRoute_NoPath() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(2);
        controller.addOrder();
        assertNull(controller.getLandRoute());
    }
    
            /**
     * Test of getLandRoute method, of class AssignOrderController.
     */
    @Test
    public void testGetLandRoute_NoPaths() throws SQLException {
        controller.getAvailableOrders();
        controller.selectOrder(2);
        controller.addOrder();
        when(pdb.getPaths()).thenReturn(new ArrayList<>());
        when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
        assertNull(controller.getLandRoute());
    }
    
    @Test
    public void testGetMostEfficient() throws SQLException {
        assertNull(controller.getMostEfficient());
        
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        controller.getLandRoute();
        controller.getAirRoute();
        assertEquals("Scooter", controller.getMostEfficient());
    }

    /**
     * Test of newDeliveryRun method, of class AssignOrderController.
     */
    @Test
    public void testNewDeliveryRun() throws SQLException {
        assertNull(controller.newDeliveryRun("Scooter"));
        
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        List<Order> orders = new ArrayList<>();
        orders.add(o1);
        when(drdb.newDeliveryRun(1, "Scooter", orders)).thenReturn(run);
        assertEquals(run.toString(), controller.newDeliveryRun("Scooter"));
        
        when(drdb.newDeliveryRun(1, "ADASD", orders)).thenReturn(null);
        assertNull(controller.newDeliveryRun("ADASD"));
    }

    /**
     * Test of addToQueue method, of class AssignOrderController.
     */
    @Test
    public void testAddToQueue() throws SQLException {
        assertFalse(controller.addToQueue());
        
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        List<Order> orders = new ArrayList<>();
        orders.add(o1);
        when(drdb.newDeliveryRun(1, "Scooter", orders)).thenReturn(run);
        controller.newDeliveryRun("Scooter");
        assertTrue(controller.addToQueue());
        
        assertEquals(2, controller.getAvailableOrders().size());
        
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        orders = new ArrayList<>();
        orders.add(o1);
        when(drdb.newDeliveryRun(2, "Scooter", orders)).thenReturn(run);
        controller.newDeliveryRun("Scooter");
        assertFalse(controller.addToQueue());
        
    }

    /**
     * Test of saveDeliveryRuns method, of class AssignOrderController.
     */
    @Test
    public void testSaveDeliveryRuns() throws Exception {
        assertEquals(0, controller.saveDeliveryRuns());
        
        controller.getAvailableOrders();
        controller.selectOrder(1);
        controller.addOrder();
        List<Order> orders = new ArrayList<>();
        orders.add(o1);
        when(drdb.newDeliveryRun(1, "Scooter", orders)).thenReturn(run);
        controller.newDeliveryRun("Scooter");
        controller.addToQueue();
        runs.add(run);
        when(drdb.saveRuns(runs)).thenReturn(1);
        assertEquals(1, controller.saveDeliveryRuns());
        
    }
    
}
