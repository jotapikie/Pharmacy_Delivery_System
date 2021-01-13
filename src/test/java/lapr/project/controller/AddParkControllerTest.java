/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Park;
import lapr.project.model.ParkSlot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 *
 * @author Helder
 */
public class AddParkControllerTest {
    private static AddParkController controller;
    private static ParkDB pdb;
    private static PharmacyDB phadb;
    private static Park p1;
    private static Park p2;
    private static ParkSlot slot;
    private static ParkSlot slot2;
    private static HashSet<ParkSlot> slots= new HashSet<>();
    
    public AddParkControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        pdb = mock(ParkDB.class);
        controller = new AddParkController(phadb, pdb,"client1@lapr.com");
        slot= new ParkSlot(1,true);
        slot2= new ParkSlot(2,false);
        slots.add(slot);
        slots.add(slot2);
        p1 = new Park(1,3,"SCOOTER",slots);
        p2 = new Park(2,5,"scooter",slots);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSelectedPark method, of class AddParkController.
     */
    @Test
    public void testGetSelectedPark() throws Exception {
        assertEquals(p1.toString(), controller.getSelectedPark(1));
        assertEquals(p2.toString(), controller.getSelectedPark(2));
        assertEquals(null, controller.getSelectedPark(4));
    }

    
}
