/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;

import java.sql.SQLException;
import lapr.project.data.AddressDB;
import lapr.project.data.ClientDB;
import lapr.project.data.CreditCardDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import lapr.project.model.GeographicalPoint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Diogo
 */
public class RegisterClientControllerTest {
    
    private static RegisterClientController controller;
    private static ClientDB cdb;
    private static AddressDB adb;
    private static CreditCardDB ccdb;
    
    private static Address add;
    private static CreditCard card;
    private static Client client;
    
   
    
    @BeforeAll
    public static void setUpClass() {
        add = new Address("Street 1", new GeographicalPoint(45.2, 23.5, 0.2), "City 1", 23, "4525-454");
        card = new CreditCard(1234567891234567L, "02/22", 123);
        client = new Client("João", "123", "joao@gmail.com", 123456789, 912541623, card, add);
        RegisterClientController c1 = new RegisterClientController();
    }
    

    
    @BeforeEach
    public void setUp() throws SQLException {
        cdb = mock(ClientDB.class);
        adb = mock(AddressDB.class);
        ccdb = mock(CreditCardDB.class);
        
        controller = new RegisterClientController(cdb, adb, ccdb);
        
        when(adb.newAdress("Street 1", 45, 45, 45, "City 1", 1, "1111-111")).thenReturn(add);
        when(ccdb.newCreditCard(1234567891234567L, "02/22", 123)).thenReturn(card);
        when(cdb.newClient("João", "joao@gmail.com", "123", 123456789, 912541623, add, card)).thenReturn(client);
        when(cdb.saveClient(client)).thenReturn(true);
    }
    


    /**
     * Test of newAddress method, of class RegisterClientController.
     */
    @Test
    public void testNewAddress() {
        String res = controller.newAddress("Street 2", 45, 45, 45, "City 2", 1, "2222-222");
        assertNull(res);
        
        res = controller.newAddress("Street 1", 45, 45, 45, "City 1", 1, "1111-111");
        assertEquals(add.toString(), res);
       
    }

    /**
     * Test of newCreditCard method, of class RegisterClientController.
     */
    @Test
    public void testNewCreditCard() {
        String res = controller.newCreditCard(1234567891231111L, "08/24", 888);
        assertNull(res);
        
        res = controller.newCreditCard(1234567891234567L, "02/22", 123);
        assertEquals(card.toString(), res);
    }

    /**
     * Test of newClient method, of class RegisterClientController.
     */
    @Test
    public void testNewClient() {
        controller.newAddress("Street 1", 45, 45, 45, "City 1", 1, "1111-111");
        controller.newCreditCard(1234567891234567L, "02/22", 123);
        String res = controller.newClient("Other name","other@gamil.com", "123", 123456712, 345267834);
        assertNull(res);
        

        res = controller.newClient("João", "joao@gmail.com", "123", 123456789, 912541623);
        assertEquals(client.toString(), res);
        
        
    }

    /**
     * Test of registClient method, of class RegisterClientController.
     */
    @Test
    public void testRegistClient() throws Exception {
            assertFalse(controller.registClient());
            
        controller.newAddress("Street 1", 45, 45, 45, "City 1", 1, "1111-111");
        controller.newCreditCard(1234567891234567L, "02/22", 123);
        controller.newClient("João", "joao@gmail.com", "123", 123456789, 912541623);
        assertTrue(controller.registClient());
        
        when(cdb.saveClient(client)).thenReturn(false);
        controller.newAddress("Street 1", 45, 45, 45, "City 1", 1, "1111-111");
        controller.newCreditCard(1234567891234567L, "02/22", 123);
        controller.newClient("João", "joao@gmail.com", "123", 123456789, 912541623);
        assertFalse(controller.registClient());
        
    }
    
}
