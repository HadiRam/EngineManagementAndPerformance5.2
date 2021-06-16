package View;

import Controller.Controller;
import Model.Asian;
import Model.Domestic;
import Model.European;
import Model.Vehicle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * Modify scene in which a user can select a vehicle from the list view on the main scene, and then choose to
 * modify that vehicle, which opens this scene, and allows the user to change the specs of a vehicle that
 * they had already added to the list view on the main scene.
 */
public class ModifyScene extends Scene {
    private Scene mPrevScene;
    private Vehicle mVehicleToModify;

    private Controller controller = Controller.getInstance();

    private Button modifyButton = new Button("Modify");

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
     * Sets up the window dimensions of the Modify scene, sets up the modify and cancel buttons
     * with appropriate action events. Ensures user typed in proper submissions to text fields,
     * otherwise error label is shown.
     * @param mainScene
     * @param selectedVehicle
     */
    public ModifyScene(MainScene mainScene, Vehicle selectedVehicle) {
        super(new GridPane(),450,250);
        mPrevScene = mainScene;
        mVehicleToModify = selectedVehicle;
        GridPane pane = new GridPane();

        pane.add(modifyButton, 1, 6);


        locationManufacturedCB = new ComboBox<>();
        locationManufacturedCB.getItems().addAll("Asia","Europe","America");
        pane.add(new Label("Place Manufactured:"), 0, 5);
        pane.add(locationManufacturedCB,1,5);
        pane.add(locationManufacturedLabel,2,5);
        locationManufacturedLabel.setTextFill(Color.RED);
        locationManufacturedLabel.setVisible(false);

        pane.add(new Label("Make:"), 0, 0);
        makeTF.setText(mVehicleToModify.getMake());
        pane.add(makeTF, 1, 0);
        pane.add(makeLabel, 2, 0);
        makeLabel.setTextFill(Color.RED);
        makeLabel.setVisible(false);

        pane.add(new Label("Model:"), 0, 1);
        modelTF.setText(mVehicleToModify.getModel());
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
        modifyButton.setOnAction(event -> modify());

        this.setRoot(pane);
    }


    /**
     * Modify method in which the new vehicle specifications are recieved from
     * the user inputs to the text fields and combo-box. Ensures there are no
     * errors within the user inputs. Constructs a new vehicle with updated
     * variables and removes the old vehicle, effectively "Modifying" that vehicle.
     */
    private void modify() {

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



        //TIf any of the error labels are visible, return.
        if(cylinderLabel.isVisible() || yearLabel.isVisible() || makeLabel.isVisible() ||
                modelLabel.isVisible()|| displacementLabel.isVisible() || locationManufacturedLabel.isVisible())
            return;
        // Otherwise, instantiate a Vehicle car object and add it to the list of vehicles.
        double finalDisplacement = displacement;
        int finalCylinders = cylinders;
        int finalYear = year;
        String placeManufactured = "";

        if(locationManufacturedCB.getSelectionModel().getSelectedIndex() == 0){

            removeVehicle(mVehicleToModify);
            Vehicle newVehicle = new Asian(finalYear, finalCylinders, make, model, finalDisplacement, placeManufactured);
            controller.getAllVehicles().add(newVehicle);


        }else if(locationManufacturedCB.getSelectionModel().getSelectedIndex() == 2){
            removeVehicle(mVehicleToModify);
            Vehicle newVehicle = new Domestic(finalYear, finalCylinders, make, model, finalDisplacement, placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }else{
            removeVehicle(mVehicleToModify);
            Vehicle newVehicle = new European(finalYear, finalCylinders, make, model, finalDisplacement, placeManufactured);
            controller.getAllVehicles().add(newVehicle);
        }





        goBackToPrevScene();


    }

    /**
     * Removes a vehicle from the list view.
     * @param vehicleToModify
     */
    private void removeVehicle(Vehicle vehicleToModify){
        if(mVehicleToModify == null){
            return;
        }else{
           controller.getAllVehicles().remove(mVehicleToModify);
        }


    }


    /**
     * Returns to the previous scene. (Main Scene)
     */
    private void goBackToPrevScene() {
        // Navigate back to the previous scene (e.g. MainScene)

        ViewNavigator.loadScene("EMP", mPrevScene);
    }

}
