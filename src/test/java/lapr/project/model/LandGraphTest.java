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
public class LandGraphTest {
    
    static LandGraph instance;
    static GeographicalPointDB gpdb;
    static PathwayDB pdb;
    static List<GeographicalPoint> points;
    static List<Pathway> paths;
    
    static GeographicalPoint p1;
    static GeographicalPoint p2;
    static GeographicalPoint p3;
    static GeographicalPoint p4;
    
    static Pathway p10;
    static Pathway p11;
    static Pathway p12;
    static Pathway p13;
    static Pathway p14;
    
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        
//        gpdb = mock(GeographicalPointDB.class);
//        pdb = mock(PathwayDB.class);
//        
//        points = new ArrayList<>();
//        paths = new ArrayList<>();
//        
//        p1 = new GeographicalPoint(45.7, 23.6, 4.8);
//        p2 = new GeographicalPoint(48.2, 54.9, 23.7);
//        p3 = new GeographicalPoint(67.3, 89.3, 54.65);
//        p4 = new GeographicalPoint(78.2, 56.3, 2.9);
//        points.add(p1);points.add(p2);points.add(p3);points.add(p4);
//        
//        p10 = new Pathway(p1, p2, 4.5, 34.2, 5.7);
//        p11 = new Pathway(p2, p3, 3.6, 29.5, 2.9);
//        p12 = new Pathway(p2, p4, 2.7, 45.8, 1.4);
//        p13 = new Pathway(p4, p1, 6.5, 18.5, 2.6);
//        p14 = new Pathway(p4, p3, 2.3, 8.9, 2.5);
//        paths.add(p10);paths.add(p11);paths.add(p12);paths.add(p13);paths.add(p14);
//        
//        when(gpdb.getGeographicalPoints()).thenReturn(points);
//        when(pdb.getPaths()).thenReturn(paths);
//        
//        MainGraph.setup(gpdb, pdb);
//        instance = new LandGraph(68, 2.8);
        
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of kBestPaths method, of class LandGraph.
     */
    @Test
    public void testKBestPaths() {
//        List<Route> routes = instance.kBestPaths(p1, p3, 2);
//        assertEquals(2, routes.size());
    }

    /**
     * Test of getRouteGraph method, of class LandGraph.
     */
    @Test
    public void testGetRouteGraph() {
        //assertNotNull(instance.getRouteGraph());
    }
    
}
