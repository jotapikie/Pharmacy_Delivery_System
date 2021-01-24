/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;

import java.sql.SQLException;
import java.util.List;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;
import lapr.project.utils.route.RouteAlgorithms;

/**
 *
 * @author Diogo
 */
public class AirGraph extends MainGraph{
        
 



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



        this.graph = new Graph<GeographicalPoint, DronePath>(true);
        MainGraph main = new MainGraph();
        
        
        for (GeographicalPoint vertex : main.getVertexes()) {
            this.graph.insertVertex(vertex);

        }
        for (Pathway mainEdge : main.getEdges()) {
            DronePath energyEdge = new DronePath(totalWeight, mainEdge.getOriginPoint(), mainEdge.getDestinationPoint(), mainEdge.getStreetType(), mainEdge.getDistance(),mainEdge.getWind(), mainEdge.getStreet());
            graph.insertEdge(energyEdge.getOriginPoint(), energyEdge.getDestinationPoint(), energyEdge, energyEdge.getCost());
            
        }
        
  
        

        
        
    }
    
 

    /**
     * Calculates the k shortest routes from origin to destination parks,
     * considering energy efficiency.
     *
     * @param origin origin park.
     * @param destination destination park.
     * @param k number of routes to calculate.
     * @return k shortest routes.
     */
    public List<Route> kBestPaths(GeographicalPoint origin, GeographicalPoint destination, int k) {
        if (origin == null || destination == null || origin.equals(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, origin, destination, k);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid graph vertexes!");
        }
    }
    
    
    
    public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, toVisit, origin, destination, k, Integer.MAX_VALUE);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There is no way to reach at least on of the geographical points.");
        }
    }
    
    public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k, int maxBattery) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(this, toVisit, origin, destination, k, maxBattery);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There is no way to reach at least on of the geographical points.");
        }
    }
    

    public Graph<GeographicalPoint, DronePath> getRouteGraph() {
        return this.graph;
    }
}
