package Logic;

import Data.DataManager;
import Data.Settings;

public class SettingsControl {

    private int pomodoroMinutes = Settings.TOMATO_MINUTES;
    private int shortBreakMinutes = Settings.SHORT_BREAK_MINUTES;
    private int longBreakMinutes = Settings.LONG_BREAK_MINUTES;
    private boolean alwaysTop = Settings.ALWAYS_TOP;
    private boolean shareData = Settings.SHARE_DATA;
    private Settings settings = new Settings();

    private static SettingsControl ourInstance = new SettingsControl();

    public static SettingsControl getInstance() {
        return ourInstance;
    }

    private SettingsControl() {
        //Get stored settings from the database
        Settings tempSetting = DataManager.getSettings();

        if (tempSetting != null){
            settings = DataManager.getSettings();
        } else { //If no settings are stored, store the default settings
            DataManager.setSettings(settings);
        }
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

    public boolean isAlwaysTop() {
        return alwaysTop;
    }

    public void setAlwaysTop(boolean alwaysTop) {
        this.alwaysTop = alwaysTop;
    }

    public boolean isShareData() {
        return shareData;
    }

    public void setShareData(boolean shareData) {
        this.shareData = shareData;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
        DataManager.setSettings(settings);
    }
}
