package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AddressTest {

    private static Address address1;
    private static Address address2;

    @BeforeAll
    public static void setUpClass() {
        address1 = new Address("Reta do Pereiro 710", new GeographicalPoint (40.738312, -7.765318, 2.3), "porto", 114, "4250-527");
        address2 = new Address("Reta do Pereiro 800", new GeographicalPoint (40.738312, -7.765318, 5.6) , "porto", 114, "4250-527");
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
        String expResult = "Reta do Pereiro 710", result = address1.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Address. Success
     */
    @Test
    void setAddress_Success() {
        String expResult = "Reta do Pereiro 800";
        address2.setStreet(expResult);
        String result = address2.getStreet();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLatitude method, of class Address. Fail
     */
    @Test
    void setAddress_Fail() {
        try {
            address1.setStreet("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setLatitude method, of class Address. Fail
     */
    @Test
    void setAddress_Fail1() {
        try {
            address1.setStreet(null);
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
            address1.setPortNumber(-10);
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
    
    @Test
    void setGeoPoint(){
        boolean flag = false;
        try{
            address1.setGeoPoint(null);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
    }

    /**
     * Test of setCity method, of class Address. Fail
     */
    @Test
    void setCity_Fail() {
        try {
            address1.setCity("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setCity method, of class Address. Fail
     */
    @Test
    void setCity_Fail1() {
        try {
            address1.setCity(null);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setCity method, of class Address. Sucess
     */
    @Test
    void setCity() {
        String expRes="Porto";
        address1.setCity(expRes);
        String res= address1.getCity();
        assertEquals(expRes,res);
    }

}
