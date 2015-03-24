/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: unweightedgraph
 * Filename: TestGraph.java
 */
package unweightedgraph;

import ch05.UnboundedQueueInterface;

/**
 * Basic test driver for the unweighted graph ADT.
 */
public class TestGraph {

	public static void main(String[] args) {
		UnweightedGraph<Integer> testgraph = new UnweightedGraph<Integer>(50);
		UnboundedQueueInterface<Integer> testqueue;
		testgraph.addVertex(1);
		testgraph.addVertex(2);
		testgraph.addVertex(3);
		testgraph.addVertex(4);
		testgraph.addVertex(5);
		testgraph.addVertex(6);
		testgraph.addVertex(7);
		testgraph.addVertex(8);
		testgraph.addVertex(9);
		testgraph.addVertex(10);
		
		testgraph.addEdge(1, 5);
		testgraph.addEdge(1, 6);
		testgraph.addEdge(1, 7);
		testgraph.addEdge(2, 7);
		
		if (testgraph.hasVertex(5))
			System.out.println("The graph has vertex 5");
		if (!testgraph.hasVertex(17))
			System.out.println("The graph does not have vertex 17");
		
		testqueue = testgraph.getAdjacent(1);
		System.out.println("The vertices adjacent to 1 are: ");
		while(!testqueue.isEmpty()) {
			System.out.println(testqueue.dequeue());
		}
	}

}
