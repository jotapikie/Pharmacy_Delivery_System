/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.route;

import java.util.ArrayList;
import java.util.List;
import lapr.project.model.GeographicalPoint;
import lapr.project.model.LandGraph;
import lapr.project.model.Pathway;
import lapr.project.utils.bst.BST;
import lapr.project.utils.graph.Graph;

/**
 *
 * @author Diogo
 */
public class RouteAlgorithms {


    
   public static List<Route> kBestRoutes(LandGraph landGraph, GeographicalPoint origin, GeographicalPoint destination, int k){

        if (landGraph == null || origin == null || destination == null || k <= 0) {
            throw new IllegalArgumentException("Invalid route arguments!");
        }
        @SuppressWarnings("unchecked")
        Graph<GeographicalPoint, Pathway> graph = landGraph.getRouteGraph();
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
            if (result.size() >= k && route.getRouteEnergy() > result.get(result.size() - 1).getRouteEnergy()) {
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
   
   
   
   
   
   
    private static void zeroVertexCounters(Graph<GeographicalPoint, Pathway> graph) {

        for (GeographicalPoint vertex : graph.vertices()) {
            vertex.resetCounter();
        }
    }

}
