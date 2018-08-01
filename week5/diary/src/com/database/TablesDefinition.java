package com.database;
/**
 *
 * @author cj
 */
public abstract class TablesDefinition {
    
    String countryTable = "CREATE TABLE Country "
        + "(country_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " PRIMARY KEY ( id ))";
    
    String provinceTable = "CREATE TABLE Province "         
        + "(province_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100), "
        + " country_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( province_id )"
        + " CONSTRAIN FOREIGN KEY (country_id) REFERENCES Country (country_id)"
        + " ON DELETE CASCADE ON UPDATE CASCADE)";
    
    String cityTable = "CREATE TABLE City "
        + "(city_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(100) NOT NULL, "
        + " province_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( city_id )"
        + " CONSTRAIN FOREIGN KEY (province_id) REFERENCES Province "
        + " (province_id) ON DELETE CASCADE ON UPDATE CASCADE)";

    String addressTable = "CREATE TABLE Address "
        + "(address_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(150) NOT NULL, "
        + " city_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( address_id )"
        + " CONSTRAIN FOREIGN KEY (city_id) REFERENCES City "
        + " (city_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
    String personTable = "CREATE TABLE Person "         
        + "(person_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " surname VARCHAR(50) NOT NULL, "
        + " age INTEGER NOT NULL, "
        + " birthday DATE NOT NULL, "
        + " photo VARCHAR(500000) ,"
        + " address_id INTEGER ,"
        + " PRIMARY KEY ( person_id ), "
        + " CONSTRAIN FOREIGN KEY (address_id) REFERENCES Address "
        + " (address_id) ON DELETE SET NULL ON UPDATE CASCADE)";
    
        String emailTable = "CREATE TABLE Email "
        + "(email_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( email_id )"
        + " CONSTRAIN FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
        String phoneTable = "CREATE TABLE Phone "
        + "(phone_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " number VARCHAR(20) NOT NULL, "
        + " countryCode VARCHAR(4) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( phone_id )"
        + " CONSTRAIN FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
    
        String registerTable = "CREATE TABLE Register "
        + "(register_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(50) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( register_id )"
        + " CONSTRAIN FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
        String userTable = "CREATE TABLE User "
        + "(user_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " nickname VARCHAR(50) NOT NULL, "
        + " password VARCHAR(16) NOT NULL, "
        + " person_id INTEGER NOT NULL, "
        + " PRIMARY KEY ( user_id )"
        + " CONSTRAIN FOREIGN KEY (person_id) REFERENCES Person "
        + " (person_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
        String groupTable = "CREATE TABLE Group "
        + "(group_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " name VARCHAR(50) NOT NULL, "
        + " photo VARCHAR(500000), "
        + " PRIMARY KEY ( group_id ))";
        
        String registerGroupTable = "CREATE TABLE RegisterGroup "
        + "(registerGroup_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " group_id INT NOT NULL, "
        + " register_id INT NOT NULL, "
        + " PRIMARY KEY ( registerGroup_id )"
        + " CONSTRAIN FOREIGN KEY (group_id) REFERENCES Group "
        + " (group_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAIN FOREIGN KEY (register_id) REFERENCES Register"
        + " (register_id) ON DELETE CASCADE ON UPDATE CASCADE)";
        
        String userGroupTable = "CREATE TABLE UserGroup "
        + "(userGroup_id INTEGER NOT NULL AUTO_INCREMENT, "
        + " group_id INT NOT NULL, "
        + " user_id INT NOT NULL, "
        + " PRIMARY KEY ( userGroup_id )"
        + " CONSTRAIN FOREIGN KEY (group_id) REFERENCES Group "
        + " (group_id) ON DELETE CASCADE ON UPDATE CASCADE, "
        + " CONSTRAIN FOREIGN KEY (user_id) REFERENCES User"
        + " (user_id) ON DELETE CASCADE ON UPDATE CASCADE)";
}
