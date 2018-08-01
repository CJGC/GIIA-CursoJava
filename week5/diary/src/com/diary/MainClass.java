package com.diary;
import com.database.*;
/**
 *
 * @author cj
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DbConnection conn = new DbConnection();
        ManageTables mt = new ManageTables(conn);
        mt.createTables(TablesDefinition.countryTable);
        mt.createTables(TablesDefinition.provinceTable);
        mt.createTables(TablesDefinition.cityTable);
        mt.createTables(TablesDefinition.addressTable);
        mt.createTables(TablesDefinition.personTable);
        mt.createTables(TablesDefinition.emailTable);
        mt.createTables(TablesDefinition.phoneTable);
        mt.createTables(TablesDefinition.registerTable);
        mt.createTables(TablesDefinition.userTable);
        mt.createTables(TablesDefinition.groupTable);
        mt.createTables(TablesDefinition.userGroupTable);
        mt.createTables(TablesDefinition.registerGroupTable);
        mt.closeStmt();
        conn.disconnect();
    }
    
}
