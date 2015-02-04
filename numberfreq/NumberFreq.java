/**
 * Author: Daniel Moulton
 * Date: Oct 23, 2014
 * Package: extracreditprojects
 * Filename: NumberFreq.java
 */
package extracreditprojects;
import ch08.*;
import java.util.Scanner;

/**
 * Allows input of numbers from 1 to 100 and then returns 
 * which number(s) occurred most often and how many times they appeared.
 */
public class NumberFreq {

	public static void main(String[] args) {
		int size = 0;
		int number = 1;
		NumFreqObject sizecompare;
		NumFreqObject tempnum;
		NumFreqObject numintree;
		NumFreqObject comparenum;
		NumFreqObject maxfreq;
		BinarySearchTree<NumFreqObject> bst = new BinarySearchTree<NumFreqObject>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter a series of numbers, from 1-100." +
				" To stop and execute the program, enter 0:");
		
		while (true) {
			
			number = scan.nextInt();
			
			tempnum = new NumFreqObject(number);
			
			if (number < 101 && number > 0) {
				
				numintree = bst.get(tempnum);
				if (numintree == null) {
					bst.add(tempnum);
				}
				
				else {
					numintree.inc();
				}
			}
			
			else if (number > 100 || number < 0) {
				System.out.println("That is an invalid number.");
			}
				
			else {
				break;
			}
		}
		
		size = bst.reset(BinarySearchTree.INORDER);
		
		System.out.println("Freq  Number");
		
		maxfreq = bst.getNext(BinarySearchTree.INORDER);
		System.out.println(maxfreq);
		
		for (int i=0; i<size-1; i++) {
			comparenum = bst.getNext(BinarySearchTree.INORDER);
			System.out.println(comparenum);
			if (comparenum.getFreq() > maxfreq.getFreq()) {
				maxfreq = comparenum;
			}
		}
		
		System.out.print("The number(s) ");
		
		size = bst.reset(BinarySearchTree.INORDER);
		
		for (int i=0; i<size; i++) {
			sizecompare = bst.getNext(BinarySearchTree.INORDER);
			if (sizecompare.getFreq() == maxfreq.getFreq()) {
				System.out.print(sizecompare.getNum() + " ");
			}
		}
		
		System.out.println( " appear(s) the most, " + maxfreq.getFreq() +
							" times.");
		
	}
}
