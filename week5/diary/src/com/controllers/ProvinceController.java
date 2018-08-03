package com.controllers;
import com.model.Province;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class ProvinceController extends Controllers {
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> province name
            content[1] -> foreign key (country id)
        */
        
        String sql = "INSERT INTO Province (name,country_id) VALUES (?,?);";
        int province_id = -1;
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
        
        sql = "SELECT * FROM Province;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            province_id = rs.getInt("province_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible to make province query");
        }
        
        if(province_id == -1) {
            System.err.println("Error capturing province_id");
            return;
        }

        Province province = new Province();
        province.setProvince_id(province_id);
        province.setName(content[0]);
        province.setCountry_id(Integer.parseInt(content[1]));
        objects.put(Integer.toString(province_id), province);
    }
    
    @Override
    public void delete(int id) {
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
        
        objects.remove(Integer.toString(id));
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> province name
            content[1] -> foreign key (country id)
        */
        
        String sql = "UPDATE Province SET "
            + "name='" + content[0] + "',"
            + "country_id='" + content[1] + "'"
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
        
        Province province = new Province();
        province.setProvince_id(id);
        province.setName(content[0]);
        province.setCountry_id(Integer.parseInt(content[1]));
        objects.replace(Integer.toString(id),province);
    }
    
    @Override
    public void show() {
        
    }
}
