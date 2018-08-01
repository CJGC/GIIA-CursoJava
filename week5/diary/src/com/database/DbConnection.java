package com.database;
import java.sql.*;
/**
 *
 * @author cj
 */
public class DbConnection {
    
    // object type Connection
    private final Connection conn;
    
    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://localhost:3306/diary";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "";

    public Connection getConnection() {
        return conn;
    }
    
    public DbConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException se) {
            throw new IllegalArgumentException("Unable to establish connection");
        }
        catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("Unable to locate jdbc class");
        }
        System.out.println("connection was established successfully.");
    }
    
    public void disconnect() {
        try {
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("Unable to discconect");
        }
    }
}
