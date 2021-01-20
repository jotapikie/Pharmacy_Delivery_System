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
import lapr.project.data.VehicleDB;
import lapr.project.model.Client;
import lapr.project.model.DeliveryRun;
import lapr.project.model.EScooter;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
import lapr.project.model.ShoppingCart;
import lapr.project.model.State;
import lapr.project.model.VehiclePath;
import lapr.project.utils.Constants;
import lapr.project.utils.Utils;
import lapr.project.utils.route.Route;
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
public class StartDeliveryRunControllerTest {
    
    private static DeliveryRun run1;
    private static DeliveryRun run2;
    private static DeliveryRun run3;
    private static List<DeliveryRun> runs;
    
    private static EScooter scooter1;
    private static EScooter scooter2;
    private static EScooter scooter3;
    private static List<EScooter> scooters;
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    private static GeographicalPoint p4;
    private static GeographicalPoint p5;
    private static GeographicalPoint p6;
    private static GeographicalPoint p7;
    private static GeographicalPoint p8;
    private static GeographicalPoint p9;
    private static GeographicalPoint p10;
    private static GeographicalPoint p11;
    private static List<GeographicalPoint> points;
    
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
    private static Pathway path11;
    private static Pathway path12;
    private static Pathway path13;
    private static List<Pathway> paths;
    
    private static StartDeliveryRunController controller;
    private static DeliveryRunDB drdb;
    private static GeographicalPointDB gpdb;
    private static ClientDB cdb;
    private static VehicleDB vdb;
    private static PathwayDB pdb;
    
    private static int idPharmacy = 1;
    private static String email = "courier@lapr3.com";
    private static double weight = 68.6;
    private static double totalWeight = 112.53;
    
    private static Route r1;
    private static Route r2;
    
    
    
    
    @BeforeAll
    public static void setUpClass() {
        List<Order> orders = new ArrayList<>();
        Order o1 = new Order();
        o1.setId(1);
        o1.setPrice(27.6);
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product(1, "Product1",0.67,3.99), 2);
        products.put(new Product(2, "Product2",1.23,9.99), 3);
        o1.setProducts(products);
        orders.add(o1);
        run1 = new DeliveryRun(1, orders);
        run2 = new DeliveryRun(2, new ArrayList<>());
        run3 = new DeliveryRun(3, orders);
        runs = new ArrayList<>();
        runs.add(run1);runs.add(run2);runs.add(run3);
       
        
        scooter1 = new EScooter(1, State.LOCKED, 60, 20);
        scooter2 = new EScooter(2, State.LOCKED, 70, 15);
        scooter3 = new EScooter(3, State.LOCKED, 25, 25);
        scooters = new ArrayList<>();
        scooters.add(scooter1);scooters.add(scooter2);scooters.add(scooter3);
        
        p1 = new GeographicalPoint(41, 39, 0.2);p1.setDescription("p1");
        p2 = new GeographicalPoint(42, 38, 0.2);p2.setDescription("p2");
        p3 = new GeographicalPoint(43, 37, 0.2);p3.setDescription("p3");
        p4 = new GeographicalPoint(44, 36, 0.2);p4.setDescription("p4");
        p5 = new GeographicalPoint(45, 35, 0.2);p5.setDescription("p5");
        p6 = new GeographicalPoint(46, 34, 0.2);p6.setDescription("p6");
        p7 = new GeographicalPoint(47, 33, 0.2);p7.setDescription("p7");
        p8 = new GeographicalPoint(48, 32, 0.2);p8.setDescription("p8");
        p9 = new GeographicalPoint(49, 31, 0.2);p9.setDescription(Constants.CHARGING_SPOT);
        p10 = new GeographicalPoint(50, 30, 0.2);p10.setDescription("p10");
        p11 = new GeographicalPoint(50, 30, 0.2);p11.setDescription("p11");
        points = new ArrayList<>();
        points.add(p1);points.add(p2);points.add(p3);
        points.add(p4);points.add(p5);points.add(p6);
        points.add(p7);points.add(p8);points.add(p9);points.add(p10);
        
        path1 = new Pathway(p1, p2, 0.2, 100, 0.2,"Street1");
        path2 = new Pathway(p1, p3, 0.3, 75, 0.23,"Street2");
        path3 = new Pathway(p2, p7, 0.45, 130, 0.1,"Street3");
        path4 = new Pathway(p4, p1, 0.24, 200, 0.16, "Street4");
        path5 = new Pathway(p3, p9, 0.34, 50, 0.13,"Street5");
        path6 = new Pathway(p9, p3, 0.45, 130, 0.1,"Street6");
        path7 = new Pathway(p7, p5, 0.267, 20, 0.89, "Street7");
        path8 = new Pathway(p5, p8, 0.36, 110, 0.25, "Street8");
        path9 = new Pathway(p5, p6, 0.17, 190, 0.47, "Street9");
        path10 = new Pathway(p8, p9, 0.78, 115, 0.18, "Street10");
        path11 = new Pathway(p6, p4, 0.65, 30, 0.2, "Street11");
        path12 = new Pathway(p8, p10, 0.6, 30, 0.3, "Street12");
        path13 = new Pathway(p3, p4, 0.34, 60, 0.3, "Street13");
        paths = new ArrayList<>();
        paths.add(path1);paths.add(path2);paths.add(path3);paths.add(path4);paths.add(path5);
        paths.add(path6);paths.add(path7);paths.add(path8);paths.add(path9);paths.add(path10);
        paths.add(path11);paths.add(path12);paths.add(path13);
        
        
   //p1 p2 p7 p5 p8 p9 p3 p4 p1
      VehiclePath vp1 = new VehiclePath(p1, p2, 100, 0.2, 0.2, totalWeight, Constants.SCOOTER_AERO_COEF, "Street1");
      VehiclePath vp2 = new VehiclePath(p2, p7, 130, 0.45, 0.1, totalWeight, Constants.SCOOTER_AERO_COEF, "Street3");
      VehiclePath vp3 = new VehiclePath(p7, p5, 20, 0.267, 0.89, totalWeight, Constants.SCOOTER_AERO_COEF, "Street7");
      VehiclePath vp4 = new VehiclePath(p5, p8, 110, 0.36, 0.25, totalWeight, Constants.SCOOTER_AERO_COEF, "Street8");
      VehiclePath vp5 = new VehiclePath(p8, p9, 115, 0.78, 0.18, totalWeight, Constants.SCOOTER_AERO_COEF, "Street10");
      VehiclePath vp6 = new VehiclePath(p9, p3, 130, 0.45, 0.1, totalWeight, Constants.SCOOTER_AERO_COEF, "Street6");
      VehiclePath vp7 = new VehiclePath(p3, p4, 60, 0.34, 0.3, totalWeight, Constants.SCOOTER_AERO_COEF, "Street13");
      VehiclePath vp8 = new VehiclePath(p4, p1, 200, 0.24, 0.16, totalWeight, Constants.SCOOTER_AERO_COEF, "Street4");
      VehiclePath vp9 = new VehiclePath(p5, p6, 190, 0.17, 0.47, totalWeight, Constants.SCOOTER_AERO_COEF, "Street9");
      VehiclePath vp10 = new VehiclePath(p6, p4, 30, 0.65, 0.12, totalWeight, Constants.SCOOTER_AERO_COEF, "Street11");
      r1 = new Route(vp1);
      r1.addPath(vp2);r1.addPath(vp3);r1.addPath(vp4);r1.addPath(vp5);r1.addPath(vp6);r1.addPath(vp7);r1.addPath(vp8);
      
      // p1 p2 p7 p5 p6 p4 p1 
      r2 = new Route(vp1);
      r2.addPath(vp2);r2.addPath(vp3);r2.addPath(vp9);r2.addPath(vp10);r2.addPath(vp8);
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        drdb = mock(DeliveryRunDB.class);
        gpdb = mock(GeographicalPointDB.class);
        cdb = mock(ClientDB.class);
        vdb = mock(VehicleDB.class);
        pdb = mock(PathwayDB.class);
        LandGraph.setup(gpdb, pdb);
        controller = new StartDeliveryRunController(drdb, gpdb, cdb, vdb, idPharmacy, weight, email);
        
        when(drdb.getDeliveryRuns(idPharmacy)).thenReturn(runs);
        when(vdb.getAvailableScooters(idPharmacy)).thenReturn(scooters);
        when(gpdb.getGeographicalPointByPharmacy(idPharmacy)).thenReturn(p1);
        
        Client c = new Client("Joao", "123", "joao@gmail.com", new ShoppingCart());
        List<Client> clients = new ArrayList<>();
        clients.add(c);
        
        when(cdb.getClientsByDeliveryRun(1)).thenReturn(clients);

       
        when(gpdb.getGeographicalPoints()).thenReturn(points);
        when(pdb.getPaths()).thenReturn(paths);
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
         assertNull(controller.selectDeliveryRun(4));
         assertEquals(run1.toString(), controller.selectDeliveryRun(1));
         assertEquals(run2.toString(), controller.selectDeliveryRun(2));
         assertEquals(run3.toString(), controller.selectDeliveryRun(3));
    }

    /**
     * Test of getAvailableScooters method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetAvailableScooters() throws Exception {
        assertEquals(Utils.listToString(scooters), controller.getAvailableScooters());
    }

    /**
     * Test of selectScooter method, of class StartDeliveryRunController.
     */
    @Test
    public void testSelectScooter() throws SQLException {
         controller.getAvailableScooters();
         assertNull(controller.selectScooter(4));
         assertEquals(scooter1.toString(), controller.selectScooter(1));
         assertEquals(scooter2.toString(), controller.selectScooter(2));
         assertEquals(scooter3.toString(), controller.selectScooter(3));
    }

    /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun_1() throws Exception {
        controller.getDeliveryRuns();
        controller.selectDeliveryRun(1);
        controller.getAvailableScooters();
        controller.selectScooter(1);
        
        List<GeographicalPoint> interm = new ArrayList<>();
        interm.add(p5);
        when(gpdb.getPointsByDeliveryRun(1)).thenReturn(interm);
       
        controller.startDeliveryRun();
        assertNotNull(controller.getRoute());
        assertEquals(r2.toString(), controller.getRoute());
        assertEquals(0, controller.getEnergyToStart());
        
    }
    
    
     /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun_Charge() throws Exception {
        controller.getDeliveryRuns();
        controller.selectDeliveryRun(1);
        controller.getAvailableScooters();
        controller.selectScooter(2);
        
        List<GeographicalPoint> interm = new ArrayList<>();
        interm.add(p8);
        when(gpdb.getPointsByDeliveryRun(1)).thenReturn(interm);
       
        when(drdb.startDelivery(1, email, r1,2)).thenReturn(true);
        assertTrue(controller.startDeliveryRun());
        String sRoute = controller.getRoute();
        assertEquals(r1.toString(),sRoute );
        assertNotNull(controller.getRoute());
        assertFalse(sRoute.isEmpty());
        assertEquals(65.38, controller.getEnergyToStart(),0.1);
        
        when(drdb.startDelivery(1, email, r1,2)).thenReturn(false);
        assertFalse(controller.startDeliveryRun());
        
    }
    
        /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun_NoPoints() throws Exception {
        controller.getDeliveryRuns();
        controller.selectDeliveryRun(1);
        controller.getAvailableScooters();
        controller.selectScooter(1);
        
        List<GeographicalPoint> interm = new ArrayList<>();
        interm.add(p5);
        when(gpdb.getPointsByDeliveryRun(1)).thenReturn(interm);
        when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
        when(pdb.getPaths()).thenReturn(new ArrayList<>());
        assertFalse(controller.startDeliveryRun());

    }
    
    
            /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun_CantReach() throws Exception {
        controller.getDeliveryRuns();
        controller.selectDeliveryRun(1);
        controller.getAvailableScooters();
        controller.selectScooter(1);
        
        List<GeographicalPoint> interm = new ArrayList<>();
        interm.add(p5);
        when(gpdb.getPointsByDeliveryRun(1)).thenReturn(interm);
        when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
        when(pdb.getPaths()).thenReturn(new ArrayList<>());
        assertFalse(controller.startDeliveryRun());

    }
    
    @Test
    public void testGetRoute(){
        assertNull(controller.getRoute());
        
        
    }
    
    

    
    
  

  
    
}
