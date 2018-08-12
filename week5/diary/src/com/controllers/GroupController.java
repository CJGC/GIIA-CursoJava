package com.controllers;
import com.model.Group;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;
import javax.imageio.ImageIO;
/**
 *
 * @author cj
 */
public class GroupController {
    
    private HashMap objects;
    
    public GroupController() throws IOException {
        
        objects = new HashMap();
        
        String sql = "SELECT * FROM _Group;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Group group = new Group();
                group.setGroup_id(rs.getInt("group_id"));
                group.setName(rs.getString("name"));
                InputStream IS = rs.getBinaryStream("photo");
                BufferedImage photo = null;
                if(IS != null) photo = ImageIO.read(IS);
                group.setPhoto(photo);
                group.setMaxAllowed(rs.getInt("maxAllowed"));
                group.setUser_id(rs.getInt("user_id"));
                objects.put(group.getGroup_id(), group);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from group controller)!");
        }
    }
    
    public void create(String[] content,BufferedImage photo) throws IOException{
        Exceptions.checkGroupData(content);
        
        String groupName = content[0];
        int maxAllowed = Integer.parseInt(content[1]);
        int user_id = Integer.parseInt(content[2]);
        
        String sql = "INSERT INTO _Group (name,photo,maxAllowed,user_id) "
                + "VALUES (?,?,?,?);";
        int group_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,groupName);
            
            if(photo != null) {
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                ImageIO.write(photo, "jpeg", photoStream);
                InputStream inputStream = 
                        new ByteArrayInputStream(photoStream.toByteArray());

                prstmt.setBinaryStream(2,inputStream,
                        photo.getHeight() * photo.getWidth());
            }
            else prstmt.setBinaryStream(2,null);
            
            prstmt.setInt(3,maxAllowed);
            prstmt.setInt(4, user_id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Group register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create group register");
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
        group.setName(groupName);
        group.setPhoto(photo);
        group.setMaxAllowed(maxAllowed);
        group.setUser_id(user_id);
        objects.put(group_id, group);
    }
    
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Group map does not have the specified key!");
            return;
        }
        
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
        
        objects.remove(id);
    }
    
    public void edit(int id, String[] content, BufferedImage photo) 
            throws IOException {
        if(!objects.containsKey(id)) {
            System.err.println("Group map does not have the specified key!");
            return;
        }

        Exceptions.checkGroupData(content);
        String groupName = content[0];
        int maxAllowed = Integer.parseInt(content[1]);
        int user_id = Integer.parseInt(content[2]);
        
        String sql = "UPDATE _Group SET "
            + "name=?, photo=?, maxAllowed=?, user_id=? WHERE group_id=?";
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,groupName);
            
            if(photo != null) {
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                ImageIO.write(photo, "jpeg", photoStream);
                InputStream inputStream = 
                        new ByteArrayInputStream(photoStream.toByteArray());
                prstmt.setBinaryStream(2,inputStream,
                        photo.getHeight() * photo.getWidth());
            }
            else prstmt.setBinaryStream(2,null);
            
            prstmt.setInt(3,maxAllowed);
            prstmt.setInt(4,user_id);
            prstmt.setInt(5,id);
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Requested group was updated successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested group");
            return;
        }
        
        Group group = (Group) objects.get(id);
        group.setGroup_id(id);
        group.setName(groupName);
        group.setPhoto(photo);
        group.setMaxAllowed(maxAllowed);
        group.setUser_id(user_id);
    }
    
    public void show() {
        objects.keySet().forEach((obj) -> {
            Group group = (Group) objects.get(obj);
            System.out.println("Group id: " + group.getGroup_id());
            System.out.println("Group name: " + group.getName());
            System.out.println("Group capacity: " + group.getMaxAllowed());
            System.out.println("Group owner: " + group.getUser_id() + "\n");
        });
    }
}
