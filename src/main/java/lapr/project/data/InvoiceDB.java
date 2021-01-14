/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Product;

/**
 *
 * @author ivoal
 */
public class InvoiceDB extends DataHandler{


    public Invoice newInvoice(Client cli, Address add, HashMap<Product, Integer> items, double totalPrice, double priceToPay, int creditsSpent, int creditsWon, int nif) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void saveInvoice(Invoice invoice, int orderId) throws SQLException {
         try (CallableStatement callStmt = getConnection().prepareCall("{ call procSaveInvoice(?,?,?,?,?,?,?) }")) {
                callStmt.setTimestamp(1, invoice.getDate());
                callStmt.setDouble(2, invoice.getTotalPrice());
                callStmt.setDouble(3, invoice.getPricePaid());
                callStmt.setDouble(4, invoice.getCreditsSpent());
                callStmt.setDouble(5, invoice.getCreditsWon());
                callStmt.setInt(6, invoice.getNif());
                callStmt.setInt(7, orderId);



            callStmt.execute();
        }
    }

}
