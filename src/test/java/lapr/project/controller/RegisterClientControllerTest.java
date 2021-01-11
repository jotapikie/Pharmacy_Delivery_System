package lapr.project.controller;

import lapr.project.data.ClientDB;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RegisterClientControllerTest {

    private static RegisterClientController registerClientController;
    private static ClientDB clientDB;
    private static RegisterClientController testController;

    @BeforeAll
    public static void setUpClass() {
        clientDB = mock(ClientDB.class);
        testController = new RegisterClientController();
        registerClientController = new RegisterClientController(clientDB);
    }

    /**
     * Test of newClient method, of class RegisterClientController.
     */
    @Test
    void testNewClient() {
        String username = "username", name = "paulo", password = "password", email = "email@gmail.com";
        int nif = 236159105, points = 0;
        CreditCard creditCard = new CreditCard(1234567890123456L, new Date(1673109541000L), 554);
        Address address = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "Porto", 122, "4250-527");

        Client expResult = new Client(username, name, password, email, nif, creditCard, address);
        when(clientDB.newClient(anyString(), anyString(), anyString(), anyString(), anyInt(), anyInt(), new Date(anyLong()), anyLong(), anyInt(), anyString(), anyDouble(), anyDouble(), anyString(), anyInt(), anyString())).thenReturn(expResult);
        Client result = registerClientController.newClient(username, name, password, email, nif, points, creditCard.getExpDate(),
                creditCard.getVisaNumber(), creditCard.getCcv(), address.getAddress(), address.getLatitude(), address.getLongitude(), address.getCity(),address.getPortNumber(),address.getZipCode());
        assertEquals(expResult, result);
    }

    /**
     * Test of registerClient method, of class RegisterClientController.
     */
    @Test
    void testRegisterClient() throws SQLException {
        when(clientDB.saveClient(anyObject())).thenReturn(true);
        assertTrue(registerClientController.registerClient());
    }

    /**
     * Test of addClientToQueue method, of class RegisterClientController.
     */
    @Test
    public void testAddClientToQueue() {
        assertTrue(testController.addClientToQueue());
    }

    /**
     * Test of getClientsList method, of class RegisterClientController.
     */
    @Test
    public void testGetClientsList() {
        List<Client> clientsList = new ArrayList<>();
        assertEquals(testController.getClientsList(), clientsList);
    }

    /**
     * Test of insertClientsBatchOp method, of class RegisterClientController.
     */
    @Test
    public void testInsertClientsBatchOp() throws Exception {
        String username = "username", name = "paulo", password = "password", email = "email@gmail.com";
        int nif = 236159105, points = 0;
        CreditCard creditCard = new CreditCard(1234567890123456L, new Date(1673109541000L), 554);
        Address address = new Address("Reta do Pereiro 710", 40.738312, -7.765318, "Porto", 122, "4250-527");

        registerClientController.newClient(username, name, password, email, nif, points, creditCard.getExpDate(),
                creditCard.getVisaNumber(), creditCard.getCcv(), address.getAddress(), address.getLatitude(), address.getLongitude(), address.getCity(),address.getPortNumber(),address.getZipCode());
        registerClientController.addClientToQueue();

        int resultFullList = registerClientController.getClientsList().size();

        when(clientDB.saveClients(registerClientController.getClientsList())).thenReturn(resultFullList);

        registerClientController.insertClientsBatchOp();

        int resultEmptyList = registerClientController.getClientsList().size();

        assertEquals(0, resultEmptyList);
        assertEquals(1, resultFullList);

    }

}
