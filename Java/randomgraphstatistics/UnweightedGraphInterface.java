/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: unweightedgraph
 * Filename: UnweightedGraphInterface.java
 */
package unweightedgraph;

import ch05.*;

/**
 * An interface for a undirected, unweighted graph ADT.
 */
public interface UnweightedGraphInterface<T> {
	//Returns true if the graph is empty
	boolean isEmpty();
	
	//Returns true if the graph is full
	boolean isFull();
	
	//Adds a vertex to the graph if:
	//	The graph is not full
	//	The graph does not contain the vertex already
	void addVertex(T vertex);	
	
	//Adds an edge between two vertices
	void addEdge(T vertex1, T vertex2);
	
	//Returns true if the graph has the vertex
	boolean hasVertex(T vertex);
	
	//Returns a queue of vertices adjacent to vertex
	UnboundedQueueInterface<T> getAdjacent(T vertex);
	
	//Sets all marks to false
	void clearMarks();
	
	//Marks vertex
	void markVertex(T vertex);
	
	//Returns true if vertex is marked
	boolean isMarked(T vertex);
	
	//Returns an unmarked vertex, returns null if none exist
	T getUnmarked();
	
}
