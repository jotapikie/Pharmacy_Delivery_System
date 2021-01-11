package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddressTest {

    private static Address address1;
    private static Address address2;

    @BeforeAll
    public static void setUpClass() {
        address1 = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "porto", 114, "4250-527");
        address2 = new Address("Reta do Pereiro 800", 40.738312, -7.765318, "porto", 114, "4250-527");
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
     * Test of getAddress method, of class Address. Success
     */
    @Test
    void getAddress_Success() {
        String expResult = "Reta do Pereiro 710", result = address1.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Address. Success
     */
    @Test
    void setAddress_Success() {
        String expResult = "Reta do Pereiro 800";
        address2.setAddress(expResult);
        String result = address2.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Address. Success
     */
    @Test
    void getLatitude_Success() {
        double expResult = 40.738312, result = address1.getLatitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Address. Success
     */
    @Test
    void setLatitude_Success() {
        double expResult = 40.256192;
        address2.setLatitude(expResult);
        double result = address2.getLatitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLatitude method, of class Address. Fail
     */
    @Test
    void setLatitude_Fail() {
        try {
            address1.setLatitude(-95);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getLongitude method, of class Address. Success
     */
    @Test
    void getLongitude_Success() {
        double expResult = -7.765318, result = address1.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLongitude method, of class Address. Success
     */
    @Test
    void setLongitude_Success() {
        double expResult = -7.765365;
        address2.setLongitude(expResult);
        double result = address2.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLongitude method, of class Address. Fail
     */
    @Test
    void setLongitude_Fail() {
        try {
            address1.setLongitude(190);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setPortNumber
     */
    @Test
    void setPortNumber_Sucess(){
        int expResult= 10;
        address1.setPortNumber(expResult);
        int result= address1.getPortNumber();
        assertEquals(expResult,result);
    }

    /**
     * Test of setPortNumber method, of class Address. Fail
     */
    @Test
    void setPortNumber_Fail() {
        try {
            address1.setPortNumber(0);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }
    /**
     * Test of setPortNumber method, of class Address. Fail
     */
    @Test
    void setPortNumber_Fail1() {
        try {
            address1.setPortNumber(-1);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setZipCode method, of class Address. Fail
     */
    @Test
    void setZipCode_Fail() {
        try {
            address1.setZipCode("4450.MAT");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setZipCode method, of class Address. Sucess
     */
    @Test
    void setZipCode() {
        String expRes="4250-527";
        address1.setZipCode(expRes);
        String res= address1.getZipCode();
        assertEquals(expRes,res);
    }
}
