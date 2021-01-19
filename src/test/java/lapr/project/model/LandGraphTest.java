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
    
    public LandGraphTest() {
    }
    
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
        
        path1 = new Pathway(p1, p2, 0.2, 200, 0.4);
        path2 = new Pathway(p2, p3, 0.2, 100, 0.4);
        path3 = new Pathway(p3, p4, 0.2, 50, 0.4);
        path4 = new Pathway(p4, p5, 0.2, 70, 0.4);
        path5 = new Pathway(p5, p6, 0.2, 80, 0.4);
        path6 = new Pathway(p6, p7, 0.2, 90, 0.4);
        path7 = new Pathway(p7, p8, 0.2, 100, 0.4);
        path8 = new Pathway(p8, p1, 0.2, 300, 0.4);
        path9 = new Pathway(p3, p9, 0.2, 50, 0.4);
        path10 = new Pathway(p9, p4, 0.2, 70, 0.4);
        path11 = new Pathway(p4, p7, 0.2, 40, 0.4);
        path12 = new Pathway(p7, p1, 0.2, 70, 0.4);
        allPaths1 = new ArrayList<>();
        allPaths1.add(path1);allPaths1.add(path2);allPaths1.add(path3);allPaths1.add(path4);
        allPaths1.add(path5);allPaths1.add(path6);allPaths1.add(path7);allPaths1.add(path8);allPaths1.add(path9);
        allPaths1.add(path10);allPaths1.add(path11);allPaths1.add(path12);
    }

    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        when(gpdb.getGeographicalPoints()).thenReturn(allPoints1);
        when(pdb.getPaths()).thenReturn(allPaths1);
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
            
     }
    
    @Test
    public void testKbestPaths(){

        
        List<GeographicalPoint> points = new ArrayList<>();
        points.add(p7);
        points.add(p8);
        EScooter e = new EScooter(1, State.ACTIVE, 40, 20);
        List<Route> routes = graph.kBestPaths(points, p1, p1, 1, e.getMaxBat());
        List<Route> routes1 = graph.kBestPaths(p1, routes.get(0).getChargingPoints().getFirst(), 1);
        Route res = routes1.get(0);
        
        for(Route r: routes){
            System.out.println(r);
            System.out.println("##################");
        }
        if(res.getTotalEnergy() > e.getActualBat()){
            System.out.println("However, you should let your scooter charge until " +res.getTotalEnergy()+ " to start the run");
        }
        
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
