/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import lapr.project.model.StreetType;
import lapr.project.utils.Utils;
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
public class AddPathControllerTest {
    
    private static AddPathController controller;
    private static GeographicalPointDB gpdb;
    private static PathwayDB pdb;
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    private static GeographicalPoint p4;
    private static List<GeographicalPoint> points;
    
    private static Pathway p;
    private static Set<Pathway> paths;
    
    @BeforeAll
    public static void setUpClass() {
        points = new ArrayList<>();
        p1 = new GeographicalPoint(21, 39, 0.2, "p1");
        p2 = new GeographicalPoint(22, 38, 0.3, "p2");
        p3 = new GeographicalPoint(22, 37, 0.4, "p3");
        p4 = new GeographicalPoint(24, 36, 0.5, "p4");
        points.add(p1);points.add(p2);points.add(p3);
        
        p = new Pathway(p1, p2, StreetType.ASPHALT, 4.5, 2.3, "Street1");
        paths = new HashSet<>();
    }
    
  
    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        pdb = mock(PathwayDB.class);
        controller = new AddPathController(gpdb, pdb);
        
        when(gpdb.getGeographicalPoints()).thenReturn(points);
        when(gpdb.getGeographicalPoint(21, 39)).thenReturn(p1);
        when(gpdb.getGeographicalPoint(22, 38)).thenReturn(p2);
        when(gpdb.getGeographicalPoint(22, 37)).thenReturn(p3);
        when(gpdb.getGeographicalPoint(24, 36)).thenReturn(p4);
        
        when(pdb.newPath(p1, p2, "Asphalt", 1, 2.4, "Street1")).thenReturn(p);
        
    }


    /**
     * Test of getAvailableGeographicalPoints method, of class AddPathController.
     */
    @Test
    public void testGetAvailableGeographicalPoints() throws Exception {
        assertEquals(Utils.listToString(points), controller.getAvailableGeographicalPoints());
        
        when(gpdb.getGeographicalPoints()).thenReturn(new ArrayList<>());
        assertEquals(new ArrayList<>(), controller.getAvailableGeographicalPoints());
    }

    /**
     * Test of selectPoints method, of class AddPathController.
     */
    @Test
    public void testSelectPoints() throws Exception {
        controller.getAvailableGeographicalPoints();
        assertNull(controller.selectPoints(1, 1, 21,39, "Asphalt", 23, 0.5, "Street1"));// Origin null
        assertNull(controller.selectPoints(21, 39, 1,1, "Sidewalk", 23, 0.5, "Street1"));// Destination null
        assertFalse(controller.selectPoints(21, 39, 22, 38, "Asphalt", 1, 2.4, "Street1").isEmpty());
    }

    /**
     * Test of addToQueue method, of class AddPathController.
     */
    @Test
    public void testAddToQueue() throws SQLException {
        assertFalse(controller.addToQueue());
        controller.getAvailableGeographicalPoints();
        controller.selectPoints(21, 39, 22, 38, "Asphalt", 1, 2.4, "Street1");
        assertTrue(controller.addToQueue());
        controller.selectPoints(21, 39, 22, 38, "Asphalt", 1, 2.4, "Street1");
        assertFalse(controller.addToQueue());
    }

    /**
     * Test of savePaths method, of class AddPathController.
     */
    @Test
    public void testSavePaths() throws Exception {
        assertEquals(0, controller.savePaths());
        
        controller.getAvailableGeographicalPoints();
        controller.selectPoints(21, 39, 22, 38, "Asphalt", 1, 2.4, "Street1");
        controller.addToQueue();
        paths.add(p);
        when(pdb.savePaths(paths)).thenReturn(1);
        assertEquals(1, controller.savePaths());
    }
    
}
