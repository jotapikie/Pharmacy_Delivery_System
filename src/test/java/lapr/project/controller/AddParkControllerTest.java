/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Park;
import lapr.project.model.ParkSlot;
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
 * @author Helder
 */
public class AddParkControllerTest {
    private static PharmacyDB pdb;
    private static ParkDB parkdb;
    private static String administratorEmail;
    private static Park park;
    private static AddParkController apc;
    private static ParkSlot slot;
    private static HashSet<ParkSlot> slots= new HashSet<>();
    
    public AddParkControllerTest() throws SQLException {
        pdb = mock(PharmacyDB.class);
        parkdb = mock(ParkDB.class);
        apc=new AddParkController(pdb,parkdb,administratorEmail);
        
        
        slot= new ParkSlot(1,true);
        slots.add(slot);
        park= new Park(1,3,"SCOOTER",slots);
        when(parkdb.getParkByID(1)).thenReturn(park);
        
    }
    
    @BeforeAll
    public static void setUpClass() {
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
     * Test of getSelectedPark method, of class AddParkController.
     */
    @Test
    public void testGetSelectedPark() throws Exception {
        System.out.println("getSelectedPark");
        String expResult = park.toString();
        String result = apc.getSelectedPark(1);
        assertEquals(expResult, result);
    }

    
}
