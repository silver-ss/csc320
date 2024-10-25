package groceryBill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class GroceryBill {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		double couponAmount;
		BigDecimal couponRate;
		double week1Bill;
		double week2Bill;
		double week3Bill;
		double week4Bill;
		//using bigDecimal for precision when averaging. 
		BigDecimal monthlyTotal;
		BigDecimal weeks = BigDecimal.valueOf(4.0);
		BigDecimal weeklyAverage;
		
		//prompt user for a coupon rate
		System.out.println("Enter the coupon rate as a decimal like 0.10 for 10%:");
		couponAmount = scnr.nextDouble();
		
		// if value exceeds 100% or is at or below 0% set to 10% else set to user entry
		if (couponAmount > 1.0  || couponAmount <= 0.0) {
			// store as 0.9 to multiply and get total after applying to total later
			couponRate = new BigDecimal(1.0 - 0.1);
			System.out.println("Invalid coupon rate of " + (couponAmount * 100.0) + "% entered, setting value to 10%");
		}
		else {
			//take the users input store it as 1 - the percentage of the coupon, round at the 4th decimal place, if 5 round up
			couponRate = new BigDecimal(1.0 - couponAmount).setScale(4, RoundingMode.HALF_UP);
		}
		
		//prompt user for last 4 grocery bills
		System.out.println("Enter your last 4 weekly grocery bills including cents seperated by a space\nEXAMPLE:");
		System.out.println("100.25 50.08 122.22 64.88");
		week1Bill = scnr.nextDouble();
		week2Bill = scnr.nextDouble();
		week3Bill = scnr.nextDouble();
		week4Bill = scnr.nextDouble();
		
		//sum monthly total rounded to the 2nd decimal since we are converting from float here
		monthlyTotal = BigDecimal.valueOf(sumTotal(week1Bill, week2Bill, week3Bill, week4Bill)).setScale(2, RoundingMode.HALF_UP);
		//calculate weekly average
		weeklyAverage = averageBills(monthlyTotal, weeks);
		
		//output the totals and averages with and without coupon amount
		System.out.println();
		System.out.println("Without the coupon:");
		System.out.println("Monthly total spending is:  $" + monthlyTotal);
		System.out.println("Average weekly spending is: $" + weeklyAverage);
		System.out.println();
		System.out.println("With the coupon:");
		System.out.println("Monthly total spending would have been:  $" 
		+ (monthlyTotal.multiply(couponRate).setScale(2, RoundingMode.HALF_UP)));
		System.out.println("Average weekly spending would have been: $" 
		+ (weeklyAverage.multiply(couponRate).setScale(2, RoundingMode.HALF_UP)));
		
	}
	// sum the weekly bills
	public static double sumTotal(double week1Bill, double week2Bill, double week3Bill, double week4Bill) {
		return week1Bill + week2Bill + week3Bill + week4Bill;
	}
	// average the weekly bills
	public static BigDecimal averageBills(BigDecimal monthlyTotal, BigDecimal weeks) {
		return monthlyTotal.divide(weeks).setScale(2, RoundingMode.HALF_UP);
	}
}
