package com.controllers;
import com.model.Country;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class CountryController extends Controllers {
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> country name
        */
        
        String sql = "INSERT INTO Country (name) VALUES (?);";
        int country_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible to create register");
            return;
        }
        
        sql = "SELECT * FROM Country;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            country_id = rs.getInt("country_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible to make country query");
        }
        
        if(country_id == -1) {
            System.err.println("Error capturing country_id");
            return;
        }

        Country country = new Country();
        country.setCountry_id(country_id);
        country.setName(content[0]);
        objects.put(Integer.toString(country_id), country);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Country WHERE country_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested country was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                    + "country id");
            return;
        }
        
        objects.remove(Integer.toString(id));
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> country name
        */
        
        String sql = "UPDATE Country SET name='" + content[0] + "' WHERE "
            + "country_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested country was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested country");
            return;
        }
        
        Country country = new Country();
        country.setCountry_id(id);
        country.setName(content[0]);
        Object temp = objects.replace(Integer.toString(id),country);
        if(temp == null)
            System.err.println("There was an error registering the edited "
                + "country");
    }
    
}
