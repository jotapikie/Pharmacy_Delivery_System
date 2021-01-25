/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.utils.graph.Graph;
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
public class AirGraphTest {
    
    private static AirGraph graph;
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    private static GeographicalPoint p4;
    private static GeographicalPoint p5;
    private static GeographicalPoint p6;
    private static GeographicalPoint p7;
    private static GeographicalPoint p8;
    private static GeographicalPoint p9;
    private static List<GeographicalPoint> allPoints1;
    
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
    
    private static GeographicalPointDB gpdb;
    private static PathwayDB pdb;
    
    @BeforeAll
    public static void setUpClass() {
                  p1 = new GeographicalPoint(23, 34, 12);p1.setDescription("p1");
          p2 = new GeographicalPoint(22, 31, 11);p2.setDescription("p2");
          p3 = new GeographicalPoint(21, 32, 10);p3.setDescription("p3");
          p4 = new GeographicalPoint(24, 33, 9);p4.setDescription("Pharmacy");
          p5 = new GeographicalPoint(25, 34, 8);p5.setDescription("p5");
          p6 = new GeographicalPoint(26, 35, 7);p6.setDescription("Pharmacy");
          p7 = new GeographicalPoint(27, 36, 6);p7.setDescription("p7");
          p8 = new GeographicalPoint(28, 37, 5);p8.setDescription("p8");
          p9 = new GeographicalPoint(29, 36, 4);p9.setDescription("p9");
        allPoints1 = new ArrayList<>();
        allPoints1.add(p1);allPoints1.add(p2);allPoints1.add(p3);allPoints1.add(p4);allPoints1.add(p5);
        allPoints1.add(p6);allPoints1.add(p7);allPoints1.add(p8);allPoints1.add(p9);
        
        path1 = new Pathway(p1, p2, StreetType.ASPHALT, 200, new Wind(1,1,1), "Street1",VehicleCategory.DRONE);
        path2 = new Pathway(p2, p3, StreetType.ASPHALT, 100, new Wind(1,1,1), "Street2",VehicleCategory.DRONE);
        path3 = new Pathway(p3, p4, StreetType.ASPHALT, 50, new Wind(1,1,1), "Street3",VehicleCategory.DRONE);
        path4 = new Pathway(p4, p5, StreetType.ASPHALT, 70, new Wind(1,1,1), "Stree4",VehicleCategory.DRONE);
        path5 = new Pathway(p5, p6, StreetType.ASPHALT, 80, new Wind(1,1,1), "Street5",VehicleCategory.DRONE);
        path6 = new Pathway(p6, p7, StreetType.ASPHALT, 90, new Wind(1,1,1), "Street6",VehicleCategory.DRONE);
        path7 = new Pathway(p7, p8, StreetType.ASPHALT, 100, new Wind(1,1,1), "Street7",VehicleCategory.DRONE);
        path8 = new Pathway(p8, p1, StreetType.ASPHALT, 300, new Wind(1,1,1), "Street8",VehicleCategory.SCOOTER);
        path9 = new Pathway(p3, p9, StreetType.ASPHALT, 50, new Wind(1,1,1), "Street9",VehicleCategory.DRONE);
        path10 = new Pathway(p9, p4, StreetType.ASPHALT, 70, new Wind(1,1,1), "Stree10",VehicleCategory.DRONE);
        path11 = new Pathway(p4, p7, StreetType.ASPHALT, 40, new Wind(1,1,1), "Street11",VehicleCategory.SCOOTER);
        path12 = new Pathway(p7, p1, StreetType.ASPHALT, 70, new Wind(1,1,1), "Street12",VehicleCategory.DRONE);
        allPaths1 = new ArrayList<>();
        allPaths1.add(path1);allPaths1.add(path2);allPaths1.add(path3);allPaths1.add(path4);
        allPaths1.add(path5);allPaths1.add(path6);allPaths1.add(path7);allPaths1.add(path8);allPaths1.add(path9);
        allPaths1.add(path10);allPaths1.add(path11);allPaths1.add(path12);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        MainGraph.setup(gpdb, pdb);
        graph = new AirGraph(123);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints1);
        when(pdb.getPaths()).thenReturn(allPaths1);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getTotalWeight method, of class AirGraph.
     */
    @Test
    public void testGetTotalWeight() throws SQLException {
        assertEquals(123,graph.getTotalWeight());
        
        boolean flag = false;
        try{
            graph = new AirGraph(-12);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(123, graph.getTotalWeight());
        
        flag = false;
        try{
            graph = new AirGraph(0);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(123, graph.getTotalWeight());
    }

    /**
     * Test of getRouteGraph method, of class AirGraph.
     */
    @Test
    public void testGetRouteGraph() {
        System.out.println(graph.getRouteGraph().numEdges());
    }
    
}
