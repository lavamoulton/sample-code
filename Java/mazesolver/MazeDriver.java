/**
 * Author: Daniel Moulton
 * Date: Nov 4, 2014
 * Package: mazeextracredit
 * Filename: MazeDriver.java
 */
package mazeextracredit;

/**
 * A driver that accepts an input file "themaze.txt" with a guaranteed path through it.
 */

import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class MazeDriver {

	public static void main(String[] args) throws IOException {
		File path = new File("themaze.txt");
		Scanner fileReader = new Scanner(path);
		int n = fileReader.nextInt();
		int m = fileReader.nextInt();
		String test;
		ArrayMaze2D mazearray = new ArrayMaze2D(n, m);
		while (fileReader.hasNext()) {
			test = fileReader.next();
			for (int i=0; i<test.length(); i++) {
				if (test.charAt(i) == 'X')
					mazearray.add(1);
				if (test.charAt(i) == 'O')
					mazearray.add(0);
			}
		}
		
		System.out.println("To solve the following maze: ");
		System.out.println(mazearray.toString());
		System.out.println("Follow this path: ");
		mazearray.solve();
		mazearray.printMaze();
		fileReader.close();
	}

}
