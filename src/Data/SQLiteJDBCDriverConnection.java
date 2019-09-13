package Data;

import java.io.File;
import java.sql.*;

public class SQLiteJDBCDriverConnection {

    private static SQLiteJDBCDriverConnection ourInstance = new SQLiteJDBCDriverConnection();

    public static SQLiteJDBCDriverConnection getInstance() {
        return ourInstance;
    }

    private Connection conn = null;
    private String url = "jdbc:sqlite:data/database";
    //private String url = "jdbc:sqlite:data/blabla";

    /**
     * Connect to the database, print, then close connection
     */
    public boolean connect() {
        boolean success = false;
        try {
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");
            File file = new File("data/database");
            System.out.println("data/database exist: "+file.exists());

            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            success = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return success;
    }

    public Connection getConnection() {
        try {
            // create a connection to the database
            if (conn == null || conn.isClosed()) {
                File file = new File("data/database");
                System.out.println("data/database exist: "+file.exists());
                System.out.println(file.getAbsolutePath());

                File file2 = new File("data/blabla");
                System.out.println("data/blabla exist: "+file2.exists());
                System.out.println(file2.getAbsolutePath());

                //Class.forName("org.sqlite.JDBC");

                conn = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }/* catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        return conn;
    }

    public Statement execute(String sql){
        Statement stmt = null;
        try {
            stmt = getConnection().createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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