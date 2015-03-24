/**
 * Author: Daniel Moulton
 * Date: Oct 23, 2014
 * Package: extracreditprojects
 * Filename: NumFreqObject.java
 */
package extracreditprojects;

/**
 * Object for keeping track of number frequency within the NumberFreq program.
 */
public class NumFreqObject implements Comparable<NumFreqObject> {
	private int number;
	private int freq;
	
	public NumFreqObject (int number) {
		this.number = number;
		freq = 1;
	}
	
	public void inc() {
		freq++;
	}
	
	public int compareTo(NumFreqObject num) {
		if (number > num.getNum()) {
			return 1;
		}
		
		else if (number < num.getNum()) {
			return -1;
		}
		
		else {
			return 0;
		}
		
	}
	
	public int getFreq() {
		return freq;
	}
	
	public int getNum() {
		return number;
	}
	
	public String toString() {
		return freq + "\t" + number;
	}
}
