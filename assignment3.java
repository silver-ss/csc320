package groceryBill;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

	public class GroceryBill {

		public static void main(String[] args) {
			Scanner scnr = new Scanner(System.in);
			double [] bills = new double[4];
			//using bigDecimal for precision when averaging.
			BigDecimal couponRate;
			BigDecimal monthlyTotal;
			BigDecimal weeks = BigDecimal.valueOf(4.0);
			BigDecimal weeklyAverage;
			
			//prompt user for a coupon rate
			couponRate = getCouponRate(scnr);
			
			// prompt user for last 4 grocery bills
			while (true) {
				System.out.println("Enter your last 4 weekly grocery bills including cents seperated by a space\nEXAMPLE:");
				System.out.println("100.25 50.08 122.22 64.88");
				boolean valid = true;
				for (int i = 0; i < bills.length; ++i) {
					try {
						bills[i] = scnr.nextDouble();
					} catch (InputMismatchException e ) {
						System.out.println("Invalid input. Please enter decimal numbers seperated by spaces only!");
						valid = false; 
						scnr.nextLine(); // clear scanner
						break; // break from for loop and start over
					}
				}
				if (valid) {
					break;
				}
			}
			scnr.close();
			// sum monthly total 
			monthlyTotal = sumTotal(bills);
			// calculate weekly average
			weeklyAverage = averageBills(monthlyTotal, weeks);
			// print out the results
			printResults(monthlyTotal, weeklyAverage, couponRate);			
		}
		
		//prompt user for a coupon rate
		public static BigDecimal getCouponRate(Scanner scnr) {
			double couponAmount;
			while (true) {
				try {
					System.out.println("Enter the coupon rate as a decimal like 0.10 for 10%:");
					couponAmount = scnr.nextDouble();
					if (couponAmount > 1.0  || couponAmount <= 0.0) { 
						throw new InputMismatchException ();
					}
					break;
				} 
				catch (InputMismatchException e) {
					System.out.println("ERROR: pelease provide a decimal number between 0.0 and 1.0" );
					scnr.nextLine(); //clear scnr
				}
			}
			return new BigDecimal(1.0 - couponAmount).setScale(4, RoundingMode.HALF_UP);
		}
		// sum the weekly bills
		public static BigDecimal sumTotal(double [] weeklyBills) {
			double sum =0;
			for (double bill : weeklyBills) {
				sum += bill;
			}
			return BigDecimal.valueOf(sum).setScale(2, RoundingMode.HALF_UP);
		}
		// average the weekly bills
		public static BigDecimal averageBills(BigDecimal monthlyTotal, BigDecimal weeks) {
			return monthlyTotal.divide(weeks).setScale(2, RoundingMode.HALF_UP);
		}
		// output results for total average and coupon effect
		public static void printResults(BigDecimal monthlyTotal, BigDecimal weeklyAverage, BigDecimal couponRate) {
			BigDecimal couponTotal = monthlyTotal.multiply(couponRate).setScale(2, RoundingMode.HALF_UP);
			BigDecimal couponWeekly = weeklyAverage.multiply(couponRate).setScale(2, RoundingMode.HALF_UP);
			System.out.println("\nWithout the Coupon:\nMonthly Total Spending is: $" + monthlyTotal);
			System.out.println("Average weekly spending is: $" + weeklyAverage);
			System.out.println("\nWith the Coupon:\nMonthly Total Spending would have been: $" + couponTotal);
			System.out.println("Average weekly spending is: $" + couponWeekly + "\n");
		}
	}