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
import lapr.project.utils.Constants;

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
    
    public static void variablesUsed(String file){
        write(String.format("Scooter weight: %.2f kg%n", Constants.SCOOTER_WEIGHT), file);
        write(String.format("Scooter aerodynamic coefficient: %.2f%n", Constants.SCOOTER_AERO_COEF), file);
        write(String.format("Scooter frontal area: %.2f m^2%n", Constants.SCOOTER_FRONTAL_AREA), file);
        write(String.format("Scooter average speed: %.2f km/h%n", Constants.SCOOTER_SPEED), file);
        write(String.format("Drone aerodynamic coefficient: %.2f%n", Constants.DRONE_AERO_COEF), file);
        write(String.format("Drone eletronical consume: %.2f%n", Constants.DRONE_ELETRONICAL_CONSUME), file);
        write(String.format("Drone frontal area: %.2f m^2%n", Constants.DRONE_FRONTAL_AREA), file);
        write(String.format("Drone top area: %.2f m^2%n", Constants.DRONE_TOP_AREA), file);
        write(String.format("Drone weight: %.2f kg%n", Constants.DRONE_WEIGHT), file);
        write(String.format("Drone average (horizontal) speed: %.2f m/s%n", Constants.DRONE_HORIZONTAL_SPEED), file);
        write(String.format("Drone average (vertical) speed: %.2f m/s%n", Constants.DRONE_VERTICAL_SPEED), file);
        write(String.format("Drone altitude: %.2f m%n", Constants.DRONE_ALTITUDE), file);
        write(String.format("Drone drag coefficient: %.2f %n", Constants.DRAG_COEF), file);
        write(String.format("Asphalt kinetic coefficient: %.2f%n", Constants.KINETIC_COEF_ASPHALT), file);
        write(String.format("Off-Road kinetic coefficient: %.2f%n", Constants.KINETIC_COEF_OFF_ROAD),file);
        write(String.format("Sidewalk kinetic coefficient: %.2f%n", Constants.KINETIC_COEF_SIDEWALK), file);
    }
}
