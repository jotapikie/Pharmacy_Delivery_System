/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

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

/**
 *
 * @author Diogo
 */
public class MainGraphTest {

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
    
    
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new GeographicalPoint(42.45, 23.4, 0.2);
        p2 = new GeographicalPoint(41.78, 36.7, 0.2);
        p3 = new GeographicalPoint(84.5, -23.3, 0.2);
        p4 = new GeographicalPoint(-4.53, 1.32, 0.2);
        p5 = new GeographicalPoint(89.2, -57.2, 0.2);
        allPoints = new ArrayList<>();
        allPoints.add(p1);allPoints.add(p2);allPoints.add(p3);allPoints.add(p4);allPoints.add(p5);
        
        path1 = new Pathway(p1, p2, 0.5, 300, 0.6);
        path2 = new Pathway(p1, p3, 0.23, 100, 0.2);
        path3 = new Pathway(p2, p3, 0.5, 300, 0.6);
        path4 = new Pathway(p3, p1, 0.23, 100, 0.2);
        path5 = new Pathway(p2, p3, 0.89, 450.2, 0.234);
        path6 = new Pathway(p3, p2, 0.89, 450.2, 0.234);
        path7 = new Pathway(p2, p4, 0.23, 120.3, 0.28);
        path8 = new Pathway(p3, p4, 0.72, 94.7, 0.59);
        path9 = new Pathway(p4, p5, 0.25, 23, 0.439);
        path10 = new Pathway(p5, p4, 0.25, 23, 0.439);
        allPaths = new ArrayList<>();
        allPaths.add(path1);allPaths.add(path2);allPaths.add(path3);allPaths.add(path4);allPaths.add(path5);
        allPaths.add(path6);allPaths.add(path7);allPaths.add(path8);allPaths.add(path9);allPaths.add(path10);
    }
    

    
    @BeforeEach
    public void setUp() {
    }
    



    /**
     * Test of setup method, of class MainGraph.
     */
    @Test
    public void testSetup() {

    }

    /**
     * Test of getVertexes method, of class MainGraph.
     */
    @Test
    public void testGetVertexes() {

    }

    /**
     * Test of getEdges method, of class MainGraph.
     */
    @Test
    public void testGetEdges() {

    }

    /**
     * Test of kBestPaths method, of class MainGraph.
     */
    @Test
    public void testKBestPaths() {

    }

    /**
     * Test of getRouteGraph method, of class MainGraph.
     */
    @Test
    public void testGetRouteGraph() {

    }
    
}
