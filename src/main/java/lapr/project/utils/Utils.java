/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Constants.PLATFORM_EMAIL, Constants.PLATFORM_PWD);
            }
        
        
        });
        
         try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(Constants.PLATFORM_EMAIL));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject(subject);  
         message.setText(msg);  
  
         // Send message  
         Transport.send(message);  
         return true;
  
      }catch (MessagingException mex) {
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
        if (distance <= 0 || altitudeDif >= distance || vehicleAerodynamicCoef < 0 || kineticCoef <= 0 || totalWeight <= 0) {
            throw new IllegalArgumentException("This path has invalid data!");
        }
        double wind = (windToPath > 0) ? -Math.pow(windToPath, 2) : Math.pow(windToPath, 2);
        double result = ((Constants.GRAVITY * totalWeight * (kineticCoef + Math.asin(Math.abs(altitudeDif) / distance)))
                + (vehicleAerodynamicCoef + wind)) * distance / 3600;
        return (result < 0) ? 0 : result;
    }
}
