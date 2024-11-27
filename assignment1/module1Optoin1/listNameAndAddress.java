package module1Option1;

public class ListNameAndAddress {

	public static void main(String[] args) {
		// Declare the 5 variables
		String firstName;
		String lastName;
		String streetAddress;
		String city;
		int zipCode;
		
		// Set the values of the variables
		firstName = "Harry";
		lastName = "Potter";
		streetAddress = "1000 Hogwarts Drive";
		city = "Hogwarts";
		zipCode = 12345;
				
		// Print out a table of the readable names of each variable and the values associated
		System.out.printf("%-12s|%-20s|\n", "Field", "Value");
		System.out.println("------------|--------------------|");
		System.out.printf("%-12s|%-20s|\n", "First Name", firstName);
		System.out.printf("%-12s|%-20s|\n", "Last Name", lastName);
		System.out.printf("%-12s|%-20s|\n", "Address", streetAddress);
		System.out.printf("%-12s|%-20s|\n", "City", city);
		System.out.printf("%-12s|%-20s|\n", "Zip Code", zipCode);
	}
}
