/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pyramid.plunder;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 * This ErrorLogger Class writes the error logs on a text file; various messages
 * are prompted for the user to indicate system response.
 */
public class ErrorLogger {
    
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
    private static Logger event = Logger.getLogger(MainMenu.class.getName());
    private static SimpleFormatter sf = new SimpleFormatter();
    private static Handler error;
    private static Handler eventHandler;
    
    private static void setup()
    {
       try {
            error = new FileHandler("error.txt");
            eventHandler = new FileHandler("Events.txt");
            error.setFormatter(sf);
            eventHandler.setFormatter(sf);
            log.addHandler(error);
            event.addHandler(eventHandler);
        } 
        catch (Exception e)
        {
            logNonRecoverableError("Can't start log "+ e);
        }
       logMessage("Log started");
    }
    
/**
 * This logMessage method is passed down String message, through the log and INFO
 * the setup is made.
     * @param message
 */    
    public static void logMessage(String message)
    {
        if(error == null)
            setup();
        event.log(Level.INFO, message);
    }
/**
 * This logError method is passed down String message, and through the if-statement
 * the warning messages are made and the error is logged.
     * @param message
 */    
    public static void logError(String message)
    {
        if(error == null)
        setup();
        log.warning("Warning Message"+message);
        JOptionPane.showMessageDialog(null, "Error:" + message,
                "Error", JOptionPane.WARNING_MESSAGE);
        logMessage("Error Logged");
    }
/**
 * This logNonRecoverableError method receives the String message, and through the
 * if-statement, the error is recorded and the user is prompted.
     * @param message
 */    
    public static void logNonRecoverableError(String message)
    {
        if(error == null)
        setup();
        log.log(Level.SEVERE, message+" please restart the game");
        JOptionPane.showMessageDialog(null, "Error:" + message + " please restart the game",
                "CRASH!!!", JOptionPane.ERROR_MESSAGE);
        logMessage("NonRecoceravleError...Exiting...Restart the game");
        System.exit(0);
    }
    
}
