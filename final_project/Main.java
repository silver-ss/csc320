package portfolioProject;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		Inventory inventory = new Inventory();
		boolean bool = true;
        // populate example inventory
		inventory.addVehicle(new Automobile("Toyota", "Rav4", "Orange", 2020, 18000));
        inventory.addVehicle(new Automobile("Honda", "CRV", "Purple", 2022, 8000));
        inventory.addVehicle(new Automobile("Tesla", "Model S", "Silver", 2024, 500));
        // main menu for user interaction
		while (bool) {
			System.out.println("choose an option:");
			System.out.println(
				  "1. Add a car\n"
				+ "2. List cars\n"
				+ "3. Remove a car\n"
				+ "4. Update car\n"
				+ "5. Quit and Export"
			);
			
			String input = scnr.nextLine(); 
			try {
				int userInt = Integer.parseInt(input);
				switch (userInt) {
					// all methods return a success or failure message so wrapping in print
					case 1:
						System.out.println(inventory.addVehicle(scnr));
						break;
					case 2:
						System.out.println(inventory.listInventory());
						break;
					case 3:
						System.out.println(inventory.removeVehicle(scnr));
						break;
					case 4:
						System.out.println(inventory.updateDetails(scnr));
						break;
					case 5:
						System.out.println(promptExport(scnr, inventory));
						bool = false;
						break;
					default:
						System.out.println("Invalid input provided. Please try again");
				} 
			} catch (NumberFormatException e) {
				System.out.println("ERROR: Invalid input provided. Please try again");
			}
		}
		scnr.close();	// close scanner after while loop to prevent memory leaks
	}
	
	// method to ask if user would like to export inventory to file before quitting. 
	private static String promptExport(Scanner scnr, Inventory inventory) {
		//predefined location for storage of csv export
		String filePath = "C:\\Users\\smsav\\OneDrive\\Desktop\\CSC320\\inventoryExport.csv";
		while (true) { //while loop to allow user to reenter if invalid entry provided
			try {
				System.out.println("Would you like to export current inventory to a CSV file? (Y/N)");
				String response = scnr.nextLine().trim().toUpperCase();
				if (response.equals("Y")) {
					return inventory.exportToCSV(filePath); // Inventory method returns a Success String
				} // exit loop when return hits for file export or no export
				else if (response.equals("N")) {
					return "Okay no file will be exported. Good bye!";
				} 
				else { throw new IllegalArgumentException("Invalid entry. Please enter 'Y' or 'N'");
				}
			} catch (IllegalArgumentException e) {
					return "ERROR: " + e.getMessage();
			}
		}
	}
}