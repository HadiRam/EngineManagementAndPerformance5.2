package Controller;
import Model.Model;
import Model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 * @author Jose I Villa
 * @version 1.0
 */
public class Controller {

    private static Controller theInstance;
    private ObservableList<Vehicle> mAllVehiclesList;

    // private constructor that enforces Singleton pattern
    private Controller() { }

    /**
     * Gets the one instance of the Controller.
     * @return The instance
     */
    public static Controller getInstance() {

        if (theInstance == null) {
            theInstance = new Controller();
            if (Model.binaryFileHasData())
            {
                theInstance.mAllVehiclesList = Model.populateListFromBinaryFile();
            }
            else
                theInstance.mAllVehiclesList = FXCollections.observableArrayList();
        }
        return theInstance;
    }




    /**
     * Gets the list of all Vehicles.
     * @return The list of all Vehicles.
     */
    public ObservableList<Vehicle> getAllVehicles() {
        return mAllVehiclesList;
    }

    /**
     * Makes a request for the model to save all the vehicles data (the list of all vehicles) to
     * a persistent binary file.
     */
    public void saveData() {
        Model.writeDataToBinaryFile(mAllVehiclesList);
    }
}
