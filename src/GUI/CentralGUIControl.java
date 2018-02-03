package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CentralGUIControl {


    static int DEFAULT_WIDTH = 290, DEFAULT_HEIGHT = 200;


    /*

    private static CentralGUIControl ourInstance = new CentralGUIControl();

    Stage window;
    Scene startScreen, sessionScreen, tutorialScreen, settingsMenu;
    private Scene settingsScreen;

    public static CentralGUIControl getInstance() {
        return ourInstance;
    }

    private CentralGUIControl() {
        setWindow(Main.getWindow());
        if (window != null){
            System.out.println("Main window initialized, making screens");
            makeScreens();
        } else {
            System.err.println("main window not initialized, screens were not made");
            System.out.println("main window not initialized");
        }
    }

    private void makeScreens(){
        try {
            startScreen = makeStartScreen();
            sessionScreen = makeSessionScreen();
            tutorialScreen = makeTutorialScreen();
            settingsMenu = makeSettingsMenu();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private Scene makeSettingsMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        return new Scene(root, SettingsScreenController.getWidth(), SettingsScreenController.getHeight());
    }

    private Scene makeTutorialScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tutorial.fxml"));
        return new Scene(root, TutorialScreenController.getWidth(), TutorialScreenController.getHeight());
    }

    private Scene makeSessionScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("session.fxml"));
        return new Scene(root, SessionScreenController.getWidth(), SessionScreenController.getHeight());
    }

    private Scene makeStartScreen() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        return new Scene(root, StartScreenController.getWidth(), StartScreenController.getHeight());
    }

    public Stage getWindow() {
        return window;
    }

    void setWindow(Stage window) {
        this.window = window;
    }

    public void showSettings() {
        System.out.println("Setting the scene to be the settings screen");
        Main.getWindow().setScene(getSettingsScreen());
    }

    public void showTutorial() {
        Main.getWindow().setScene(getTutorialScreen());
    }

    public void showSession(){
        System.out.println("Setting the scene to be the session screen");
        Main.getWindow().setScene(getSessionScreen());
    }

    public void showStartScreen(){
        System.out.println("Setting the scene to be the start screen");
        Main.getWindow().setScene(getStartScreen());
    }

    Scene getSettingsScreen() {
        if (settingsScreen == null){
            try {
                settingsScreen = makeSettingsMenu();
            } catch (IOException e) {
                System.out.println("Failed to make settings menu scene");
                e.printStackTrace();
            }
        }
        return settingsScreen;
    }

    Scene getTutorialScreen() {
        if (tutorialScreen == null){
            try {
                tutorialScreen = makeTutorialScreen();
            } catch (IOException e) {
                System.out.println("Failed to make tutorial menu screen");
                e.printStackTrace();
            }
        }
        return tutorialScreen;
    }

    Scene getSessionScreen() {
        if (sessionScreen == null){
            try {
                sessionScreen = makeSessionScreen();
            } catch (IOException e) {
                System.out.println("Failed to make session menu screen");
                e.printStackTrace();
            }
        }
        return sessionScreen;
    }

    Scene getStartScreen() {
        if (startScreen == null){
            try {
                startScreen = makeStartScreen();
            } catch (IOException e) {
                System.out.println("Failed to make session menu screen");
                e.printStackTrace();
            }
        }
        return startScreen;
    }

    */
}
