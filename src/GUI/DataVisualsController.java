package GUI;

import Logic.SettingsControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;

public class DataVisualsController {

    public BarChart yearChart;
    public BarChart monthChart;
    public BarChart weekChart;
    public AreaChart dayChart;
    private static double width = 400;
    private static double height = 300;

    @FXML
    public void initialize() {
        //TODO: Put data in the charts

    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public void handleBackToMainButtonClick(ActionEvent actionEvent) {
        Main.showStartScreen();
    }
}
