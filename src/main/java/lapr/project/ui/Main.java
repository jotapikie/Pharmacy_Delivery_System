package lapr.project.ui;

import lapr.project.data.DataHandler;
import lapr.project.model.CalculatorExample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    /**
     * Private constructor to hide implicit public one.
     */
    private Main() {

    }

    /**
     * Application main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, SQLException {
        
        AddToCartUI addToCart = new AddToCartUI("clien1@lapr3.com");
//        CalculatorExample calculatorExample = new CalculatorExample();
//        int value = calculatorExample.sum(3, 5);
//
//        if (LOGGER.isLoggable(Level.INFO))
//            LOGGER.log(Level.INFO, String.valueOf(value));
//
//        //load database properties
//
//        try {
//            Properties properties =
//                    new Properties(System.getProperties());
//            InputStream input = new FileInputStream("target/classes/application.properties");
//            properties.load(input);
//            input.close();
//            System.setProperties(properties);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        //Initial Database Setup
//        DataHandler dh = new DataHandler();
//        dh.scriptRunner("target/test-classes/demo_jdbc.sql");



    }
}

