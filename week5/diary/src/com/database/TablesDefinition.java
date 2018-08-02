package com.database;
/**
 *
 * @author cj
 */
public class TablesDefinition {
    
    public static final String countryTable = 
        "CREATE TABLE IF NOT EXISTS Country "
        + "(country_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " PRIMARY KEY ( country_id ))";
    
    public static final String provinceTable = 
        "CREATE TABLE IF NOT EXISTS Province "         
        + "(province_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " country_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( province_id ), "
        + " CONSTRAINT FOREIGN KEY (country_id) REFERENCES Country (country_id)"
        + " ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String cityTable = 
        "CREATE TABLE IF NOT EXISTS City "
        + "(city_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " province_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( city_id ), "
        + " CONSTRAINT FOREIGN KEY (province_id) REFERENCES Province "
        + " (province_id) ON DELETE CASCADE ON UPDATE CASCADE)";

    public static final String addressTable = 
        "CREATE TABLE IF NOT EXISTS Address "
        + "(address_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(150) NOT NULL, "
        + " city_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( address_id ), "
        + " CONSTRAINT FOREIGN KEY (city_id) REFERENCES City "
        + " (city_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String personTable = 
        "CREATE TABLE IF NOT EXISTS Person "         
        + "(person_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " surname VARCHAR(50) NOT NULL, "
        + " age INTEGER NOT NULL, "
        + " birthday DATE NOT NULL, "
        + " photo VARCHAR(10000), "
        + " address_id INTEGER, "
        + " PRIMARY KEY ( person_id ), "
        + " CONSTRAINT FOREIGN KEY (address_id) REFERENCES Address "
        + " (address_id) ON DELETE SET NULL ON UPDATE CASCADE)";
    
    public static final String emailTable = 
        "CREATE TABLE IF NOT EXISTS Email "
        + "(email_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( email_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String phoneTable = 
        "CREATE TABLE IF NOT EXISTS Phone "
        + "(phone_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " number VARCHAR(20) NOT NULL, "
        + " countryCode VARCHAR(4) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( phone_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String registerTable = 
        "CREATE TABLE IF NOT EXISTS Register "
        + "(register_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(50) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( register_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String userTable = 
        "CREATE TABLE IF NOT EXISTS User "
        + "(user_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(50) NOT NULL, "
        + " password VARCHAR(16) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( user_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String groupTable = 
        "CREATE TABLE IF NOT EXISTS _Group "
        + "(group_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " photo VARCHAR(500), "
        + " maxAllowed INTEGER NOT NULL, "
        + " PRIMARY KEY ( group_id ))";
        
    public static final String registerGroupTable = 
        "CREATE TABLE IF NOT EXISTS RegisterGroup "
        + "(registerGroup_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " group_id INTEGER NOT NULL, "
        + " register_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( registerGroup_id ), "
        + " CONSTRAINT FOREIGN KEY (group_id) REFERENCES _Group "
        + " (group_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAINT FOREIGN KEY (register_id) REFERENCES Register"
        + " (register_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String userGroupTable = 
        "CREATE TABLE IF NOT EXISTS UserGroup "
        + "(userGroup_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " group_id INTEGER NOT NULL, "
        + " user_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( userGroup_id ), "
        + " CONSTRAINT FOREIGN KEY (group_id) REFERENCES _Group "
        + " (group_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAINT FOREIGN KEY (user_id) REFERENCES User"
        + " (user_id) ON DELETE CASCADE ON UPDATE CASCADE)";
}
