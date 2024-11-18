package portfolioProject;

import java.util.*;

public class Automobile {
	//class attributes
	private String make;
	private String model;
	private String color;
	private int year;
	private int mileage;
	
	//default constructor
	public Automobile() {
		this.make = "Unknown";
		this.model = "Unknown";
		this.color = "Unknown";
		this.year = 0;
		this.mileage = 0;
	}
	
	// parameterized constructor
	public Automobile(String make, String model, String color, int year, int mileage) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.mileage = mileage;
	}
	
	// setters
    public void setMake(String make) { this.make = make; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }
    public void setYear(int year) { this.year = year; }
    public void setMileage(int mileage) { this.mileage = mileage; }
    // getters 
    public String getMake() { return make; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    
    //validate string values and return the result
	public static String getStringAttribute(Scanner scnr, String attribute) {
		String value = "";
		while (true) {
			try {
				System.out.println("Enter the vehicle " + attribute + ":");
				value = scnr.nextLine();
				if (value.isBlank()) {
					throw new IllegalArgumentException ("ERROR: " + attribute + " cannot be blank");
				}
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return value;
	}
	
	// validate int values and return a string
	public static int getIntAttribute(Scanner scnr, String attribute) {
		int value;
		while (true) {
			try {
				System.out.println("Enter the vehicle " + attribute);
				value = scnr.nextInt();
				if ((attribute.equals("year")) && (value < 1886)) {
					throw new IllegalArgumentException ("ERROR: " + attribute + " must be after 1885");
				}
				else if ((attribute.equals("mileage")) && (value < 0)) {
					throw new IllegalArgumentException ("ERROR: " + attribute + " cannot be negative");
				}
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		return value;	
	}
	
	// add new vehicle method
	public static void addVehicle(Scanner scnr, ArrayList<Object> inventory) {
		String make; 
		String model;
		String color;
		int year;
		int mileage;
		// add to inventory array
		inventory.add(new Automobile(
			make = getStringAttribute(scnr, "make"), 
			model = getStringAttribute(scnr, "model"),
			color = getStringAttribute(scnr, "color"),
			year = getIntAttribute(scnr, "year"),
			mileage = getIntAttribute(scnr, "mileage")));
		//success message
		System.out.println("Success!! " + color + " " + year + " " + make + " " +
				model + " with " + mileage + " miles added to inventory!");
	}
	
	//method to list inventory
	public static String [][] listVehicles(ArrayList<Object> inventory) {
		String [][] vehicleInfo = new String[inventory.size()][6];
		for (int i = 0; i < inventory.size(); ++i) {
			Automobile car = (Automobile) inventory.get(i);
			vehicleInfo[i][0] = String.valueOf(i);
			vehicleInfo[i][1] = car.getMake();
			vehicleInfo[i][2] = car.getModel();
			vehicleInfo[i][3] = car.getColor();
			vehicleInfo[i][4] = String.valueOf(car.getYear());
			vehicleInfo[i][5] = String.valueOf(car.getMileage());
		}
		return vehicleInfo;
	}
	
	//format list into table when printing
	public static void formatList(String [][] info) {
		System.out.printf("%-5s | %-10s | %-10s | %-7s | %-4s | %-7s |\n",
	            "ID", "Make", "Model", "Color", "Year", "Mileage");
		System.out.printf("----- | ---------- | ---------- | ------- | ---- | ------- |\n");
		for (int i = 0; i < info.length; ++i) {
	        System.out.printf("%-5s | %-10s | %-10s | %-7s | %-4s | %-7s |\n",
	                info[i][0], info[i][1], info[i][2], info[i][3], info[i][4], info[i][5]);
		}
	}
	
	//method to remove a vehicle
	public static void removeVehicle(Scanner scnr, ArrayList<Object> inventory) {
		int userEntry;
		while(true) {
			System.out.println("Provide the id of the vehicle you would like to remove:");
			try {
				userEntry = scnr.nextInt();
				if ((userEntry < 0) || (userEntry >= (inventory.size()))) {
					throw new IllegalArgumentException ("ERROR: Please enter a valid ID between 0 and " + (inventory.size() -1));
				} 
				break;
			} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
			}
		}
		inventory.remove(userEntry);
		System.out.println("Success!! vehicle with id " + userEntry + " has been removed!");
	}
	
	//method to update vehicle attributes
	public static void updateDetails(Scanner scnr, ArrayList<Object> inventory) {
		int vehicleId;
		String detail;
		String stringValue = "";
		int intValue = -1;
		while(true) {
			System.out.println("Enter the ID for the vehicle you would like to update:");
			try {
				vehicleId = scnr.nextInt();
				scnr.nextLine(); // clear scanner
				if ((vehicleId < 0) || (vehicleId >= (inventory.size()))) {
					throw new IllegalArgumentException ("ERROR: Please enter a valid ID between 0 and " + (inventory.size() -1));
				} 
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			try {
				System.out.println("Which detail would you like to update?");
				System.out.println("Enter one of these: make, model, color, year, mileage");
				detail = scnr.nextLine();
				switch (detail) {
					case "make", "model", "color":
						stringValue = getStringAttribute(scnr, detail);
						break;
					case "year", "mileage":
						intValue = getIntAttribute(scnr, detail);
						break;
					default:
						throw new IllegalArgumentException("ERROR: Invalid detail choice provided");
				}
				break; // exit while loop if no exception hit
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			} 
		}
		Automobile car = (Automobile) inventory.get(vehicleId);
		switch (detail.toLowerCase()) {
        case "make":
            car.setMake(stringValue);
            break;
        case "model":
            car.setModel(stringValue);
            break;
        case "color":
            car.setColor(stringValue);
            break;
        case "year":
            car.setYear(intValue);
            break;
        case "mileage":
            car.setMileage(intValue);
            break;
		}
	}
		
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		ArrayList<Object> inventory = new ArrayList<>();
        boolean bool = true;
        // populate example inventory
		inventory.add(new Automobile("Toyota", "Rav4", "Orange", 2020, 18000));
        inventory.add(new Automobile("Honda", "CRV", "Purple", 2022, 8000));
        inventory.add(new Automobile("Tesla", "Model S", "Silver", 2024, 500));
        
		while (bool) {
			System.out.println("choose an option:");
			System.out.println(
				  "1. add a car\n"
				+ "2. list cars\n"
				+ "3. remove a car\n"
				+ "4. update car\n"
				+ "5. quit");
			int userInt = scnr.nextInt();
			scnr.nextLine(); //clear scanner
			switch (userInt) {
				case 1:
					addVehicle(scnr, inventory);
					break;
				case 2:
					formatList(listVehicles(inventory));
					break;
				case 3:
					removeVehicle(scnr, inventory);
					break;
				case 4:
					updateDetails(scnr, inventory);
					break;
				default:
					bool = false;
			}
		}
	}
}