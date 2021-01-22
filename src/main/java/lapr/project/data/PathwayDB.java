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
                Pathway p = new Pathway(new GeographicalPoint(rs.getFloat(1), rs.getFloat(2), rs.getFloat(3)), new GeographicalPoint(rs.getFloat(5), rs.getFloat(6), rs.getFloat(7)), StreetType.valueOf(rs.getString(9)),  rs.getDouble(10), rs.getDouble(11), rs.getString(12));
                listPaths.add(p);
            }
        }
        return listPaths;
    }



    public Pathway newPath(GeographicalPoint or, GeographicalPoint dest, String type, int windDirection, double windSpeed, String street) {
        double pathDirec = Utils.pathDirection(or.getLatitude(), or.getLongitude(), dest.getLatitude(), dest.getLongitude());
        double wind = Utils.windToPath(pathDirec, windDirection, windSpeed);
        double distance = Utils.distance(or.getLatitude(), dest.getLatitude(), or.getLongitude(), dest.getLongitude(), or.getElevation(), dest.getElevation());
        return new Pathway(or, dest, StreetType.fromString(type), distance, wind, street);
    }

    public int savePaths(Set<Pathway> paths) throws SQLException {
       Connection c = getConnection();
        int rows[];
        try (CallableStatement callStmt = getConnection().prepareCall("{ call procAddPath(?,?,?,?,?,?,?,?) }")) {
            for (Pathway path : paths) {
                callStmt.setDouble(1, path.getOriginPoint().getLongitude());
                callStmt.setDouble(2, path.getOriginPoint().getLatitude());
                callStmt.setDouble(3, path.getDestinationPoint().getLongitude());
                callStmt.setDouble(4, path.getDestinationPoint().getLatitude());
                callStmt.setDouble(5, path.getDistance());
                callStmt.setString(6, path.getStreet());
                callStmt.setString(7, path.getStreetType().getName());
                callStmt.setDouble(8, (double) Math.round(path.getWind() * 100) / 100);

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
