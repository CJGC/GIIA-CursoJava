package com.controllers;
import java.util.HashMap;
/**
 *
 * @author cj
 */
public abstract class Controllers {
    
    protected HashMap objects;
    
    Controllers() {
        objects = new HashMap();
    }
    
    public abstract void create(String[] content);
    public abstract void delete(int id);
    public abstract void edit(int id, String[] content);
    public abstract void show();
    
}
