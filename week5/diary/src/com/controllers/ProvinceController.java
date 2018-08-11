package com.controllers;
import com.model.Province;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class ProvinceController extends Controllers {
    
    public ProvinceController() {
        String sql = "SELECT * FROM Province;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Province province = new Province();
                province.setProvince_id(rs.getInt("province_id"));
                province.setName(rs.getString("name"));
                province.setCountry_id(rs.getInt("country_id"));
                objects.put(province.getProvince_id(), province);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from province controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkProvinceData(content);
        String provinceName = content[0];
        int country_id = Integer.parseInt(content[1]);
        
        String sql = "INSERT INTO Province (name,country_id) VALUES (?,?);";
        int province_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,provinceName);
            prstmt.setInt(2,country_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Province register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create province register");
            return;
        }
        
        sql = "SELECT * FROM Province;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            province_id = rs.getInt("province_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform province query");
        }
        
        if(province_id == -1) {
            System.err.println("Error capturing province_id");
            return;
        }

        Province province = new Province();
        province.setProvince_id(province_id);
        province.setName(provinceName);
        province.setCountry_id(country_id);
        objects.put(Integer.toString(province_id), province);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Province map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM Province WHERE province_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested province was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                    + "province");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("Province map does not have the specified key!");
            return;
        }
        
        Exceptions.checkProvinceData(content);
        String provinceName = content[0];
        int country_id = Integer.parseInt(content[1]);
        
        String sql = "UPDATE Province SET "
            + "name='" + provinceName + "',"
            + "country_id='" + country_id + "'"
            + " WHERE province_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested province was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested province");
            return;
        }
        
        Province province = (Province) objects.get(id);
        province.setProvince_id(id);
        province.setName(provinceName);
        province.setCountry_id(country_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Province province = (Province) objects.get(obj);
            System.out.println("Province id: " + province.getProvince_id());
            System.out.println("Province name: " + province.getName());
            System.out.println("Country id: " + province.getCountry_id()+ "\n");
        });
    }
}
