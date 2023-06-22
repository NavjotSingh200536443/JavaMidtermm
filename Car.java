import java.time.LocalDate;

public class Car {
    // Car attributes
    private int carID;
    private int modelYear;
    private String make;
    private String model;
    private double price;
    private LocalDate dateSold;

    // Constructor
    public Car(int carID, int modelYear, String make, String model, double price, LocalDate dateSold) {
        this.carID = carID;
        this.modelYear = modelYear;
        this.make = make;
        this.model = model;
        this.price = price;
        this.dateSold = dateSold;
    }

    // Getters and Setters
    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateSold() {
        return dateSold;
    }

    public void setDateSold(LocalDate dateSold) {
        this.dateSold = dateSold;
    }

    // Validate the car attributes
    public void validate() throws Exception {
        // Validation rule: Car ID should be greater than 0
        if (carID <= 0) {
            throw new Exception("Car ID must be greater than 0. Please change the car ID.");
        }

        // Validation rule: Make should be "Acura", "Ford", "Honda", "Nissan" or "Tesla"
        if (!make.equals("Acura") && !make.equals("Ford") && !make.equals("Honda") && !make.equals("Nissan") && !make.equals("Tesla")) {
            throw new Exception("Invalid car make. Valid options are: Acura, Ford, Honda, Nissan, Tesla. Please change the make.");
        }

        // Validation rule: Model should be at least 2 characters long
        if (model.length() < 2) {
            throw new Exception("Car model must be at least 2 characters long. Please change the model.");
        }

        // Validation rule: The price should be greater than 0
        if (price <= 0) {
            throw new Exception("Car price must be greater than 0. Please change the price.");
        }

        // Validation rule: The date sold should be no later than today
        if (dateSold.isAfter(LocalDate.now())) {
            throw new Exception("Invalid sale date. The sale date cannot be in the future. Please change the date sold.");
        }
    }
}
