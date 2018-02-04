package GUI;

import Data.DataManager;
import Data.Settings;
import Logic.SettingsControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SettingsScreenController {

    private static int width = CentralGUIControl.DEFAULT_WIDTH, height = CentralGUIControl.DEFAULT_HEIGHT;
    public Button backToMainButton;
    public CheckBox onTopCheckBox;
    public CheckBox shareDataCheckBox;
    public TextField tomatoTimeField;
    public TextField shortTimeField;
    public TextField longTimeField;

    @FXML
    public void initialize() {
        SettingsControl settingsControl = SettingsControl.getInstance();
        onTopCheckBox.setSelected(settingsControl.isAlwaysTop());
        shareDataCheckBox.setSelected(settingsControl.isShareData());
        tomatoTimeField.setText(Integer.toString(settingsControl.getPomodoroMinutes()));
        shortTimeField.setText(Integer.toString(settingsControl.getShortBreakMinutes()));
        longTimeField.setText(Integer.toString(settingsControl.getLongBreakMinutes()));
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        SessionScreenController.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        SessionScreenController.height = height;
    }

    public void handleBackButtonClick(ActionEvent actionEvent) {
        Main.showStartScreen();
    }

    public void handleSaveSettingsButtonClick(ActionEvent actionEvent) {
        boolean onTop = onTopCheckBox.isSelected();
        boolean shareData = shareDataCheckBox.isSelected();
        String tomato = tomatoTimeField.getText();
        String shortBreak = shortTimeField.getText();
        String longBreak = longTimeField.getText();

        int tomatoTime = Integer.parseInt(tomato);
        int shortBreakTime = Integer.parseInt(shortBreak);
        int longBreakTime = Integer.parseInt(longBreak);

        Settings settings = new Settings(tomatoTime,shortBreakTime,longBreakTime,onTop,shareData);

        SettingsControl.getInstance().setSettings(settings);

    }
}
