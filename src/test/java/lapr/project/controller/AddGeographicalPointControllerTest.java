/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import lapr.project.data.GeographicalPointDB;
import lapr.project.model.GeographicalPoint;
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
public class AddGeographicalPointControllerTest {
    
    private static AddGeographicalPointController controller;
    private static GeographicalPointDB gpdb;
    
    private static GeographicalPoint p1;
    private static GeographicalPoint p2;
    private static GeographicalPoint p3;
    
    private static Set<GeographicalPoint> points;
    
    @BeforeAll
    public static void setUpClass() {
        p1 = new GeographicalPoint(34.6, 23.7, 0.2, "p1");
        p2 = new GeographicalPoint(37.8, 21.8, 0.2, "p2");
        p3 = new GeographicalPoint(6.7, 8.9, 0.3, "p3");
        
        points = new HashSet<>();
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        gpdb = mock(GeographicalPointDB.class);
        
        controller = new AddGeographicalPointController(gpdb);
        
        when(gpdb.newGeographicalPoint(34.6, 23.7, 0.2, "p1")).thenReturn(p1);
        when(gpdb.newGeographicalPoint(37.8, 21.8, 0.2, "p2")).thenReturn(p2);
        when(gpdb.newGeographicalPoint(6.7, 8.9, 0.3, "p3")).thenReturn(p3);
        p3 = mock(GeographicalPoint.class);
        when(p3.toString()).thenReturn("");
        when(gpdb.savePoints(new HashSet<>())).thenReturn(0);
        
        
        
    }
    

    /**
     * Test of newGeographicalPoint method, of class AddGeographicalPointController.
     */
    @Test
    public void testNewGeographicalPoint() {
        assertNull(controller.newGeographicalPoint(1, 1, 0.2, "p4"));
        assertEquals(p1.toString(), controller.newGeographicalPoint(34.6, 23.7, 0.2, "p1"));
        assertEquals(p2.toString(), controller.newGeographicalPoint(37.8, 21.8, 0.2, "p2"));
    }

    /**
     * Test of addToQueue method, of class AddGeographicalPointController.
     */
    @Test
    public void testAddToQueue() {
            assertFalse(controller.addToQueue());
            controller.newGeographicalPoint(34.6, 23.7, 0.2, "p1");
            assertTrue(controller.addToQueue());
            controller.newGeographicalPoint(37.8, 21.8, 0.2, "p2");
            assertTrue(controller.addToQueue());
    }

    /**
     * Test of savePoints method, of class AddGeographicalPointController.
     */
    @Test
    public void testSavePoints() throws Exception {
         assertEquals(0, controller.savePoints());
         controller.newGeographicalPoint(34.6, 23.7, 0.2, "p1");
         assertTrue(controller.addToQueue());
         points.add(p1);
         when(gpdb.savePoints(points)).thenReturn(1);
         assertEquals(1, controller.savePoints());
         
         controller.newGeographicalPoint(37.8, 21.8, 0.2, "p2");
         assertTrue(controller.addToQueue());
         points.add(p2);
         when(gpdb.savePoints(points)).thenReturn(2);
         assertEquals(2, controller.savePoints());
    }
    
}
