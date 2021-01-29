/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.ui.text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import lapr.project.data.DataHandler;

/**
 *
 * @author Diogo
 */
public class Utils {
    
    private static DataHandler dh = new DataHandler();
    

    
    public static void executeScript(String file){
        try{
            dh.scriptRunner("textFiles/clear.sql");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void write(String message, String file){
        File log = new File(file);
        BufferedWriter bufferedWriter = null;
        try{
            if(!log.exists()){
                log.createNewFile();
            }

        FileWriter fileWriter = new FileWriter(log, true);

        bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(message);


        } catch(IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferedWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
