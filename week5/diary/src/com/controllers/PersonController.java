package com.controllers;
import com.model.Person;
import com.exceptions.Exceptions;
import com.database.DBManagement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import javax.imageio.ImageIO;
/**
 *
 * @author cj
 */
public class PersonController {
        
    private final String datePattern;
    private final SimpleDateFormat sdf;
    private Date date;
    private HashMap objects;
    
    public PersonController() throws IOException {
        datePattern = "yyyy-mm-dd";
        sdf = new SimpleDateFormat(datePattern);
        objects = new HashMap();
    
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
                InputStream IS = rs.getBinaryStream("photo");
                BufferedImage photo = null;
                if(IS != null) photo = ImageIO.read(IS);
                person.setPhoto(photo);
                person.setAddress_id(rs.getInt("address_id"));
                objects.put(person.getPerson_id(), person);
            }
        }
        catch(SQLException e) {
            System.err.println("Error in sql query (from person controller)!");
        }
    }
    
    public void create(String[] content,BufferedImage photo) throws IOException{
        /* --------- Content array contains ---------
            content[3] -> foreign key (address_id)
        */
        Exceptions.checkPersonData(content);
        String personName = content[0];
        String personSurname = content[1];
        String personBirthDay = content[2];
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = LocalDate.parse(personBirthDay);
        int personAge = Period.between(birthDay, currentDate).getYears();
        
        String sql = "INSERT INTO Person "
            + "(name,surname,age,birthday,photo,address_id) "
            + "VALUES (?,?,?,?,?,?);";
        int person_id = -1;
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,personName);
            prstmt.setString(2,personSurname);
            prstmt.setInt(3,personAge);
            prstmt.setDate(4, java.sql.Date.valueOf(personBirthDay));
            
            if(photo != null) {
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                ImageIO.write(photo, "jpeg", photoStream);
                InputStream inputStream = 
                        new ByteArrayInputStream(photoStream.toByteArray());
                prstmt.setBinaryStream(5,inputStream,
                        photo.getHeight() * photo.getWidth());
            }
            else prstmt.setBinaryStream(5,null);
            
            if(content[3] != "") prstmt.setInt(6,Integer.parseInt(content[3]));
            else prstmt.setNull(6,java.sql.Types.INTEGER);
            
            prstmt.executeUpdate();
            prstmt.close();
            System.out.println("Person register was created successfully");
        }
        catch(SQLException e) {
            System.err.println("Was not possible create person register");
            return;
        }
        
        sql = "SELECT * FROM Person;";
        try {
            ResultSet rs = DBManagement.getStatement().executeQuery(sql);
            rs.last();
            person_id = rs.getInt("person_id");
            rs.close();
            date = sdf.parse(personBirthDay);
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
        person.setName(personName);
        person.setSurname(personSurname);
        person.setAge(personAge);
        person.setBirthday(date);
        person.setPhoto(photo);
        if(content[3] != "") person.setAddress_id(Integer.parseInt(content[3]));
        objects.put(Integer.toString(person_id), person);
    }
    
    public void delete(int id) {
        if(!objects.containsKey(id)) {
            System.err.println("Person map does not have the specified key!");
            return;
        }
        
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
        
        objects.remove(id);
    }
    
    public void edit(int id, String[] content,BufferedImage photo) 
            throws IOException {
        /* --------- Content array contains ---------
            content[3] -> foreign key (address_id)
        */
        if(!objects.containsKey(id)) {
            System.err.println("Person map does not have the specified key!");
            return;
        }
        
        Exceptions.checkPersonData(content);
        String personName = content[0];
        String personSurname = content[1];
        String personBirthDay = content[2];
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = LocalDate.parse(personBirthDay);
        int personAge = Period.between(birthDay, currentDate).getYears();
        
        String sql = "UPDATE Person SET "
            + "name=?, surname=?, age=?, birthday=?, photo=?, address_id=?"
            + " WHERE person_id=?";
        try {
            PreparedStatement prstmt;
            prstmt = DBManagement.getConnection().prepareStatement(sql);
            prstmt.setString(1,personName);
            prstmt.setString(2,personSurname);
            prstmt.setInt(3,personAge);
            prstmt.setDate(4,java.sql.Date.valueOf(personBirthDay));
            
            if(photo != null) {
                ByteArrayOutputStream photoStream = new ByteArrayOutputStream();
                ImageIO.write(photo, "jpeg", photoStream);
                InputStream inputStream = 
                        new ByteArrayInputStream(photoStream.toByteArray());
                prstmt.setBinaryStream(5,inputStream,
                        photo.getHeight() * photo.getWidth());
            }
            else prstmt.setBinaryStream(5,null);
            
            if(content[3] != "") prstmt.setInt(6,Integer.parseInt(content[3]));
            else prstmt.setNull(6,java.sql.Types.INTEGER);
            
            prstmt.setInt(7, id);
            prstmt.executeUpdate();
            System.out.println("Requested person was updated successfully");
            prstmt.close();
            date = sdf.parse(personBirthDay);
        }
        catch(SQLException e) {
            System.err.println("Was not possible update the requested person");
            return;
        }
        catch(ParseException ex) {
            System.err.println("There was an error parsing the date");
            return;
        }
        
        Person person = (Person) objects.get(id);
        person.setPerson_id(id);
        person.setName(personName);
        person.setSurname(personSurname);
        person.setAge(personAge);
        person.setBirthday(date);
        person.setPhoto(photo);
        if(content[3] != "") person.setAddress_id(Integer.parseInt(content[3]));
    }
    
    public void show() {
        objects.keySet().forEach((obj) -> {
            Person person = (Person) objects.get(obj);
            System.out.println("Person's id: " + person.getPerson_id());
            System.out.println("Person's name: " + person.getName());
            System.out.println("Person's surname: " + person.getSurname());
            System.out.println("Person's age: " + person.getAge());
            System.out.println("Person's date: " + person.getBirthday().toString());
            System.out.println("Address id: " + person.getAddress_id() + "\n");
        });
    }

}
