/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import lapr.project.model.EScooter;
import lapr.project.model.ParkSlot;
import lapr.project.model.State;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class ParkSlotDB extends DataHandler{

    public HashSet<ParkSlot> getParkSlotsByPark(int parkId) throws SQLException {
        HashSet<ParkSlot> parks = new HashSet<>();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetSlotsByPark(?) }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.setInt(2, parkId);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                
                boolean ableToCharge = false;
                ParkSlot ps;
                if(rs.getInt(2) == 1){
                       ableToCharge = true;
                }
                int vehicle = rs.getInt(3);
                if(rs.wasNull()){
                    ps = new ParkSlot(rs.getInt(1), ableToCharge);
                }else{
                    ps = new ParkSlot(parkId, ableToCharge, new EScooter(vehicle, rs.getDouble(4), State.ACTIVE, rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getDouble(9), rs.getDouble(10)));
                }
            }
        }
        return parks;
    }
    
}
