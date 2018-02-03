package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * The GUI controller for the start screen
 */
public class StartScreenController {

    private static int width = CentralGUIControl.DEFAULT_WIDTH, height = CentralGUIControl.DEFAULT_HEIGHT;
    public Label gameTitleLabel;
    public Button newGameButton;
    public Button settingsButton;
    public Button tutorialButton;
    public Button quitGameButton;

    public void handleNewSessionButtonClick(ActionEvent actionEvent) {
        System.out.println("StartMenu New Session button clicked!");
        Main.showSession();
    }

    public void handleQuitGameButtonClick(ActionEvent actionEvent) {
        System.out.println("StartMenu Quit button clicked!");
        Main.getWindow().close();
    }

    public void handleSettingsButtonClick(ActionEvent actionEvent) {
        System.out.println("StartMenu Settings button clicked!");
        Main.showSettings();
    }

    public void handleTutorialButtonClick(ActionEvent actionEvent) {
        System.out.println("StartMenu Settings button clicked!");
        Main.showTutorial();
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

}
