package View;

import Controller.Controller;
import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * AddScence class extends Scence. This class is used to create a new scene that is displayed when the user clicks the
 * "Add Vehicle" button on the main scene. This scene allows the user to enter their vehicles specs, and when submitted,
 * adds the vehicle to the list view on the Main Scene
 */
public class AddScene extends Scene {

    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();

    private Button saveButton = new Button("Save");

    private ComboBox<String> locationManufacturedCB;
    private Label locationManufacturedLabel = new Label("Location Manufactured is required.");

    private TextField makeTF = new TextField();
    private Label makeLabel = new Label("Make is required.");

    private TextField cylindersTF = new TextField();
    private Label cylinderLabel = new Label("Cylinder amount is required.");

    private TextField yearTF = new TextField();
    private Label yearLabel = new Label("year is required.");


    private TextField modelTF = new TextField();
    private Label modelLabel = new Label("Model is required.");

    private TextField displacementTF = new TextField();
    private Label displacementLabel = new Label("displacement is required.");

    private Button cancelButton = new Button("Cancel");

    /**
     * Add scene method in which the scene itself is displayed in a new window, using a grid pane to organise the different
     * elements on the scene. Sets up a combo box and several text Fields in order to take in user inputs.
     * Sets error labels to visible if the user forgets to enter information within a given text field.
     *
     * @param prevScene
     */
    public AddScene(Scene prevScene) {
        super(new GridPane(),450,250);
        mPrevScene = prevScene;

        GridPane pane = new GridPane();

        pane.add(saveButton, 1, 6);
        saveButton.setOnAction(event -> save());

        locationManufacturedCB = new ComboBox<>();
        locationManufacturedCB.getItems().addAll("Asia","Europe","America","Unkown");
        pane.add(new Label("Place Manufactured:"), 0, 5);
        pane.add(locationManufacturedCB,1,5);
        pane.add(locationManufacturedLabel,2,5);
        locationManufacturedLabel.setTextFill(Color.RED);
        locationManufacturedLabel.setVisible(false);

        pane.add(new Label("Make:"), 0, 0);
        pane.add(makeTF, 1, 0);
        pane.add(makeLabel, 2, 0);
        makeLabel.setTextFill(Color.RED);
        makeLabel.setVisible(false);

        pane.add(new Label("Model:"), 0, 1);
        pane.add(modelTF, 1, 1);
        pane.add(modelLabel, 2, 1);
        modelLabel.setTextFill(Color.RED);
        modelLabel.setVisible(false);


        pane.add(new Label("Year:"), 0, 2);
        pane.add(yearTF, 1, 2);
        pane.add(yearLabel,2,2);
        yearLabel.setTextFill(Color.RED);
        yearLabel.setVisible(false);

        pane.add(new Label("Cylinders:"), 0, 3);
        pane.add(cylindersTF, 1, 3);
        pane.add(cylinderLabel,2,3);
        cylinderLabel.setTextFill(Color.RED);
        cylinderLabel.setVisible(false);

        pane.add(new Label("Displacement:"), 0, 4);
        pane.add(displacementTF, 1, 4);
        pane.add(displacementLabel,2,4);
        displacementLabel.setTextFill(Color.RED);
        displacementLabel.setVisible(false);


        pane.add(cancelButton, 0, 6);
        cancelButton.setOnAction(event -> goBackToPrevScene());

        this.setRoot(pane);
    }

    /**
     * Method to set up the save buttons functions. This method will ensure all user inputs are given, and then
     * saves those user inputs to variables. These variables are used to create Vehicle objects using specific child
     * classes depending on placeManufactured variable.
     */
    private void save() {
        //
        String make, model;
        double displacement=0.0;
        int year = 0, cylinders = 0;

        make = makeTF.getText();
        makeLabel.setVisible(make.isEmpty());

        model = modelTF.getText();
        modelLabel.setVisible(model.isEmpty());

        try {
            displacement = Double.parseDouble(displacementTF.getText());
            if (displacement < 0.0)
                displacementLabel.setVisible(true);
        }
        catch (NumberFormatException e) {  displacementLabel.setVisible(true); }

        try {
            year = Integer.parseInt(yearTF.getText());
            if (year < 0)
                yearLabel.setVisible(true);
        }
        catch (NumberFormatException e) {
            yearLabel.setVisible(true);
        }

        try {
            cylinders = Integer.parseInt(cylindersTF.getText());
            if (cylinders < 0)
                cylinderLabel.setVisible(true);
        }
        catch (NumberFormatException e) {
            cylinderLabel.setVisible(true);
        }



        //If any of the error labels are visible, return.
        if(cylinderLabel.isVisible() || yearLabel.isVisible() || makeLabel.isVisible() ||
                modelLabel.isVisible()|| displacementLabel.isVisible() || locationManufacturedLabel.isVisible())
            return;
        // Otherwise, instantiate a Vehicle car object and add it to the list of vehicles.
        double finalDisplacement = displacement;
        int finalCylinders = cylinders;
        int finalYear = year;
        String placeManufactured = "";

        if(locationManufacturedCB.getSelectionModel().getSelectedIndex() == 0){


            Vehicle newVehicle = new Asian(finalYear, finalCylinders, make, model, finalDisplacement,placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }else if(locationManufacturedCB.getSelectionModel().getSelectedIndex() == 2){
            Vehicle newVehicle = new Domestic(finalYear, finalCylinders, make, model, finalDisplacement, placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }else if (locationManufacturedCB.getSelectionModel().getSelectedIndex() == 1) {
            Vehicle newVehicle = new European(finalYear, finalCylinders, make, model, finalDisplacement, placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }else{
            Vehicle newVehicle = new Unkown(finalYear,finalCylinders,make,model,finalDisplacement,placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }





        goBackToPrevScene();

    }

    /**
     * Method that allows the user to return to the previous scene. (Returns to the Main Scene).
     */

    private void goBackToPrevScene() {
        // Navigate back to the previous scene (e.g. MainScene)
        ViewNavigator.loadScene("Engine Management and Performance(EMP)", mPrevScene);
    }
}
