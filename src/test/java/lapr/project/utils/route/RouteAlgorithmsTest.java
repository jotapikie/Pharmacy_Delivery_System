/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.*;
import lapr.project.utils.graph.Graph;
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
public class RouteAlgorithmsTest {
    
    private static LandGraph landGraph;
    
    private static PathwayDB pdb;
    private static GeographicalPointDB gpdb;
    

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
    private static List<Pathway> allPaths1;
    private static List<Pathway> allPaths2;
    


    
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
        
        allPaths1 = new ArrayList<>();
        allPaths1.add(path1);allPaths1.add(path2);allPaths1.add(path3);allPaths1.add(path4);allPaths1.add(path5);
        allPaths1.add(path6);allPaths1.add(path7);allPaths1.add(path8);
        
        allPaths2 = new ArrayList<>();
        allPaths2.add(path1);allPaths2.add(path2);allPaths2.add(path9);allPaths2.add(path10);
        
        
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints);
        when(pdb.getPaths()).thenReturn(allPaths1);
        LandGraph.setup(gpdb, pdb);
        landGraph = new LandGraph(135.8);
    }
    
    @Test
    public void testBestRoute_Point2Point() {
//        assertEquals(6, landGraph.getRouteGraph().numVertices());
//        assertEquals(8, landGraph.getRouteGraph().numEdges());
//        List<Route> routes = new ArrayList<>();
//        
//        routes = landGraph.kBestPaths(p1, p4, 1);
//        assertNotNull(routes.get(0));
//        
//        routes = landGraph.kBestPaths(p4, p1, 1);
//        assertNotNull(routes);
        
    }
    
    @Test
    public void testBestRoute_Visit() throws SQLException {
        List<Route> routes = new ArrayList<>();
        List<GeographicalPoint> points = new ArrayList<>();
//        points.add(p2);
//        
//        routes = landGraph.kBestPaths(points, p1, p1, 1);
//        assertNotNull(routes.get(0));
//        
//        points.clear();
//        points.add(p3);
//        routes = landGraph.kBestPaths(points, p1, p1, 1);
//        assertNotNull(routes.get(0));
//        
//        points.clear();
//        points.add(p5);
//        routes = landGraph.kBestPaths(points, p1, p1, 1);
//        assertNull(routes);
//        
//        points.clear();
//        points.add(p4);
//        routes = landGraph.kBestPaths(points, p1, p3, 1);
//        assertNotNull(routes.get(0));
        
        // SCENARIO 02
        points.clear();
        points.add(p4);
        routes = landGraph.kBestPaths(points, p1, p1, 1);
        assertNotNull(routes.get(0));
        
        
    }
    
    @Test
    public void testScenario03() throws SQLException {
//               // Scenario 03
//        List<GeographicalPoint> points = new ArrayList<>();
//        List<Route> routes = new ArrayList<>();
//        when(pdb.getPaths()).thenReturn(allPaths2);
//        MainGraph.setup(gpdb, pdb);
//        landGraph = new LandGraph(123);
//        points.clear();
//        points.add(p6);
//        routes = landGraph.kBestPaths(points, p1, p1, 1);
//        System.out.println(routes.get(0));
    }

    
    
    


    
}
