/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.*;
import lapr.project.utils.Constants;
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
    private static AirGraph airGraph;
    
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
    private static Pathway path11;
    private static Pathway path12;
    private static Pathway path13;
    private static Pathway path14;
    private static List<Pathway> allPaths1;

    


    
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
        path11 = new Pathway(p5, p2, StreetType.ASPHALT, 120, new Wind(1.2,2,1), "Street3", VehicleCategory.SCOOTER);
        path12 = new Pathway(p5, p6, StreetType.ASPHALT, 130, new Wind(1.2,2,1), "Street7", VehicleCategory.SCOOTER);
        path13 = new Pathway(p1, p2, StreetType.ASPHALT, 150, new Wind(1.2,2,1), null, VehicleCategory.DRONE);
        path14 = new Pathway(p2, p1, StreetType.ASPHALT, 150, new Wind(1.2,2,1), null, VehicleCategory.DRONE);
        
        
        allPaths1 = new ArrayList<>();
        allPaths1.add(path1);allPaths1.add(path2);allPaths1.add(path3);allPaths1.add(path4);allPaths1.add(path5);
        allPaths1.add(path6);allPaths1.add(path7);allPaths1.add(path8);allPaths1.add(path9);allPaths1.add(path10);
        allPaths1.add(path11);allPaths1.add(path12);
        
    
        
        
    }
    
    @BeforeEach
    public void setUp() throws SQLException {
        RouteAlgorithms r = new RouteAlgorithms();
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints);
        when(pdb.getPaths()).thenReturn(allPaths1);
        LandGraph.setup(gpdb, pdb);
        landGraph = new LandGraph(135.8);
        
    }
    
        @Test
    public void testBestPaths_Exceptions() throws SQLException {
        boolean flag = false;
        try{
            RouteAlgorithms.kBestRoutes(null, p1, p3, 2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, null, p3, 2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, null, 2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, p3, 0, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, p3, 1, -1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        LandGraph temp = new LandGraph(100);
        temp = mock(LandGraph.class);
        when(temp.getRouteGraph()).thenReturn(null);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(temp, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        Graph<GeographicalPoint, ScooterPath> g = new Graph<>(false);
        when(temp.getRouteGraph()).thenReturn(g);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(temp, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        List<GeographicalPoint> toVisit = new ArrayList<>();
        toVisit.add(p4);
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(null,toVisit, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, null, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, null, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,null, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p3, 0, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p3, 1, -4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        toVisit.clear();
        toVisit.add(p1);
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        toVisit.clear();
        toVisit.add(p3);
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p3, 1, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        
    }
    
    
    @Test
    public void testBestRoute_Point2Point() {
        assertEquals(6, landGraph.getRouteGraph().numVertices());
        assertEquals(12, landGraph.getRouteGraph().numEdges());
        List<Route> routes = RouteAlgorithms.kBestRoutes(landGraph, p1, p2, 3, 3);
        assertEquals(3, routes.size());
        Iterator<Route> it = routes.iterator();
        
        Route r = it.next();
        assertEquals(2, r.getNumGeographicalPoints());
        assertEquals(1000, r.getTotalDistance());
        assertEquals(0.19, r.getTotalEnergy(), 0.01);
        assertEquals(119, r.getTotalTime());
        
        r = it.next();
        ScooterPath s1 = new ScooterPath(p1, p2, 1000, StreetType.ASPHALT, new Wind(1,1,1),135.8, "Street1");
        ScooterPath s2 = new ScooterPath(p1, p6, 150, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street6");
        ScooterPath s3 = new ScooterPath(p6, p1, 150, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street6");
        Route res = new Route(s2);
        res.addPath(s3);
        res.addPath(s1);
        assertEquals(res, r);
        assertEquals(4, r.getNumGeographicalPoints());
        assertEquals(1300, r.getTotalDistance());
        assertEquals(0.32, r.getTotalEnergy(), 0.01);
        assertEquals(153, r.getTotalTime());
        
        
        routes = RouteAlgorithms.kBestRoutes(landGraph, p3, p6, 1, 3);
        assertEquals(1, routes.size());
        it = routes.iterator();
        r = it.next();
        s1 = new ScooterPath(p3, p2, 2100, StreetType.ASPHALT, new Wind(1,1,1),135.8, "Street2");
        s2 = new ScooterPath(p2, p5, 900, StreetType.ASPHALT, new Wind(1,1,1), 135.8, "Street3");
        s3 = new ScooterPath(p5, p6, 130, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street7");
        res = new Route(s1);
        res.addPath(s2);
        res.addPath(s3);
        assertEquals(res, r);
    }
    
    @Test
    public void testBestRoute_Visit() throws SQLException {
        List<GeographicalPoint> toVisit = new ArrayList<>();
        List<Route> routes = RouteAlgorithms.kBestRoutes(landGraph, toVisit, p1, p3,1,3);
        assertEquals(1, routes.size());
        Route res = routes.get(0);
        
        ScooterPath s1 = new ScooterPath(p1, p2, 1000, StreetType.ASPHALT, new Wind(1,1,1), 135.8, "Street1");
        ScooterPath s2 = new ScooterPath(p2, p3, 2100, StreetType.ASPHALT, new Wind(1,1,1), 135.8, "Street2");
        Route temp = new Route(s1);
        temp.addPath(s2);
        assertEquals(temp, res);
        
        toVisit.add(p4);
        ScooterPath s3 = new ScooterPath(p3, p4, 3000, StreetType.ASPHALT, new Wind(1,1,1), 135.8, "Street5");
        ScooterPath s4 = new ScooterPath(p4, p5, 500, StreetType.ASPHALT, new Wind(1,1,1), 135.8, "Street4");
        ScooterPath s5 = new ScooterPath(p5, p2, 120, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street3");
        routes = RouteAlgorithms.kBestRoutes(landGraph, toVisit, p1, p3, 1, 3);
        res = routes.get(0);
        temp = new Route(s1);
        temp.addPath(s2);temp.addPath(s3);temp.addPath(s4);temp.addPath(s5);temp.addPath(s2);
        assertEquals(temp, res);
        
        toVisit.add(p6);
        ScooterPath s6 = new ScooterPath(p1, p6, 150, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street6");
        ScooterPath s7 = new ScooterPath(p6, p1, 150, StreetType.ASPHALT, new Wind(1.2,2,1), 135.8, "Street6");
        routes = RouteAlgorithms.kBestRoutes(landGraph, toVisit, p1, p3, 1, 3);
        res = routes.get(0);
        temp = new Route(s6);temp.addPath(s7);
        temp.addPath(s1);temp.addPath(s2);temp.addPath(s3);temp.addPath(s4);temp.addPath(s5);temp.addPath(s2);
        assertEquals(temp, res);
        
        
        

        
        
    }
    
 
    


    
    
    


    
}
