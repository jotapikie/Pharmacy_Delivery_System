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
public class MainGraphTest {
    
    private static MainGraph graph;
    
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
        path2 = new Pathway(p1, p3, 0.23, 100, 0.2);
        path3 = new Pathway(p2, p3, 0.5, 300, 0.6);
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
        MainGraph.setup(null, null);
        MainGraph.setup(gpdb, pdb);
        graph = new MainGraph();
    }
    





    /**
     * Test of getVertexes method, of class MainGraph.
     */
    @Test
    public void testGetVertexes() throws SQLException {
        Iterator<GeographicalPoint> it = graph.getVertexes().iterator();
        assertEquals(p1, it.next());
        assertEquals(p2, it.next());
        assertEquals(p3, it.next());
        assertEquals(p4, it.next());
        assertEquals(p5, it.next());
        assertFalse(it.hasNext());
        
        List<GeographicalPoint> temp = new ArrayList<>(allPoints);
        temp.add(p1);
        when(gpdb.getGeographicalPoints()).thenReturn(temp);
        boolean flag = false;
        try{
            MainGraph tempGraph = new MainGraph();
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of getEdges method, of class MainGraph.
     */
    @Test
    public void testGetEdges() throws SQLException {
        Iterator<Pathway> it = graph.getEdges().iterator();
        assertEquals(path1, it.next());
        assertEquals(path2, it.next());
        assertEquals(path3, it.next());
        for(int i = 0; i < 6; i++){
            assertTrue(it.hasNext());
            it.next();
            
        }
        assertFalse(it.hasNext());
        
        List<Pathway> temp = new ArrayList<>(allPaths);
        temp.add(path1);
        when(pdb.getPaths()).thenReturn(temp);
        boolean flag = false;
        try{
            MainGraph tempGraph = new MainGraph();
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);

    }

    /**
     * Test of kBestPaths method, of class MainGraph.
     */
    @Test
    public void testKBestPaths() throws SQLException {
            List<Route> res = graph.kBestPaths(p1, p3, 1);
            Iterator<Route> it = res.iterator();
            
            // p1 --> p3
            Route r1 = new Route(path2);
            assertEquals(r1, it.next());
            
            res = graph.kBestPaths(p1, p5, 2);
            it = res.iterator();
            
            //p1--> p3 --> p4 --> p5
            r1 = new Route(path2);
            r1.addPath(path8);
            r1.addPath(path9);
            assertEquals(r1, it.next());
            
            //p1--> p3 --> p4 --> p5--> p4 -- > p5
            r1.addPath(path10);
            r1.addPath(path9);
            assertEquals(r1, it.next());
            
            
            //Exception
            boolean flag = false;
            try{
                graph.kBestPaths(null, p3, 1);
            }catch(IllegalArgumentException e){
                flag = true;
            }
            assertTrue(flag);
            
            
            flag = false;
            try{
                graph.kBestPaths(p1, null, 1);
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
            
            // No path 
            res = graph.kBestPaths(p4, p1, 1);
            assertNull(res);
             
            // No geographical points
             when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
             when(pdb.getPaths()).thenReturn(new ArrayList<>());
             MainGraph.setup(gpdb, pdb);
             graph = new MainGraph();
             assertEquals(false,graph.getEdges().iterator().hasNext());
             assertEquals(false,graph.getVertexes().iterator().hasNext());
             
             flag = false;
             try{
                   graph.kBestPaths(p1, p3, 1);
             }catch(IllegalArgumentException e){
                 flag = true;
             }
             assertTrue(flag);

            
            
    }
    
       /**
     * Test of kBestPaths method, of class MainGraph.
     */
    @Test
    public void testKBestPaths_With_visit() throws SQLException {
            List<GeographicalPoint> toVisit = new ArrayList<>();
            toVisit.add(p4);
            List<Route> res = graph.kBestPaths(toVisit,p1, p3, 1);
            assertNull(res); // NO PATH

            
            toVisit = new ArrayList<>();
            toVisit.add(p1);
            toVisit.add(p5);
            res = graph.kBestPaths(toVisit, p2, p4, 2);
            Iterator<Route> it = res.iterator();
            
            //p2--> p3 --> p1 --> p3 --> p4 --> p4 --> p5 --> p4
            Route r1 = new Route(path3);
            r1.addPath(path4);
            r1.addPath(path2);
            r1.addPath(path8);
            r1.addPath(path9);
            r1.addPath(path10);
            assertEquals(r1, it.next());
            

            
            
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
            

             
            // No geographical points
             when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
             when(pdb.getPaths()).thenReturn(new ArrayList<>());
             MainGraph.setup(gpdb, pdb);
             graph = new MainGraph();
             assertEquals(false,graph.getEdges().iterator().hasNext());
             assertEquals(false,graph.getVertexes().iterator().hasNext());
             
             flag = false;
             try{
                   graph.kBestPaths(new ArrayList<>(),p1, p3, 1);
             }catch(IllegalArgumentException e){
                 flag = true;
             }
             assertTrue(flag);

    }

    
}
