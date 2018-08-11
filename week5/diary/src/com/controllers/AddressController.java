package com.controllers;
import com.model.Address;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class AddressController extends Controllers {
    
    public AddressController() {
        String sql = "SELECT * FROM Address;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Address address = new Address();
                address.setAddress_id(rs.getInt("address_id"));
                address.setName(rs.getString("name"));
                address.setCity_id(rs.getInt("city_id"));
                objects.put(address.getAddress_id(), address);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from address controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkAddressData(content);
        String addressValue = content[0];
        int city_id = Integer.parseInt(content[1]);
        
        String sql = "INSERT INTO Address (name,city_id) VALUES (?,?);";
        int address_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,addressValue);
            prstmt.setInt(2,city_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Address register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create address register");
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
        address.setName(addressValue);
        address.setCity_id(city_id);
        objects.put(Integer.toString(address_id), address);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Address map does not have the specified key!");
            return;
        }
        
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
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("Address map does not have the specified key!");
            return;
        }
        
        Exceptions.checkAddressData(content);
        String addressValue = content[0];
        int city_id = Integer.parseInt(content[1]);
        
        String sql = "UPDATE Address SET "
            + "name='" + addressValue + "',"
            + "city_id='" + city_id + "'"
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
        
        Address address = (Address) objects.get(id);
        address.setAddress_id(id);
        address.setName(addressValue);
        address.setCity_id(city_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Address address = (Address) objects.get(obj);
            System.out.println("Address id: " + address.getAddress_id());
            System.out.println("Address name: " + address.getName());
            System.out.println("City id: " + address.getCity_id() + "\n");
        });
    }
}
