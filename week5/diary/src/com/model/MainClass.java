package com.model;
import com.database.*;
import com.controllers.CountryController;
/**
 *
 * @author cj
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBManagement.buildConnection();
        DBManagement.buildStmt();
        DBManagement.createTables(TablesDefinition.countryTable);
        DBManagement.createTables(TablesDefinition.provinceTable);
        DBManagement.createTables(TablesDefinition.cityTable);
        DBManagement.createTables(TablesDefinition.addressTable);
        DBManagement.createTables(TablesDefinition.personTable);
        DBManagement.createTables(TablesDefinition.emailTable);
        DBManagement.createTables(TablesDefinition.phoneTable);
        DBManagement.createTables(TablesDefinition.registerTable);
        DBManagement.createTables(TablesDefinition.userTable);
        DBManagement.createTables(TablesDefinition.groupTable);
        DBManagement.createTables(TablesDefinition.userGroupTable);
        DBManagement.createTables(TablesDefinition.registerGroupTable);
        
        CountryController cc = new CountryController();
        DBManagement.closeStmt();
        DBManagement.disconnect();
    }
    
}
