package module1Optoin1;

public class listNameAndAddress {
	
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
		System.out.println("Field		|Value");
		System.out.println("----------------------");
		System.out.println("First Name:	|" + firstName);
		System.out.println("Last Name: 	|" + lastName);
		System.out.println("Address: 	|" + streetAddress);
		System.out.println("City: 		|" + city);
		System.out.println("Zip Code: 	|" + firstName);
	}

}
