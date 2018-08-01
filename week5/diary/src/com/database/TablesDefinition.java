package com.database;
/**
 *
 * @author cj
 */
public abstract class TablesDefinition {
    String firsTable = "CREATE TABLE REGISTRATION "
        + "(id INTEGER not NULL, "
        + " first VARCHAR(255), "
        + " last VARCHAR(255), "
        + " age INTEGER, "
        + " PRIMARY KEY ( id ))";
}
