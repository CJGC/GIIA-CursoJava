package com.controllers;
import com.model.Group;
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
                BufferedImage photo = ImageIO.read(IS);
                group.setPhoto(photo);
                group.setMaxAllowed(rs.getInt("maxAllowed"));
                objects.put(group.getGroup_id(), group);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from group controller)!");
        }
    }
    
    public void create(String[] content,BufferedImage photo) throws IOException{
        /* --------- Content array contains ---------
            content[0] -> name
            content[1] -> maxAllowed
        */
        
        String sql = "INSERT INTO _Group (name,photo,maxAllowed) "
                + "VALUES (?,?,?);";
        int group_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            
            ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
            ImageIO.write(photo, "jpeg", photoStream);
            InputStream inputStream = 
                    new ByteArrayInputStream(photoStream.toByteArray());
            
            prstmt.setBinaryStream(2,inputStream,
                    photo.getHeight() * photo.getWidth());
            
            prstmt.setInt(3,Integer.parseInt(content[1]));
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
        group.setPhoto(photo);
        group.setMaxAllowed(Integer.parseInt(content[1]));
        objects.put(Integer.toString(group_id), group);
    }
    
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
    
    public void edit(int id, String[] content, BufferedImage photo) 
            throws IOException {
        /* --------- Content array contains ---------
            content[0] -> name
            content[1] -> maxAllowed
        */
        
        String sql = "UPDATE _Group SET "
            + "name=?, photo=?, maxAllowed=? WHERE group_id=?";
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            
            ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
            ImageIO.write(photo, "jpeg", photoStream);
            InputStream inputStream = 
                    new ByteArrayInputStream(photoStream.toByteArray());
            prstmt.setBinaryStream(2,inputStream,
                    photo.getHeight() * photo.getWidth());
            prstmt.setInt(3,Integer.parseInt(content[1]));
            
            System.out.println("Requested group was updated successfully");
            prstmt.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested group");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Group map does not have the specified key!");
            return;
        }
        
        Group group = (Group) objects.get(id);
        group.setGroup_id(id);
        group.setName(content[0]);
        group.setPhoto(photo);
        group.setMaxAllowed(Integer.parseInt(content[1]));
    }
    
    public void show() {
        objects.keySet().forEach((obj) -> {
            Group group = (Group) objects.get(obj);
            System.out.println("Group id: " + group.getGroup_id());
            System.out.println("Group name: " + group.getName());
            System.out.println("Group capacity: " + group.getMaxAllowed() + 
                "\n");
        });
    }
}
