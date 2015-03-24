/**
 * Author: Daniel Moulton
 * Date: Nov 3, 2014
 * Package: mazeextracredit
 * Filename: ArrayMaze2D.java
 */
package mazeextracredit;

import ch05.*;

/**
 * A 2D array representing a maze with dimensions of N, M.
 * Inherently only handles mazes with limited dead ends, e.g. 1 tile long
 */
public class ArrayMaze2D {
	
	UnboundedQueueInterface<String> mazesolve = new LinkedUnbndQueue<String>();
	private int n = 0, m = 0;
	private int mazeArray[][];
	private int countx = 0;
	private int county = 0;
	private int currentx = 0;
	private int currenty = 0;
	private String direction;
	private String teststring, holdstring;
	private int dircount = 1;
	
	//basic constructor creating a 2D array of dimensions n x m
	public ArrayMaze2D (int n, int m) {
		this.n = n;
		this.m = m;
		mazeArray = new int[n][m];
	}
	
	//displays whether the spot to the left of the maze spot is open
	public boolean hasLeft(int x, int y) {
		if (x-1 >= 0) {	
			if (mazeArray[x-1][y] == 0)
				return true;
		}
		return false;
	}
	
	//displays whether the spot to the right of the maze spot is open
	public boolean hasRight(int x, int y) {
		if (x+1 <= n-1) {
			if (mazeArray[x+1][y] == 0)
				return true;
		}
		return false;
	}
	
	//displays whether the spot above the maze spot is open
	public boolean hasDown(int x, int y) {
		if (y+1 <= m-1) {
			if (mazeArray[x][y+1] == 0)
				return true;
		}
		return false;
	}
	
	//displays whether the spot below the maze spot is open
	public boolean hasUp(int x, int y) {
		if (y-1 >= 0) {
			if (mazeArray[x][y-1] == 0)
				return true;
		}
		return false;
	}
	
	//assumes that the counters starts at 0 and adds integer values to the array
	//in a left to right fashion
	public void add(int addnum) {
		if (countx >= n) {
			county++;
			countx=1;
			mazeArray[0][county] = addnum;
		}
		else {
			mazeArray[countx][county] = addnum;
			countx++;
		}
	}
	
	//returns true if the current spot in the maze is the final position
	public boolean isEnd(int x, int y) {
		if (x == (n-1) && y == (m-1))
			return true;
		else
			return false;
	}
	
	public void switchDir() {
		if (direction == "L") {
			direction = "R";
			return;
		}
		if (direction == "R") {
			direction = "L";
			return;
		}
		if (direction == "U") {
			direction = "D";
			return;
		}
		if (direction == "D") {
			direction = "U";
			return;
		}
	}

	//solves the maze
	public void solve() {
		recSolve(currentx, currenty);
	}
	
	public void recSolve(int x, int y) {
		if (isEnd(x, y))
			return;
		else if (isDead(direction, x, y)) {
			switchDir();
			recSolve(currentx, currenty);
		}
		else if (hasRight(x, y) && direction != "R") {
			currentx = x;
			currenty = y;
			direction = "L";
			if (!isDead(direction, x+1, y) || isEnd(x+1, y)) {
				mazesolve.enqueue("right");
			}
			recSolve(x+1, y);
		}
		else if (hasDown(x, y) && direction != "D") {
			currentx = x;
			currenty = y;
			direction = "U";
			if (!isDead(direction, x, y+1) || isEnd(x, y+1)) {
				mazesolve.enqueue("down");
			}
			recSolve(x, y+1);
		}
		else if (hasLeft(x, y) && direction != "L") {
			currentx = x;
			currenty = y;
			direction = "R";
			if (!isDead(direction, x-1, y) || isEnd(x-1, y)) {
				mazesolve.enqueue("left");
			}
			recSolve(x-1, y);
		}
		else if (hasUp(x, y) && direction != "U") {
			currentx = x;
			currenty = y;
			direction = "D";
			if (!isDead(direction, x, y-1) || isEnd(x, y-1)) {
				mazesolve.enqueue("up");
			}
			recSolve(x, y-1);
		}
	}
	
	public void printMaze() {
		holdstring = mazesolve.dequeue();
		
		while (!mazesolve.isEmpty()) {
			teststring = mazesolve.dequeue();
			if (holdstring.equals(teststring)) {
				dircount++;
			}
			else {
				System.out.println("Go " + holdstring + " " + dircount + " " + "time(s).");
				holdstring = teststring;
				dircount = 1;
			}
		}
		System.out.println("Go " + holdstring + " " + dircount + " " + "time(s).");
	}
	
	//returns true if the spot in the maze has no outlets
	public boolean isDead(String dir, int x, int y) {
		if (dir == "L") {
			if (!hasRight(x, y) && !hasUp(x, y) && !hasDown(x, y))
				return true;
		}
		if (dir == "R") {
			if (!hasLeft(x, y) && !hasUp(x, y) && !hasDown(x, y))
				return true;
		}
		if (dir == "U") {
			if (!hasLeft(x, y) && !hasRight(x, y) && !hasDown(x, y))
				return true;
		}
		if (dir == "D") {
			if (!hasLeft(x, y) && !hasRight(x, y) && !hasUp(x,y))
				return true;
		}
		return false;
	}
	
	//Writes out the maze in original text form using "X"'s and "O"'s
	public String toString() {
		String result = "";
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (mazeArray[j][i] == 0)
					result += "O";
				if (mazeArray[j][i] == 1)
					result += "X";
			}
			result += "\n";
		}
		
		return result;
	}
	
 }
