package com.controllers;
import com.model.Email;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class EmailController extends Controllers {
    
    public EmailController() {
        
        String sql = "SELECT * FROM Email;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Email email = new Email();
                email.setEmail_id(rs.getInt("email_id"));
                email.setName(rs.getString("name"));
                email.setPerson_id(rs.getInt("person_id"));
                objects.put(email.getEmail_id(), email);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from email controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> name
            content[1] -> foreign key (person id)
        */
        Exceptions.checkEmailData(content);
        String emailAddress = content[0];
        int person_id = Integer.parseInt(content[1]);
        
        String sql = "INSERT INTO Email (name,person_id) "
                + "VALUES (?,?);";
        int email_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,emailAddress);
            prstmt.setInt(2,person_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Email register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create email register");
            return;
        }
        
        sql = "SELECT * FROM Email;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            email_id = rs.getInt("email_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform email query");
        }
        
        if(email_id == -1) {
            System.err.println("Error capturing email_id");
            return;
        }

        Email email = new Email();
        email.setEmail_id(email_id);
        email.setName(emailAddress);
        email.setPerson_id(person_id);
        objects.put(email_id, email);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Email map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM Email WHERE email_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested email was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested email");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("Email map does not have the specified key!");
            return;
        }
        
        Exceptions.checkEmailData(content);
        String emailAddress = content[0];
        int person_id = Integer.parseInt(content[1]);
        
        String sql = "UPDATE Email SET "
            + "name='" + emailAddress + "',"
            + "person_id='" + person_id + "'"
            + " WHERE email_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested email was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested email");
            return;
        }
        
        Email email = (Email) objects.get(id);
        email.setEmail_id(id);
        email.setName(emailAddress);
        email.setPerson_id(person_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Email email = (Email) objects.get(obj);
            System.out.println("Email id: " + email.getEmail_id());
            System.out.println("Email name: " + email.getName());
            System.out.println("Person id: " + email.getPerson_id() + "\n");
        });
    }
}
