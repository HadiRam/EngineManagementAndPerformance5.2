package View;


import Controller.Controller;
import Model.Vehicle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.css.Size;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;

/**
 * Main Scene class that extends scene. This scene is opened when the user first starts
 * the program. This scene includes the list View of all vehicles, along with several buttons
 * that serve different functions of the program,
 */
public class MainScene extends Scene {

    /**
     * Constants for the background window dimensions
     */
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private Vehicle selectedVehicle;

    //setting up the controller
    private Controller controller = Controller.getInstance();

    //initializing all the buttons required
    private Button vehicleAdd = new Button("Add new vehicle"), removeVehicle = new Button("Remove vehicle"),
            modifyVehicle = new Button("Modify Vehicle"), displayTechTips = new Button("Display Random Tech Tip"),
            findOBD2 = new Button("Find OBD2 Code");


    //initializing the Image view and List view
    private ImageView backGroundIV = new ImageView();

    private ListView<Vehicle> vehicleLV = new ListView<>();

    //Initializing the title label
    private Label titleLabel = new Label("Welcome to Engine Management and Performance (EMP) Tool");

    //Initializing the list
    private ObservableList<Vehicle> vehicleList;


    /**
     * Main scene method that constructs the main scene window. Sets up the dimensions of the window, adds the
     * List View, adds several buttons and Labels. Sets up action events for those buttons.
     */
    public MainScene() {


        super(new GridPane(), WIDTH, HEIGHT);
        vehicleList = controller.getAllVehicles();


        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

//Setting up the background image
        backGroundIV.setImage(new Image("FordGt.png"));
        backGroundIV.setFitWidth(WIDTH);
        backGroundIV.setFitHeight(HEIGHT / 2.5);

        pane.add(backGroundIV, 0, 0, 3, 3);


//adding buttons to the scene
        pane.add(vehicleAdd, 2, 60);
        pane.add(removeVehicle, 2, 80);
        pane.add(modifyVehicle, 4, 80);
        pane.add(displayTechTips, 4, 60);
        pane.add(findOBD2, 6, 80);
        //pane.add(selectedVehicle,1,20);


        //Adding the List View
        vehicleList = controller.getAllVehicles();
        vehicleLV.setItems(vehicleList);

        vehicleLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Vehicle>() {
            /**
             * Overridden method that checks for a selection within the List View of vehicles, and un-disables
             * the remove, modify and findOBD2 buttons if a selection is made.
             * @param observableValue
             * @param oldValue
             * @param newValue
             */
            @Override
            public void changed(ObservableValue<? extends Vehicle> observableValue, Vehicle oldValue, Vehicle newValue) {
                selectedVehicle = newValue;
                removeVehicle.setDisable(selectedVehicle == null);
                modifyVehicle.setDisable(selectedVehicle == null);
                findOBD2.setDisable(selectedVehicle==null);
            }
        });





        vehicleLV.setPrefWidth(WIDTH);
        vehicleLV.setPrefHeight(HEIGHT * 1.5);
        pane.add(vehicleLV, 0, 70, 10, 10);

        //Adding the title label
        pane.add(titleLabel, 2, 40, 10, 20);
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setFont(Font.font(20));

        //Setting up an Action Event for the buttons
        vehicleAdd.setOnAction(event -> addVehicle());
        removeVehicle.setDisable(true);
        modifyVehicle.setDisable(true);
        findOBD2.setDisable(true);
        removeVehicle.setOnAction(event -> removeVehicle());
        modifyVehicle.setOnAction(event -> modifyVehicle());
        displayTechTips.setOnAction(event -> TechTipDisplay());
        findOBD2.setOnAction(event -> obd2CodeDisplay());



        this.setRoot(pane);

    }

    /**
     * Method that opens the OBD2CodeScene
     */
    private void obd2CodeDisplay(){
        ViewNavigator.loadScene("ODB2 Code for Selected Vehicle", new obd2CodeScene(this));

    }

    /**
     * Method that opens the TechTipDislplayScene
     */
    private void TechTipDisplay(){
        ViewNavigator.loadScene("Random Tech Tip", new TechTipDisplayScene(this));
    }

    /**
     * Method that opens the modify vehicle scene
     */
    private void modifyVehicle() {
        if(selectedVehicle == null)
            return;
        ViewNavigator.loadScene("Modify Vehicle", new ModifyScene(this,selectedVehicle));
    }

    /**
     * Method that opens the AddVehicle scene
     */
    private void addVehicle() {
        // Use the ViewNavigator to load the AddScene
        ViewNavigator.loadScene("Add Vehicle", new AddScene(this));
        // Update the display when done.
        //updateDisplay();

    }

    /**
     * Method that removes a selected vehicle from the list view.
     */
    private void removeVehicle(){
        if(selectedVehicle == null){
            return;
        }else{
            vehicleList.remove(selectedVehicle);
        }

        updateDisplay();

    }

    /**
     * Method that refreshes the list view.
     */
    private void updateDisplay()
    {
        vehicleLV.refresh();
    }

}

