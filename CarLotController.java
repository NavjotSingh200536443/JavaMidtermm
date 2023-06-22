import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

public class CarLotController implements Initializable {
    @FXML
    private TableView<Car> carTableView;

    @FXML
    private ComboBox<Integer> yearComboBox;

    @FXML
    private Label totalCarsLabel;

    @FXML
    private Label totalSalesLabel;

    @FXML
    private ImageView iconImageView;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis manufacturerAxis;

    @FXML
    private NumberAxis countAxis;

    private CarDatabase carDatabase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carDatabase = new CarDatabase();

        // Populate TableView with Car objects from the database
        try {
            List<Car> cars = carDatabase.getAllCars();
            carTableView.getItems().addAll(cars);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Populate ComboBox with years from the database
        try {
            List<Integer> years = carDatabase.getYearsOfSales();
            yearComboBox.getItems().addAll(years);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update labels with total number of cars sold and total sales
        int totalCars = carTableView.getItems().size();
        double totalSales = carTableView.getItems().stream().mapToDouble(Car::getPrice).sum();

        totalCarsLabel.setText(Integer.toString(totalCars));
        totalSalesLabel.setText(NumberFormat.getCurrencyInstance().format(totalSales));

        // Set application icon and title
        Image iconImage = new Image(getClass().getResourceAsStream("C:/Users/Jagermeister/Desktop/car.png"));
        iconImageView.setImage(iconImage);

        // Set chart data for number of cars sold by manufacturer
        updateBarChart();
    }

    private void updateBarChart() {
        barChart.getData().clear();

        try {
            List<Car> cars = carDatabase.getAllCars();

            int acuraCount = 0;
            int fordCount = 0;
            int hondaCount = 0;
            int nissanCount = 0;
            int teslaCount = 0;

            for (Car car : cars) {
                String make = car.getMake();

                if (make.equals("Acura")) {
                    acuraCount++;
                } else if (make.equals("Ford")) {
                    fordCount++;
                } else if (make.equals("Honda")) {
                    hondaCount++;
                } else if (make.equals("Nissan")) {
                    nissanCount++;
                } else if (make.equals("Tesla")) {
                    teslaCount++;
                }
            }

            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("Acura", acuraCount));
            series.getData().add(new XYChart.Data<>("Ford", fordCount));
            series.getData().add(new XYChart.Data<>("Honda", hondaCount));
            series.getData().add(new XYChart.Data<>("Nissan", nissanCount));
            series.getData().add(new XYChart.Data<>("Tesla", teslaCount));

            barChart.getData().add(series);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
