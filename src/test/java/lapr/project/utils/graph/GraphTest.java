/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils.graph;

import java.util.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Diogo
 */
public class GraphTest {
  Graph<String, String> instance = new Graph<>(true) ;
    
    public GraphTest() {
    }
    

    /**
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");
                      
        assertTrue((instance.numVertices()==0));
        
        instance.insertVertex("A");
        assertTrue((instance.numVertices()==1));       
        
        instance.insertVertex("B");
        assertTrue( (instance.numVertices()==2));
        
        instance.removeVertex("A");
        assertTrue( (instance.numVertices()==1));
        
        instance.removeVertex("B");
        assertTrue( (instance.numVertices()==0));
    }
    
    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<String> itVerts = instance.vertices().iterator();
        
        assertTrue(itVerts.hasNext()==false);
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        	
        itVerts = instance.vertices().iterator();
                
        assertTrue((itVerts.next().compareTo("A")==0));
        assertTrue((itVerts.next().compareTo("B")==0));

        instance.removeVertex("A");
		
        itVerts = instance.vertices().iterator();
        assertTrue((itVerts.next().compareTo("B"))==0);

	instance.removeVertex("B");
		
        itVerts = instance.vertices().iterator();
	assertTrue(itVerts.hasNext()==false);	
    }

    /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testNumEdges() {
        System.out.println("Test numEdges");
        
        assertTrue((instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        assertTrue( (instance.numEdges()==1));
        
        instance.insertEdge("A","C","Edge2",1);
        assertTrue((instance.numEdges()==2));
        
        instance.removeEdge("A","B");
        assertTrue((instance.numEdges()==1));    

        instance.removeEdge("A","C");
        assertTrue((instance.numEdges()==0));
    }
    
        /**
     * Test of numEdges method, of class Graph.
     */
    @Test
    public void testIsDirected() {
        System.out.println("Test isDirected");
        
        assertTrue(instance.isDirected());
    }
    
    /**
     * Test of getKey methof, of class Graph.
     */
    @Test
    public void testGetKey(){
        instance.insertVertex("A");
        instance.insertVertex("B");
        assertEquals(0, instance.getKey("A"));
        assertEquals(1, instance.getKey("B"));
        instance.removeVertex("A");
        instance.removeVertex("B");
    }
    
    @Test
    public void testAdjVertices(){
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertEdge("A", "B", "Edge", 2);
        instance.insertEdge("A", "C", "Edge", 3);
        
        Iterable<String> res = instance.adjVertices("A");
        Iterator<String> it = res.iterator();
        assertEquals("B", it.next());
        assertEquals("C", it.next());
        
        assertNull(instance.adjVertices("D"));
        instance.removeVertex("A");
        instance.removeVertex("B");
        instance.removeVertex("C");
        
        
        
    }
    
    

    /**
     * Test of edges method, of class Graph.
     */
    @Test
    public void testEdges() {
        System.out.println("Test Edges");

        Iterator<Edge<String,String>> itEdge = instance.edges().iterator();

        assertTrue((itEdge.hasNext()==false));

        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);

        itEdge = instance.edges().iterator();
        
        itEdge.next(); itEdge.next();
        assertTrue(itEdge.next().getElement().equals("Edge3")==true); 
        
        itEdge.next(); itEdge.next();
        assertTrue(itEdge.next().getElement().equals("Edge6")==true);
        
        instance.removeEdge("A","B");

        itEdge = instance.edges().iterator();
        assertTrue(itEdge.next().getElement().equals("Edge2")==true);

        instance.removeEdge("A","C"); instance.removeEdge("B","D");
        instance.removeEdge("C","D"); instance.removeEdge("C","E");
        instance.removeEdge("D","A"); instance.removeEdge("E","D");
        instance.removeEdge("E","E");
        itEdge = instance.edges().iterator();
        assertTrue((itEdge.hasNext()==false));
    }

    /**
     * Test of getEdge method, of class Graph.
     */
    @Test
    public void testGetEdge() {
        System.out.println("Test getEdge");
		        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		
        assertTrue(instance.getEdge("A","E")==null);
		
        assertTrue(instance.getEdge("B","D").getElement().equals("Edge3")==true);       
        assertTrue(instance.getEdge("D","B")==null);

	instance.removeEdge("D","A");	
        assertTrue(instance.getEdge("D","A")==null);
        
        assertTrue(instance.getEdge("E","E").getElement().equals("Edge8")==true);
    }

    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");
        			 
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
        
        Edge<String,String> edge0 = new Edge<>();
        
        String[] vertices = new String[2];
        
        //assertTrue("endVertices should be null", instance.endVertices(edge0)==null);

        Edge<String,String> edge1 = instance.getEdge("A","B");
        //vertices = instance.endVertices(edge1);
        assertTrue( instance.endVertices(edge1)[0].equals("A"));
        assertTrue( instance.endVertices(edge1)[1].equals("B"));
        
        assertNull(instance.endVertices(null));
        
        Graph<String, String> temp = new Graph<>(true) ;
        temp.insertVertex("A");
        temp.insertVertex("B");
        temp.insertVertex("H");
        temp.insertEdge("A", "H", "e1", 2);
        temp.insertEdge("H", "B", "e2", 2);
        
        edge1 = temp.getEdge("A", "H");
        assertNull(instance.endVertices(edge1));
        edge1 = temp.getEdge("H", "B");
        assertNull(instance.endVertices(edge1));
        
        
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		     
        Edge<String,String> edge5 = instance.getEdge("C","E");
        String vert = instance.opposite("A", edge5);
        assertTrue( vert==null);
        
        Edge<String,String> edge1 = instance.getEdge("A","B");
        vert = instance.opposite("A", edge1);
        assertTrue(vert.equals("B")==true);
        
        Edge<String,String> edge8 = instance.getEdge("E","E");
        vert = instance.opposite("E", edge8);
        assertTrue( vert.equals("E")==true);
        
        assertNull(instance.opposite("Y", edge8));
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		    
        int outdeg = instance.outDegree("G");    
        assertTrue(outdeg==-1);
        
        outdeg = instance.outDegree("A");
        assertTrue(outdeg==2);
        
        outdeg = instance.outDegree("B");
        assertTrue(outdeg==1);
         
        outdeg = instance.outDegree("E");
        assertTrue(outdeg==2);    
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		       
        int indeg = instance.inDegree("G");    
        assertTrue(indeg==-1);
        
        indeg = instance.inDegree("A");
        assertTrue(indeg==1);
        
        indeg = instance.inDegree("D");
        assertTrue(indeg==3);
         
        indeg = instance.inDegree("E");
        assertTrue(indeg==2);  
    }

    /**
     * Test of outgoingEdges method, of class Graph.
     */
    @Test
    public void testOutgoingEdges() {
        System.out.println(" Test outgoingEdges");
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		                        
        Iterator<Edge<String,String>> itEdge = instance.outgoingEdges("C").iterator();
        Edge<String,String> first = itEdge.next();
        Edge<String,String> second = itEdge.next();
        assertTrue(
                  ( (first.getElement().equals("Edge4")==true && second.getElement().equals("Edge5")==true) ||
                    (first.getElement().equals("Edge5")==true && second.getElement().equals("Edge4")==true) )); 
        
        instance.removeEdge("E","E");
        
        itEdge = instance.outgoingEdges("E").iterator();
        assertTrue((itEdge.next().getElement().equals("Edge7")==true));
        
        instance.removeEdge("E","D");

        itEdge = instance.outgoingEdges("E").iterator();
        assertTrue((itEdge.hasNext()==false));
        
        assertNull(instance.outgoingEdges("Y"));
    }

    /**
     * Test of incomingEdges method, of class Graph.
     */
    @Test
    public void testIncomingEdges() {
        		
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
        
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		      
        Iterator<Edge<String,String>> itEdge = instance.incomingEdges("D").iterator();
        
        assertTrue( (itEdge.next().getElement().equals("Edge3")==true));
        assertTrue((itEdge.next().getElement().equals("Edge4")==true));
        assertTrue( (itEdge.next().getElement().equals("Edge7")==true));
        
        itEdge = instance.incomingEdges("E").iterator();
        
        assertTrue((itEdge.next().getElement().equals("Edge5")==true));
        assertTrue((itEdge.next().getElement().equals("Edge8")==true));
        
        instance.removeEdge("E","E");
        
        itEdge = instance.incomingEdges("E").iterator();
        
        assertTrue((itEdge.next().getElement().equals("Edge5")==true));
        
        instance.removeEdge("C","E");

        itEdge = instance.incomingEdges("E").iterator();
        assertTrue((itEdge.hasNext()==false));
    }

    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");
        
        instance.insertVertex("A");   
        instance.insertVertex("B");    
        instance.insertVertex("C");    
        instance.insertVertex("D");      
        instance.insertVertex("E");
             
        Iterator <String> itVert = instance.vertices().iterator();
		
        assertTrue((itVert.next().equals("A")==true));
        assertTrue((itVert.next().equals("B")==true));
        assertTrue((itVert.next().equals("C")==true));
        assertTrue((itVert.next().equals("D")==true));
        assertTrue((itVert.next().equals("E")==true));
        
        assertFalse(instance.insertVertex("A"));
    }
    
    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");
        
        assertTrue( (instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        assertTrue((instance.numEdges()==1));      
        
        instance.insertEdge("A","C","Edge2",1);
        assertTrue( (instance.numEdges()==2));
        
        instance.insertEdge("B","D","Edge3",3);
        assertTrue( (instance.numEdges()==3));
        
        instance.insertEdge("C","D","Edge4",4);
        assertTrue( (instance.numEdges()==4));
        
        instance.insertEdge("C","E","Edge5",1);
        assertTrue( (instance.numEdges()==5));
        
        instance.insertEdge("D","A","Edge6",2);
        assertTrue((instance.numEdges()==6));
        
        instance.insertEdge("E","D","Edge7",1);
        assertTrue( (instance.numEdges()==7));
        
        instance.insertEdge("E","E","Edge8",1);
        assertTrue((instance.numEdges()==8));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertTrue((itEd.next().getElement().equals("Edge3")==true));
        itEd.next(); itEd.next();
        assertTrue((itEd.next().getElement().equals("Edge6")==true));
        
        assertFalse(instance.insertEdge("D", "A", "Edge9", 2));
        
        Graph<String, String> temp = new Graph<>(false) ;
        temp.insertVertex("A");
        temp.insertVertex("B");
        temp.insertVertex("C");
        assertTrue(temp.insertEdge("A", "B", "Edge1", 2));
        assertFalse(temp.insertEdge("A", "B", "Edge1", 2));
        
        
    }

    /**
     * Test of removeVertex method, of class Graph.
     */
    @Test
    public void testRemoveVertex() {       
        System.out.println("Test removeVertex");
        
        instance.insertVertex("A");
        instance.insertVertex("B");
        instance.insertVertex("C");
        instance.insertVertex("D");
        instance.insertVertex("E");
 
        instance.removeVertex("C");
        assertTrue((instance.numVertices()==4));
      
        Iterator<String> itVert = instance.vertices().iterator();
        assertTrue((itVert.next().equals("A")==true));
        assertTrue((itVert.next().equals("B")==true));
        assertTrue((itVert.next().equals("D")==true));
        assertTrue((itVert.next().equals("E")==true));
        
        instance.removeVertex("A");
        assertTrue((instance.numVertices()==3));
   
        itVert = instance.vertices().iterator();
        assertTrue((itVert.next().equals("B")==true));
        assertTrue((itVert.next().equals("D")==true));
        assertTrue((itVert.next().equals("E")==true));

        instance.removeVertex("E");
        assertTrue((instance.numVertices()==2));

        itVert = instance.vertices().iterator();

        assertTrue(itVert.next().equals("B")==true);
        assertTrue(itVert.next().equals("D")==true);
        
        instance.removeVertex("B"); instance.removeVertex("D");
        assertTrue((instance.numVertices()==0));
        
        assertFalse(instance.removeVertex("Y"));
    }
    
    /**
     * Test of removeEdge method, of class Graph.
     */
    @Test
    public void testRemoveEdge() {     
        System.out.println("Test removeEdge");
        
        assertTrue((instance.numEdges()==0));

        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
		     
        assertTrue((instance.numEdges()==8));
        
        instance.removeEdge("E","E");
        assertTrue((instance.numEdges()==7));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertTrue((itEd.next().getElement().equals("Edge3")==true));
        itEd.next(); itEd.next(); 
        assertTrue((itEd.next().getElement().equals("Edge6")==true));
        
        instance.removeEdge("C","D");
        assertTrue((instance.numEdges()==6));
        
        itEd = instance.edges().iterator();	
        itEd.next(); itEd.next();
        assertTrue((itEd.next().getElement().equals("Edge3")==true));
        assertTrue((itEd.next().getElement().equals("Edge5")==true));
        assertTrue((itEd.next().getElement().equals("Edge6")==true));
        assertTrue( (itEd.next().getElement().equals("Edge7")==true));
        
        instance.insertEdge("D", "E", "E1", 2);
        assertFalse(instance.removeEdge("D", "Y"));
        assertFalse(instance.removeEdge("y", "E"));
        instance.insertVertex("x");
        instance.insertVertex("k");
        assertFalse(instance.removeEdge("x", "y"));
        instance.removeVertex("x");
        instance.removeVertex("k");
        
        Graph<String, String> temp = new Graph<>(false);
        temp.insertVertex("A");
        temp.insertVertex("B");
        assertFalse(temp.removeEdge("A", "B"));
        temp.insertEdge("A", "B", "E1", 2);
        assertTrue(temp.removeEdge("A", "B"));
        
    }
    
    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testClone() {
	System.out.println("Test Clone");
         
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
        
        Graph<String,String> instClone = instance.clone();
	
        assertTrue( instance.numVertices()==instClone.numVertices());
        assertTrue( instance.numEdges()==instClone.numEdges());
	
        //vertices should be equal
        Iterator<String> itvertClone = instClone.vertices().iterator();
        Iterator<String> itvertSource = instance.vertices().iterator();
	while (itvertSource.hasNext())
            assertTrue((itvertSource.next().equals(itvertClone.next())==true));
    }

    @Test
    public void testEquals() {
        System.out.println("Test Equals");
              
        instance.insertEdge("A","B","Edge1",6);
        instance.insertEdge("A","C","Edge2",1);
        instance.insertEdge("B","D","Edge3",3);
        instance.insertEdge("C","D","Edge4",4);
        instance.insertEdge("C","E","Edge5",1);
        instance.insertEdge("D","A","Edge6",2);
        instance.insertEdge("E","D","Edge7",1);
        instance.insertEdge("E","E","Edge8",1);
        
        assertFalse( instance.equals(null));
		
	assertTrue(instance.equals(instance));
		
	assertTrue(instance.equals(instance.clone()));
        
        Graph<String,String> other = instance.clone();
       
        other.removeEdge("E","E");
        assertFalse(instance.equals(other));
        
        other.insertEdge("E","E","Edge8",1);
        assertTrue( instance.equals(other));
        
        other.removeVertex("D");
        assertFalse(instance.equals(other));
        
        Graph<String, String> temp1 = new Graph<>(true) ;
        Graph<String, String> temp2 = new Graph<>(true) ;
        
        temp1.insertVertex("A");
        temp2.insertVertex("A");
        temp1.insertVertex("B");
        temp2.insertVertex("C");
        assertFalse(temp1.equals(temp2));
        
    }
    
    
    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        
        System.out.println(instance);
        
        Graph<String, String> temp = new Graph<>(true) ;
        temp.insertVertex("A");
        temp.insertVertex("B");
        System.out.println(temp);
        
    }
    
}
