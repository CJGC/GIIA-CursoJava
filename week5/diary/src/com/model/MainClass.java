package com.model;
import com.database.*;
import com.controllers.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author cj
 */
public class MainClass {
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        BufferedImage img;
        img = ImageIO.read(new File("/home/cj/Desktop/workspace/GIIA-CursoJava/week5/diary/src/com/model/1.jpeg"));
        
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
        
        GroupController ec= new GroupController();
        String[] values = {"Fighters group","250"};
        //ec.create(values,img);
        //ec.edit(1,values,img);
        //ec.delete(1);
        ec.show();
        DBManagement.closeStmt();
        DBManagement.disconnect();
    }
    
}
