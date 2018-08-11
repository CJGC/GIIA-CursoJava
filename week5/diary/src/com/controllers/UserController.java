package com.controllers;
import com.model.User;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class UserController extends Controllers {
    
    public UserController() {
        
        String sql = "SELECT * FROM User;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                User user = new User();
                user.setUser_id(rs.getInt("user_id"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setPerson_id(rs.getInt("person_id"));
                objects.put(user.getUser_id(), user);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from user controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkUserData(content);
        String userNickname = content[0];
        String userPassword = content[1];
        int person_id = Integer.parseInt(content[2]);
        
        String sql = "INSERT INTO User (nickname,password,person_id) "
                + "VALUES (?,?,?);";
        int user_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,userNickname);
            prstmt.setString(2,userPassword);
            prstmt.setInt(3, person_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("User register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create user register");
            return;
        }
        
        sql = "SELECT * FROM User;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            user_id = rs.getInt("user_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform user query");
        }
        
        if(user_id == -1) {
            System.err.println("Error capturing user_id");
            return;
        }

        User user = new User();
        user.setUser_id(user_id);
        user.setNickname(userNickname);
        user.setPassword(userPassword);
        user.setPerson_id(person_id);
        objects.put(Integer.toString(user_id), user);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("User map does not have the specified key!");
            return;
        }
        
        String sql = "DELETE FROM User WHERE user_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested user was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested user");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("User map does not have the specified key!");
            return;
        }
        
        Exceptions.checkUserData(content);
        String userNickname = content[0];
        String userPassword = content[1];
        int person_id = Integer.parseInt(content[2]);
        
        String sql = "UPDATE User SET "
            + "nickname='" + userNickname + "',"
            + "password='" + userPassword + "',"
            + "person_id='" + person_id + "'"
            + " WHERE user_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested user was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested user");
            return;
        }
        
        User user = (User) objects.get(id);
        user.setUser_id(id);
        user.setNickname(userNickname);
        user.setPassword(userPassword);
        user.setPerson_id(person_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            User user = (User) objects.get(obj);
            System.out.println("User id: " + user.getUser_id());
            System.out.println("User nickname: " + user.getNickname());
            System.out.println("User password: " + user.getPassword());
            System.out.println("Person id: " + user.getPerson_id() + "\n");
        });
    }
}
