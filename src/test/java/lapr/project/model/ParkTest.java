package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {


    private static Park park1;
    private static Park park2;
    private static Park park3;
    private static ParkSlot slot;
    private static ParkSlot slot2;
    private static HashSet<ParkSlot> slots= new HashSet<>();
    private ParkTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        slot= new ParkSlot(1,true);
        slot2= new ParkSlot(2,false);
        slots.add(slot);
        slots.add(slot2);
        park1 = new Park(1,3,"SCOOTER",slots);
        park2 = new Park(2,5,"scooter",slots);
        park3 = new Park(3, 3, "scooter", slots);
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
    @Test
    void getId() {
        int expResult=1;
        int res=park1.getId();
        assertEquals(expResult,res);
    }

    @Test
    void setIdSucess() {
        int expResult=10;
        park1.setId(10);
        int res= park1.getId();
        assertEquals(expResult,res);
    }
    @Test
    void setIdFail() {
        try {
            park1.setId(0);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }
    @Test
    void setIdFail2() {
        try {
            park1.setId(-1);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void getnMaxVehicles() {
        int expRes=3;
        int res= park1.getnMaxVehicles();
        assertEquals(expRes,res);
    }

    @Test
    void setnMaxVehicles() {
        int expResult=10;
        park1.setnMaxVehicles(10);
        int res= park1.getnMaxVehicles();
        assertEquals(expResult,res);
    }

    @Test
    void setnMaxVehiclesFail() {
        try {
            park1.setnMaxVehicles(-10);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void setnMaxVehiclesFail1() {
        try {
            park1.setnMaxVehicles(0);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void getType() {
        String expRes="SCOOTER";
        String res= park1.getType();
        assertEquals(expRes,res);
    }

    @Test
    void setTypeSucess() {
        String expResult="SCOOTER";
        park1.setType(expResult);
        String res= park1.getType();
        assertEquals(expResult,res);
    }

    @Test
    void setTypeFail() {
        try {
            park1.setType("mota");
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void getSlots() {
        HashSet<ParkSlot> expRes= slots;
        HashSet<ParkSlot> res= park1.getSlots();
        assertEquals(expRes,res);
    }

    @Test
    void setSlots() {
        HashSet<ParkSlot> expResult= slots;
        park1.setSlots(slots);
        HashSet<ParkSlot> res= park1.getSlots();
        assertEquals(expResult,res);
    }

    @Test
    void setSlotsFail() {
        try {
            slots.add(new ParkSlot(5,true));
            slots.add(new ParkSlot(7,false));
            park3.setSlots(slots);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void setSlotsFail1() {
        try {
            HashSet<ParkSlot> slotsEmpty= new HashSet<>();
            park3.setSlots(slotsEmpty);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }



    @Test
    void updatePark() {
        park2.updatePark(5,"SCOOTER",slots);
        assertEquals(park2.getnMaxVehicles(), 5);
        assertEquals(park2.getType(), "SCOOTER");
        assertEquals(park2.getSlots(), slots);
        assertNotEquals(park2.toString(), park1.toString());
    }

    @Test
    void testHashCode() {
    }

    /**
     * Test of equals method
     */
    @Test
    public void testEquals() {
        Object obj = null;
        boolean expResult = false;
        boolean result = park1.equals(obj);
        assertEquals(expResult, result);
    }


}