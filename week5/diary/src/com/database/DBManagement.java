package com.database;
import java.sql.*;
/**
 *
 * @author cj
 */
public class DBManagement {
    
    // object type Connection
    private static Connection conn;
    private static Statement stmt;
    
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/diary";
    
    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection() {
        return conn;
    }
    
    public static Statement getStatement() {
        return stmt;
    }
    
    public static void buildConnection() {
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
    
    public static void disconnect() {
        try {
            conn.close();
        }
        catch(SQLException e) {
            System.err.println("Unable to discconect");
        }
    }

    public static void buildStmt() {
        try {
            stmt = conn.createStatement();
        }
        catch(SQLException e) {
            throw new IllegalArgumentException("Unable to create statement");
        }
    }
    
    public static void closeStmt() {
        try {
            stmt.close();
        }
        catch(SQLException e) {
            System.err.println("Unable to disconnect statement");
        }
    }
    
    public static void createTables(String sql) {
        try {
            stmt.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.err.println("Table was not created successfully!");
            return;
        }
        System.out.println("Table was created successfully!");
    }
}
