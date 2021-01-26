/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.MainGraph;
import lapr.project.model.Pathway;
import lapr.project.utils.Utils;
import lapr.project.utils.bst.BST;
import lapr.project.utils.graph.Graph;

/**
 *
 * @author Diogo
 */
public class RouteAlgorithms {



   public static List<Route> kBestRoutes(MainGraph mainGraph, GeographicalPoint origin, GeographicalPoint destination, int k){

        if (mainGraph == null || origin == null || destination == null || k <= 0) {
            throw new IllegalArgumentException("Invalid route arguments!");
        }
        
        @SuppressWarnings("unchecked")
        Graph<GeographicalPoint, Pathway> graph =  (Graph<GeographicalPoint, Pathway>) mainGraph.getRouteGraph();
        if (graph == null || !graph.isDirected()) {
            throw new IllegalArgumentException("Invalid graph!");
        }
        zeroVertexCounters(graph);
        List<Route> result = new ArrayList<>();
        BST<Route> bst = new BST<>();

        // Insert first routes into the BST, from origin to each neighbour 
        boolean flag = true;
        for (GeographicalPoint adjVertex : graph.adjVertices(origin)) {
            Pathway edge = graph.getEdge(origin, adjVertex).getElement();
            // Required to update the origin vertex in the graph itself, not the received copy
            if (flag) {
                edge.getOriginPoint().incrementCounter();
                flag = false;
            }
            bst.insert(new Route(edge));
        }

        // While there are routes in BST
        while (!bst.isEmpty()) {

            // Get the shortest route in BST
            Route route = bst.smallestElement();

            // Check if k wanted routes have been found or next route has the same cost
            if (result.size() >= k && route.getTotalEnergy() > result.get(result.size() - 1).getTotalEnergy()) {
                break;
            }

            // Remove the route from BST and increment the counter of the end vertex
            bst.remove(route);
            GeographicalPoint vertex = route.getDestination();
            vertex.incrementCounter();

            // Check if route is a wanted route
            if (vertex.equals(destination)) {
                result.add(route);
            }

            // Check if the end vertex has not been found more than k times
            if (vertex.getCounter() <= k) {

                // Concatenate each neighbour to route and insert each new route into BST
                for (GeographicalPoint adjVertex : graph.adjVertices(vertex)) {
                    Route newRoute = new Route(route);
                    newRoute.addPath(graph.getEdge(vertex, adjVertex).getElement());
                    bst.insert(newRoute);
                }
            }
        }
        return (result.isEmpty()) ? null : result;
    }
   
   
   
   
   
   
    private static <V> void  zeroVertexCounters(Graph<GeographicalPoint, V> graph) {

        for (GeographicalPoint vertex : graph.vertices()) {
            vertex.resetCounter();
        }
    }
    


    public static <V> List<Route> kBestRoutes(MainGraph graph, List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k, int maxEnergy) {
        
        if (graph == null || origin == null || destination == null || toVisit == null || k <= 0) {
            throw new IllegalArgumentException("Invalid route arguments!");
        }

        // Check if there any vertexes to visit
        if (toVisit.isEmpty()) {
            
            return RouteAlgorithms.kBestRoutes(graph, origin, destination, k);
        }
        if (toVisit.contains(origin) || toVisit.contains(destination)) {
            throw new IllegalArgumentException("Invalid intermediate vertexes!");
        }

        // Fill a map with all the possible pairs of vertexes and their k shortest routes
        Map<Pair<GeographicalPoint, GeographicalPoint>, List<Route>> map = new HashMap<>();
        fillRouteMap(graph, toVisit, origin, destination, k, map);

        // Check if only 1 permutation is possible
        if (toVisit.size() == 1) {
            return kBestOfPermutation(map, toVisit, origin, destination, k, maxEnergy);
        }

        // Permute all possible orders of vertexes
        List<List<GeographicalPoint>> permutations = Utils.permutations(toVisit);
        BST<Route> bst = new BST<>();

        // Calculate the k shortest routes of each permutation
        for (List<GeographicalPoint> permutation : permutations) {
            List<Route> obtained = kBestOfPermutation(map, permutation, origin, destination, k, maxEnergy);

            // Add each obtained route to BST
            if (obtained != null) {
                for (Route route : obtained) {
                    if(route.getMinimumEnergy()<= maxEnergy){
                        bst.insert(route);
                    }
                }
            }
        }

        // Obtain k shortest routes of all routes in BST
        List<Route> result = new ArrayList<>();
        int count = 0;
        while (!bst.isEmpty()) {

            // Get the shortest route in BST
            Route route = bst.smallestElement();

            // Check if k wanted routes have been found or next route has the same cost
            if (count >= k && route.getTotalEnergy() > result.get(result.size() - 1).getTotalEnergy()) {
                break;
            }
            bst.remove(route);
            result.add(route);
            count++;
        }
        return (result.isEmpty()) ? null : result;
    }

    private static <V> void fillRouteMap(MainGraph graph, List<GeographicalPoint> toVisit, GeographicalPoint origin, GeographicalPoint destination, int k, Map<Pair<GeographicalPoint, GeographicalPoint>, List<Route>> map) {
        for (GeographicalPoint vertex : toVisit) {
            map.put(new Pair<>(origin, vertex), RouteAlgorithms.kBestRoutes(graph, origin, vertex, k));
            map.put(new Pair<>(vertex, destination), RouteAlgorithms.kBestRoutes(graph, vertex, destination, k));
            for (GeographicalPoint otherVertex : toVisit) {
                if (!vertex.equals(otherVertex)) {
                    map.put(new Pair<>(vertex, otherVertex), RouteAlgorithms.kBestRoutes(graph, vertex, otherVertex, k));
                }
            }
        }
    }

    private static List<Route> kBestOfPermutation(Map<Pair<GeographicalPoint, GeographicalPoint>, List<Route>> map, List<GeographicalPoint> permutation, GeographicalPoint origin, GeographicalPoint destination, int k, int maxEnergy) {
        // Fill a list and a BST with the best and alternative route segments for each pair of vertexes
        List<Route> bestList = new ArrayList<>();
        BST<Route> altBST = new BST<>();
        if (!fillRouteSegments(map, permutation, origin, destination, bestList, altBST)) {
            return null;
        }

        // Create first complete route with all the best route segments
        List<Route> result = new ArrayList<>();
        Route route = new Route(bestList.get(0));
        for (int i = 1; i < bestList.size(); i++) {
            route.addPaths(bestList.get(i).getPaths());
        }
        if(route.getMinimumEnergy()<=maxEnergy){
            result.add(route);
        }

        // While there are alternatives in BST and k wanted routes have not been found
        int count = 1;
        while (!altBST.isEmpty()) {

            // Obtain the best alternative route segment
            Route alternative = altBST.smallestElement();
            altBST.remove(alternative);

            // Check if the alternative corresponds to the first segment to create the next route
            if (alternative.getOrigin().equals(origin)) {
                route = new Route(alternative);
            } else {
                route = new Route(bestList.get(0));
            }

            // Complete the route with the required route segments
            for (int i = 1; i < bestList.size(); i++) {

                // Check if the alternative corresponds to the required segment to add to the route
                if (alternative.getOrigin().equals(permutation.get(i - 1))) {
                    route.addPaths(alternative.getPaths());
                } else {
                    route.addPaths(bestList.get(i).getPaths());
                }
            }

            // Check if k wanted routes have been found or next route has the same cost
            if (count >= k && route.getTotalEnergy() > result.get(result.size() - 1).getTotalEnergy()) {
                break;
            }
            result.add(route);
            count++;
        }
        return result;
    }

    private static boolean fillRouteSegments(Map<Pair<GeographicalPoint, GeographicalPoint>, List<Route>> map, List<GeographicalPoint> permutation, GeographicalPoint origin, GeographicalPoint destination, List<Route> bestList, BST<Route> altBST) {
        int size = permutation.size();
        for (int i = -1; i < size; i++) {
            List<Route> segments;
            if (i == -1) {
                segments = map.get(new Pair<>(origin, permutation.get(0)));
            } else if (i == size - 1) {
                segments = map.get(new Pair<>(permutation.get(i), destination));
            } else {
                segments = map.get(new Pair<>(permutation.get(i), permutation.get(i + 1)));
            }

            // Check if a complete route is possible
            if (segments == null) {
                return false;
            }

            // Add the best route segment to the list and the alternatives to the BST
            bestList.add(segments.get(0));
            for (int j = 1; j < segments.size(); j++) {
                altBST.insert(segments.get(j));
            }
        }
        return true;
    }

    /**
     * Test purposes only
     */
    protected RouteAlgorithms() {
    }
    
    

}
