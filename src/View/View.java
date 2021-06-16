package View;

import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.naming.ldap.Control;

/**
 * The <code>View</code> class represents the user interface for the application.  It initializes the
 * primary stage (window), then instructs the ViewNavigator to load the very first scene (the MainScene).
 *
 * @author Jose Villa
 * @version 1.0
 */
public class View extends Application {
    /**
     * Starts the application by setting the stage (window) with a custom Vehicle icon, then navigating
     * to the first scene (canvas), which happens to be the MainScene for this application.
     * @param primaryStage The primary stage (window)
     * @throws Exception if one occurs during startup.
     */
    public void start(Stage primaryStage) throws Exception{
        ViewNavigator.setStage(primaryStage);
        ViewNavigator.loadScene("Engine Management and Performance(EMP)", new MainScene());

    }

    /**
     * Stop is called whenever the application shuts down (e.g. user closes the window).
     * In this case we request the Controller to initiate the saving of all influencer data to the binary file.
     */
    @Override
    public void stop() {
        Controller.getInstance().saveData();
    }

    /**
     * The entry point to this JavaFX application.  Application.launch will eventually make a call
     * to the start() method.
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
}
