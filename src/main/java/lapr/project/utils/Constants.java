/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.sql.Timestamp;

/**
 *
 * @author Diogo
 */
public class Constants {
        // Connection
    public static final String CONNECTION_JDBCURL = "jdbc:oracle:thin:@vsrvbd1.dei.isep.ipp.pt:1521/pdborcl";
    public static final String CONNECTION_USERNAME = "LAPR3_G55";
    public static final String CONNECTION_ACCESS = "qwerty";
    public static final String PLATFORM_EMAIL = "pharmacygolapr@gmail.com";
    public static final String PLATFORM_PWD = "lapr3g55";
    
    public static final int DEFAULT_ID = 1;
    public static final double DEFAULT_PRICE = 0;

    public static final int INITIAL_POINTS = 0;
    public static double GRAVITY = 9.8;
    public static Timestamp DEFAULT_DATE = new Timestamp(1);
    public static String DEFAULT_STATUS= "Processing";
}
