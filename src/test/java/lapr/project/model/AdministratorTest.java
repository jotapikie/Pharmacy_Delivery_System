/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Helder
 */
public class AdministratorTest {
    
    private Administrator test;
    
    public AdministratorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        test= new Administrator("Gustavo","gts@gmail.com","gustavo123");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Administrator.
     */
    @Test
    public void testToString() {
        String expResult = "Name: Gustavo - Email: gts@gmail.com - Password: gustavo123";
        String result = test.toString();
        assertEquals(expResult, result);
    }
    
}
