package com.controllers;
import com.model.Address;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class AddressController extends Controllers {
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> address name
            content[1] -> foreign key (city id)
        */
        
        String sql = "INSERT INTO Address (name,city_id) VALUES (?,?);";
        int address_id = -1;
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
            System.err.println("Was not possible create register");
            return;
        }
        
        sql = "SELECT * FROM Address;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            address_id = rs.getInt("address_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform address query");
        }
        
        if(address_id == -1) {
            System.err.println("Error capturing address_id");
            return;
        }

        Address address = new Address();
        address.setAddress_id(address_id);
        address.setName(content[0]);
        address.setCity_id(Integer.parseInt(content[1]));
        objects.put(Integer.toString(address_id), address);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Address WHERE address_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested address was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested address");
            return;
        }
        
        objects.remove(Integer.toString(id));
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> address name
            content[1] -> foreign key (city id)
        */
        
        String sql = "UPDATE Address SET "
            + "name='" + content[0] + "',"
            + "city_id='" + content[1] + "'"
            + " WHERE address_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested address was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested address");
            return;
        }
        
        Address address = new Address();
        address.setAddress_id(id);
        address.setName(content[0]);
        address.setCity_id(Integer.parseInt(content[1]));
        objects.replace(Integer.toString(id),address);
    }
    
    @Override
    public void show() {
        
    }
}
