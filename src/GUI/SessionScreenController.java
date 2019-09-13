package GUI;

import Data.DataManager;
import Data.Work;
import Logic.SettingsControl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.awt.*;
import java.util.concurrent.TimeUnit;

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
        maxSeconds = DataManager.getInstance().getSettings().getTomatoMinutes()*60;
        refreshTimeDisplay();
    }

    @FXML
    public void initialize() {
        maxSeconds = SettingsControl.getInstance().getPomodoroMinutes()*60;
        initializeTimer();
        refreshTimeDisplay();
        maxSeconds = DataManager.getInstance().getSettings().getTomatoMinutes()*60;
        secondsLeft = maxSeconds;
    }

    private Timeline initializeTimer() {
        progBar.progressProperty().setValue((double)secondsLeft/(double)maxSeconds);
        if (timeline != null){
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
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
                    alarm();
                    if (pomodoroMode){
                        pomodoroCounter++;
                        timeline.getCurrentTime().toSeconds();
                        int minutes = (maxSeconds-secondsLeft)/60;
                        System.out.println("Attempting to add "+ minutes +" minutes of work to the database");
                        int blocks = 0;
                        if (pomodoroCounter/4 > 1) {
                            blocks = pomodoroCounter / 4;
                            pomodoroCounter = pomodoroCounter%4;
                        }
                        DataManager.addWork(new Work(minutes, 1, blocks));
                    }
                    secondsLeft = maxSeconds;
                }
            }
        });
        timeline.getKeyFrames().add(frame);
        return timeline;
    }

    /**
     * Make a sound notification to alert to user to the fact that the time has run out
     */
    private void alarm(){
        Toolkit.getDefaultToolkit().beep();
        /*
        try {

            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);

            Toolkit.getDefaultToolkit().beep();
            TimeUnit.SECONDS.sleep(1);

            Toolkit.getDefaultToolkit().beep();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
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
            pauseButton.setText("Go!");
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
        timeline.play();
        countingDown = true;
        pauseButton.setText("Pause");
    }

    public void handleShortBreakButtonClick(ActionEvent actionEvent) {
        maxSeconds = SettingsControl.getInstance().getShortBreakMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = false;
        timeline.play();
        countingDown = true;
        pauseButton.setText("Pause");
    }

    public void handleLongBreakButtonClick(ActionEvent actionEvent) {
        maxSeconds = SettingsControl.getInstance().getLongBreakMinutes() * 60;
        secondsLeft = maxSeconds;
        refreshTimeDisplay();
        pomodoroMode = false;
        timeline.play();
        countingDown = true;
        pauseButton.setText("Pause");
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
        if (pomodoroMode){
            int minutes = (maxSeconds-secondsLeft)/60;
            System.out.println("Attempting to add "+ minutes +" minutes of work to the database");
            DataManager.addWork(new Work(minutes, 0,0));
        }
        resetSession();
    }

    void resetSession(){
        //TODO: Reset/null runtime session information
        pomodoroCounter = 0;
        countingDown = false;
        timeline.stop();
        refreshMaxseconds();
        secondsLeft = maxSeconds;
        pomodoroMode = true;
        pauseButton.setText("Go!");
        initializeTimer();
        refreshTimeDisplay();

        //TODO: Reset session information in database
    }
}
