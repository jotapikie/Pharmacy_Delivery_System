package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ClientTest {

    private static Client client1;
    private static Client client2;
    private static Client client3;
    private final static CreditCard creditCard= new CreditCard(1231231231231231L, "02/22", 554);
    private final static Address address= new Address("Reta do Pereiro 710", new GeographicalPoint(40.738312, -7.765318, 67), "porto", 114,"4250-527");
    private final static ShoppingCart cart= new ShoppingCart();
    
    private ClientTest() {
        Client temp = new Client("name1", "password1", "email1@email.com", 123456789,912341999, creditCard, address);
        assertEquals(912341999,temp.getPhoneNumber());
    }

    @BeforeAll
    public static void setUpClass() {
        client1 = new Client("name1", "password1", "email1@email.com", 123456789,912341999, creditCard, address);
        client2 = new Client("name2", "password2", "email2@email.com", 987654321,914528456, creditCard, address);
        client3 = new Client("name3","1234","blabla@gmail.com",cart);
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
     * Test of getUsername method, of class Client. Success
     */
    @Test
    void getName_Success() {
        String expResult = "name1", result = client1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Client. Success
     */
    @Test
    void setName_Success() {
        String expResult = "username2";
        client2.setName(expResult);
        String result = client2.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client. Null.
     */
    @Test
    void setName_Null() {
        try {
            client1.setName(null);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setName method, of class Client. Empty.
     */
    @Test
    void setName_Empty() {
        try {
            client1.setName("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }
    
    @Test
    public void testGetPhoneNumber(){
        client1.setPhoneNumber(912541222);
        assertEquals(912541222, client1.getPhoneNumber());
        
    }
    
    @Test
    public void testSetPhoneNumber(){
        client1.setPhoneNumber(111111111);
        boolean flag = false;
        try{
            client1.setPhoneNumber(9232);
        }catch(IllegalArgumentException e){
         flag = true;
        }
        assertTrue(flag);
        assertEquals(111111111, client1.getPhoneNumber());
    }





    /**
     * Test of getPassword, of class Client. Success.
     */
    @Test
    void getPassword_Success() {
        String expResult = "password1", result = client1.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword, of class Client. Success.
     */
    @Test
    void setPassword_Success() {
        String expResult = "password3";
        client2.setPassword(expResult);
        String result = client2.getPassword();
        assertEquals(expResult, result);
    }


    /**
     * Test of getEmail method, of class Client. Success.
     */
    @Test
    void getEmail_Success() {
        String expResult = "email1@email.com", result = client1.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Client. Success.
     */
    @Test
    void setEmail_Success() {
        String expResult = "newemail@email.com";
        client2.setEmail(expResult);
        String result = client2.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEmail method, of class Client. Null.
     */
    @Test
    void setEmail_Null() {
        try {
            client1.setEmail(null);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setEmail method, of class Client. Empty.
     */
    @Test
    void setEmail_Empty() {
        try {
            client1.setEmail("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setEmail method, of class Client. Invalid.
     */
    @Test
    void setEmail_Invalid() {
        try {
            client1.setEmail("invalid");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getNif method, of class Client. Success.
     */
    @Test
    void getNif_Success() {
        int expResult = 123456789, result = client1.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Client. Success.
     */
    @Test
    void setNif_Success() {
        int expResult = 123789456;
        client2.setNif(expResult);
        int result = client2.getNif();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNif method, of class Client. Invalid.
     */
    @Test
    void setNif_Invalid() {
        client1.setNif(111111111);
        try {
            client1.setNif(123456);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
        assertEquals(111111111, client1.getNif());
    }

    /**
     * Test of setNif method, of class Client. Invalid.
     */
    @Test
    void setNif_Invalid2() {
        try {
            client1.setNif(1234567891);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getPoints method, of class Client. Success.
     */
    @Test
    void getPontos_Success() {
        int expResult = 0,
        result = client1.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPoints method, of class Client. Success.
     */
    @Test
    void setPontos_Success() {
        int expResult = 35;
        client2.setPoints(expResult);
        int result = client2.getPoints();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPoints method, of class Client. Invalid.
     */
    @Test
    void setPontos_Invalid() {
        try {
            client1.setPoints(-5);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getCard method, of class Client. Success.
     */
    @Test
    void getCard_Success() {
        CreditCard expResult = creditCard;
        CreditCard result = client1.getCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCard method, of class Client. Success.
     */
    @Test
    void setCard_Success() {
        CreditCard expResult = new CreditCard(1231231231231231L, "02/22", 554);
        client2.setCard(expResult);
        CreditCard result = client2.getCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Client. Success.
     */
    @Test
    void getAddress_Success() {
        Address expResult = address;
        Address result = client1.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Client. Success.
     */
    @Test
    void setAddress_Success() {
        Address expResult = new Address("Reta do Pereiro 710", new GeographicalPoint(40.738312, -7.765318, 67), "porto", 114,"4250-527");
        client2.setAddress(expResult);
        Address result = client2.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCart method, of class Client. Success.
     */
    @Test
    void getCart_Success() {
        ShoppingCart expResult = cart;
        ShoppingCart result=  client3.getCart();
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testEquals() {
        Client c1 = new Client("Paulo", "123", "joao@gmail.com", cart);c1.setNif(123456789);
        Client c2 = new Client("Tiago", "123", "tiago@gmail.com", cart);c2.setNif(123456777);
        
        assertTrue(c1.equals(c1));
        
        assertFalse(c1.equals(creditCard));
        
        assertFalse(c1.equals(c2));
        
        c2.setNif(123456789);
        assertTrue(c1.equals(c2));
    }

    @Test
    public void testHashCode() {
        client1.setNif(123456789);
        assertEquals(123456789, client1.hashCode());
        
        client1.setNif(111111111);
        assertEquals(111111111, client1.hashCode());
    }
    
    
    


}
