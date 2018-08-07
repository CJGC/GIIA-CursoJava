package com.model;
import com.database.*;
import com.controllers.*;
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
//        DBManagement.createTables(TablesDefinition.countryTable);
//        DBManagement.createTables(TablesDefinition.provinceTable);
//        DBManagement.createTables(TablesDefinition.cityTable);
//        DBManagement.createTables(TablesDefinition.addressTable);
//        DBManagement.createTables(TablesDefinition.personTable);
//        DBManagement.createTables(TablesDefinition.emailTable);
//        DBManagement.createTables(TablesDefinition.phoneTable);
//        DBManagement.createTables(TablesDefinition.registerTable);
//        DBManagement.createTables(TablesDefinition.userTable);
//        DBManagement.createTables(TablesDefinition.groupTable);
//        DBManagement.createTables(TablesDefinition.userGroupTable);
//        DBManagement.createTables(TablesDefinition.registerGroupTable);
        
        RegisterController ec= new RegisterController();
        String[] values = {"Jdk","1"};
        //ec.create(values);
        //ec.edit(4, values);
        ec.delete(4);
        ec.show();
        DBManagement.closeStmt();
        DBManagement.disconnect();
    }
    
}
