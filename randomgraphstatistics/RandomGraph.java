/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: unweightedgraph
 * Filename: RandomGraph.java
 */
package unweightedgraph;

import ch05.*;
import java.util.Scanner;

/**
 * Randomly generates a graph and then counts the number of connected components
 */

import java.util.Random;

public class RandomGraph {

	public static void main(String[] args) {
		final int MAXNUM = 10; 	// Number of vertices in your randomized graph
		final int RANDNUM = 25;	//Percentage chance of generating an edge between two vertices
		int minVertices;
		int maxVertices;
		int ranNum;
		Scanner scan = new Scanner(System.in);
		System.out.println("Simple random graph generator with " + MAXNUM + " vertices and a randomized chance of generating an edge:");
		UnweightedGraph<Integer> myGraph = randGraph1(MAXNUM, RANDNUM);
		connected(myGraph);
		System.out.println("Generate a graph between __ and __ vertices: ");
		minVertices = scan.nextInt();
		maxVertices = scan.nextInt();
		System.out.println("1 / __ chance of generating an edge between two vertices (Recommended 5-20): ");
		ranNum = scan.nextInt();
		UnweightedGraph<Integer> myGraph2 = randGraph2(minVertices, maxVertices, ranNum);
		connected(myGraph2);
		scan.close();
	}
	
	//Simple random graph generator that generates the number of vertices wanted (numVertices) and uses a randNum that loosely lowers the percentage chance of generating
	//an edge between two vertices. When randNum is higher, there is a lower chance of generating edges.
	public static UnweightedGraph<Integer> randGraph1(int numVertices, int randNum) {
		Random rand = new Random();
		UnweightedGraph<Integer> randGraph = new UnweightedGraph<Integer>(numVertices);
		
		//adds numVertices vertices
		for (int i=0; i<numVertices; i++) {
			randGraph.addVertex(i);
		}
		
		//cycles through possible vertex pairs and generates edges if the value between 0 and randNum is 1
		for (int i=0; i<numVertices; i++) {
			for (int j=0; j<numVertices; j++) {
				if (j != i && rand.nextInt(randNum) == 1) {
					randGraph.addEdge(i, j);
				}
			}
		}
		return randGraph;
	}
	
	//More complicated random graph Generator, creating a graph with minVertices to maxVertices vertices and a 1 / ranNum chance of generating an edge between two vertices
	public static UnweightedGraph<Integer> randGraph2(int minVertices, int maxVertices, int ranNum) {
		Random rand = new Random();
		
		int range = (maxVertices+1) - minVertices;
		int size = rand.nextInt(range)+minVertices;
		
		//generates a graph with a random number of vertices from minVertices to maxVertices
		UnweightedGraph<Integer> randGraph = new UnweightedGraph<Integer>(size);
		
		for (int k=0; k<size; k++) {
			randGraph.addVertex(k);
		}
		
		randGraph.clearMarks();
		
		//Goes through with 1 / ranNum chance of generating an edge between two vertices
		for (int i=0; i<size; i++) {
			if (randGraph.hasVertex(i)) {
				randGraph.markVertex(i);
				for (int j=0; j<size; j++) {
					if (randGraph.hasVertex(j) && !randGraph.isMarked(j)) {
						if (rand.nextInt(ranNum) == 1) {
							randGraph.addEdge(i, j);
						}
					}
				}
			}
		}
		
		return randGraph;
	}
	
	//Returns a list of all clusters of connected vertices, and the total number of connected components within the graph
	public static void connected(UnweightedGraph<Integer> graph) {
	    
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
			//Simple print statement to show different clusters of vertices
			System.out.print("The following vertices are connected: ");
			//enqueues the first vertex in the graph
			vertexQueue.enqueue(graph.getUnmarked());
			
			//loops until there are no unmarked connected vertices left
			while (!vertexQueue.isEmpty()) {
				//keeps track of whether there is more than one vertex in a given cluster
				testcount++;
				//Dequeues a vertex, prints it and marks it if it is unmarked
				vertex = vertexQueue.dequeue();
				if (!graph.isMarked(vertex)) {
					System.out.print(vertex+1 + " ");
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
			System.out.println();
		}
		System.out.println("The total number of connected components is " + count);
	}

}
