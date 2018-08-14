package com.controllers;
import com.model.Country;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class CountryController extends Controllers {
    
    public CountryController() {
        String sql = "SELECT * FROM Country;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Country country = new Country();
                country.setCountry_id(rs.getInt("country_id"));
                country.setName(rs.getString("name"));
                objects.put(country.getCountry_id(), country);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from country controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkCountryData(content);
        String countryName = content[0];
        
        String sql = "INSERT INTO Country (name) VALUES (?);";
        int country_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,countryName);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Country register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible to create country register");
            return;
        }
        
        sql = "SELECT MAX(country_id) AS country_id FROM Country;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            country_id = rs.getInt("country_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible to perform country query");
        }
        
        if(country_id == -1) {
            System.err.println("Error capturing country id");
            return;
        }

        Country country = new Country();
        country.setCountry_id(country_id);
        country.setName(countryName);
        objects.put(country_id, country);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Country map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM Country WHERE country_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested country was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                    + "country");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("Country map does not have the specified key!");
            return;
        }
        
        Exceptions.checkCountryData(content);
        String countryName = content[0];
        
        String sql = "UPDATE Country SET name='" + countryName + "' WHERE "
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
        
        Country country = (Country) objects.get(id);
        country.setCountry_id(id);
        country.setName(countryName);
}
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Country country = (Country) objects.get(obj);
            System.out.println("Country id: " + country.getCountry_id());
            System.out.println("Country name: " + country.getName() + "\n");
        });
    }
    
}
