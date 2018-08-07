package com.controllers;
import com.model.RegisterGroup;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class RegisterGroupController extends Controllers {
    
    public RegisterGroupController() {
        
        String sql = "SELECT * FROM RegisterGroup;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                RegisterGroup registerGroup = new RegisterGroup();
                registerGroup.setRegisterGroup_id(rs.getInt("registerGroup_id"));
                registerGroup.setGroup_id(rs.getInt("group_id"));
                registerGroup.setRegister_id(rs.getInt("register_id"));
                objects.put(registerGroup.getRegisterGroup_id(), registerGroup);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from registerGroup "
                + "controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> group_id
            content[1] -> register_id
        */
        
        String sql = "INSERT INTO RegisterGroup (group_id,register_id) "
                + "VALUES (?,?);";
        int registerGroup_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setInt(1,Integer.parseInt(content[0]));
            prstmt.setInt(2,Integer.parseInt(content[1]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create register");
            return;
        }
        
        sql = "SELECT * FROM RegisterGroup;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            registerGroup_id = rs.getInt("registerGroup_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform registerGroup query");
        }
        
        if(registerGroup_id == -1) {
            System.err.println("Error capturing registerGroup_id");
            return;
        }

        RegisterGroup registerGroup = new RegisterGroup();
        registerGroup.setRegisterGroup_id(registerGroup_id);
        registerGroup.setGroup_id(Integer.parseInt(content[0]));
        registerGroup.setRegister_id(Integer.parseInt(content[1]));
        objects.put(Integer.toString(registerGroup_id), registerGroup);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM RegisterGroup WHERE registerGroup_id=" 
            + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested RegisterGroup was deleted "
                + "successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                + "RegisterGroup");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("RegisterGroup map does not have the specified "
                + "key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> group_id
            content[1] -> register_id
        */
        
        String sql = "UPDATE RegisterGroup SET "
            + "group_id='" + content[0] + "',"
            + "register_id='" + content[1] + "'"
            + " WHERE registerGroup_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested RegisterGroup was updated "
                    + "successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested "
                + "RegisterGroup");
            return;
        }
        
        RegisterGroup registerGroup = (RegisterGroup) objects.get(id);
        registerGroup.setRegisterGroup_id(id);
        registerGroup.setGroup_id(Integer.parseInt(content[0]));
        registerGroup.setRegister_id(Integer.parseInt(content[1]));
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            RegisterGroup registerGroup = (RegisterGroup) objects.get(obj);
            System.out.println("Register group id: " + 
                registerGroup.getRegisterGroup_id());
            System.out.println("Group id: " + registerGroup.getGroup_id());
            System.out.println("Register id: " + registerGroup.getRegister_id() 
                + "\n");
        });
    }
}
