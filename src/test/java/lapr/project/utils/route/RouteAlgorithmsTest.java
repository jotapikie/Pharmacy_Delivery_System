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
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.MainGraph;
import lapr.project.model.Pathway;
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
public class RouteAlgorithmsTest {
    
    private static MainGraph mainGraph;
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
        MainGraph.setup(gpdb, pdb);
        new RouteAlgorithms();
        landGraph = new LandGraph(135.8, 4.7);
        mainGraph = new MainGraph();
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
            RouteAlgorithms.kBestRoutes(mainGraph, null, p2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // DESTINATION NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph, p1, null, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // k <= 0
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph, p1, p2, -3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // ROUTE WITHOUT HAVING ENERGY IN CONSIDEARION (RETURN THE LESS DISTANCE ROUTE)
        List<Route> routes = RouteAlgorithms.kBestRoutes(mainGraph, p1, p4, 2);
        assertEquals(2, routes.size());
        Route res1 = routes.get(0);
        Route res2 = routes.get(1);
        //p1-> p3 -> p4
        Route exp = new Route(path2);
        exp.addPath(path8);
        assertEquals(exp, res1);
        assertEquals(path2.getDistance()+path8.getDistance(), res1.getRouteDistance());
        assertTrue(res1.getRouteDistance() == res1.getRouteCost());
        
        // p1 -> p3 -> p4 -> p5 -> p4
        exp = new Route(path2);
        exp.addPath(path8);
        exp.addPath(path9);
        exp.addPath(path10);
        assertEquals(exp, res2);
        assertEquals(path2.getDistance() + path8.getDistance() + path9.getDistance() + path10.getDistance(), res2.getRouteDistance());
        assertTrue(res2.getRouteDistance() == res2.getRouteCost());
        
        // NO PATH
        routes = RouteAlgorithms.kBestRoutes(mainGraph, p4, p3, 1);
        assertNull(routes);
 
        
        // ROUTE WITH ENERGY CONSIDERATION
        routes = RouteAlgorithms.kBestRoutes(landGraph, p1, p4, 2);
        assertEquals(2, routes.size());
        res1 = routes.get(0);
        res2 = routes.get(1);
        
        exp = new Route(path2);
        exp.addPath(path8);
        assertEquals(exp.getPaths().size(), res1.getPaths().size());
        assertTrue(exp.getPaths().get(0).getOriginPoint().equals(res1.getPaths().get(0).getOriginPoint()));
        assertTrue(exp.getPaths().get(0).getDestinationPoint().equals(res1.getPaths().get(0).getDestinationPoint()));
        assertEquals(33.9, res1.getRouteCost(), 0.1);
        assertEquals(path2.getDistance() + path8.getDistance(), res1.getRouteDistance());
        assertTrue(res1.getRouteCost()< res1.getRouteDistance());
        
        mainGraph = mock(MainGraph.class);
        when(mainGraph.getRouteGraph()).thenReturn(null);
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph, p1, p2, 3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        when(mainGraph.getRouteGraph()).thenReturn(new Graph<>(false));
        
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph, p1, p2, 3);
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
            RouteAlgorithms.kBestRoutes(null,new ArrayList<>(), p1, p2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // ORIGIN NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,new ArrayList<>(), null, p2, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // DESTINATION NULL
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,new ArrayList<>(), p1, null, 1);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // k <= 0
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,new ArrayList<>(), p1, p2, -3);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // toVisit = null
        flag = false;
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,null, p1, p2, 4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // To visit copntains origin point
        flag = false;
        List<GeographicalPoint> toVisit = new ArrayList<>();
        toVisit.add(p1);
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,toVisit, p1, p2, 4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        // To visit contains destination point
        flag = false;
        toVisit.clear();
        toVisit.add(p2);
        try{
            RouteAlgorithms.kBestRoutes(mainGraph,toVisit, p1, p2, 4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);

        
        // if to visit is empty, the resutl should be the result of the best route between origin and destination
        List<Route> res = RouteAlgorithms.kBestRoutes(mainGraph, new ArrayList<GeographicalPoint>(), p1, p2, 3);
        List<Route> exp = RouteAlgorithms.kBestRoutes(mainGraph, p1, p2, 3);
        assertEquals(exp, res);
        
        toVisit.clear();
        toVisit.add(p5);
        toVisit.add(p3);
        res = RouteAlgorithms.kBestRoutes(mainGraph,toVisit, p1, p4, 3);
        assertEquals(3, res.size());
        
        //p1->p3->p4->p5->p4
        Route expected = new Route(path2);
        expected.addPath(path8);
        expected.addPath(path9);
        expected.addPath(path10);
        assertEquals(expected, res.get(0));
        
        
        
        
        
    }
    
}
