package cta5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WeeklyTemps {	
	private static ArrayList<String> dayOfWeek = new ArrayList<>(Arrays.asList(
			"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",	"Saturday", "Sunday"));
	private static ArrayList<Double> dailyTemp = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean continueWhile = true;
		
		setTemps(dayOfWeek, scnr);
		
		while(continueWhile) {
			menuPrompt();
			String userEntry = scnr.next();
			switch (userEntry.toLowerCase()) {
				case "quit":
					continueWhile = false;
					System.out.println("Ending in 3...2..1");
					break;
				case "week":
					getWeek();
					break;
				default:
					getDayTemp(userEntry);
					break;
			}
		}
		scnr.close();
	}
		
	// initial prompt 
	public static void menuPrompt() {
		System.out.println("\nEnter a day of the week such as 'Monday' to see daily average tempature");
		System.out.println("Enter 'week' to see daily average tempatures for the full week");
		System.out.println("Enter 'quit' to exit");
	}
	
	//print daily average 
	public static void getDayTemp(String day) {
		boolean found = false;		
		for (int i = 0; i < dayOfWeek.size(); ++i) {
			if (day.equalsIgnoreCase(dayOfWeek.get(i))) {
				System.out.println("On " + dayOfWeek.get(i) + " it was " + dailyTemp.get(i) + " degrees.");
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Day not found, please enter a full name such as Monday or Sunday");
		}
	}
	
	// print days and weekly average
	public static void getWeek() {
		double sum = 0.0;		
		for (int i = 0; i < dayOfWeek.size(); ++i) {
			System.out.println("On " + dayOfWeek.get(i) + " it was " + dailyTemp.get(i) + " degrees.");
			sum += dailyTemp.get(i);
		}
		System.out.printf("The weekly average tempature was: %.2f degrees\n",  (sum/dayOfWeek.size()));
	}
	
	// prompt user and collect daily average temperatures
	public static void setTemps (ArrayList<String> dayOfWeek, Scanner scnr) {
		int i = 0;
		while (i < dayOfWeek.size()) {
			try {
				System.out.printf("Provide the average temperature for last %s:\n", dayOfWeek.get(i));
				dailyTemp.add(scnr.nextDouble());
				++i;
			} catch (InputMismatchException e) {
				System.out.println("ERROR: Invalid value provided Please proivde the temperature for " + dayOfWeek.get(i));
				scnr.nextLine(); // clear scanner
			}
		}
	}
}
