/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: unweightedgraph
 * Filename: UnweightedGraph.java
 */
package unweightedgraph;

import ch05.LinkedUnbndQueue;
import ch05.UnboundedQueueInterface;

/**
 * An ADT for an Undirected, Unweighted graph
 */
public class UnweightedGraph<T> implements UnweightedGraphInterface<T> {

	private int maxVertices;
	private int numVertices;
	private T[] vertices;
	private boolean[][] edges;
	private boolean[] marks;
	
	public UnweightedGraph(int capacity) {
		maxVertices = capacity;
		numVertices = 0;
		vertices = (T[]) new Object[capacity];
		edges = new boolean[capacity][capacity];
		marks = new boolean[capacity];
	}
	
	public boolean isEmpty() {
		if (numVertices == 0)
			return true;
		return false;
	}

	public boolean isFull() {
		if (numVertices == maxVertices)
			return true;
		return false;
	}

	public void addVertex(T vertex) {
		if (!hasVertex(vertex)) {
			vertices[numVertices] = vertex;
			for (int i = 0; i < numVertices; i++) {
				edges[numVertices][i] = false;
				edges[i][numVertices] = false;
			}
			numVertices++;
		}
		
	}
	
	public int indexIs(T vertex) {
		int index = 0;
		while (!vertex.equals(vertices[index]))
			index++;
		return index;
	}
	
	public void addEdge(T vertex1, T vertex2) {
		int index1;
		int index2;
		index1 = indexIs(vertex1);
		index2 = indexIs(vertex2);
		edges[index1][index2] = true;
		edges[index2][index1] = true;
	}
	
	public boolean hasEdge(T vertex1, T vertex2) {
		int index1;
		int index2;
		index1 = indexIs(vertex1);
		index2 = indexIs(vertex2);
		if (edges[index1][index2] || edges[index2][index1])
			return true;
		else
			return false;
	}

	public boolean hasVertex(T vertex) {
		for (int i = 0; i < numVertices; i++) {
			if (vertices[i].equals(vertex))
				return true;
		}
		return false;
	}

	public UnboundedQueueInterface<T> getAdjacent(T vertex) {
		UnboundedQueueInterface<T> adjVertices = new LinkedUnbndQueue<T>();
		for (int i=0; i<numVertices; i++) {
			if (edges[indexIs(vertex)][i])
				adjVertices.enqueue(vertices[i]);
		}
		return adjVertices;
	}

	public void clearMarks() {
		for (int i=0; i<numVertices; i++) {
			marks[i] = false;
		}
		
	}

	public void markVertex(T vertex) {
		if (hasVertex(vertex)) {
			  marks[indexIs(vertex)] = true;
		}
		
	}

	public boolean isMarked(T vertex) {
		if (marks[indexIs(vertex)])
			  return true;
		else
			  return false;
	}

	public T getUnmarked() {
		for (int i=0; i<numVertices; i++) {
			  if (!isMarked(vertices[i]))
				  return vertices[i];
		}
		return null;
	}

}
