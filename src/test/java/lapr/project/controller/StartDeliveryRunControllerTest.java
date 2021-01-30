/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lapr.project.data.ClientDB;
import lapr.project.data.DeliveryRunDB;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.data.VehicleDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.DeliveryRun;
import lapr.project.model.Drone;
import lapr.project.model.EScooter;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.MainGraph;
import lapr.project.model.Order;
import lapr.project.model.Pathway;
import lapr.project.model.Product;
import lapr.project.model.State;
import lapr.project.model.StreetType;
import lapr.project.model.Vehicle;
import lapr.project.model.VehicleCategory;
import lapr.project.model.Wind;
import lapr.project.utils.Utils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Matchers;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Diogo
 */
public class StartDeliveryRunControllerTest {
    
    private static StartDeliveryRunController controller1;
    private static StartDeliveryRunController controller2;
    
    private static DeliveryRunDB drdb;
    private static GeographicalPointDB gpdb;
    private static ClientDB cdb;
    private static VehicleDB vdb;
    private static PathwayDB pdb;
    
    private static EScooter v1;
    private static EScooter v2;
    private static Drone v3;
    private static Drone v4;
    private static List<Vehicle> scooters;
    private static List<Vehicle> drones;
    
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    private static GeographicalPoint p4;
    private static GeographicalPoint p5;
    private static GeographicalPoint p6;
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
    private static Pathway path11;
    private static Pathway path12;
    private static List<Pathway> allPaths1;
    
    private static DeliveryRun dr1;
    private static DeliveryRun dr2;
    private static DeliveryRun dr3;
    
    private static List<DeliveryRun> runsScooter;
    private static List<DeliveryRun> runsDrone;
    

    private static int idPharmacy = 1;
    private static String courierEmail = "courier@gmail.com";
    private static double courierWeight = 80;
    
    public StartDeliveryRunControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new GeographicalPoint(-8.60929,41.15227,104, "Pharmacy - Trindade"); //Trindade
        p2 = new GeographicalPoint(-8.61398,41.14582,87, "Clerigos");
        p3 = new GeographicalPoint(-8.60746,41.14871,87, "Bolhao");
        p4 = new GeographicalPoint(-8.61118,41.14063,25, "Cais");
        p5 = new GeographicalPoint(-8.60657,41.14723,91, "Majestic");
        p6 = new GeographicalPoint(41.16875,-8.68995,4, "Pharmacy - C. do Queijo");
        allPoints = new ArrayList<>();
        allPoints.add(p1);allPoints.add(p2);allPoints.add(p3);allPoints.add(p4);allPoints.add(p5);allPoints.add(p6);
        
        path1 = new Pathway(p1, p2, StreetType.ASPHALT, 1000, new Wind(1,1,1), "Street1",VehicleCategory.SCOOTER);
        path2 = new Pathway(p2, p1, StreetType.ASPHALT, 1000, new Wind(1,1,1), "Street1",VehicleCategory.SCOOTER);
        path3 = new Pathway(p2, p3, StreetType.ASPHALT, 2100, new Wind(1,1,1), "Street2",VehicleCategory.SCOOTER);
        path4 = new Pathway(p3, p2, StreetType.ASPHALT, 2100, new Wind(1,1,1), "Street2",VehicleCategory.SCOOTER);
        path5 = new Pathway(p2, p5, StreetType.ASPHALT, 900, new Wind(1,1,1), "Street3",VehicleCategory.SCOOTER);
        path6 = new Pathway(p4, p5, StreetType.ASPHALT, 500, new Wind(1,1,1), "Street4",VehicleCategory.SCOOTER);
        path7 = new Pathway(p4, p3, StreetType.ASPHALT, 3000, new Wind(1,1,1), "Street5",VehicleCategory.SCOOTER);
        path8 = new Pathway(p3, p4, StreetType.ASPHALT, 3000, new Wind(1,1,1), "Street5",VehicleCategory.SCOOTER);
        path9 = new Pathway(p1, p6, StreetType.ASPHALT, 150, new Wind(1.2,2,1), "Street6", VehicleCategory.SCOOTER);
        path10 = new Pathway(p6, p1, StreetType.ASPHALT, 150, new Wind(1.2,2,1), "Street6", VehicleCategory.SCOOTER);
        path11 = new Pathway(p1, p2, StreetType.ASPHALT, 150, new Wind(1.2,2,1), null, VehicleCategory.DRONE);
        path12 = new Pathway(p2, p1, StreetType.ASPHALT, 150, new Wind(1.2,2,1), null, VehicleCategory.DRONE);
        
        allPaths1 = new ArrayList<>();
        allPaths1.add(path1);allPaths1.add(path2);allPaths1.add(path3);allPaths1.add(path4);allPaths1.add(path5);
        allPaths1.add(path6);allPaths1.add(path7);allPaths1.add(path8);allPaths1.add(path9);allPaths1.add(path10);
        allPaths1.add(path11);allPaths1.add(path12);
        
        Order o1 = new Order(1, new Timestamp(132324), new Timestamp(1111111), "Prepared", 45.3, new Address("Street1", p2, "City1", 89, "4525-323"));
        Map<Product, Integer> products = new HashMap<>();
        products.put(new Product(1,"p1",0.5,25.6), 2);
        products.put(new Product(2,"p2",0.56,12.3), 1);
        o1.setProducts(products);
        
        List<Order> orders = new ArrayList<>();
        orders.add(o1);
        dr2 = new DeliveryRun(2, new ArrayList<>(orders), VehicleCategory.DRONE);
        
        Order o2 = new Order(2, new Timestamp(132324), new Timestamp(1111111), "Prepared", 45.3, new Address("Street1", p4, "City1", 89, "4525-323"));
        Map<Product, Integer> products2 = new HashMap<>();
        products2.put(new Product(3,"p3",0.78,21.6), 2);
        products2.put(new Product(4,"p2",0.78,11.3), 3);
        o2.setProducts(products2);
        
        orders.add(o2);
        dr1 = new DeliveryRun(1, new ArrayList<>(orders), VehicleCategory.SCOOTER);
        
        Order o3 = new Order(3, new Timestamp(132324), new Timestamp(1111111), "Prepared", 45.3, new Address("Street1", p3, "City1", 89, "4525-323"));
        Map<Product, Integer> products3 = new HashMap<>();
        products2.put(new Product(1,"p1",0.72,21.6), 1);
        products2.put(new Product(4,"p2",0.78,11.3), 3);
        o3.setProducts(products3);
        
        orders.add(o3);
        dr3 = new DeliveryRun(3, new ArrayList<>(orders), VehicleCategory.SCOOTER);
        
        runsScooter = new ArrayList<>();runsDrone = new ArrayList<>();
        runsDrone.add(dr2);
        runsScooter.add(dr1);runsScooter.add(dr3);
        
        v1 = new EScooter(1, State.LOCKED, 3.1, 2.1);
        v2 = new EScooter(2, State.CHARGING, 3.2, 2.0);
        v3 = new Drone(3, State.CHARGING, 2.6,1.5);
        v4 = new Drone(4,State.LOCKED,2.3,1.2);
        
        scooters = new ArrayList<>();
        scooters.add(v1);scooters.add(v2);
        
        drones = new ArrayList<>();
        drones.add(v3);drones.add(v4);
        
    }
    
    @AfterAll
    public static void tearDownClass() {
        StartDeliveryRunController c1 = new StartDeliveryRunController(idPharmacy, courierEmail, courierWeight);
        StartDeliveryRunController c2 = new StartDeliveryRunController(idPharmacy, null, 0);
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        drdb = mock(DeliveryRunDB.class);
        gpdb = mock(GeographicalPointDB.class);
        cdb = mock(ClientDB.class);
        vdb = mock(VehicleDB.class);
        pdb = mock(PathwayDB.class);
        MainGraph.setup(gpdb, pdb);
        controller1 = new StartDeliveryRunController(drdb, gpdb, cdb, vdb, idPharmacy, courierWeight, courierEmail);
        controller2 = new StartDeliveryRunController(drdb, gpdb, cdb, vdb, idPharmacy, 0, null);
        when(drdb.getDeliveryRuns(idPharmacy, VehicleCategory.SCOOTER)).thenReturn(runsScooter);
        when(drdb.getDeliveryRuns(idPharmacy, VehicleCategory.DRONE)).thenReturn(runsDrone);
        when(vdb.getAvailableDrones(idPharmacy)).thenReturn(drones);
        when(vdb.getAvailableScooters(idPharmacy)).thenReturn(scooters);
        when(gpdb.getGeographicalPointByPharmacy(idPharmacy)).thenReturn(p1);
        Client c = new Client("n", "a", "aads@sds.com", 126374856, 274285930, new CreditCard(1234567891234567L, "11/22", 123), new Address("Street1", p1, courierEmail, idPharmacy, "4524-345"));
        List<Client> clients = new ArrayList<>();
        clients.add(c);
        when(cdb.getClientsByDeliveryRun(1)).thenReturn(clients);
        when(cdb.getClientsByDeliveryRun(2)).thenReturn(clients);
        when(cdb.getClientsByDeliveryRun(3)).thenReturn(clients);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints);
        when(pdb.getPaths()).thenReturn(allPaths1);
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDeliveryRuns method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetDeliveryRuns() throws Exception {
        assertEquals(Utils.listToString(runsDrone), controller2.getDeliveryRuns());
        assertEquals(Utils.listToString(runsScooter), controller1.getDeliveryRuns());
    }
    
    

    /**
     * Test of selectDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testSelectDeliveryRun() throws SQLException {
        assertNull(controller1.selectDeliveryRun(2));
        assertNull(controller2.selectDeliveryRun(2));
        
        controller1.getDeliveryRuns();
        assertNull(controller1.selectDeliveryRun(45));
        assertEquals(dr1.toString(), controller1.selectDeliveryRun(1));
        
        controller2.getDeliveryRuns();
        assertNull(controller2.selectDeliveryRun(34));
        assertEquals(dr2.toString(), controller2.selectDeliveryRun(2));
        
    }

    /**
     * Test of getAvailableVehicles method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetAvailableVehicles() throws Exception {
        assertEquals(Utils.listToString(drones), controller2.getAvailableVehicles());
        assertEquals(Utils.listToString(scooters), controller1.getAvailableVehicles());
    }

    /**
     * Test of selectVehicle method, of class StartDeliveryRunController.
     */
    @Test
    public void testSelectVehicle() throws SQLException {
        assertNull(controller1.selectVehicle(1));
        assertNull(controller2.selectVehicle(2));
        
        controller1.getAvailableVehicles();
        assertEquals(v1.toString(), controller1.selectVehicle(1));
        assertEquals(v2.toString(), controller1.selectVehicle(2));
        
        controller2.getAvailableVehicles();
        assertEquals(v3.toString(), controller2.selectVehicle(3));
        assertEquals(v4.toString(), controller2.selectVehicle(4));
    }

    /**
     * Test of startDeliveryRun method, of class StartDeliveryRunController.
     */
    @Test
    public void testStartDeliveryRun() throws Exception {
        controller1.getDeliveryRuns();
        controller1.selectDeliveryRun(1);
        controller1.getAvailableVehicles();
        controller1.selectVehicle(1);
        when(drdb.startDelivery(Matchers.anyInt(), Matchers.anyString(), Matchers.any(), Matchers.anyInt())).thenReturn(true);
        assertTrue(controller1.startDeliveryRun());
        assertNotNull(controller1.getRoute());
        assertEquals(2.32, controller1.getEnergyToStart(), 0.1);
        
        controller2.getDeliveryRuns();
        controller2.selectDeliveryRun(2);
        controller2.getAvailableVehicles();
        controller2.selectVehicle(3);
        when(drdb.startDelivery(Matchers.anyInt(), Matchers.anyString(), Matchers.any(), Matchers.anyInt())).thenReturn(false);
        assertFalse(controller2.startDeliveryRun());
        assertNotNull(controller2.getRoute());
        assertEquals(0.008, controller2.getEnergyToStart(), 0.001);
    }

    /**
     * Test of getRoute method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetRoute() {

    }

    /**
     * Test of getEnergyToStart method, of class StartDeliveryRunController.
     */
    @Test
    public void testGetEnergyToStart() {
  
    }
    
}
