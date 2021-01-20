package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {


    private static Park park1;
    private static Park park2;
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
        park1 = new Park(1,3,"SCOOTER",1001);
        park2 = new Park(2,5,"scooter",1002);
        park1.setSlots(slots);
        park2.setSlots(slots); 
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
    void setnMaxVehiclesFail() {
        try {
            park1.setnMaxVehicles(-10);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
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
        Set<ParkSlot> expRes= slots;
        Set<ParkSlot> res= park1.getSlots();
        assertEquals(expRes,res);
    }

    @Test
    void setSlots() {
        Set<ParkSlot> expResult= slots;
        park1.setSlots(slots);
        Set<ParkSlot> res= park1.getSlots();
        assertEquals(expResult,res);
        
        boolean flag = false;
        try{
            park1.setSlots(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        flag = false;
        park1.setnMaxVehicles(2);
        HashSet<ParkSlot> temp = new HashSet<>();
        temp.add(new ParkSlot(5, true));
        temp.add(new ParkSlot(6, true));
        temp.add(new ParkSlot(7, true));
        try{
            park1.setSlots(temp);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        
        
    }

    @Test
    void updatePark() {
        park2.updatePark(5,"SCOOTER",slots);
        assertEquals(park2.getnMaxVehicles(), 5);
        assertEquals(park2.getType(), "SCOOTER");
        assertEquals(park2.getSlots(), slots);
        assertNotEquals(park2.toString(), park1.toString());
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

    /**
     * Test of equals method
     */
    @Test
    public void testEquals1() {
        Object obj = park1;
        boolean expResult = true;
        boolean result = park1.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method
     */
    @Test
    public void testEquals2() {
        String obj = "TEST";
        boolean expResult = false;
        boolean result = park1.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method
     */
    @Test
    public void testEquals3() {
        Object obj= park2;
        boolean expResult = false;
        boolean result = park1.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method
     */
    @Test
    public void testEquals4() {
        park2.setId(1);
        Object obj= park2;
        boolean expResult = false;
        boolean result = park1.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Location.
     */
    @Test
    public void testHashCode() {

        int expResult = 103;
        int result = park1.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class Park.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = park1.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Park.
     */
    @Test
    public void testSetId() {
        int expResult=10;
        park1.setId(10);
        int res= park1.getId();
        assertEquals(expResult,res);
        
        boolean flag = false;
        try{
            park1.setId(-4);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of getnMaxVehicles method, of class Park.
     */
    @Test
    public void testGetnMaxVehicles() {
        int expRes=3;
        int res= park1.getnMaxVehicles();
        assertEquals(expRes,res);
    }

    /**
     * Test of setnMaxVehicles method, of class Park.
     */
    @Test
    public void testSetnMaxVehicles() {
        int expResult=10;
        park1.setnMaxVehicles(10);
        int res= park1.getnMaxVehicles();
        assertEquals(expResult,res);
    }

    /**
     * Test of getType method, of class Park.
     */
    @Test
    public void testGetType() {
        String expRes="SCOOTER";
        String res= park1.getType();
        assertEquals(expRes,res);
    }
    
    /**
     * Test of getMaxEnergy method, of class Park.
     */
    @Test
    public void testGetMaxEnergy() {
        double expRes=1001;
        String res= park1.getType();
        assertEquals(expRes,res);
    }
    
    /**
     * Test of setMaxEnergy method, of class Park.
     */
    @Test
    public void testSetMaxEnergy() {
        int expResult=1000;
        park1.setnMaxVehicles(1000);
        int res= park1.getnMaxVehicles();
        assertEquals(expResult,res);
    }

    /**
     * Test of toString method, of class Park.
     */
    @Test
    public void testToString() {
        String expResult = "10 3 SCOOTER";
        String result = park1.toString();
        assertEquals(expResult, result);
    }






}