package lapr.project.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private static User user1;
    private static User user2;
    private static User user3;

    public UserTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        user1 = new User("username_test","email_test", "password_test");
        user2 = new User("username_test2","email_test2", "password_test2");
        user3 = new User("username_test","email_test3", "password_test3");
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
     * Test of getUsername method, of class User. Success
     */
    @Test
    void getName_Success() {
        String expResult = "username_test", result = user1.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User. Success.
     */
    @Test
    void setName_Success() {
        String expResult = "new_username";
        user2.setName(expResult);
        String result = user2.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class User. Null.
     */
    @Test
    void setUsername_Null() {
        try {
            user1.setName(null);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setUsername method, of class User. Empty.
     */
    @Test
    void setUsername_Empty() {
        try {
            user1.setName("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of getPassword method, of class User. Success
     */
    @Test
    void getPassword_Success() {
        String expResult = "password_test", result = user1.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User. Success.
     */
    @Test
    void setPassword_Success() {
        String expResult = "new_password";
        user2.setPassword(expResult);
        String result = user2.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class User. Null.
     */
    @Test
    void setPassword_Null() {
        try {
            user1.setPassword(null);
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of setPassword method, of class User. Empty.
     */
    @Test
    void setPassword_Empty() {
        try {
            user1.setPassword("");
            fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException ignored) {
        }
    }

    /**
     * Test of equals and hashCode methods, of class User. Successful.
     */
    @Test
    void testEqualsAndHash_Successful() {
        assertTrue(user1.equals(user3) && user3.equals(user1));
        assertEquals(user1.hashCode(), user3.hashCode());
    }

    /**
     * Test of equals and hashCode methods, of class User. Unsuccessful.
     */
    @Test
    void testEqualsAndHash_Unsuccessful() {
        assertFalse(user1.equals(user2) && user2.equals(user1));
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    /**
     * Test of equals method, of class User. Null.
     */
    @Test
    void testEquals_Null() {
        assertFalse(user1.equals(null));
    }

    /**
     * Test of equals method, of class User. Same object.
     */
    @Test
    void testEquals_SameObject() {
        assertEquals(user2, user2);
    }

    /**
     * Test of equals method, of class User. Different class.
     */
    @Test
    void testEquals_DifferentClass() {
        double diffClass = 5.12;
        assertNotEquals(diffClass, user1);
    }


}