package cta4;

import java.util.InputMismatchException;
import java.util.Scanner; 

public class CriticalThinkingAssignmentOption1 {
	public static void main(String[] args) {
		int count = 0; 
		float interestRate = 0.2f;
		float total;
		Scanner scnr = new Scanner(System.in);
		float [] array = new float[5];

		while (count < 5) {
			System.out.println("Enter a floating point number " + (count + 1) + " of 5:");
			try{
			array[(count)] = scnr.nextFloat();
			++count;
			} catch (InputMismatchException e) { // present error if non-float is provided
				System.out.println("ERROR: Invalid entry provided, pleae provide a decimal number like 2.4");
				scnr.nextLine(); // clear scanner
			}
		}
		scnr.close();
		//call methods to print out results
		total = getTotal(array);
		getAverage(total, count);
		getMax(array);
		getMin(array);
		getInterest(interestRate, total);	
	}
	// find minimum values in array
	private static void getMin(float [] array) {
		float min = array[0];
		for (float num : array) {
			if (num < min) {min = num;}
		}
		System.out.println("The Minimum is: " + min);
	}
	// find maximum values in array
	private static void getMax(float [] array) {
		float max = array[0];
		for (float num : array) {
			if (num > max) {max = num;}
		}
		System.out.println("The Maximum is: " + max);
	}
	// sum the user entries
	private static float getTotal(float [] array) {
		float total = 0.0f;
		for (float num : array) {
			total += num;
		}
		System.out.println("The Total is: " + total);
		return total;
	}
	// find the average of all entries
	private static void getAverage (float total, float count) {
		float average = (total / count);
		System.out.printf("The Average is: %.2f\n", average);
	}
	// calculate the interest at 20%
	private static void getInterest(float interestRate, float total) {
		float interest = total * interestRate;
		System.out.printf("The Interest at %.1f%% is %.2f\n", (interestRate * 100), interest);
	}
}
