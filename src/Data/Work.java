package Data;

public class Work {

    private int minutes, pomodoros, blocks;

    public Work(int minutes, int pomodoros, int blocks) {
        this.minutes = minutes;
        this.pomodoros = pomodoros;
        this.blocks = blocks;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPomodoros() {
        return pomodoros;
    }

    public void setPomodoros(int pomodoros) {
        this.pomodoros = pomodoros;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }
}
