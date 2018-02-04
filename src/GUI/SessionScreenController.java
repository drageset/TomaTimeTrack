package GUI;

import Data.DataManager;
import Data.Work;
import Logic.SettingsControl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

/**
 * GUI Controller class for the session screen
 */
public class SessionScreenController {

    static int width = CentralGUIControl.DEFAULT_WIDTH, height = CentralGUIControl.DEFAULT_HEIGHT;
    public Button backToMainButton;
    public Label timeDisplay;
    public Button pauseButton;
    public ProgressBar progBar;
    public Button resetButton;
    private boolean countingDown = false;
    private int maxSeconds = SettingsControl.getInstance().getPomodoroMinutes()*60;
    private int secondsLeft = maxSeconds;
    private boolean pomodoroMode = true;
    private int pomodoroCounter = 0;
    private Timeline timeline;

    public void refreshMaxseconds(){
        maxSeconds = SettingsControl.getInstance().getPomodoroMinutes()*60;
        refreshTimeDisplay();
    }

    @FXML
    public void initialize() {
        maxSeconds = SettingsControl.getInstance().getPomodoroMinutes()*60;
        initializeTimer();
        refreshTimeDisplay();
    }

    private Timeline initializeTimer() {
        progBar.progressProperty().setValue((double)secondsLeft/(double)maxSeconds);
        if (timeline != null){
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), new EventHandler<ActionEvent>(){

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
                    if (pomodoroMode){
                        pomodoroCounter++;
                    }
                }
            }
        });
        timeline.getKeyFrames().add(frame);
        return timeline;
    }

    public void handleBackButtonClick(ActionEvent actionEvent) {
        //TODO: Save work done this session to database
        //TODO: Reset/null session information
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
        maxSeconds = SettingsControl.getInstance().getPomodoroMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = true;
    }

    public void handleShortBreakButtonClick(ActionEvent actionEvent) {
        maxSeconds = SettingsControl.getInstance().getShortBreakMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = false;
    }

    public void handleLongBreakButtonClick(ActionEvent actionEvent) {
        maxSeconds = SettingsControl.getInstance().getLongBreakMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = false;
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
        progBar.progressProperty().setValue((double)secondsLeft/(double)maxSeconds);
    }

    public void handleResetButtonClick(ActionEvent actionEvent) {
        System.out.println("Reset Button clicked!");
        //TODO: Save work done this session to database
        if (pomodoroMode){
            System.out.println("Attempting to add "+ (maxSeconds-secondsLeft)/60 +" minutes of work to the database");
            DataManager.addWork(new Work((maxSeconds-secondsLeft)/60,pomodoroCounter,pomodoroCounter/4));
        }

        resetSession();
    }

    void resetSession(){
        //TODO: Reset/null runtime session information
        pomodoroCounter = 0;
        countingDown = false;
        timeline.stop();
        maxSeconds = SettingsControl.getInstance().getPomodoroMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = true;
        pauseButton.setText("Play");
        initializeTimer();
        refreshTimeDisplay();

        //TODO: Reset session information in database
    }
}
