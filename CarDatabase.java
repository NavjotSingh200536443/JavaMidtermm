import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarDatabase {
    // JDBC connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/F22Midterm";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // Method to query the database and return an ArrayList of all Car objects
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM carSales";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int carID = resultSet.getInt("carID");
                int modelYear = resultSet.getInt("modelYear");
                String make = resultSet.getString("make");
                String model = resultSet.getString("model");
                double price = resultSet.getDouble("price");
                LocalDate dateSold = resultSet.getDate("dateSold").toLocalDate();

                Car car = new Car(carID, modelYear, make, model, price, dateSold);
                cars.add(car);
            }
        }

        return cars;
    }

    // Method to query the database and return an ArrayList of integers representing the years a car was sold in
    public List<Integer> getYearsOfSales() throws SQLException {
        List<Integer> years = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "SELECT DISTINCT modelYear FROM carSales";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int year = resultSet.getInt("modelYear");
                years.add(year);
            }
        }

        return years;
    }
}
