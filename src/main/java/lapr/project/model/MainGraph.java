/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.model;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lapr.project.data.GeographicalPointDB;
import lapr.project.data.PathwayDB;
import lapr.project.utils.graph.Edge;
import lapr.project.utils.graph.Graph;
import lapr.project.utils.route.Route;
import lapr.project.utils.route.RouteAlgorithms;

/**
 *
 * @author Diogo
 */
public class MainGraph {
    /**
     * Static instance of the main graph.
     */
    private static MainGraph instance = null;

    /**
     * Static instance of the main graph.
     */
    private static GeographicalPointDB locationDB = null;

    /**
     * Static instance of the main graph.
     */
    protected static PathwayDB pathDB = null;

    /**
     * Static method to get or create an instance of the main graph.
     *
     * @throws java.sql.SQLException
     */
    public static MainGraph getInstance() throws SQLException {
        if (instance == null) {
            instance = new MainGraph();
        }
        return instance;
    }

    /**
     * Static method to reset the main graph.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Static method to setup the data accesses of the main graph (implemented
     * to allow for unit testing).
     */
    public static void setup(GeographicalPointDB newLocationDB, PathwayDB newPathDB) {
        locationDB = (newLocationDB != null) ? newLocationDB : new GeographicalPointDB();
        pathDB = (newPathDB != null) ? newPathDB : new PathwayDB();
    }

    /**
     * Static method to erase the setup of data accesses of the main graph
     * (implemented to allow for unit testing).
     */
    public static void eraseSetup() {
        locationDB = null;
        pathDB = null;
    }

    /**
     * Main graph of locations connected by paths.
     */
    private final Graph<GeographicalPoint,Pathway> mainGraph;
    
    private MainGraph toUse = this;
    


    /**
     * Constructs an instance of the main graph.
     */
    public MainGraph() throws SQLException {
        mainGraph = new Graph<GeographicalPoint, Pathway>(true);
        setup(locationDB, pathDB);
        List<GeographicalPoint> vertexes = locationDB.getGeographicalPoints();
        List<Pathway> edges = pathDB.getPaths();
        
        for (GeographicalPoint vertex : vertexes) {
            if (!mainGraph.insertVertex(vertex)) {
                throw new IllegalArgumentException("Invalid graph Vertex!");
            }
        }
        for (Pathway edge : edges) {
            if(edge.getCategory().equals(VehicleCategory.SCOOTER)){
                if (!mainGraph.insertEdge(edge.getOriginPoint(), edge.getDestinationPoint(), edge, edge.getCost())) {
                    throw new IllegalArgumentException("Invalid graph Edge!");
                }
            }
        }
    }

    /**
     * Returns an iterable of the vertexes of the main graph.
     *
     * @return iterable of the vertexes of the main graph.
     */
    public Iterable<GeographicalPoint> getVertexes() {
        return mainGraph.vertices();
    }

    /**
     * Returns an iterable of the edges of the main graph.
     *
     * @return iterable of the edges of the main graph.
     */
    public Iterable<Pathway> getEdges() {
        List<Pathway> edges = new ArrayList<>();
        for (Edge<GeographicalPoint, Pathway> e : mainGraph.edges()) {
            edges.add(e.getElement());
        }
        return edges;
    }

    public void setToUse(MainGraph toUse) {
        this.toUse = toUse;
    }

    public MainGraph getToUse() {
        return toUse;
    }
    
    

    /**
     * Calculates the k shortest routes from origin to destination parks.
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
            return RouteAlgorithms.kBestRoutes(toUse, origin,  destination, k);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid graph vertexes!");
        }
    }

    /**
     * Calculates the k shortest routes from origin to destination parks,
     * visiting a given set of locations.
     *
     * @param toVisit set of locations to visit.
     * @param origin origin park.
     * @param destination destination park.
     * @param k number of routes to calculate.
     * @return k shortest routes.
     */
    public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(toUse, toVisit, origin,destination, k, Integer.MAX_VALUE);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid graph vertexes!");
        }
    }
    
   public List<Route> kBestPaths(List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k, int maxBattery) {
        if (origin == null || destination == null || toVisit == null || toVisit.contains(origin) || toVisit.contains(destination) || k <= 0) {
            throw new IllegalArgumentException("Invalid algorithm arguments!");
        }
        try {
            return RouteAlgorithms.kBestRoutes(toUse, toVisit, origin, destination, k, maxBattery);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("There is no way to reach at least on of the geographical points.");
        }
    }
    
    

    /**
     * Returns the graph to use for route algorithms.
     *
     * @return graph to use for route algorithms.
     */
    public Graph<GeographicalPoint,? extends Pathway> getRouteGraph() {
        MainGraph instance;
        try {
            MainGraph.resetInstance();
            instance = MainGraph.getInstance();
        } catch (SQLException e) {
            return null;
        }
        return instance.mainGraph;
    }

}
