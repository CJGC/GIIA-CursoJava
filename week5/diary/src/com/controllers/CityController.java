package com.controllers;
import com.model.City;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class CityController extends Controllers {
    
    public CityController() {
        String sql = "SELECT * FROM City;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                City city = new City();
                city.setCity_id(rs.getInt("city_id"));
                city.setName(rs.getString("name"));
                city.setProvince_id(rs.getInt("province_id"));
                objects.put(city.getCity_id(), city);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from city controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkCityData(content);
        String cityName = content[0];
        int province_id = Integer.parseInt(content[1]);
        
        String sql = "INSERT INTO City (name,province_id) VALUES (?,?);";
        int city_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,cityName);
            prstmt.setInt(2,province_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("City register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create city register");
            return;
        }
        
        sql = "SELECT MAX(city_id) AS city_id FROM City;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            city_id = rs.getInt("city_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform city query");
        }
        
        if(city_id == -1) {
            System.err.println("Error capturing city_id");
            return;
        }

        City city = new City();
        city.setCity_id(city_id);
        city.setName(cityName);
        city.setProvince_id(province_id);
        objects.put(city_id, city);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("City map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM City WHERE city_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested city was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested city");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("City map does not have the specified key!");
            return;
        }
        
        Exceptions.checkCityData(content);
        String cityName = content[0];
        int province_id = Integer.parseInt(content[1]);
        
        String sql = "UPDATE City SET "
            + "name='" + cityName + "',"
            + "province_id='" + province_id + "'"
            + " WHERE city_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested city was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested city");
            return;
        }
        
        City city = (City) objects.get(id);
        city.setCity_id(id);
        city.setName(cityName);
        city.setProvince_id(province_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            City city = (City) objects.get(obj);
            System.out.println("City id: " + city.getCity_id());
            System.out.println("City name: " + city.getName());
            System.out.println("Province id: " + city.getProvince_id() + "\n");
        });
    }
}
