/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.util.HashSet;

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
public class PharmacyTest {
    
    private static Pharmacy pharmacyTest;
    private static Pharmacy pharmacyTest2;
    private static Administrator admin=new Administrator("helder","helder@gmail.com","abc");
    private static HashSet<Park> parks= new HashSet<Park>();
    private static Address address = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "porto", 114, "4250-527");
    
    public PharmacyTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        pharmacyTest =new Pharmacy(1,912345678,"Farmacia Central",admin,address,new HashSet<Park>());
        pharmacyTest2 =new Pharmacy(2,912345678,"Farmacia ",admin,address,new HashSet<Park>());
                
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Phamarcy.
     */
    @Test
    public void testGetId() {
        int expResult = 1;
        int result = pharmacyTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Phamarcy.
     */
    @Test
    public void testSetId() {
        int expResult = 2;
        pharmacyTest.setId(expResult);
        int result = pharmacyTest.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhoneNumber method, of class Phamarcy.
     */
    @Test
    public void testGetPhoneNumber() {
        int expResult = 912345678;
        int result = pharmacyTest.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhoneNumber method, of class Phamarcy.
     */
    @Test
    public void testSetPhoneNumber() {
        int expResult = 987654321;
        pharmacyTest.setPhoneNumber(expResult);
        int result= pharmacyTest.getPhoneNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Phamarcy.
     */
    @Test
    public void testGetName() {
        String expResult = "Farmacia Central";
        String result = pharmacyTest.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAdministrator method, of class Phamarcy.
     */
    @Test
    public void testGetAdministrator() {
        Administrator expResult = admin;
        Administrator result = pharmacyTest.getAdministrator();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAdministrator method, of class Phamarcy.
     */
    @Test
    public void testSetAdministrator() {
        Administrator administrator = new Administrator("helder","helder@gmail.com","abc");
        pharmacyTest.setAdministrator(administrator);
        Administrator result= pharmacyTest.getAdministrator();
        assertEquals(administrator,result);
        
    }

    /**
     * Test of getAddress method, of class Phamarcy.
     */
    @Test
    public void testGetAddress() {;
        Address result = pharmacyTest.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of setAddress method, of class Phamarcy.
     */
    @Test
    public void testSetAddress() {
        Address address = new Address("Rua da torre", 40.738312, -7.765318, "porto", 114, "4250-527");
        pharmacyTest.setAddress(address);
        Address result= pharmacyTest.getAddress();
        assertEquals(address, result);
    }

    /**
     * Test of getParks method, of class Phamarcy.
     */
    @Test
    public void testGetParks() {
        HashSet<Park> expResult = new HashSet<Park>();
        HashSet<Park> result = pharmacyTest.getParks();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setName method, of class Phamarcy.
     */
    @Test
    public void testSetName() {
        String expResult = "Farmacia XPTO";
        pharmacyTest.setName(expResult);
        String result= pharmacyTest.getName();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class Phamarcy.
     */
    @Test
    public void testEquals() {
        boolean expResult = false;
        boolean result = pharmacyTest.equals(pharmacyTest2);
        assertEquals(expResult, result);
    }


}
