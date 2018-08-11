package com.controllers;
import com.model.UserGroup;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class UserGroupController extends Controllers {
    
    public UserGroupController() {
        
        String sql = "SELECT * FROM UserGroup;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setUserGroup_id(rs.getInt("userGroup_id"));
                userGroup.setGroup_id(rs.getInt("group_id"));
                userGroup.setUser_id(rs.getInt("user_id"));
                objects.put(userGroup.getUserGroup_id(), userGroup);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from userGroup "
                + "controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        Exceptions.checkUserGroupData(content);
        int group_id = Integer.parseInt(content[0]);
        int user_id = Integer.parseInt(content[1]);
        
        String sql = "INSERT INTO UserGroup (group_id,user_id) "
                + "VALUES (?,?);";
        int userGroup_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setInt(1,group_id);
            prstmt.setInt(2,user_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("UserGroup register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create userGroup register");
            return;
        }
        
        sql = "SELECT * FROM UserGroup;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            userGroup_id = rs.getInt("userGroup_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform userGroup query");
        }
        
        if(userGroup_id == -1) {
            System.err.println("Error capturing userGroup_id");
            return;
        }

        UserGroup userGroup = new UserGroup();
        userGroup.setUserGroup_id(userGroup_id);
        userGroup.setGroup_id(group_id);
        userGroup.setUser_id(user_id);
        objects.put(userGroup_id, userGroup);
    }
    
    @Override
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("UserGroup map does not have the specified "
                + "key!");
            return;
        }
        
        String sql = "DELETE FROM UserGroup WHERE userGroup_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested UserGroup was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                + "UserGroup");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        if(!objects.containsKey(id)) {
            System.err.println("UserGroup map does not have the specified "
                + "key!");
            return;
        }
        
        Exceptions.checkUserGroupData(content);
        int group_id = Integer.parseInt(content[0]);
        int user_id = Integer.parseInt(content[1]);
        
        String sql = "UPDATE UserGroup SET "
            + "group_id='" + group_id + "',"
            + "user_id='" + user_id + "'"
            + " WHERE userGroup_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested userGroup was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested "
                + "userGroup");
            return;
        }
        
        UserGroup userGroup = (UserGroup) objects.get(id);
        userGroup.setUserGroup_id(id);
        userGroup.setGroup_id(group_id);
        userGroup.setUser_id(user_id);
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            UserGroup userGroup = (UserGroup) objects.get(obj);
            System.out.println("User group id: " + userGroup.getUserGroup_id());
            System.out.println("Group id: " + userGroup.getGroup_id());
            System.out.println("User id: " + userGroup.getUser_id() + 
                "\n");
        });
    }
}
