package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class ParkSlotTest {


    private static ParkSlot slot;
    private static ParkSlot slot2;
    private ParkSlotTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        slot= new ParkSlot(1,true);
        slot2= new ParkSlot(2,false);
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
        int expRes=1;
        int res=slot.getId();
        assertEquals(expRes,res);
    }

    @Test
    void setIdSucess() {
        int expResult=10;
        slot.setId(10);
        int res= slot.getId();
        assertEquals(expResult,res);
    }
    @Test
    void setIdFail() {
        try {
            slot.setId(0);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }
    @Test
    void setIdFail2() {
        try {
            slot.setId(-10);
            fail("Expected IllegalArgument");
        }catch (IllegalArgumentException ignored){

        }
    }

    @Test
    void isAbleToCharge() {
        boolean expRes=true;
        boolean res=slot.isAbleToCharge();
        assertEquals(expRes,res);
    }

    @Test
    void isAbleToCharge1() {
        slot2.setAbleToCharge(false);
        boolean expRes=false;
        boolean res=slot2.isAbleToCharge();
        assertEquals(expRes,res);
    }
    @Test
    void setAbleToCharge() {
        boolean expRes=true;
        slot2.setAbleToCharge(true);
        boolean res= slot2.isAbleToCharge();
        assertEquals(expRes,res);
    }




    @Test
    void setScooter() {
        EScooter scooter= new EScooter(1,State.INACTIVE,10,1);
        slot.setScooter(scooter);
        EScooter scooterRes=slot.getScooter();
        assertEquals(scooter,scooterRes);
    }

    @Test
    void getScooter() {
        EScooter scooterExp=null;
        EScooter scooter=slot2.getScooter();
        assertEquals(scooterExp,scooter);

    }


}