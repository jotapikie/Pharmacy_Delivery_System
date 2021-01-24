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
        
        path1 = new Pathway(p1, p2, StreetType.ASPHALT, 300, new Wind(1,1,1), "Street1");
        path2 = new Pathway(p1, p3, StreetType.ASPHALT, 100, new Wind(1,1,1), "Street2");
        path3 = new Pathway(p2, p3, StreetType.ASPHALT, 300, new Wind(1,1,1), "Street3");
        path4 = new Pathway(p3, p1, StreetType.ASPHALT, 100, new Wind(1,1,1), "Street4");
        path6 = new Pathway(p3, p2, StreetType.ASPHALT, 450.2, new Wind(1,1,1), "Street5");
        path7 = new Pathway(p2, p4, StreetType.ASPHALT, 120.3, new Wind(1,1,1), "Street6");
        path8 = new Pathway(p3, p4, StreetType.ASPHALT, 94.7, new Wind(1,1,1),"Street7");
        path9 = new Pathway(p4, p5, StreetType.ASPHALT, 23, new Wind(1,1,1),"Street8");
        path10 = new Pathway(p5, p4, StreetType.ASPHALT, 23, new Wind(1,1,1),"Street9");
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
        new RouteAlgorithms();
        landGraph = new LandGraph(135.8);
    }
    


    /**
     * Test of kBestRoutes method, of class RouteAlgorithms.
     */
    @Test
    public void testKBestRoutes_4args() {
        // GRAPH NULL
        boolean flag = false;
        try{
            RouteAlgorithms.kBestRoutes(null, p1, p2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // ORIGIN NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, null, p2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // DESTINATION NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, null, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // k <= 0
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, p2, -3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        landGraph = mock(LandGraph.class);
        when(landGraph.getRouteGraph()).thenReturn(null);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, p2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        when(landGraph.getRouteGraph()).thenReturn(new Graph<>(false));
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph, p1, p2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
        
        
        

    }

    /**
     * Test of kBestRoutes method, of class RouteAlgorithms.
     */
    @Test
    public void testKBestRoutes_5args() {
        boolean flag = false;
        try{
            RouteAlgorithms.kBestRoutes(null,new ArrayList<>(), p1, p2, 1,Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // ORIGIN NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,new ArrayList<>(), null, p2, 1, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // DESTINATION NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,new ArrayList<>(), p1, null, 1, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // k <= 0
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,new ArrayList<>(), p1, p2, -3, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // toVisit = null
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(landGraph,null, p1, p2, 4, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // To visit copntains origin point
        flag = false;
        List<GeographicalPoint> toVisit = new ArrayList<>();
        toVisit.add(p1);
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p2, 4, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // To visit contains destination point
        flag = false;
        toVisit.clear();
        toVisit.add(p2);
        try{
            RouteAlgorithms.kBestRoutes(landGraph,toVisit, p1, p2, 4, Integer.MAX_VALUE);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);

        
 
        
        
        
        
        
    }
    
}
