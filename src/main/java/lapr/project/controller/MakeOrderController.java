/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;


public class MakeOrderController {
    
//    private final ClientDB cdb;
//    private final AddressDB adb;
//    private final OrderDB odb;
//    private final InvoiceDB idb;
//    private final PharmacyStockDB ppdb;
//    private final String email;
//    
//    private Client cli;
//    private ShoppingCart cart;
//    private HashMap<Product, Integer> items;
//    private final Platform plat;
//    private Address add;
//    private Pharmacy pha;
//    private Order ord;
//    
//    private double totalPrice;
//    private double discPrice;
//    private double priceToPay;
//    
//    private int cred;
//    private int creditsSpent;
//    private int creditsWon;
//
//    public MakeOrderController(ClientDB cdb, AddressDB adb, OrderDB odb,InvoiceDB idb,PharmacyStockDB ppdb, String email) {
//        this.cdb = cdb;
//        this.adb = adb;
//        this.odb = odb;
//        this.idb = idb;
//        this.ppdb = ppdb;
//        this.email = email;
//        items = new HashMap<>();
//        plat = new Platform();
//        totalPrice = 0;
//        discPrice = 0;
//        priceToPay = 0;
//        cred = 0;
//        creditsSpent = 0;
//        creditsWon= 0;
//    }
//   
//    public String getCart(){
//        cli = cdb.getClient(email);
//        cart = cli.getCart();
//        items = cart.getItems();
//        totalPrice = plat.getDeliveryPrice() + cart.getPrice();
//        return cart.toString();
//    }
//    
//    public String getAddress(){
//        add = adb.getAddressByClient(email);
//        return add.toString();
//    }
//    
//    public int getDefaultNif(){
//        return cli.getNif();
//    }
//    
//    public int getCredits(){
//        cred = cli.getPoints();
//        discPrice = totalPrice - Utils.creditsToEuros(cred);
//        priceToPay = totalPrice;
//        return cred;
//    }
//    
//    public void discount(){
//        priceToPay = discPrice;
//        creditsSpent = cred;
//    }
//    
//    public double getFinalPrice(){
//        return priceToPay;
//    }
//    
//    public void makeOrder(int nif, int id) throws SQLException{
//        cli.setPoints(cli.getPoints() - creditsSpent);
//        ord = odb.newOrder(id, totalPrice, items);
//        creditsWon = Utils.creditsWon(totalPrice);
//        cli.setPoints(cli.getPoints() + creditsWon);
//        Invoice inv = idb.newInvoice(cli, add, items, totalPrice, priceToPay, creditsSpent, creditsWon, nif, id);
//        getNearestPharmacy();
//        odb.saveOrder(ord, cli, pha);
//        idb.saveInvoice(inv, ord.getId());
//        cdb.setPoints(cli.getPoints());
//        checkPharmacyStock();
//        sendEmail();
//        
//    }
//    
//    private void getNearestPharmacy(){
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    private boolean checkPharmacyStock() throws SQLException{
//        
//        HashMap<Product, Integer> missingProducts = new HashMap<>();
//        for(Product p : items.keySet()){
//            int quantity = ppdb.getQuantity(pha.getId(), p.getId());
//            int missing = items.get(p) - quantity;
//            if(missing > 0){
//                missingProducts.put(p, missing);
//            }
//        }
//        
//        if(missingProducts.isEmpty()){
//            odb.setStatus(ord.getId(), "Processed", pha.getId());
//            ppdb.updateAfterSale(pha.getId(), items);
//            return true;
//        }else{
//            // CREATE A NEW ORDER WITH ord ASSOCIATED
//            // ASSIGN IT TO THE NEAREST PHARMACY WITH STOCK
//            return false;
//            
//            
//        }
//        
//    }
//    
//    private void sendEmail(){
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
 
    
    

    
    

    

}
    