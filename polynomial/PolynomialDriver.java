/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: extracreditprojects
 * Filename: PolynomialDriver.java
 */
package extracreditprojects;

/**
 * Test driver class for the polynomial class
 */
public class PolynomialDriver {

	
	public static void main(String[] args) {
		Polynomial mypolynomial = new Polynomial(3);
		mypolynomial.setCoefficient(3, 5);
		mypolynomial.setCoefficient(1, 2);
		mypolynomial.setCoefficient(0,-3);
		
		System.out.println(mypolynomial.evaluate(0));
		System.out.println(mypolynomial.evaluate(1));
		System.out.println(mypolynomial.evaluate((float) 0.5));

	}

}
