package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class TutorialScreenController implements Initializable {

    private static int width = CentralGUIControl.DEFAULT_WIDTH, height = CentralGUIControl.DEFAULT_HEIGHT;
    @FXML public Button backToMainButton;
    @FXML public TextFlow tutorialTextFlow;

    private String tutorialString = "The Pomodoro Technique is a time management technique that splits work into " +
            "units of 25 minutes, called Pomodoros.\n " +
            "We take short breaks (5 minute default) between each Pomodoro.\n " +
            "4 Pomodoros are called a Block.\n " +
            "We take long breaks (15 minute default) between blocks.\n " +
            "The Pomodoro technique is aimed to enhance concentration and productivity, and increase quality of work.";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Text tutorialText = new Text(tutorialString);
        tutorialTextFlow.getChildren().add(tutorialText);
    }

    public void handleBackButtonClick(ActionEvent actionEvent) {
        Main.showStartScreen();
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
