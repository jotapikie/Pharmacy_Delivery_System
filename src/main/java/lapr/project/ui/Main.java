package lapr.project.ui;

import lapr.project.controller.EndDeliveryRunController;
import lapr.project.controller.UpdateVehicleController;
import lapr.project.model.State;
import lapr.project.utils.Utils;

import java.io.*;
import java.nio.file.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 * @author Nuno Bettencourt <nmb@isep.ipp.pt> on 24/05/16.
 */
class Main {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = Logger.getLogger("MainLog");

    //Path for all files shared with C/Assembly component
    private static final String PATH_FILES = "D:\\ARQCP\\partilha\\pls\\LAPR3\\files\\";

    //File Constants
    private static final String KEY1= "estimated_";
    private static final String KEY2= ".data";
    private static final String KEY3= ".flag";

    private static final UpdateVehicleController uvc= new UpdateVehicleController();
    private static final EndDeliveryRunController drc= new EndDeliveryRunController();
    private static final Utils util= new Utils();




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
    public static void main(String[] args) throws IOException, SQLException, InterruptedException {


        Thread watcher = new Thread(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path path = Paths.get(PATH_FILES);
                WatchKey key= path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);


                boolean check = false;
                File deleteEstimate = null;
                File deleteFlag;

                while (true) {
                    for (WatchEvent<?> event : key.pollEvents()) {

                        System.out.println("\nola jota");
                        String filename = event.context().toString();
                        int length = filename.length();
                        if (filename.startsWith(KEY1)) {

                            if (filename.startsWith(KEY2, length - KEY2.length()) && !check) {
                                check = true;
                                deleteEstimate = new File(filename);
                            }

                            if (filename.startsWith(KEY3, length - KEY3.length()) && check) {
                                deleteFlag = new File(filename);

                                if (!deleteProtocol(deleteEstimate, deleteFlag))
                                    logWarning("Estimated files have not been deleted");
                            } else
                                logWarning("Something is wrong with estimated file");

                        }
                    }
                }
            } catch (IOException | InterruptedException e  ) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
        );

        watcher.start();
        watcher.join();

    }

    private static void logWarning(String message) {
        LOGGER.log(Level.WARNING, message);
    }



    //Gets information of both estimate files; finishes their journeys and sends an email to the user in question either with the estimated time or an alert that the scooter is not parked correctly
    private static boolean deleteProtocol(File deleteEstimate, File deleteFlag) throws IOException, InterruptedException, MessagingException {

        try (BufferedReader br1 = Files.newBufferedReader(Paths.get(PATH_FILES + deleteEstimate))) {

            String line = br1.readLine();
            String[] arrSplit = line.split(" ");
            String email;


            if (arrSplit.length == 4) {

                uvc.updateScooterState(Integer.parseInt(arrSplit[0]), State.CHARGING);
                uvc.update();
                drc.lockScooter(Integer.parseInt(arrSplit[0]), Integer.parseInt(arrSplit[1]), Integer.parseInt(arrSplit[2]));
                email=drc.setEndDate(Integer.parseInt(arrSplit[0]));


                Utils.sendEmail(email, "Scooter battery charging duration estimate", "We estimate that your scooter will have full charge in " + arrSplit[3] + " hours.");

                Thread.sleep(2000);
                Files.delete(Paths.get(PATH_FILES + deleteEstimate));
                Thread.sleep(1000);
                Files.delete(Paths.get(PATH_FILES + deleteFlag));

                LOGGER.log(Level.INFO, "Estimate files deleted successfully and email with estimate sent.");

                return true;

            } else {
               email= drc.setEndDate(Integer.parseInt(arrSplit[0]));

                Utils.sendEmail(email, "Scooter not Locked", "Your scooter is not properly locked. Please lock it so that the scooter can charge");
                return true;
            }
        } catch (NumberFormatException e) {
            logWarning("Estimate file recieved is in the wrong format.");
        } catch (FileNotFoundException e) {
            logWarning("Something went wrong with estimated file");
        } catch (RuntimeException e) {
            logWarning("Email not sent properly.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    
    


                  
                  

                  
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

