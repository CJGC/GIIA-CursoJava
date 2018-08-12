package com.database;
/**
 *
 * @author cj
 */
public class TablesDefinition {
    
    public static final String COUNTRY_TABLE = 
        "CREATE TABLE IF NOT EXISTS Country "
        + "(country_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " PRIMARY KEY ( country_id ))";
    
    public static final String PROVINCE_TABLE = 
        "CREATE TABLE IF NOT EXISTS Province "         
        + "(province_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " country_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( province_id ), "
        + " CONSTRAINT FOREIGN KEY (country_id) REFERENCES Country (country_id)"
        + " ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String CITY_TABLE = 
        "CREATE TABLE IF NOT EXISTS City "
        + "(city_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " province_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( city_id ), "
        + " CONSTRAINT FOREIGN KEY (province_id) REFERENCES Province "
        + " (province_id) ON DELETE CASCADE ON UPDATE CASCADE)";

    public static final String ADDRESS_TABLE = 
        "CREATE TABLE IF NOT EXISTS Address "
        + "(address_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(150) NOT NULL, "
        + " city_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( address_id ), "
        + " CONSTRAINT FOREIGN KEY (city_id) REFERENCES City "
        + " (city_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String PERSON_TABLE = 
        "CREATE TABLE IF NOT EXISTS Person "
        + "(person_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " surname VARCHAR(100) NOT NULL, "
        + " birthday DATE NOT NULL, "
        + " age INTEGER NOT NULL, "
        + " photo blob, "
        + " address_id INTEGER, "
        + " PRIMARY KEY ( person_id ), "
        + " CONSTRAINT FOREIGN KEY (address_id) REFERENCES Address "
        + " (address_id) ON DELETE SET NULL ON UPDATE CASCADE)";
    
    public static final String EMAIL_TABLE = 
        "CREATE TABLE IF NOT EXISTS Email "
        + "(email_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( email_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String PHONE_TABLE = 
        "CREATE TABLE IF NOT EXISTS Phone "
        + "(phone_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " number VARCHAR(20) NOT NULL, "
        + " countryCode VARCHAR(4) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( phone_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String USER_TABLE = 
        "CREATE TABLE IF NOT EXISTS User "
        + "(user_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(100) NOT NULL, "
        + " password VARCHAR(16) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( user_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";    
    
    public static final String REGISTER_TABLE = 
        "CREATE TABLE IF NOT EXISTS Register "
        + "(register_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(100) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " user_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( register_id ), "
        + " CONSTRAINT FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAINT FOREIGN KEY (user_id) REFERENCES User "
        + " (user_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    public static final String GROUP_TABLE = 
        "CREATE TABLE IF NOT EXISTS _Group "
        + "(group_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " photo blob, "
        + " maxAllowed INTEGER NOT NULL, "
        + " user_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( group_id ), "
        + " CONSTRAINT FOREIGN KEY (user_id) REFERENCES User "
        + " (user_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
    public static final String REGISTER_GROUP_TABLE = 
        "CREATE TABLE IF NOT EXISTS RegisterGroup "
        + "(registerGroup_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " group_id INTEGER NOT NULL, "
        + " register_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( registerGroup_id ), "
        + " CONSTRAINT FOREIGN KEY (group_id) REFERENCES _Group "
        + " (group_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAINT FOREIGN KEY (register_id) REFERENCES Register"
        + " (register_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
}
