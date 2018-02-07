package GUI;

import Data.DataManager;
import Logic.SettingsControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage window;
    private static Scene startScreen, sessionScreen, tutorialScreen, settingsScreen, dataScreen;



    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        DataManager.createTables();
        SettingsControl.getInstance().setSettings(DataManager.getSettings());

        window.setTitle("T3");
        window.setAlwaysOnTop(SettingsControl.getInstance().isAlwaysTop());
        window.setResizable(false);
        makeScreens();
        showStartScreen();
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void makeScreens(){
        try {
            startScreen = makeStartScreen();
            sessionScreen = makeSessionScreen();
            tutorialScreen = makeTutorialScreen();
            settingsScreen = makeSettingsScreen();
            dataScreen = makeDataScreen();
        } catch (IOException e){
            System.out.println("Main: Failed to make screens!");
            e.printStackTrace();
        }
    }

    private Scene makeDataScreen() throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("datavisuals.fxml"));
        return new Scene(root, DataVisualsController.getWidth(), DataVisualsController.getHeight());
    }

    private Scene makeSettingsScreen() throws IOException {
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
        Scene scene = new Scene(root, StartScreenController.getWidth(), StartScreenController.getHeight());
        scene.getStylesheets().add(String.valueOf(this.getClass().getResource("material.css")));
        return scene;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void showSettings() {
        System.out.println("Setting the scene to be the settings screen");
        window.setScene(getSettingsScreen());
    }

    public static void showTutorial() {
        window.setScene(getTutorialScreen());
    }

    public static void showSession(){
        System.out.println("Setting the scene to be the session screen");
        window.setScene(getSessionScreen());
    }

    public static void showStartScreen(){
        System.out.println("Setting the scene to be the start screen");
        window.setScene(getStartScreen());
    }

    static Scene getSettingsScreen() {
        return settingsScreen;
    }

    static Scene getTutorialScreen() {
        return tutorialScreen;
    }

    static Scene getSessionScreen() {
        return sessionScreen;
    }

    static Scene getStartScreen() {
        return startScreen;
    }

    static Scene getDataScreen() {
        return dataScreen;
    }

    public static void showData() {
        window.setScene(getDataScreen());
    }
}
