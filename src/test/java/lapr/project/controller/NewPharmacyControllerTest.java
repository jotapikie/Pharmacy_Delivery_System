/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.util.HashSet;
import java.util.Set;
import lapr.project.data.AddressDB;
import lapr.project.data.AdministratorDB;
import lapr.project.data.ParkDB;
import lapr.project.data.PharmacyDB;
import lapr.project.model.Address;
import lapr.project.model.Administrator;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Park;
import lapr.project.model.ParkSlot;
import lapr.project.model.Pharmacy;
import lapr.project.model.VehicleCategory;
import lapr.project.utils.Constants;
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
public class NewPharmacyControllerTest {
    
    private static RegisterPharmacyController controller;
    private static PharmacyDB pdb;
    private static AddressDB adb;
    private static AdministratorDB admindb;
    private static ParkDB parkdb;
    
    private static Address add;
    private static Administrator admin;
    private static Park park;
    private static Pharmacy pha;
    private static Set<Park> parks;
    
    private static Set<Pharmacy> phs;
    
    @BeforeAll
    public static void setUpClass() {
        pdb = mock(PharmacyDB.class);
        adb = mock(AddressDB.class);
        admindb = mock(AdministratorDB.class);
        parkdb = mock(ParkDB.class);
        
        add = new Address("Street1", new GeographicalPoint(12, 45, 0.2), "City1", 45, "3453-456");
        admin = new Administrator("Admin1", "admin1@gmail.com", "123");
        
        phs = new HashSet<>();
        Set<ParkSlot> slots = new HashSet<>();
        slots.add(new ParkSlot(1, true));
        slots.add(new ParkSlot(2, true));
        slots.add(new ParkSlot(3, true));
        slots.add(new ParkSlot(4, false));
        slots.add(new ParkSlot(5, false));
        park = new Park(1, 5, VehicleCategory.SCOOTER, 67, slots);
        parks = new HashSet<>();
        parks.add(park);
        pha = new Pharmacy(1, 912541234, "Pharmacy1", admin, add, parks);
    }
    

    
    @BeforeEach
    public void setUp() {
        controller = new RegisterPharmacyController(pdb, adb, admindb, parkdb);
        
        when(adb.newAdress("Street1", 12, 45, 0.2, "City1", 45, "3453-456")).thenReturn(add);
        when(admindb.newAdministrator("Admin1", "admin1@gmail.com", "123")).thenReturn(admin);
        when(parkdb.newPark(1,5, 3, "Scooter", 67)).thenReturn(park);
        when(pdb.newPhamarcy(Constants.DEFAULT_ID, 912541234, "Pharmacy1", admin, add, parks)).thenReturn(pha);
                
    }


    /**
     * Test of newAddress method, of class RegisterPharmacyController.
     */
    @Test
    public void testNewAddress() {
        String res = controller.newAddress("Street1", 12, 45, 0.2, "City1", "3453-456", 45);
        assertEquals(add.toString(), res);
    }

    /**
     * Test of newAdministrator method, of class RegisterPharmacyController.
     */
    @Test
    public void testNewAdministrator() {
        assertEquals(admin.toString(), controller.newAdministrator("Admin1", "admin1@gmail.com", "123"));
    }

    /**
     * Test of newPark method, of class RegisterPharmacyController.
     */
    @Test
    public void testNewPark() {
        assertEquals(park.toString(), controller.newPark("Scooter", 5, 3, 67));
    }

    /**
     * Test of newPharmacy method, of class RegisterPharmacyController.
     */
    @Test
    public void testNewPharmacy() {
        controller.newAddress("Street1", 12, 45, 0.2, "City1", "3453-456", 45);
        controller.newAdministrator("Admin1", "admin1@gmail.com", "123");
        controller.newPark("Scooter", 5, 3, 67);
        assertEquals(pha.toString(), controller.newPharmacy("Pharmacy1", 912541234));
    }

    /**
     * Test of addToQueue method, of class RegisterPharmacyController.
     */
    @Test
    public void testAddToQueue() {
            assertFalse(controller.addToQueue()); // pha == null
           
            controller.newAddress("Street1", 12, 45, 0.2, "City1", "3453-456", 45);
            controller.newAdministrator("Admin1", "admin1@gmail.com", "123");
            controller.newPark("Scooter", 5, 3, 67);
            controller.newPharmacy("Pharmacy1", 912541234);
            assertTrue(controller.addToQueue());
            
            controller.newAddress("Street1", 12, 45, 0.2, "City1", "3453-456", 45);
            controller.newAdministrator("Admin1", "admin1@gmail.com", "123");
            controller.newPark("Scooter", 5, 3, 67);
            controller.newPharmacy("Pharmacy1", 912541234);
            assertFalse(controller.addToQueue());
            
            
    }

    /**
     * Test of registPharmacies method, of class RegisterPharmacyController.
     */
    @Test
    public void testRegistPharmacies() throws Exception {
            assertEquals(0,controller.registPharmacies());
            
            controller.newAddress("Street1", 12, 45, 0.2, "City1", "3453-456", 45);
            controller.newAdministrator("Admin1", "admin1@gmail.com", "123");
            controller.newPark("Scooter", 5, 3, 67);
            controller.newPharmacy("Pharmacy1", 912541234);
            controller.addToQueue();
            phs.add(pha);
            when(pdb.save(phs)).thenReturn(1);
            assertEquals(1, controller.registPharmacies());
    }
    
}
