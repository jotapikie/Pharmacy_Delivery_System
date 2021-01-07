/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.CreditCard;
import oracle.jdbc.OracleTypes;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Diogo
 */
public class ClientDB extends DataHandler {

    /**
     * Creates a new client with attributes passed by parameter so it can be
     * then saved in our database.
     *
     * @param name              - client's name
     * @param password          - client's password
     * @param email             - client's email
     * @param nif               - client's nif
     * @param points            - client's points
     * @param expDate           - credit card's expiracy date
     * @param visa              - credit card's visa card number
     * @param ccv               - credit card's ccv card number
     * @param address           - client's address
     * @param longitude         - client's longitude
     * @param latitude          - client's latitude
     * @return client's instance
     */
    public Client newClient(String username,String name, String password, String email, int nif, int points, Date expDate, int visa, int ccv, String address, double latitude, double longitude) {
        return new Client(username,name,password,email,nif,points,new CreditCard(visa, expDate, ccv), new Address(address,latitude,longitude));
    }

    /**
     * Saves the client passed by parameter in our database.
     *
     * @param c - instance of client that will be saved.
     * @return true if we're able to add the client, false if we're not
     * @throws java.sql.SQLException
     */
    public boolean saveClient(Client c) throws SQLException {
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterClient(?,?,?,?,?,?,?,?) }")) {

            callStmt.setString(1, c.getUsername());
            callStmt.setString(2, c.getName());
            callStmt.setString(3, c.getPassword());
            callStmt.setString(4, c.getEmail());
            callStmt.setInt(5, c.getNif());
            callStmt.setInt(6, c.getPoints());
            callStmt.setString(7, c.getCard().getExpDate().toString());
            callStmt.setInt(8,c.getCard().getVisaNumber());
            callStmt.setInt(9, c.getCard().getCcv());
            callStmt.setString(10, c.getAddress().getAddress());
            callStmt.setDouble(11, c.getAddress().getLatitude());
            callStmt.setDouble(12,c.getAddress().getLongitude());


            callStmt.execute();
        }
        return true;
    }


    /**
     * Method to get the points of the client which's username is passed by parameter.
     *
     * @param username - username of the client whom's points are being returned.
     * @return client's points.
     */
    public double getClientPoints(String username) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetClientPoints(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setString(2, username);
            callStmt.execute();

            return callStmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check table Clients.");
    }

    /**
     * Method to get the debt of the client which's username is passed by parameter.
     *
     * @param username - username of the client whom's debt are being returned.
     * @return client's debt.
     */
    public double getClientDebt(String username) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetClientDebt(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.NUMERIC);
            callStmt.setString(2, username);
            callStmt.execute();

            return callStmt.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check table Clients.");
    }

    public int saveClients(List<Client> clientsList) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procRegisterClient(?,?,?,?,?,?,?,?) }")) {
            for (Client c : clientsList) {
                callStmt.setString(1, c.getUsername());
                callStmt.setString(2, c.getName());
                callStmt.setString(3, c.getPassword());
                callStmt.setString(4, c.getEmail());
                callStmt.setInt(5, c.getNif());
                callStmt.setInt(6, c.getPoints());
                callStmt.setString(7, c.getCard().getExpDate().toString());
                callStmt.setInt(8,c.getCard().getVisaNumber());
                callStmt.setInt(9, c.getCard().getCcv());
                callStmt.setString(10, c.getAddress().getAddress());
                callStmt.setDouble(11, c.getAddress().getLatitude());
                callStmt.setDouble(12,c.getAddress().getLongitude());

                callStmt.addBatch();
            }

            con.setAutoCommit(false);

            try {
                rows = callStmt.executeBatch();
                con.commit();
            } catch (BatchUpdateException e) {
                con.rollback();
                throw new SQLException(e.getNextException());
            } finally {
                con.setAutoCommit(true);
            }

            return rows.length;
        }

    }
    public Client getClient(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
