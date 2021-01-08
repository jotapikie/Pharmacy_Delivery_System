package lapr.project.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EScooterTest {

    private static EScooter scooterTest;

    public EScooterTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        scooterTest = new EScooter(20, 20, State.ACTIVE, 2, 2, 50, 75, 5);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getType method, of class Vehicle.
     */
    @Test
    public void testGetType() {
        String expResult = "ACTIVE";
        String result = scooterTest.getState().toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class vehicle.
     */
    @Test
    public void testSetType() {
        String value = "ACTIVE";
        EScooter instance = new EScooter();
        instance.setState(State.ACTIVE);
        String result = instance.getState().toString();
        String expResult = value;
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class vehicle.
     */
    @Test
    public void testSetStateMisspeled() {

        try {
            scooterTest.setState(State.valueOf("booo"));
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setState method, of class Vehicle.
     */
    @Test
    public void testSetStateEmpty() {

        try {
            scooterTest.setState(State.valueOf(""));
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }


    /**
     * Test of getMaxBat method, of class Vehicle.
     */
    @Test
    public void testGetMaxBat() {
        double expResult = 2;
        double result = scooterTest.getMaxBat();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setMaxBat method, of class Vehicle.
     */
    @Test
    public void testSetMaxBat3() {

        try {
            scooterTest.setMaxBat(-7);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setMaxBat method, of class Vehicle.
     */
    @Test
    public void testSetMaxBat4() {

        try {
            scooterTest.setMaxBat(0);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setMaxBat method, of class Vehicle
     */
    @Test
    public void testSetMaxBat1() {

        try {
            int value = 1;
            EScooter instance = new EScooter();
            instance.setMaxBat(value);
            double result = instance.getMaxBat();
            double expResult = value;
            assertEquals(expResult, result, 0);
        } catch (IllegalArgumentException e) {
            fail("expected IllegalArgumentException");
        }
    }

    /**
     * Test of getActualBat method, of class Vehicle.
     */
    @Test
    public void testGetActualBat() {
        double expResult = 2;
        double result = scooterTest.getActualBat();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatSmaller() {

        try {
            scooterTest.setActualBat(-10);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatBigger() {

        try {
            scooterTest.setActualBat(101);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatLimit() {

        try {
            scooterTest.setActualBat(-2);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatLimit9() {

        try {
            scooterTest.setActualBat(-1);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatLimit0() {

        try {
            int value = 0;
            EScooter instance = new EScooter();
            instance.setActualBat(value);
            double result = instance.getActualBat();
            int expResult = value;
            assertEquals(expResult, result, 0);
        } catch (IllegalArgumentException e) {
            fail("expected IllegalArgumentException");
        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatLimit1() {

        try {
            int value = 100;
            EScooter instance = new EScooter();
            instance.setMaxBat(102);
            instance.setActualBat(value);
            double result = instance.getActualBat();
            double expResult = value;
            assertEquals(expResult, result, 0);
        } catch (IllegalArgumentException e) {
            fail("expected IllegalArgumentException");
        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetActualBatLimit2() {

        try {
            int value = 1;
            EScooter instance = new EScooter();
            instance.setMaxBat(2);
            instance.setActualBat(value);
            double result = instance.getActualBat();
            int expResult = value;
            assertEquals(expResult, result, 0);
        } catch (IllegalArgumentException e) {
            fail("expected IllegalArgumentException");
        }
    }

    /**
     * Test of getMotor method, of class Vehicle.
     */
    @Test
    public void testGetMotor() {
        EScooter instance = new EScooter();
        int expResult = 0;
        double result = instance.getMotor();
        assertEquals(expResult, result);
    }
    
        /**
     * Test of getMotor method, of class Vehicle.
     */
    @Test
    public void testGetMotor2() {
        int expResult = 50;
        double result = scooterTest.getMotor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetMotor1() {

        try {
            scooterTest.setMotor(-1);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetMotor3() {

        try {
            scooterTest.setMotor(0);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {

        }
    }

    /**
     * Test of setActualBat method, of class Vehicle.
     */
    @Test
    public void testSetMotor2() {

        try {
            int value = 6;
            EScooter instance = new EScooter();
            instance.setMotor(value);
            double result = instance.getMotor();
            int expResult = value;
            assertEquals(expResult, result, 0);
        } catch (IllegalArgumentException e) {
            fail("expected IllegalArgumentException");
        }
    }

    /**
     * Test of updateEScooterData method, of class EScooter.
     */
    @Test
    public void testUpdateEScooterData() {
        EScooter scooterTest2 = new EScooter(123, 20, State.ACTIVE, 10, 8, 508, 75, 6);
        scooterTest2.updateScooterData(100, 8.0, 9.0, State.INACTIVE, 5, 5);
        assertEquals(scooterTest2.getActualBat(), 8);
        assertEquals(scooterTest2.getAeroCoef(), 8.0,0.0);
        assertEquals(scooterTest2.getid(), 123);
        assertEquals(scooterTest2.getFrontalArea(), 9.0,0.0);
        assertEquals(scooterTest2.getMaxBat(), 5, 0.0);
        assertEquals(scooterTest2.getMotor(), 5);
        assertEquals(scooterTest2.getState(), State.INACTIVE);
        assertEquals(scooterTest2.getWeight(), 100);
        assertNotEquals(scooterTest.toString(), scooterTest2.toString());
    }

    @Test
    public void testToString() {
        EScooter scooterTest2 = new EScooter(20, 20, State.ACTIVE, 2, 2, 50, 75, 5);
        assertEquals(scooterTest.toString(), scooterTest2.toString());
    }

}