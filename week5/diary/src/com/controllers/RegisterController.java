package com.controllers;
import com.model.Register;
import com.database.DBManagement;
import java.sql.*;
/**
 *
 * @author cj
 */
public class RegisterController extends Controllers {
    
    public RegisterController() {
        
        String sql = "SELECT * FROM Register;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Register register = new Register();
                register.setRegister_id(rs.getInt("register_id"));
                register.setNickname(rs.getString("nickname"));
                register.setPerson_id(rs.getInt("person_id"));
                objects.put(register.getRegister_id(), register);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from register "
                + "controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> nickname
            content[1] -> foreign key (person id)
        */
        
        String sql = "INSERT INTO Register (nickname,person_id) "
                + "VALUES (?,?);";
        int register_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.setInt(2,Integer.parseInt(content[1]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create register");
            return;
        }
        
        sql = "SELECT * FROM Register;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            register_id = rs.getInt("register_id");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform register query");
        }
        
        if(register_id == -1) {
            System.err.println("Error capturing register_id");
            return;
        }

        Register register = new Register();
        register.setRegister_id(register_id);
        register.setNickname(content[0]);
        register.setPerson_id(Integer.parseInt(content[1]));
        objects.put(Integer.toString(register_id), register);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Register WHERE register_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested register was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested "
                + "register");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Register map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> nickname
            content[1] -> foreign key (person id)
        */
        
        String sql = "UPDATE Register SET "
            + "nickname='" + content[0] + "',"
            + "person_id='" + content[1] + "'"
            + " WHERE register_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested register was updated successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested "
                + "register");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Register map does not have the specified key!");
            return;
        }
        
        Register register = (Register) objects.get(id);
        register.setRegister_id(id);
        register.setNickname(content[0]);
        register.setPerson_id(Integer.parseInt(content[1]));
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Register register = (Register) objects.get(obj);
            System.out.println("Register id: " + register.getRegister_id());
            System.out.println("Register nickname: " + register.getNickname());
            System.out.println("Person id: " + register.getPerson_id() + "\n");
        });
    }
}
