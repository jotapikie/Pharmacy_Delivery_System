/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.data;

import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import lapr.project.model.EScooter;
import lapr.project.model.ParkSlot;
import lapr.project.model.State;
import lapr.project.utils.Constants;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class ParkSlotDB extends DataHandler{
    
    public ParkSlot newParkSlot(boolean ableToCharge){
        return new ParkSlot(Constants.DEFAULT_ID, ableToCharge);
    }

    public Set<ParkSlot> getParkSlotsByPark(int parkId) throws SQLException {
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
                    parks.add(ps);
                }else{
                    ps = new ParkSlot(parkId, ableToCharge, new EScooter(vehicle,  State.ACTIVE, rs.getInt(6), rs.getInt(7)));
                    parks.add(ps);
                }
            }
        }
        return parks;
    }

    public int saveParkSlots(Set<ParkSlot> slots, int idPark) throws SQLException {
        Connection con = getConnection();
        int[] rows;
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddParkSlot(?,?) }")) {
            for (ParkSlot p : slots) {
                callStmt.setInt(1, idPark);
                if(p.isAbleToCharge()){
                    callStmt.setInt(2, 1);
                }else{
                    callStmt.setInt(2, 0);
                }
   

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
    
}
