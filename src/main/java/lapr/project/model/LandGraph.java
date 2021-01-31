/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;
import lapr.project.utils.route.RouteAlgorithms;


/**
 *
 * @author Diogo
 */
public class LandGraph extends MainGraph implements Cloneable{
    

    
    /**
     * Graph of locations connected by paths, considering energy efficiency.
     */
    private final Graph<GeographicalPoint, ScooterPath> graph;


    private double totalWeight;


    /**
     * Constructs an instance of the energy graph.
     *
     * @param totalWeight total weight of the vehicle and the person.
     * @param vehicleAerodynamicCoef aerodynamic coefficient of the vehicle.
     * @throws java.sql.SQLException
     */
    public LandGraph(double totalWeight) throws SQLException {
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Invalid energy information!");
        }
        this.totalWeight = totalWeight;

        this.graph = new Graph<>(true);

        
        
        for (GeographicalPoint vertex : super.getVertexes()) {
            this.graph.insertVertex(vertex);

        }
        for (Pathway mainEdge : pathDB.getPaths()) {
            if(mainEdge.getCategory().equals(VehicleCategory.SCOOTER)){
            ScooterPath energyEdge = new ScooterPath(mainEdge.getOriginPoint(), mainEdge.getDestinationPoint(), mainEdge.getDistance(),
                    mainEdge.getStreetType(), mainEdge.getWind(), totalWeight, mainEdge.getStreet());
            graph.insertEdge(energyEdge.getOriginPoint(), energyEdge.getDestinationPoint(), energyEdge, energyEdge.getCost());
            }
            
        }
        
        super.setToUse(this);
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    @Override
    public LandGraph clone() throws CloneNotSupportedException {
        try {
            return new LandGraph(this.totalWeight);
        } catch (SQLException ex) {
            Logger.getLogger(LandGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
  

    
    @Override
    public Graph<GeographicalPoint, ScooterPath> getRouteGraph() {
        return this.graph;
    }

  
    

 








}
