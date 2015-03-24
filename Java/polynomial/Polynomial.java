/**
 * Author: Daniel Moulton
 * Date: Nov 13, 2014
 * Package: extracreditprojects
 * Filename: Polynomial.java
 */
package extracreditprojects;

/**
 * A class that represents a polynomial object.
 */
public class Polynomial {
	int degree;
	int[] coefficients;
	//constructor that accepts the degree of the polynomial
	public Polynomial (int degree) {
		this.degree = degree;
		coefficients = new int[degree+1];
	}
	
	public void setCoefficient(int degree, int coefficient) {
		coefficients[degree] = coefficient;
	}
	
	public float evaluate(float input) {
		float result = 0;
		for (int i=0; i<coefficients.length;i++) {
			result += coefficients[i]*(Math.pow(input, i));
		}
		return result;
	}
}
