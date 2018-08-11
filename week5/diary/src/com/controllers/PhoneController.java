package com.controllers;
import com.model.Phone;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class PhoneController extends Controllers {
    
    public PhoneController() {
        
        String sql = "SELECT * FROM Phone;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Phone phone = new Phone();
                phone.setPhone_id(rs.getInt("phone_id"));
                phone.setNumber(rs.getString("number"));
                phone.setCountryCode(rs.getString("countryCode"));
                phone.setPerson_id(rs.getInt("person_id"));
                objects.put(phone.getPhone_id(), phone);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from phone controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkPhoneData(content);
        String phoneNumber = content[0];
        String countryCode = content[1];
        int person_id = Integer.parseInt(content[2]);
        
        String sql = "INSERT INTO Phone (number,countryCode,person_id) "
                + "VALUES (?,?,?);";
        int phone_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,phoneNumber);
            prstmt.setString(2,countryCode);
            prstmt.setInt(3,person_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Phone register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create phone register");
            return;
        }
        
        sql = "SELECT * FROM Phone;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            phone_id = rs.getInt("phone_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform phone query");
        }
        
        if(phone_id == -1) {
            System.err.println("Error capturing phone_id");
            return;
        }

        Phone phone = new Phone();
        phone.setPhone_id(phone_id);
        phone.setNumber(phoneNumber);
        phone.setCountryCode(countryCode);
        phone.setPerson_id(person_id);
        objects.put(phone_id, phone);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Phone map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM Phone WHERE phone_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested phone was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested phone");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("Phone map does not have the specified key!");
            return;
        }

        Exceptions.checkPhoneData(content);
        String phoneNumber = content[0];
        String countryCode = content[1];
        int person_id = Integer.parseInt(content[2]);
        
        String sql = "UPDATE Phone SET "
            + "number='" + phoneNumber + "',"
            + "countryCode='" + countryCode + "',"
            + "person_id='" + person_id + "'"
            + " WHERE phone_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested phone was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested phone");
            return;
        }
        
        Phone phone = (Phone) objects.get(id);
        phone.setPhone_id(id);
        phone.setNumber(phoneNumber);
        phone.setCountryCode(countryCode);
        phone.setPerson_id(person_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Phone phone = (Phone) objects.get(obj);
            System.out.println("Phone id: " + phone.getPhone_id());
            System.out.println("Phone number: " + phone.getNumber());
            System.out.println("Person id: " + phone.getPerson_id() + "\n");
        });
    }
}
