package com.controllers;
import com.model.Group;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class GroupController extends Controllers {
    
    public GroupController() {
        
        String sql = "SELECT * FROM _Group;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Group group = new Group();
                group.setGroup_id(rs.getInt("group_id"));
                group.setName(rs.getString("name"));
                group.setPhoto(rs.getString("photo"));
                group.setMaxAllowed(rs.getInt("maxAllowed"));
                objects.put(group.getGroup_id(), group);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from group controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> name
            content[1] -> photo
            content[2] -> maxAllowed
        */
        
        String sql = "INSERT INTO _Group (name,photo,maxAllowed) "
                + "VALUES (?,?,?);";
        int group_id = -1;
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
        
        sql = "SELECT * FROM _Group;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            group_id = rs.getInt("group_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform group query");
        }
        
        if(group_id == -1) {
            System.err.println("Error capturing group_id");
            return;
        }

        Group group = new Group();
        group.setGroup_id(group_id);
        group.setName(content[0]);
        group.setPhoto(content[1]);
        group.setMaxAllowed(Integer.parseInt(content[2]));
        objects.put(Integer.toString(group_id), group);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM _Group WHERE group_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested group was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                + "group");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Group map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> name
            content[1] -> photo
            content[2] -> maxAllowed
        */
        
        String sql = "UPDATE _Group SET "
            + "name='" + content[0] + "',"
            + "photo='" + content[1] + "',"
            + "maxAllowed='" + content[2] + "'"
            + " WHERE group_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested group was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested "
                + "group");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Group map does not have the specified key!");
            return;
        }
        
        Group group = (Group) objects.get(id);
        group.setGroup_id(id);
        group.setName(content[0]);
        group.setPhoto(content[1]);
        group.setMaxAllowed(Integer.parseInt(content[2]));
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Group group = (Group) objects.get(obj);
            System.out.println("Group id: " + group.getGroup_id());
            System.out.println("Group name: " + group.getName());
            System.out.println("Group photo: " + group.getPhoto());
            System.out.println("Group capacity: " + group.getMaxAllowed() + 
                "\n");
        });
    }
}
