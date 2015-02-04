/**
 * Author: Daniel Moulton
 * Date: Nov 24, 2014
 * Package: unweightedgraph
 * Filename: StatsGraph.java
 */
package unweightedgraph;

import java.util.Random;

import ch05.LinkedUnbndQueue;
import ch05.UnboundedQueueInterface;

/**
 * Generates graphs with 26 nodes and 10, 20, 30, 40, 50, 60 unique edges - five graphs per number, and displays the average number of connected components
 */
public class StatsGraph {

	public static void main(String[] args) {
		UnweightedGraph<Integer> statGraph;
		int statSum = 0;
		final int TRIALS = 6;
		
		for (int i=10; i<70; i=i+10) {
			for (int j=0; j<TRIALS; j++) {
				statGraph = statGraph(i);
				statSum += connected(statGraph(i));
			}
			System.out.println("Average number of connected components for " + i + " edges and 26 nodes: " + (double)statSum/TRIALS);
			statSum=0;
			}
	}
	
	public static UnweightedGraph<Integer> statGraph(int edges) {
		UnweightedGraph<Integer> randGraph = new UnweightedGraph<Integer>(26);
		Random rand = new Random();
		boolean test = true;
		for (int i=0; i<26; i++) {
			randGraph.addVertex(i);
		}
		int coord1, coord2;
		for (int i=0; i<edges; i++) {
			coord1 = rand.nextInt(26);
			coord2 = rand.nextInt(26);
			test = true;
			while (test) {
				if (randGraph.hasEdge(coord1, coord2)) {
					if (coord1 == 25)
						coord1 = -1;
					if (coord2 == 25)
						coord2 = -1;
					coord1++; coord2++;
				}
				else {
					randGraph.addEdge(coord1, coord2);
					test = false;
				}
			}
		}
		return randGraph;
	}
	
	//Returns the number of connected components within the graph
	public static int connected(UnweightedGraph<Integer> graph) {
		    
		int vertex;
		int testvertex;
		int count = 0;
		int testcount = 0;
		UnboundedQueueInterface<Integer> adjacentQueue = new LinkedUnbndQueue<Integer>();
		UnboundedQueueInterface<Integer> vertexQueue = new LinkedUnbndQueue<Integer>();
			
		//clears all marks in the graph
		graph.clearMarks();
			
		//keeps going until all vertices are marked
		while (graph.getUnmarked() != null) {
			//enqueues the first vertex in the graph
			vertexQueue.enqueue(graph.getUnmarked());
				
			//loops until there are no unmarked connected vertices left
			while (!vertexQueue.isEmpty()) {
				//keeps track of whether there is more than one vertex in a given cluster
				testcount++;
				//Dequeues a vertex, prints it and marks it if it is unmarked
				vertex = vertexQueue.dequeue();
				if (!graph.isMarked(vertex)) {
					graph.markVertex(vertex);
				}
				//gets adjacent vertices, if they are not marked, they are put into vertexQueue
				adjacentQueue = graph.getAdjacent(vertex);
				while (!adjacentQueue.isEmpty()) {
					testvertex = adjacentQueue.dequeue();
					if (!graph.isMarked(testvertex)) {
						vertexQueue.enqueue(testvertex);
					}
				}
			}
			//if the cluster contains more than one vertex, increment the connected component count
			if (testcount > 1)
				count++;
			testcount = 0;
		}
		return count;
	}
}
