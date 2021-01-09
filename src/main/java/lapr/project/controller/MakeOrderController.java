/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*

package lapr.project.controller;

import java.util.List;

*/
/**
 *
 * @author ivoal
 *//*

public class MakeOrderController {
    
    private ShoppingCart cart;
    private Client client;
    private int nif;
    private DeliveryOrder order;
    private DeliveryOrderDB orderDB;
    private Invoice invoice;
    private InvoiceDB invoiceDB;
    private List<DeliveryOrder> orders;
    private List<Invoice> invoices;
    
    
    public ShoppingCart getCart(){
        cart=client.getShoppingCart();
        return cart;
    }
    
    
    public int getCart(){
        nif=client.getNif();
        return nif;
    }
    
    
    
    public boolean newOrder(int nif){
        this.nif=nif;
        order=orderDB.newDeliveryOrder();
        invoice=invoiceDB.newInvoice();
        setInvoice(invoice);
        return orderListAdd(order);
    }
    
    
    public boolean orderListAdd(order){
        return orders.add(order);
    }
    
    
}
*/
