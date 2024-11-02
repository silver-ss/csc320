package cta4;

import java.util.Scanner; 

public class CriticalThinkingAssignmentOption1 {
	public static void main(String[] args) {
		float total = 0.0f;
		int count = 0; 
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		float interestRate = 0.2f;
		float userInput;
		float average;
		float interest;
		Scanner scnr = new Scanner(System.in);

		
		while (count < 5) {
			System.out.println("Enter a floating point number " + (count + 1) + " of 5:");
			userInput = scnr.nextFloat();
			//add new number to total
			total += userInput;
			//update max is new number is the greatest
			if(userInput > max) {
				max = userInput;
			}
			//update min if new number is the smallest
			if(userInput < min) {
				min = userInput;
			}
			++count;
		}
		average = total/count;
		interest = total * interestRate;
		
		System.out.println("The Total is: " + total);
		System.out.println("The Average is: " + average);
		System.out.println("The Maximum is: " + max);
		System.out.println("The Minimum is: " + min);
		System.out.println("The Interest at " + (interestRate * 100) + "% is: " + interest);	
	}
}