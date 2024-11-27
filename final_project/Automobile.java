package portfolioProject;

public class Automobile {
//class attributes
	private String make;
	private String model;
	private String color;
	private int year;
	private int mileage;
	
//default constructor
	public Automobile() {
		make = "Unknown";
		model = "Unknown";
		color = "Unknown";
		year = 0;
		mileage = 0;
		System.out.println("Succes!! Automobile initalized with default constructor");
	}
	
// parameterized constructor
	public Automobile(String make, String model, String color, int year, int mileage) {
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		this.mileage = mileage;
		System.out.println("Success!! Automobile initalized with parameterized constuctor");
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

    // list details in string array
	public String [] listDetails() {
		String [] vehicleInfo = new String[5];
		// populate array
		vehicleInfo[0] = make;
		vehicleInfo[1] = model;
		vehicleInfo[2] = color;
		vehicleInfo[3] = String.valueOf(year);
		vehicleInfo[4] = String.valueOf(mileage);
		return vehicleInfo;
	}
}