package Data;

public class Settings {

    private int tomatoMinutes;
    private int shortBreakMinutes;
    private int longBreakMinutes;
    private boolean alwaysInFront;
    private boolean shareAnonData;

    public Settings(int tomatoMinutes, int shortBreakMinutes, int longBreakMinutes, boolean alwaysInFront, boolean shareAnonData) {
        this.tomatoMinutes = tomatoMinutes;
        this.shortBreakMinutes = shortBreakMinutes;
        this.longBreakMinutes = longBreakMinutes;
        this.alwaysInFront = alwaysInFront;
        this.shareAnonData = shareAnonData;
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

    public boolean isAlwaysInFront() {
        return alwaysInFront;
    }

    public void setAlwaysInFront(boolean alwaysInFront) {
        this.alwaysInFront = alwaysInFront;
    }

    public boolean isShareAnonData() {
        return shareAnonData;
    }

    public void setShareAnonData(boolean shareAnonData) {
        this.shareAnonData = shareAnonData;
    }
}
