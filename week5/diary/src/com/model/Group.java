package com.model;

import java.io.FileInputStream;

/**
 *
 * @author cj
 */
public class Group {
    private int group_id;
    private String name;
    private String owner;
    private int maxAllowed;
    private FileInputStream photo;

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getMaxAllowed() {
        return maxAllowed;
    }

    public void setMaxAllowed(int maxAllowed) {
        this.maxAllowed = maxAllowed;
    }

    public FileInputStream getPhoto() {
        return photo;
    }

    public void setPhoto(FileInputStream photo) {
        this.photo = photo;
    }
}
