package Logic;

public class SettingsControl {

    private int pomodoroMinutes = 25;
    private int shortBreakMinutes = 5;
    private int longBreakMinutes = 15;
    private boolean alwaysTop = true;
    private boolean shareDate = true;

    private static SettingsControl ourInstance = new SettingsControl();

    public static SettingsControl getInstance() {
        return ourInstance;
    }

    private SettingsControl() {
        //Get stored settings from the database
    }

    public int getShortBreakMinutes() {
        return shortBreakMinutes;
    }

    public int getLongBreakMinutes() {
        return longBreakMinutes;
    }

    public int getPomodoroMinutes() {
        return pomodoroMinutes;
    }
}
