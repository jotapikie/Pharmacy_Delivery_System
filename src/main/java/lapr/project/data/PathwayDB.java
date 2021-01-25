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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.Pathway;
import lapr.project.model.StreetType;
import lapr.project.model.VehicleCategory;
import lapr.project.model.Wind;
import lapr.project.utils.Utils;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diogo
 */
public class PathwayDB extends DataHandler{
    

    public List<Pathway> getPaths() throws SQLException {
        List<Pathway> listPaths = new ArrayList<>();
        getConnection();
        try (CallableStatement callStmt = getConnection().prepareCall("{ ? = call funcGetPaths() }")) {
            callStmt.registerOutParameter(1, OracleTypes.CURSOR);
            callStmt.execute();
            ResultSet rs = (ResultSet) callStmt.getObject(1);
            while (rs.next()) {
                Pathway p = new Pathway(new GeographicalPoint(rs.getDouble(1), rs.getDouble(2), rs.getDouble(3), rs.getString(4)), new GeographicalPoint(rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8)),StreetType.fromString(rs.getString(10)),  rs.getDouble(9), new Wind(rs.getDouble(11),rs.getDouble(12),rs.getDouble(13)), rs.getString(15), VehicleCategory.fromString(rs.getString(14)));
                listPaths.add(p);
            }
        }
        return listPaths;
    }



    public Pathway newPath(GeographicalPoint or, GeographicalPoint dest, String type, double vx, double vy, double vz, String street, String category) {
        Wind w = new Wind(vx, vy, vz);
        double distance = Utils.distance(or.getLatitude(), dest.getLatitude(), or.getLongitude(), dest.getLongitude(), or.getElevation(), dest.getElevation());
        return new Pathway(or, dest, StreetType.fromString(type), distance, w, street, VehicleCategory.fromString(category));
    }

    public int savePaths(Set<Pathway> paths) throws SQLException {
       Connection c = getConnection();
        int rows[];
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddPath(?,?,?,?,?,?,?,?,?,?,?) }")) {
            for (Pathway path : paths) {
                callStmt.setDouble(1, path.getOriginPoint().getLongitude());
                callStmt.setDouble(2, path.getOriginPoint().getLatitude());
                callStmt.setDouble(3, path.getDestinationPoint().getLongitude());
                callStmt.setDouble(4, path.getDestinationPoint().getLatitude());
                callStmt.setDouble(5, path.getDistance());
                callStmt.setString(6, path.getStreet());
                callStmt.setString(7, path.getStreetType()==null?null:path.getStreetType().getName());
                callStmt.setDouble(8, path.getWind().vx);
                callStmt.setDouble(9, path.getWind().vx);
                callStmt.setDouble(10, path.getWind().vx);
                callStmt.setString(11, path.getCategory().getName());
                callStmt.addBatch();
            }
            c.setAutoCommit(false);
            try {
                rows = callStmt.executeBatch();
                c.commit();
            } catch (BatchUpdateException e) {
                c.rollback();
                throw new SQLException(e.getNextException());
            } finally {
                c.setAutoCommit(true);
            }
            return rows.length;
        }
    }


    
    
    
}
