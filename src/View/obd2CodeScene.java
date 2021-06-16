package View;

import Controller.Controller;
import Model.Model;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Author: Jose I Villa
 * purpose: The use of this scene is for the display of generic vehicle OBD2 codes, the textField is used for
 * finding a unique code.
 */
public class obd2CodeScene extends Scene {

    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();
    private String textBoxSearch;
    private Button closeButton = new Button("Return to EMP"), searchButton = new Button("Find Code");
    private TextField obd2SearchBar = new TextField();
    private String foundCode = "Here is the definition of the code: " + obd2SearchBar.getText().toUpperCase() + "\n";
    private Label search = new Label("Please enter your OBD2 DTC  "), codeFound = new Label();
    private HBox codeFinder = new HBox(search, obd2SearchBar, searchButton);

    ListView<String> obdListView = new ListView<>();
    ObservableList<String> obdList;

    /**
     * Constructor for the OBD2 scene, contains the majority of code needed to run scene "function", the
     * file proccessing code is located in the Model.class.
     * @param prevScene
     */
    public obd2CodeScene(Scene prevScene) {
        super(new GridPane(), 800, 250);
        GridPane pane = new GridPane();
        mPrevScene = prevScene;
        search.setTextFill(Color.BLACK);
        search.setFont(Font.font(16));
        //obdList = controller.getAllVehicles();
        obdListView.setItems(obdList);
        pane.add(codeFinder, 1,1,1,1);
        pane.add(closeButton, 2,1 );
        pane.add(obdListView, 1, 2, 4, 1 );
        pane.add(codeFound, 3, 2,1 , 2);
        if (!(Model.codeList.size() > 0)) {
            Model.populateCodeList();
        }
        obdListView.setItems(Model.codeList);
        //searchButton.setOnAction();
        searchButton.setOnAction(event -> Model.findCode(obd2SearchBar.getText()));
        searchButton.setOnAction(event -> codeFound.setText(foundCode + Model.codeFound));


        closeButton.setOnAction(event -> goBackToPrevScene());
        this.setRoot(pane);



    }

    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Engine Management and Performance(EMP)",mPrevScene);
    }

}
