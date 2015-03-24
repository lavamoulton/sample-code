/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: extracreditprojects
 * Filename: PolynomialScanner.java
 */
package extracreditprojects;

/**
 * Class that accepts a command line argument and converts it into a polynomial object
 * Proceeds to evaluate values of the polynomial in a loop
 */

import java.util.Scanner;

public class PolynomialScanner {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int degree;
		String str;
		boolean loop = true;
		System.out.println("Please enter the degree of your polynomial followed by the coefficients: ");
		degree = scan.nextInt();
		Polynomial mypolynomial = new Polynomial(degree);
		for (int i=degree;i>-1;i--) {
			mypolynomial.setCoefficient(i, scan.nextInt());
		}
		while (loop) {
			System.out.println("Enter a value to evaluate: ");
			System.out.println(mypolynomial.evaluate(scan.nextFloat()));
			System.out.println("Enter 0 to quit: ");
			if (scan.nextInt() == 0)
				break;
		}
		scan.close();
	}

}
