/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.WeakHashMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lapr.project.model.Wind;
import lapr.project.utils.route.Route;

/**
 *
 * @author Diogo
 */
public class Utils {
    
    

    public static boolean sendEmail(String to, String subject, String msg){
       Properties prop = new Properties();
        
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/application.properties"));
            final String email = props.getProperty("email.user");
            final String password = props.getProperty("email.password");
            Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        
        
        });
            try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(email));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject(subject);  
         message.setText(msg);  
  
         // Send message  
         Transport.send(message);  
         return true;
  
      }catch (MessagingException mex) {
          return false;

      }  
        } catch (IOException ex) {
            return false;
        }     
   }  
    
        
        
    
    
    
    /**
     * Distance in meters between two points.
     * @param lat1
     * @param lat2
     * @param lon1
     * @param lon2
     * @param al1
     * @param al2
     * @return 
     */
  public static double distance(double lat1, double lat2, double lon1,
        double lon2, double al1, double al2) {

    final int R = 6371; // Radius of the earth

    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; // convert to meters

    double height = al1 - al2;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
}
    
     /**
     * Calculates the permutations of a given list.
     *
     * @param toPermute list to permute.
     * @return list of permutations.
     */
    public static <V> List<List<V>> permutations(List<V> toPermute) {
        if (toPermute == null) {
            return null;
        }
        List<List<V>> result = new ArrayList<>();
        if (!toPermute.isEmpty()) {
            permutations(toPermute, new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * Calculates the permutations of a given list.
     *
     * @param toPermute list to permute.
     * @param temp temporary list.
     * @param permutations list of permutations.
     */
    private static <V> void permutations(List<V> toPermute, List<V> temp, List<List<V>> permutations) {
        if (temp.size() == toPermute.size()) {
            permutations.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < toPermute.size(); i++) {
            if (!temp.contains(toPermute.get(i))) {
                temp.add(toPermute.get(i));
                permutations(toPermute, temp, permutations);
                temp.remove(temp.size() - 1);
            }
        }
    }


    public static double pathEnergyCost(double totalWeight, double kineticCoef, double vehicleAerodynamicCoef, double windToPath, double altitudeDif, double distance) {
        if (distance <= 0 || altitudeDif >= distance || vehicleAerodynamicCoef < 0 || kineticCoef <= 0 || totalWeight <= 0) { //
            throw new IllegalArgumentException("This path has invalid data!");
        };
        double wind = (windToPath > 0) ? -Math.pow(windToPath, 2) : Math.pow(windToPath, 2);
        double result = ((Constants.GRAVITY * totalWeight * (kineticCoef + Math.asin(Math.abs(altitudeDif) / distance)))
                + (vehicleAerodynamicCoef + wind)) * distance / 3600;
        return (result < 0) ? 0 : result*0.001;
    }

    public static double pathEnergyCostDrone(double totalWeight, double vehicleAerodynamicCoef, double powerTransfer, double liftDrag, double consumoEletronico, double areaFrontal, double areaTopo, double velocidadeMedia, double velocidadeVertical, Wind windToPath, double altitudeDifI, double altitudeDifF, double distance) {
        if (distance <= 0 || altitudeDifI >= distance || altitudeDifF >= distance || vehicleAerodynamicCoef < 0 || consumoEletronico <= 0 || areaFrontal <= 0 || areaTopo <= 0 || velocidadeMedia <= 0 || totalWeight <= 0) {
            throw new IllegalArgumentException("This path has invalid data!");
        }
        double fAero = ((-(0.5 * Constants.RO_AR * vehicleAerodynamicCoef * areaFrontal * windToPath.vx * windToPath.vx)) + (-(0.5 * Constants.RO_AR * vehicleAerodynamicCoef * areaFrontal * windToPath.vy * windToPath.vy)) + (-(0.5 * Constants.RO_AR * vehicleAerodynamicCoef * areaTopo * windToPath.vz * windToPath.vz)));
        double eHorizontal = (((totalWeight * Constants.GRAVITY) / (liftDrag * powerTransfer)) * distance);
        double eAero = fAero * distance;
        double fImpulso = ((totalWeight * Constants.GRAVITY) + (0.5 * Constants.RO_AR * areaTopo * Constants.DRAG_COEF * velocidadeVertical * velocidadeVertical));
        double eSubir = ((fImpulso * altitudeDifI));
        double eDescer = ((fImpulso * altitudeDifF) );
        double tempoSubirDescer= (altitudeDifF/velocidadeVertical) + (altitudeDifI/velocidadeVertical);
        double tempoHorizontal= distance/velocidadeMedia;
        double tempoTotalHoras= (tempoHorizontal+tempoSubirDescer)/3600;
        return (consumoEletronico*tempoTotalHoras)+((((eAero + eDescer + eHorizontal + eSubir)/Constants.RENDIMENTO_DRONE)/3600)*100)/Constants.DRONE_BATTERY_VOLTAGE;
    }


    public static <V> List<String> listToString(List<V> lst) {
        List<String> res = new ArrayList<>();
        for(V v : lst){
            res.add(v.toString());
        }
        return res;
    }


    public static double pathDirection(double latitude1, double longitude1, double latitude2, double longitude2) {
        if (!validateCoordinates(latitude1, longitude1) || !validateCoordinates(latitude2, longitude2)) {
            throw new IllegalArgumentException("Invalid coordinates!");
        }
        return Math.toDegrees(Math.atan2(Math.abs(longitude2 - longitude1), Math.abs(latitude2 - latitude1)));
    }


    public static double windToPath(double pathDirec, Wind wind) {
        if (wind == null) {
            throw new IllegalArgumentException("Invalid wind speed!");
        }
        if (!validateDegrees(pathDirec) || !validateDegrees(wind.direction())) {
            throw new IllegalArgumentException("Invalid degrees!");
        }
        return wind.speed() * Math.cos(wind.direction() - pathDirec);
    }

    private static boolean validateCoordinates(double latitude, double longitude) {
        return Math.abs(latitude) <= 90 && Math.abs(longitude)<=180;
    }

    private static boolean validateDegrees(double degrees) {
        return 0 <= degrees && degrees < 360;
    }
    
    public static String secondsToTime(long seconds){
        int hours = (int) (seconds / 3600);
        int minutes = (int) ((seconds % 3600) / 60);
        int second = (int) (seconds % 60);

        String sHo = hours< 0 ? "" :String.format("%02dh", hours);
        String sMi = minutes<0 ? "":String.format("%02dm", minutes);
        String sSe = second<0 ? "":String.format("%02ds", second);
        return String.format("%s %s %s",sHo, sMi, sSe);
    }
    
    public static double kmhTOms(double distance){
        return distance * 0.277777778;
    }
}
