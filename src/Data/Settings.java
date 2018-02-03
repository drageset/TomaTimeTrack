package Data;

public class Settings {

    // DEFAULT VALUES FOR SETTINGS
    public static final int TOMATO_MINUTES = 25;
    public static final int SHORT_BREAK_MINUTES = 5;
    public static final int LONG_BREAK_MINUTES = 15;
    public static final boolean ALWAYS_TOP = true;
    public static final boolean SHARE_DATA = true;


    private int tomatoMinutes;
    private int shortBreakMinutes;
    private int longBreakMinutes;
    private boolean alwaysTop;
    private boolean shareAnonData;

    public Settings(int tomatoMinutes, int shortBreakMinutes, int longBreakMinutes, boolean alwaysTop, boolean shareAnonData) {
        this.tomatoMinutes = tomatoMinutes;
        this.shortBreakMinutes = shortBreakMinutes;
        this.longBreakMinutes = longBreakMinutes;
        this.alwaysTop = alwaysTop;
        this.shareAnonData = shareAnonData;
    }

    public Settings(){
        tomatoMinutes = TOMATO_MINUTES;
        shortBreakMinutes = SHORT_BREAK_MINUTES;
        longBreakMinutes = LONG_BREAK_MINUTES;
        alwaysTop = ALWAYS_TOP;
        shareAnonData = SHARE_DATA;

    }

    public int getTomatoMinutes() {
        return tomatoMinutes;
    }

    public void setTomatoMinutes(int tomatoMinutes) {
        this.tomatoMinutes = tomatoMinutes;
    }

    public int getShortBreakMinutes() {
        return shortBreakMinutes;
    }

    public void setShortBreakMinutes(int shortBreakMinutes) {
        this.shortBreakMinutes = shortBreakMinutes;
    }

    public int getLongBreakMinutes() {
        return longBreakMinutes;
    }

    public void setLongBreakMinutes(int longBreakMinutes) {
        this.longBreakMinutes = longBreakMinutes;
    }

    public boolean isAlwaysTop() {
        return alwaysTop;
    }

    public void setAlwaysTop(boolean alwaysTop) {
        this.alwaysTop = alwaysTop;
    }

    public boolean isShareAnonData() {
        return shareAnonData;
    }

    public void setShareAnonData(boolean shareAnonData) {
        this.shareAnonData = shareAnonData;
    }
}
