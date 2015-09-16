
package pyramid.plunder;

/**
 * This class is called when the game begins
 */
public class PyramidPlunder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
        ErrorLogger.logMessage("Running Code");
        MainMenu m = new MainMenu();
        m.start();
        }
        catch(Exception e)
        {
            ErrorLogger.logNonRecoverableError("Error in starting the game " + e);
        }
    }
}
