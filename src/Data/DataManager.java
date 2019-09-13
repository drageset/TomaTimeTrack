package Data;

import Util.DateTimeUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

    private static DataManager instance;

    public static DataManager getInstance(){
        if (instance == null)
            instance = new DataManager();
        return instance;
    }

    public void createTables() {
        SQLiteJDBCDriverConnection connection = SQLiteJDBCDriverConnection.getInstance();

        // SQL statement for creating a new table
        String createWorkTable = "CREATE TABLE IF NOT EXISTS work (\n"
                + "	date_hour INTEGER PRIMARY KEY,\n"
                + "	minutes INTEGER,\n"
                + "	pomodoros INTEGER,\n"
                + "	blocks INTEGER\n"
                + ");";

        String createSessionTable = "CREATE TABLE IF NOT EXISTS current_session (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	stage INTEGER NOT NULL,\n" // 1,3,5,7: pomodoro. 2,4,6: 5 min break. 8: 15 minute break
                + "	seconds_left INTEGER NOT NULL\n"
                + ");";

        String createSettingsTable = "CREATE TABLE IF NOT EXISTS settings (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	tomato_minutes INTEGER NOT NULL,\n"
                + "	short_break_minutes INTEGER NOT NULL,\n"
                + "	long_break_minutes INTEGER NOT NULL,\n"
                + "	always_front INTEGER NOT NULL,\n"
                + "	share_data INTEGER NOT NULL\n"
                + ");";

        try {
            Statement stmt = connection.getConnection().createStatement();

            // create new tables
            stmt.execute(createWorkTable);
            stmt.execute(createSessionTable);
            stmt.execute(createSettingsTable);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Exception while trying to create tables");
        } finally {
            connection.close();
        }

        Settings settings = getSettings();
        boolean settingsExist = false;
        String getSettingsQuery = "SELECT * FROM settings;";
        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(getSettingsQuery);

            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                settingsExist = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }

        if (!settingsExist){
            String insertDefaultSettings = "INSERT INTO settings ( id, tomato_minutes, short_break_minutes, long_break_minutes, always_front, share_data )" +
                    " VALUES ("+
                    " " + 1 + ", " +
                    " " + settings.getTomatoMinutes() + ", " +
                    " " + settings.getShortBreakMinutes() + ", " +
                    " " + settings.getLongBreakMinutes() + ", " +
                    " " + (settings.isAlwaysTop() ? 1 : 0) + ", " +
                    " " + (settings.isShareAnonData() ? 1 : 0) + ");";

            try {
                Statement stmt = connection.getConnection().createStatement();
                stmt.executeUpdate(insertDefaultSettings);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                System.err.println("Exception while trying to insert data into settings");
            } finally {
                connection.close();
            }
        }
        boolean workExists = false;

        String workExistQuery = "SELECT * FROM work WHERE date_hour = "+ DateTimeUtility.getDateTimeId()+";";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(workExistQuery);

            ResultSet rs = stmt.getResultSet();

            if (rs.next()) {
                workExists = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }

        if (!workExists){
            String insertWork = "INSERT INTO work ( date_hour, minutes, pomodoros, blocks )" +
                    " VALUES ("+
                    " " + DateTimeUtility.getDateTimeId() + ", " +
                    " " + 0 + ", " +
                    " " + 0 + ", " +
                    " " + 0 + ");";

            try {
                Statement stmt = connection.getConnection().createStatement();
                stmt.executeUpdate(insertWork);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                System.err.println("Exception while trying to insert data into work ");
            } finally {
                connection.close();
            }
        }

    }

    public static void clearSession(){
        String clearSession = "DELETE FROM current_session;";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(clearSession);
            SQLiteJDBCDriverConnection.getInstance().close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void clearWork(){
        String clearWork = "DELETE FROM work;";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(clearWork);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }
    }

    public static void clearAll(){
        clearSession();
        clearWork();
    }

    public static Work getWork(int dateHour){

        String getWorkQuery = "SELECT minutes, pomodoros, blocks FROM work " +
                "WHERE date_hour = "+ dateHour +";";

        Work work = null;
        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(getWorkQuery);

            ResultSet rs = stmt.getResultSet();

            if (rs.next()){
                int minutes = rs.getInt("minutes");
                int pomodoros = rs.getInt("pomodoros");
                int blocks = rs.getInt("blocks");
                work = new Work(minutes,pomodoros,blocks);
            } else {
                System.out.println("Work was requested for date "+dateHour+" but there was no entry. Return 0 work");
                work = new Work(0,0,0);
            }
            if (rs.next()){
                throw new Error("There appears to be more than one entry with this id: "+dateHour);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }
        return work;
    }

    private static void insertWork(int dateHourId){
        String insertWork = "INSERT INTO work ( date_hour, minutes, pomodoros, blocks )" +
                " VALUES ("+
                " " + dateHourId + ", " +
                " " + 0 + ", " +
                " " + 0 + ", " +
                " " + 0 + ");";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.executeUpdate(insertWork);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Exception while trying to insert data into work ");
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }
    }

    public static void addWork(Work work){

        int dateHourId = DateTimeUtility.getDateTimeId();
        String query = "INSERT OR REPLACE INTO work (date_hour, minutes, pomodoros, blocks)"+
        "VALUES ( "+dateHourId+", "+
                "(SELECT minutes FROM work WHERE date_hour = "+dateHourId+") + "+work.getMinutes()+", "+
                "(SELECT pomodoros FROM work WHERE date_hour = "+dateHourId+") + "+work.getPomodoros()+", "+
                "(SELECT blocks FROM work WHERE date_hour = "+dateHourId+") + "+work.getBlocks()+
          ");";

        //String query = "IF NOT EXISTS (SELECT * FROM work WHERE date_hour = " + DateTimeUtility.getDateTimeId() +" ) INSERT INTO work (date_hour, minutes, pomodoros, blocks) VALUES (" + DateTimeUtility.getDateTimeId() + ", " + work.getMinutes() + ", " + work.getPomodoros() + ", "+ work.getBlocks() +");";
        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.executeUpdate(query);
            System.out.println("addWork query executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }

        /*
        String addWorkQuery = "UPDATE work SET " +
                "minutes = minutes + " + work.getMinutes() + ", " +
                "pomodoros = pomodoros + "+ work.getPomodoros() +", " +
                "blocks = blocks + "+ work.getBlocks() +" " +
                "WHERE date_hour = "+ dateHourId +";";



        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.executeUpdate(addWorkQuery);
            System.out.println("addWork query executed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }
        */
    }

    public Settings getSettings(){
        Settings settings = null;

        String getSettingsQuery = "SELECT * FROM settings;";
        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(getSettingsQuery);

            ResultSet rs = stmt.getResultSet();

            if (rs.next()){
                int tomatoMinutes = rs.getInt("tomato_minutes");
                int shortBreakMinutes = rs.getInt("short_break_minutes");
                int longBreakMinutes = rs.getInt("long_break_minutes");
                boolean alwaysInFront = rs.getBoolean("always_front");
                boolean shareData = rs.getBoolean("share_data");
                settings = new Settings(tomatoMinutes, shortBreakMinutes, longBreakMinutes, alwaysInFront, shareData);
            } else {
                System.out.println("Settings were requested from the SQLite table \"settings\", but no entries were found. Returning default values.");
                settings = new Settings();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }

        return settings;
    }

    public static void setSettings(Settings settings){
        String updateSettingsQuery = "UPDATE settings SET " +
                "tomato_minutes = " + settings.getTomatoMinutes() + ", " +
                "short_break_minutes = " + settings.getShortBreakMinutes() + ", " +
                "long_break_minutes = " + settings.getLongBreakMinutes() + ", " +
                "always_front = " + (settings.isAlwaysTop() ? 1 : 0) + ", " +
                "share_data = " + (settings.isShareAnonData() ? 1 : 0) + ";";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.executeUpdate(updateSettingsQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            SQLiteJDBCDriverConnection.getInstance().close();
        }
    }

}
