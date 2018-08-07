package com.controllers;
import com.model.Phone;
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
        /* --------- Content array contains ---------
            content[0] -> number
            content[1] -> countryCode
            content[2] -> foreign key (person id)
        */
        
        String sql = "INSERT INTO Phone (number,countryCode,person_id) "
                + "VALUES (?,?,?);";
        int phone_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.setString(2,content[1]);
            prstmt.setInt(3,Integer.parseInt(content[2]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create register");
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
        phone.setNumber(content[0]);
        phone.setCountryCode(content[1]);
        phone.setPerson_id(Integer.parseInt(content[2]));
        objects.put(Integer.toString(phone_id), phone);
    }
    
    @Override
    public void delete(int id) {
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
        
        if(!objects.containsKey(id)) {
            System.err.println("Phone map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> number
            content[1] -> countryCode
            content[2] -> foreign key (person id)
        */
        
        String sql = "UPDATE Phone SET "
            + "number='" + content[0] + "',"
            + "countryCode='" + content[1] + "',"
            + "person_id='" + content[2] + "'"
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
        
        if(!objects.containsKey(id)) {
            System.err.println("Phone map does not have the specified key!");
            return;
        }
        
        Phone phone = (Phone) objects.get(id);
        phone.setPhone_id(id);
        phone.setNumber(content[0]);
        phone.setCountryCode(content[1]);
        phone.setPerson_id(Integer.parseInt(content[2]));
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
