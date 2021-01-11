package lapr.project.controller;

import lapr.project.data.PharmacyDB;
import lapr.project.model.*;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class NewPharmacyControllerTest {

    private static NewPharmacyController nfc2;
    private static NewPharmacyController nfc;
    private static PharmacyDB fdb;

    @BeforeAll
    public static void setUpClass() {
        fdb = mock(PharmacyDB.class);
        nfc = new NewPharmacyController();
        nfc2 = new NewPharmacyController(fdb);
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    public NewPharmacyControllerTest() {
    }

    /**
     * Test of newPharmacy method, of class NewPharmacyController.
     */
    @Test
    public void testNewPharmacy(){

        Administrator admin= new Administrator("jota","1180567@isep.ipp.pt","senha");
        Address address= new Address("Rua",1,1,"porto",10,"4250-222");
        HashSet<Park> parks= new HashSet<>();
        HashSet<ParkSlot> slots= new HashSet<>();
        slots.add(new ParkSlot(1, true));
        parks.add(new Park(1,2,"SCOOTER", slots));
        Pharmacy value = new Pharmacy(1,918222222,"FarmaciaCool",admin,address,parks);
        when(fdb.newPhamarcy(anyInt(),anyInt(),anyString(),anyObject(),anyObject(),anyObject())).thenReturn(value);

        Pharmacy expResult = value;
        Pharmacy result = nfc2.newPharmacy(1,918222222,"FarmaciaCool",admin,address,parks);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerPharmacy method, of class NewPharmacyController.
     */
    @Test
    public void testRegisterPharmacy() throws SQLException {
        NewPharmacyController nfc3 = new NewPharmacyController(fdb);
        int id= 1;
        int nr=918822222;
        String name= "DABID";
        Administrator admin= new Administrator("jota","1180567@isep.ipp.pt","senha");
        Address address= new Address("Rua",1,1,"porto",10,"4250-222");
        HashSet<Park> parks= new HashSet<>();
        HashSet<ParkSlot> slots= new HashSet<>();
        slots.add(new ParkSlot(1, true));
        parks.add(new Park(1,2,"SCOOTER", slots));
        nfc3.newPharmacy(id,nr,name,admin,address,parks);
        when(fdb.save(any(Pharmacy.class))).thenReturn(true);

        boolean expResult = true;
        boolean result = nfc3.registerPharmacy();
        assertEquals(expResult, result);

    }

    /**
     * Test of addPharmacyToQueue method, of class NewPharmacyController.
     */
    @Test
    public void testAddPharmacyToQueue() {
        NewPharmacyController nfc2 = new NewPharmacyController();
        int id= 1;
        int nr=918822222;
        String name= "DABID";
        Administrator admin= new Administrator("jota","1180567@isep.ipp.pt","senha");
        Address address= new Address("Rua",1,1,"porto",10,"4250-222");
        HashSet<Park> parks= new HashSet<>();
        HashSet<ParkSlot> slots= new HashSet<>();
        slots.add(new ParkSlot(1, true));
        parks.add(new Park(1,2,"SCOOTER", slots));
        Pharmacy p = nfc2.newPharmacy(id, nr,  name, admin, address, parks);
        nfc2.addPharmacyToQueue();
        assertEquals(1, nfc2.getPharmacyList().size());
    }

    /**
     * Test of insertPharmacyBatchOp method, of class NewPharmacyController.
     */
    @Test
    public void testInsertPharmaciesBatchOp() throws Exception {
        int numInserts = nfc2.insertPharmacyBatchOp();
        when(fdb.save(nfc2.getPharmacyList())).thenReturn(numInserts);



        assertEquals(0, nfc.getPharmacyList().size());
        assertEquals(numInserts, numInserts);
    }

    /**
     * Test of getPharmacy method, of class NewPharmacyController.
     */
    @Test
    public void testGetPharmacies() {
        int id= 1;
        int nr=918822222;
        String name= "DABID";
        Administrator admin= new Administrator("jota","1180567@isep.ipp.pt","senha");
        Address address= new Address("Rua",1,1,"porto",10,"4250-222");
        HashSet<Park> parks= new HashSet<>();
        HashSet<ParkSlot> slots= new HashSet<>();
        slots.add(new ParkSlot(1, true));
        parks.add(new Park(1,2,"SCOOTER", slots));

        Pharmacy b = nfc.newPharmacy(id,nr,name,admin,address,parks);
        HashSet<Pharmacy> pharms = new HashSet<>();
        pharms.add(b);
        nfc.addPharmacyToQueue();
        assertEquals(pharms, nfc.getPharmacyList());
    }

}