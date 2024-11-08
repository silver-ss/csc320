package cta5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class weeklyTemps {	
	private static ArrayList<String> dayOfWeek = new ArrayList<>(Arrays.asList(
			"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",	"Saturday", "Sunday"));
	private static ArrayList<Double> dailyTemp = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Provide the past 7 average daily tempatures seperated by spaces");
		
		
		while (dailyTemp.size() < 7) {
			try {
				dailyTemp.add(scnr.nextDouble());
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number");
				scnr.next(); // clear invalid inputs before new input
			}
		}
				
		boolean continueWhile = true;
		while(continueWhile) {
			menuPrompt();
			String userEntry = scnr.next();
		
			switch (userEntry.toLowerCase()) {
				case "quit":
					continueWhile = false;
					System.out.println("Ending in 3...2..1");
					break;
				case "week":
					getWeek(userEntry);
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
		System.out.println("Enter a day of the week such as 'Monday' to see daily average tempature");
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
	public static void getWeek(String week) {
		double sum = 0.0;		
		for (int i = 0; i < dayOfWeek.size(); ++i) {
			System.out.println("On " + dayOfWeek.get(i) + " it was " + dailyTemp.get(i) + " degrees.");
			sum += dailyTemp.get(i);
		}
		System.out.println("The weekly average tempature was: " + (sum/dayOfWeek.size()) + " degrees" );
	}
}