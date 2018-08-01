package com.diary;
import com.database.DbConnection;
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
        conn.disconnect();
    }
    
}
