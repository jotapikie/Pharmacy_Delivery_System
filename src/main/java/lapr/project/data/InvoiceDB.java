/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.util.HashMap;
import lapr.project.model.Address;
import lapr.project.model.Client;
import lapr.project.model.Invoice;
import lapr.project.model.Product;

/**
 *
 * @author ivoal
 */
public class InvoiceDB {

    public Invoice newInvoice(Client cli, Address add, HashMap<Product, Integer> items, double totalPrice, double priceToPay, int creditsSpent, int creditsWon, int nif, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void saveInvoice(Invoice inv, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
