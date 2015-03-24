/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: extracreditprojects
 * Filename: TriangleExist.java
 */
package extracreditprojects;

/**
 * Class that takes in 3 numbers and decides whether a triangle exists
 * Returns Does not exist, Scalar, Isosceles, Equilateral
 */

import java.util.Scanner;

public class TriangleExist {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean loop = true;
		int x, y, z;
		//begins loop
		while (loop) {
			System.out.println("Please enter three integers seperated by a space: ");
			x = scan.nextInt();
			y = scan.nextInt();
			z = scan.nextInt();
			System.out.print("The numbers " + x + " " + y + " " + z + " make the following triangle: " );
			System.out.println(triangleSolve(x,y,z));
			System.out.println("If you would like to quit, enter 0: ");
			if (scan.nextInt() == 0) {
				loop = false;
			}
		}
		scan.close();
	}
	
	//Method created to decide whether or not a triangle exists
	//If it does, it will print whether the triangle is scalene, isosceles, or equilateral
	public static String triangleSolve(int a, int b, int c) {
		//returns equilateral if all 3 numbers are the same
		if (a == b && b == c) {
			return "Equilateral";
		}
		//returns DNE if any 2 sides are not greater than or equal to the third side
		else if (a+b < c || a+c < b || b+c < a) {
			return "Does not exist";
		}
		//returns isosceles if any of the 2 sides equal each other
		else if (a == b || a == c || b == c) {
			return "Isosceles";
		}
		//returns scalene
		else {
			return "Scalene";
		}
	}

}
