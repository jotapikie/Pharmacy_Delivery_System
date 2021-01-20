package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class CreditCardTest {

    private static CreditCard creditCard1;
    private static CreditCard creditCard2;

    @BeforeAll
    public static void setUpClass() {
        creditCard1 = new CreditCard(1234567890123456L, "02/22", 554);
        creditCard2 = new CreditCard(6543210987654321L, "04/22", 684);
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
     * Test of getVisaNumber method, of class CreditCard. Success
     */
    @Test
    void getVisaNumber_Success() {
        long expResult = 1234567890123456L, result = creditCard1.getVisaNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVisaNumber method, of class CreditCard. Success
     */
    @Test
    void setVisaNumber_Success() {
        long expResult = 1234567890123456L;
        creditCard2.setVisaNumber(expResult);
        long result = creditCard2.getVisaNumber();
        assertEquals(expResult, result);
        
        boolean flag = false;
        try{
            creditCard2.setVisaNumber(3545);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(expResult, creditCard2.getVisaNumber());
    }

	/**
     * Test of setVisaNumber method, of class CreditCard. Fail
     */
    @Test
    void setVisaNumber_Fail(){
        try {
            creditCard1.setVisaNumber(15);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getExpDate method, of class CreditCard. Success
     */
    @Test
    void getExpDate_Success() {
        String expResult = "02/22", result = creditCard1.getExpDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setExpDate method, of class CreditCard. Success
     */
    @Test
    void setExpDate_Success() {
        String expResult = "02/22"; 
        creditCard2.setExpDate("02/22");
        String result = creditCard2.getExpDate();
        assertEquals(expResult, result);
        boolean flag = false;
        try{
            creditCard2.setExpDate("4433446");
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(expResult, creditCard2.getExpDate());
        
    }

    /**
     * Test of setExpDate method, of class CreditCard. Fail
     */
    @Test
    void setExpDate_Fail(){
        try {
            creditCard1.setExpDate("sdfs");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getCcv method, of class CreditCard. Success
     */
    @Test
    void getCcv_Success() {
        double expResult = 554, result = creditCard1.getCcv();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCcv method, of class CreditCard. Success
     */
    @Test
    void setCcv_Success() {
        int expResult = 625;
        creditCard2.setCcv(expResult);
        double result = creditCard2.getCcv();
        assertEquals(expResult, result);
        
       boolean flag = false;
        try{
            creditCard2.setVisaNumber(3545);
        }catch(IllegalArgumentException e){
            flag = true;
        }
        assertTrue(flag);
        assertEquals(expResult, creditCard2.getCcv());
    }

    /**
     * Test of setCcv method, of class CreditCard. Fail
     */
    @Test
    void setCcv_Fail(){
        try {
            creditCard1.setCcv(23);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }
}
