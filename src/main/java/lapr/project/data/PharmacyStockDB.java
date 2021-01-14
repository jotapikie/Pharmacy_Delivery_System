/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lapr.project.model.Product;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class PharmacyStockDB extends DataHandler{


    public void updateStock(int idPharmacy, int id, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getQuantity(int idPharmacy, int idProduct) {
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetQuantity(?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, idPharmacy);
            callStmt.setInt(3, idProduct);
            callStmt.execute();

            return callStmt.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check table Pharmacy_Product");
    }

    public boolean hasProduct(Product p, int quantity, int idPharmacy){
       try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcPharmacyHasProduct (?,?,?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.INTEGER);
            callStmt.setInt(2, p.getId());
            callStmt.setInt(3, quantity);
            callStmt.setInt(4, idPharmacy);


            return callStmt.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NullPointerException("No info in the database. Check table Pharmacy_Product");
    }

    public boolean hasProducts(HashMap<Product, Integer> missingProducts, int idPharmacy) {
           for(Product p : missingProducts.keySet()){
               if(!hasProduct(p, missingProducts.get(p), idPharmacy)){
                   return false;
               }
           }
           return true;
    }
    
    public void updateStock(int pharmacyId, Map<Product,Integer> products) throws SQLException{
        int oldQuantity = 0;
        int newQuantity = 0;
        for(Product p : products.keySet()){
            oldQuantity = getQuantity(pharmacyId,p.getId());
            newQuantity = oldQuantity - products.get(p);
            setProductQuantity(pharmacyId,p.getId(), newQuantity);
        }
    }

    private void setProductQuantity(int pharmacyId, int productId, int newQuantity) throws SQLException {
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procPharmacyProductQuantity(?,?,?) }")) {
              callStmt.setInt(1, pharmacyId);
              callStmt.setInt(2, productId);
              callStmt.setInt(3, newQuantity);
              callStmt.execute();
        }
    }
    
}
