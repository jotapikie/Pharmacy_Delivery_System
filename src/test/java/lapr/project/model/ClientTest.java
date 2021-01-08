package lapr.project.model;

import org.junit.jupiter.api.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ClientTest {

    private static Client client1;
    private static Client client2;

    private ClientTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        client1 = new Client("username1", "name1", "password1", "email1@email.com", 123456789, 35, new CreditCard(1231231231231231L, new Date(1673109541000L), 554), new Address("Reta do Pereiro 710", 40.738312, -7.765318));
        client2 = new Client("username2", "name2", "password2", "email2@email.com", 987654321, 15, new CreditCard(1231231231231231L, new Date(1673109541000L), 554), new Address("Reta do Pereiro 710", 40.738312, -7.765318));
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
    void getUsername_Success() {
        String expResult = "username1", result = client1.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Client. Success
     */
    @Test
    void setUsername_Success() {
        String expResult = "username2";
        client2.setName(expResult);
        String result = client2.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of getHeight method, of class Client. Success.
     */
    @Test
    void getName_Success() {
        String expResult = "name1", result = client1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHeight method, of class Client. Success.
     */
    @Test
    void setName_Success() {
        String expResult = "name1";
        client2.setName(expResult);
        String result = client2.getName();
        assertEquals(expResult, result);
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
        try {
            client1.setNif(123456);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getPoints method, of class Client. Success.
     */
    @Test
    void getPontos_Success() {
        int expResult = 35, result = client1.getPoints();
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
            client1.setNif(-5);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getCard method, of class Client. Success.
     */
    @Test
    void getCard_Success() {
        CreditCard expResult = new CreditCard(1231231231231231L, new Date(1673109541000L), 554);
        CreditCard result = client1.getCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCard method, of class Client. Success.
     */
    @Test
    void setCard_Success() {
        CreditCard expResult = new CreditCard(1231231231231231L, new Date(1673109541000L), 554);
        client2.setCard(expResult);
        CreditCard result = client2.getCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAddress method, of class Client. Success.
     */
    @Test
    void getAddress_Success() {
        Address expResult = new Address("Reta do Pereiro 710", 40.738312, -7.765318);
        Address result = client1.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Client. Success.
     */
    @Test
    void setAddress_Success() {
        Address expResult = new Address("Reta do Pereiro 710", 40.738312, -7.765318);
        client2.setAddress(expResult);
        Address result = client2.getAddress();
        assertEquals(expResult, result);
    }

}
