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
import lapr.project.model.Address;
import lapr.project.model.Administrator;
import lapr.project.model.Park;
import lapr.project.model.ParkSlot;
import lapr.project.model.Pharmacy;
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
    
    private static AddParkController apc;
    private static ParkDB pdb;
    private static PharmacyDB phadb;
    private static Park p1;
    private static ParkSlot slot;
    private static ParkSlot slot2;
    private static String email="admin@";
    private static HashSet<ParkSlot> slots= new HashSet<>();
    private static Pharmacy pha;
    private static Administrator admin=new Administrator("helder","helder@gmail.com","abc");
    private static Address address = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "porto", 114, "4250-527");
    
    
    public AddParkControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() throws SQLException {
        pdb=mock(ParkDB.class);
        phadb=mock(PharmacyDB.class);
        apc=new AddParkController(phadb,pdb,email);
        slot= new ParkSlot(1,true);
        slot2= new ParkSlot(2,false);
        slots.add(slot);
        slots.add(slot2);
        p1=new Park(1,3,"SCOOTER",slots);
        when(pdb.getParkByID(1)).thenReturn(p1);
        
        pha=new Pharmacy(1,912345678,"Farmacia Central",admin,address,new HashSet<Park>());
        when(phadb.getPharmacyByAdministrator(email)).thenReturn(pha);
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
        int id = 1;
        String expResult = p1.toString();
        String result = apc.getSelectedPark(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of addParkToPharmacy method, of class AddParkController.
     */
    @Test
    public void testAddParkToPharmacy() throws Exception {
        apc.addParkToPharmacy();
        HashSet<Park> parks =new HashSet<>();
        parks=pha.getParks();
        assertEquals(true, parks.contains(p1));
    }
    
}
