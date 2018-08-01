package com.database;
import java.sql.*;
/**
 *
 * @author cj
 */
public class ManageTables {
    
    private Statement stmt;
    
    ManageTables(Connection conn) {
        try {
            stmt = conn.createStatement();
        }
        catch(SQLException e) {
            throw new IllegalArgumentException("Unable to create statement");
        }
    }
    
    public void closeStmt() {
        try {
            stmt.close();
        }
        catch(SQLException e) {
            System.err.println("Unable to disconnect statement");
        }
    }
    
    public void createTables(Connection conn,String sql) {
        try {
            stmt.executeUpdate(sql);
        }
        catch(SQLException e) {
            System.err.println("Table was not created successfully!");
        }
    }
}
