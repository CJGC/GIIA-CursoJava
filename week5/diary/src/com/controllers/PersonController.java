package com.controllers;
import com.model.Person;
import com.database.DBManagement;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author cj
 */
public class PersonController extends Controllers {
        
    private final String pattern;
    private final SimpleDateFormat sdf;
    private Date date;
    
    public PersonController() {
        pattern = "yyyy-mm-dd";
        sdf = new SimpleDateFormat(pattern);
    
        String sql = "SELECT * FROM Person;";
        try {
            ResultSet rs =  DBManagement.getStatement().executeQuery(sql);
            while(rs.next()) {
                Person person = new Person();
                person.setPerson_id(rs.getInt("person_id"));
                person.setName(rs.getString("name"));
                person.setSurname(rs.getString("surname"));
                person.setAge(rs.getInt("age"));
                person.setBirthday(rs.getDate("birthday"));
                person.setPhoto(rs.getString("photo"));
                person.setAddress_id(rs.getInt("address_id"));
                objects.put(person.getPerson_id(), person);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from person controller)!");
        }
    }
    
    @Override
    public void create(String[] content) {
        /* --------- Content array contains ---------
            content[0] -> person's name
            content[1] -> person's surname
            content[2] -> person's age
            content[3] -> person's birthday
            content[4] -> person's photo
            content[5] -> foreign key (address_id)
        */
        
        String sql = "INSERT INTO Person "
            + "(name,surname,age,birthday,photo,address_id) "
            + "VALUES (?,?,?,?,?,?);";
        int person_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,content[0]);
            prstmt.setString(2,content[1]);
            prstmt.setInt(3,Integer.parseInt(content[2]));
            prstmt.setDate(4, java.sql.Date.valueOf(content[3]));
            prstmt.setString(5,content[4]);
            prstmt.setInt(6, Integer.parseInt(content[5]));
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create register");
            return;
        }
        
        sql = "SELECT * FROM Person;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            person_id = rs.getInt("person_id");
            rs.close();
            date = sdf.parse(content[3]);
        }
        catch(SQLException e) {
            System.err.println("Was not possible perform person query");
        }
        catch (ParseException e) {
            System.err.println("There was an error parsing the date");
            return;
        }
        
        if(person_id == -1) {
            System.err.println("Error capturing person_id");
            return;
        }

        Person person = new Person();
        person.setPerson_id(person_id);
        person.setName(content[0]);
        person.setSurname(content[1]);
        person.setAge(Integer.parseInt(content[2]));
        person.setBirthday(date);
        person.setPhoto(content[4]);
        person.setAddress_id(Integer.parseInt(content[5]));
        objects.put(Integer.toString(person_id), person);
    }
    
    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Person WHERE person_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested person was deleted successfully");
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible delete the requested person");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Person map does not have the specified key!");
            return;
        }
        
        objects.remove(id);
    }
    
    @Override
    public void edit(int id, String[] content) {
        /* --------- Content array contains ---------
            content[0] -> person's name
            content[1] -> person's surname
            content[2] -> person's age
            content[3] -> person's birthday
            content[4] -> person's photo
            content[5] -> foreign key (address_id)
        */
        
        String sql = "UPDATE Person SET "
            + "name='" + content[0] + "',"
            + "surname='" + content[1] + "',"
            + "age='" + content[2] + "',"
            + "birthday='" + content[3] + "',"
            + "photo='" + content[4] + "',"
            + "address_id='" + content[5] + "'"
            + " WHERE person_id=" + id + ";";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            System.out.println("Requested person was updated successfully");
            date = sdf.parse(content[3]);
            rs.close();
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested person");
            return;
        }
        catch(ParseException ex) {
            System.err.println("There was an error parsing the date");
            return;
        }
        
        if(!objects.containsKey(id)) {
            System.err.println("Person map does not have the specified key!");
            return;
        }
        
        Person person = (Person) objects.get(id);
        person.setPerson_id(id);
        person.setName(content[0]);
        person.setSurname(content[1]);
        person.setAge(Integer.parseInt(content[2]));
        person.setBirthday(date);
        person.setPhoto(content[4]);
        person.setAddress_id(Integer.parseInt(content[5]));
    }
    
    @Override
    public void show() {
        objects.keySet().forEach((obj) -> {
            Person person = (Person) objects.get(obj);
            System.out.println("Person's id: " + person.getPerson_id());
            System.out.println("Person's name: " + person.getName());
            System.out.println("Person's surname: " + person.getSurname());
            System.out.println("Person's age: " + person.getAge());
            System.out.println("Person's date: " + person.getBirthday());
            System.out.println("Person's photo: " + person.getPhoto());
            System.out.println("Address id: " + person.getAddress_id() + "\n");
        });
    }

}
