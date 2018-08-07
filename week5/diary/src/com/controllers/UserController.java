package com.controllers;
import com.model.User;
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
        /* --------- Content array contains ---------
            content[0] -> nickname
            content[1] -> password
            content[2] -> foreign key (person id)
        */
        
        String sql = "INSERT INTO User (nickname,password,person_id) "
                + "VALUES (?,?,?);";
        int user_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.setString(2,content[1]);
            prstmt.setInt(3, Integer.parseInt(content[2]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create register");
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
        user.setNickname(content[0]);
        user.setPassword(content[1]);
        user.setPerson_id(Integer.parseInt(content[2]));
        objects.put(Integer.toString(user_id), user);
    }
    
    @Override
    public void delete(int id) {
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
        
        if(!objects.containsKey(id)) {
            System.err.println("User map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> nickname
            content[1] -> password
            content[2] -> foreign key (person id)
        */
        
        String sql = "UPDATE User SET "
            + "nickname='" + content[0] + "',"
            + "password='" + content[1] + "',"
            + "person_id='" + content[2] + "'"
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
        
        if(!objects.containsKey(id)) {
            System.err.println("User map does not have the specified key!");
            return;
        }
        
        User user = (User) objects.get(id);
        user.setUser_id(id);
        user.setNickname(content[0]);
        user.setPassword(content[1]);
        user.setPerson_id(Integer.parseInt(content[2]));
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
