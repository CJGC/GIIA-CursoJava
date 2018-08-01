package com.database;
import java.sql.*;
/**
 *
 * @author cj
 */
public class ManageTables {
    
    private Statement stmt;
    
    public ManageTables(DbConnection conn) {
        try {
            stmt = conn.getConnection().createStatement();
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
    
    public void createTables(String sql) {
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
