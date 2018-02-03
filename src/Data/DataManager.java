package Data;

import Util.DateUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataManager {

    public void createTables() {
        // SQL statement for creating a new table
        String createWorkTable = "CREATE TABLE IF NOT EXISTS work (\n"
                + "	dd_mm_yy TEXT PRIMARY KEY,\n"
                + "	minutes INTEGER,\n"
                + "	pomodoros INTEGER,\n"
                + "	blocks INTEGER\n"
                + ");";

        String createSessionTable = "CREATE TABLE IF NOT EXISTS current_session (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	stage INTEGER NOT NULL,\n" // 1,3,5,7: pomodoro. 2,4,6: 5 min break. 8: 15 minute break
                + "	seconds_left INTEGER NOT NULL\n"
                + ");";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();

            // create new tables
            stmt.execute(createWorkTable);
            stmt.execute(createSessionTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearSession(){
        String clearSession = "DELETE FROM current_session;";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(clearSession);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearWork(){
        String clearWork = "DELETE FROM work;";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.execute(clearWork);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearAll(){
        clearSession();
        clearWork();
    }

    public Work getWork(String ddmmyy){

        String getWorkQuery = "SELECT minutes, pomodoros, blocks FROM work " +
                "WHERE id = "+ ddmmyy +";";

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
                System.out.println("Work was requested for date "+ddmmyy+" but there was no entry. Return 0 work");
                work = new Work(0,0,0);
            }
            if (rs.next()){
                throw new Error("There appears to be more than one entry with this id: "+ddmmyy);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return work;
    }

    public void addWork(Work work){
        String ddmmyy = DateUtility.getDateString();
        String addWorkQuery = "UPDATE work SET " +
                "minutes = minutes + " + work.getMinutes() + ", " +
                "pomodoros = pomodoros + "+ work.getPomodoros() +", " +
                "blocks = blocks + "+ work.getBlocks() +" " +
                "WHERE id = "+ ddmmyy +";";

        try {
            Statement stmt = SQLiteJDBCDriverConnection.getInstance().getConnection().createStatement();
            stmt.executeUpdate(addWorkQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
