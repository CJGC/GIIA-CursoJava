package com.model;

import java.awt.image.BufferedImage;
import java.util.Date;
/**
 *
 * @author cj
 */
public class Person {
    
    private int person_id;
    private String name;
    private String surname;
    private Date birthday;
    private int age;
    private BufferedImage photo;
    private int address_id;

    public int getPerson_id() {
        return person_id;
    }
    
    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public BufferedImage getPhoto() {
        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
