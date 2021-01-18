/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Diogo
 */
public class LandGraphTest {
    
    private static LandGraph graph;
    
    private static PathwayDB pdb;
    private static GeographicalPointDB gpdb;
    

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
    private static Pathway path6;
    private static Pathway path7;
    private static Pathway path8;
    private static Pathway path9;
    private static Pathway path10;
    private static List<Pathway> allPaths;
    
    public LandGraphTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new GeographicalPoint(42.45, 23.4, 0.2);p1.setDescription("p1");
        p2 = new GeographicalPoint(41.78, 36.7, 0.2);p2.setDescription("p2");
        p3 = new GeographicalPoint(84.5, -23.3, 0.2);p3.setDescription("p3");
        p4 = new GeographicalPoint(-4.53, 1.32, 0.2);p4.setDescription("p4");
        p5 = new GeographicalPoint(89.2, -57.2, 0.2);p5.setDescription("p5");
        allPoints = new ArrayList<>();
        allPoints.add(p1);allPoints.add(p2);allPoints.add(p3);allPoints.add(p4);allPoints.add(p5);
        
        path1 = new Pathway(p1, p2, 0.5, 300, 0.6);
        path2 = new Pathway(p1, p3, 2, 100, 2);
        path3 = new Pathway(p2, p3, 0.5, 100, 0.6);
        path4 = new Pathway(p3, p1, 0.23, 100, 0.2);
        path6 = new Pathway(p3, p2, 0.89, 450.2, 0.234);
        path7 = new Pathway(p2, p4, 0.23, 120.3, 0.28);
        path8 = new Pathway(p3, p4, 0.72, 94.7, 0.59);
        path9 = new Pathway(p4, p5, 0.25, 23, 0.439);
        path10 = new Pathway(p5, p4, 0.25, 23, 0.439);
        allPaths = new ArrayList<>();
        allPaths.add(path1);allPaths.add(path2);allPaths.add(path3);allPaths.add(path4);
        allPaths.add(path6);allPaths.add(path7);allPaths.add(path8);allPaths.add(path9);allPaths.add(path10);
    }

    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints);
        when(pdb.getPaths()).thenReturn(allPaths);
        LandGraph.setup(gpdb, pdb);
        graph = new LandGraph(126, 0.6);
    }
    
  


    /**
     * Test of kBestPaths method, of class LandGraph.
     */
    @Test
    public void testKBestPaths_3args() throws SQLException {
            // Origin null
            boolean flag = false;
            try{
                graph.kBestPaths(null, p3, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            // Destination null
            flag = false;
            try{
                graph.kBestPaths(p1, null, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            // Origin = Destination
            flag = false;
            try{
                graph.kBestPaths(p1, p1, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            // k <= 0
            flag = false;
            try{
                graph.kBestPaths(p1, p3, -1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            List<Route> res = graph.kBestPaths(p1, p3, 1);
            Route exp = new Route(path2);
            Route result = res.iterator().next();
            assertEquals(exp.getRouteDistance(),result.getRouteDistance());
            assertEquals(exp.getPaths().size(), result.getPaths().size());
            assertEquals(68.5, result.getRouteCost(), 0.1);
            
            // NO PATH
            res = graph.kBestPaths(p4, p1, 1);
            assertNull(res);
            

             
 
            
            
    }

    /**
     * Test of kBestPaths method, of class LandGraph.
     */
    @Test
    public void testKBestPaths_4args() throws SQLException {
         //Exception
            boolean flag = false;
            try{
                graph.kBestPaths(new ArrayList<>(), null, p3, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            
            flag = false;
            try{
                graph.kBestPaths(new ArrayList<>(),p1, null, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            flag = false;
            try{
                graph.kBestPaths(p1, p1, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            flag = false;
            try{
                graph.kBestPaths(p1, p3, -1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            flag = false;
            try{
                graph.kBestPaths(null, p1, p3, 2);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            

             

             
    

    }

    @Test
    public void testInvalidData() throws SQLException{
        boolean flag = false;
        try{
            graph = new LandGraph(-1, 45);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        try{
            graph = new LandGraph(120, -2);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
    }
    
}
