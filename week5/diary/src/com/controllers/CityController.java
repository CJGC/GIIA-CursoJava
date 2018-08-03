package com.controllers;
import com.model.City;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class CityController extends Controllers {
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> city name
            content[1] -> foreign key (province id)
        */
        
        String sql = "INSERT INTO City (name,province_id) VALUES (?,?);";
        int city_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.setInt(2,Integer.parseInt(content[1]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible to create register");
            return;
        }
        
        sql = "SELECT * FROM City;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            city_id = rs.getInt("city_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible to perform city query");
        }
        
        if(city_id == -1) {
            System.err.println("Error capturing city_id");
            return;
        }

        City city = new City();
        city.setCity_id(city_id);
        city.setName(content[0]);
        city.setProvince_id(Integer.parseInt(content[1]));
        objects.put(Integer.toString(city_id), city);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM City WHERE city_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested city was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                    + "city");
            return;
        }
        
        objects.remove(Integer.toString(id));
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> city name
            content[1] -> foreign key (province id)
        */
        
        String sql = "UPDATE City SET "
            + "name='" + content[0] + "',"
            + "province_id='" + content[1] + "'"
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
        
        City city = new City();
        city.setCity_id(id);
        city.setName(content[0]);
        city.setProvince_id(Integer.parseInt(content[1]));
        objects.replace(Integer.toString(id),city);
    }
    
    @Override
    public void show() {
        
    }
}
