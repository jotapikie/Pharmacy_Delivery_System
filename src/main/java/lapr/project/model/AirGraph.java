/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import lapr.project.utils.graph.Graph;



/**
 *
 * @author Diogo
 */
public class AirGraph extends MainGraph{
        
 
    private final double totalWeight;


 /**
     * Graph of locations connected by paths, considering energy efficiency.
     */
    private final Graph<GeographicalPoint, DronePath> graph;
    /**
     * Constructs an instance of the energy graph.
     *
     * @param totalWeight total weight of the vehicle and the person.
     * @param vehicleAerodynamicCoef aerodynamic coefficient of the vehicle.
     * @throws java.sql.SQLException
     */
    public AirGraph(double totalWeight) throws SQLException {
        if (totalWeight <= 0) {
            throw new IllegalArgumentException("Invalid energy information!");
        }
        this.totalWeight = totalWeight;


        this.graph = new Graph<>(true);
        MainGraph main = new MainGraph();
        
        
        for (GeographicalPoint vertex : main.getVertexes()) {
            this.graph.insertVertex(vertex);

        }
        for (Pathway mainEdge : pathDB.getPaths()) {
            if(mainEdge.getCategory().equals(VehicleCategory.DRONE)){
                DronePath energyEdge = new DronePath(totalWeight, mainEdge.getOriginPoint(), mainEdge.getDestinationPoint(), mainEdge.getDistance(),mainEdge.getWind());
                graph.insertEdge(energyEdge.getOriginPoint(), energyEdge.getDestinationPoint(), energyEdge, energyEdge.getCost());
            }
        }
        super.setToUse(this);
    }
    
    public double getTotalWeight() {
        return totalWeight;
    }
    
    @Override
    public Graph<GeographicalPoint, DronePath> getRouteGraph() {
        return this.graph;
    }
}
