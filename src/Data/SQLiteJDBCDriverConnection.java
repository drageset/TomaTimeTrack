package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBCDriverConnection {

    private static SQLiteJDBCDriverConnection ourInstance = new SQLiteJDBCDriverConnection();

    public static SQLiteJDBCDriverConnection getInstance() {
        return ourInstance;
    }

    private Connection conn = null;
    private String url = "jdbc:sqlite:data/database";

    /**
     * Connect to the database
     */
    public boolean connect() {
        boolean success = false;
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            success = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return success;
    }

    public Connection getConnection() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public Statement execute(String sql){
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return stmt;
    }

    public void close(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}