package portfolioProject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Inventory {
	private ArrayList<Automobile> inventory;
	
	// default constructor
	public Inventory() {
		inventory = new ArrayList<>();
		System.out.println("Success!! Inventory initalized");
	}
	
	// validate string values and return the result
	public String getStringAttribute(Scanner scnr, String attribute) {
		String value = "";
		while (true) { // while loop to allow user to re-enter if exception hit.
			try {
				System.out.println("Enter the vehicle " + attribute + ":");
				value = scnr.nextLine();
				if (value.isBlank()) {
					throw new IllegalArgumentException ("ERROR: " + attribute + " cannot be blank");
				}
				return value;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}	
	}
		
	// validate int values and return a string
	public int getIntAttribute(Scanner scnr, String attribute) {
		int value;
		while (true) { //while loop to allow user to re-enter if exception hit.
			try {
				System.out.println("Enter the vehicle " + attribute);
				String input = scnr.nextLine(); 
				value = Integer.parseInt(input);
				if ((attribute.equals("year")) && (value < 1886)) { //year car was invented
					throw new IllegalArgumentException ("ERROR: " + attribute + " must be after 1885");
				}
				else if ((attribute.equals("mileage")) && (value < 0)) { // has to be 0 or positive number
					throw new IllegalArgumentException ("ERROR: " + attribute + " cannot be negative");
				}
				return value;
			} catch (NumberFormatException e) {
	            System.out.println("ERROR: Please enter a valid integer for " + attribute + ".");
			} catch (IllegalArgumentException e) { 
				System.out.println(e.getMessage());
			} 
		}	
	}
	
	// add new vehicle method
	public String addVehicle(Scanner scnr) {
		try {
			//using methods to validate, catch exceptions and allow re-try. 
			String make = getStringAttribute(scnr, "make"); 
			String model = getStringAttribute(scnr, "model");
			String color = getStringAttribute(scnr, "color");
			int year = getIntAttribute(scnr, "year");
			int mileage = getIntAttribute(scnr, "mileage");
			// create new Automobile with validated values then add to inventory array
			inventory.add(new Automobile(make, model, color, year, mileage));
			//success message
			return "Success!! " + color + " " + year + " " + make + " " +
					model + " with " + mileage + " miles added to inventory!";
		} catch (Exception e) {
			//error message
			return "ERROR: " + e.getMessage();
		}
	}
	
	//overloading method to allow initial creation of sample inventory in main
	public String addVehicle(Automobile auto) {
		inventory.add(auto);
		return "Success!! added sample inventory Automobile";
	}
		
	//format list into table when printing
	public String listInventory() {
		int carID = 0; // start with beginning of inventory array
		try { 
			//formatted header for table
			System.out.printf("%-5s | %-10s | %-10s | %-7s | %-4s | %-7s |\n",
					"ID", "Make", "Model", "Color", "Year", "Mileage");
			System.out.printf("----- | ---------- | ---------- | ------- | ---- | ------- |\n");
			//list all objects in inventory in same format as headers with line break after each object
			for (Automobile car: inventory) {
				String [] details = car.listDetails();
				System.out.printf("%-5s | %-10s | %-10s | %-7s | %-4s | %-7s |\n",
						carID, details[0], details[1], details[2], details[3], details[4]);
				++carID; // update to show the ID which is the index in array
			}
			//success message
			return "Success!! Listed inventory";
		} catch (Exception e) {
			//error message
			return "ERROR: " + e.getMessage();
		}
	}
			
	//method to remove a vehicle
	public String removeVehicle(Scanner scnr) {
		while(true) {
			System.out.println("Provide the id of the vehicle you would like to remove:");
			try {
				String input = scnr.nextLine(); 
				int userEntry = Integer.parseInt(input);
				if ((userEntry < 0) || (userEntry >= (inventory.size()))) {
					throw new IllegalArgumentException ("ERROR: Please enter a valid ID between 0 and " + (inventory.size() -1));
				} 
				// removing the object from inventory array removes all references and makes eligible for garbage collection. 
				inventory.remove(userEntry);
				String success = "Success!! vehicle with id " + userEntry + " has been removed!";
				return success; // exit while loop when removal successful
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}
			
	// helper for updateDetails to validate the provided ID
	private int validateID(Scanner scnr) {
		while (true) {
			System.out.println("Enter the ID for the vehicle you would like to update:");
			try {
				String input = scnr.nextLine(); 
				int vehicleId = Integer.parseInt(input);
				if ((vehicleId < 0) || (vehicleId >= (inventory.size()))) {
					throw new IllegalArgumentException ("ERROR: Please enter a valid ID between 0 and " + (inventory.size() -1));
				} 
				return vehicleId; // exit while loop when valid ID provided				
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	// helper for updateDetails to get a valid detail to be updated
	private String validateDetail(Scanner scnr) {
		String detail;
		while (true) {
			try {
				System.out.println("Which detail would you like to update?");
				System.out.println("Enter one of these: make, model, color, year, mileage");
				detail = scnr.nextLine().toLowerCase();
				if (!detail.matches("make|model|color|year|mileage")) {
					throw new IllegalArgumentException ("ERROR: invalid detail provided, try again");
				}
				return detail; // exit while loop when valid name provided
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} 
		}
	}
	
 	//method to update vehicle attributes
	public String updateDetails(Scanner scnr) {
		int vehicleId = validateID(scnr); // helper method above
		String detail = validateDetail(scnr); // helper method above
		Automobile car = inventory.get(vehicleId);
		String success = "Success!! updated " + detail + " for vehicle with ID " + vehicleId;
		String updateString;
		int updateInt;
		try {
			switch (detail) {
	        	case "make":
	        		updateString = getStringAttribute(scnr, "make");
	        		car.setMake(updateString);
	        		return success + " to " + updateString;
	        	case "model":
	        		updateString = getStringAttribute(scnr, "model");
	        		car.setModel(updateString);
	        		return success + " to " + updateString;
	        	case "color":
	        		updateString = getStringAttribute(scnr, "color");
	        		car.setColor(updateString);
	        		return success + " to " + updateString;
	        	case "year":
	        		updateInt = getIntAttribute(scnr, "year");
	        		car.setYear(updateInt);
	        		return success + " to " + updateInt;
	        	case "mileage":
	        		updateInt = getIntAttribute(scnr, "mileage");
	        		car.setMileage(updateInt);
	        		return success + " to " + updateInt;
	        	default:
	        		return "Error: Invalid detail provided.";
			}
		} catch (Exception e) {
			 return "ERROR: " + e.getMessage();
		}
	}
	
	// method to export to file
	public String exportToCSV(String filePath) {
		// try to set up FileWriter for predefined file path
		try (FileWriter writer = new FileWriter(filePath)) {
			// white header for csv
			writer.write("Make,Model,Color,Year,Mileage\n");
			// loop through object in inventory array
			for (Automobile car : inventory) {	
				String [] details = car.listDetails();
				StringJoiner joiner = new StringJoiner(",");
				// loop through details for each object
				for (String detail : details) {
					// join each detail separated by a comma
					joiner.add(detail);
				}
				// write object as comma separated string to csv, move to next line.
				writer.write(joiner.toString() + "\n");
			}
			//success message if no exceptions hit.
			return "Inventory successfully written to CSV file at: " + filePath + " Good Bye!";
		} catch (IOException e) {
			//error message if exception hit
			return "ERROR: " + e.getMessage();
		}
	}
}