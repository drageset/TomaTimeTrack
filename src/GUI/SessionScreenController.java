package GUI;

import Logic.SettingsControl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * GUI Controller class for the session screen
 */
public class SessionScreenController {

    static int width = CentralGUIControl.DEFAULT_WIDTH, height = CentralGUIControl.DEFAULT_HEIGHT;
    public Button backToMainButton;
    public Label timeDisplay;
    public Button pauseButton;
    private boolean countingDown = false;
    private int secondsLeft = 25*60;
    private Timeline timeline;

    private Timeline initializeTimer() {
        timeline = new Timeline();
        timeline.setCycleCount(secondsLeft);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>(){

            /**
             * Invoked when a specific event of the type for which this handler is
             * registered happens.
             *
             * @param event the event which occurred
             */
            @Override
            public void handle(ActionEvent event) {
                secondsLeft--;
                refreshTimeDisplay();
                if (secondsLeft <= 0){
                    timeline.stop();
                    //TODO: Play alarm sound!
                }
            }
        });
        timeline.getKeyFrames().add(frame);
        return timeline;
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

    public void handlePauseButtonClick(ActionEvent actionEvent) {
        if (timeline == null){
            initializeTimer();
        }

        if (countingDown){
            timeline.stop();
            pauseButton.setText("Play");
        } else {
            timeline.play();
            pauseButton.setText("Pause");
        }
        countingDown = !countingDown;
    }

    public void handleTomatoButtonClick(ActionEvent actionEvent) {
        secondsLeft = SettingsControl.getInstance().getPomodoroMinutes() * 60;
        refreshTimeDisplay();
    }

    public void handleShortBreakButtonClick(ActionEvent actionEvent) {
        secondsLeft = SettingsControl.getInstance().getShortBreakMinutes() * 60;
        refreshTimeDisplay();
    }

    public void handleLongBreakButtonClick(ActionEvent actionEvent) {
        secondsLeft = SettingsControl.getInstance().getLongBreakMinutes() * 60;
        refreshTimeDisplay();
    }

    private void refreshTimeDisplay(){
        String minutes = Integer.toString(secondsLeft/60);
        String seconds = Integer.toString(secondsLeft%60);
        if (secondsLeft/60 < 10){
            minutes = "0" + minutes;
        }
        if (secondsLeft%60 < 10){
            seconds = "0" + seconds;
        }
        timeDisplay.setText(minutes + ":" + seconds);
    }
}
