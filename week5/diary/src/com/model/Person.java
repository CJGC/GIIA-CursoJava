package com.model;

import java.io.FileInputStream;
import java.util.Date;
/**
 *
 * @author cj
 */
public class Person {
    
    private int person_id;
    private String name;
    private String surname;
    private int age;
    private Date birthday;
    private FileInputStream photo;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
