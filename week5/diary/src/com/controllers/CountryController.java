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
        /* --------- Content array contains ---------
            content[0] -> country name
        */
        Exceptions.checkCountryData(content);
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
            System.err.println("Was not possible to perform country query");
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
        
        if(!objects.containsKey(id)) {
            System.err.println("Country map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> country name
        */
        Exceptions.checkCountryData(content);
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
        
        if(!objects.containsKey(id)) {
            System.err.println("Country map does not have the specified key!");
            return;
        }
        
        Country country = (Country) objects.get(id);
        country.setCountry_id(id);
        country.setName(content[0]);
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
