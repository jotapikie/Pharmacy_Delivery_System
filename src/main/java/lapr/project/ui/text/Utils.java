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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lapr.project.data.DataHandler;

/**
 *
 * @author Diogo
 */
public class Utils {
    
    private static DataHandler dh = new DataHandler();
    

    
    public static void executeScript(String file){
        try{
            dh.scriptRunner(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static List<String> importFile(String s) {
        boolean ignoreFirstLine = true;
        List<String> listToReturn = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(s))) {
            List<String> l = stream.collect(Collectors.toList());
            for (String str : l) {
                if (str.contains("#")) {
                    // Skip line
                } else if (ignoreFirstLine) {
                    ignoreFirstLine = false;
                } else {
                    listToReturn.add(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }
    
    public static boolean deleteFile(String s){
        File f = new File(s);
        return f.delete();
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
