package View;

import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * This gives the user a random tech tip for their vehicle. After this scene is opened,
 * a random tech tip is immediately displayed. The tech tip is retrieved from the techTips.txt file.
 */
public class TechTipDisplayScene extends Scene {



    private Scene mPrevScene;

    private Controller controller = Controller.getInstance();

    private Button closeButton = new Button("close");

    private Label randomTechTipLabel = new Label();


    /**
     * This method retrieves a random tech tip from the techTips.txt file, and displays it for the user,
     * everytime this scene is opened. Also sets up the cancel button.
     * @param prevScene
     */
    public TechTipDisplayScene(Scene prevScene) {
        super(new GridPane(), 800, 250);

        String tip = "";
        int i = 0;
        mPrevScene = prevScene;
        GridPane pane = new GridPane();
        String[] tipList = new String[15];
            try {
                Scanner infile = new Scanner(new File("res//techTips.txt"));
                while(infile.hasNextLine())
                {
                    tipList[i++] = infile.nextLine();
                }
                infile.close();
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            }
        Random rdm = new Random();
        int randomIndex = rdm.nextInt(i);
        randomTechTipLabel.setText(fitToWindow(tipList[randomIndex]));
        randomTechTipLabel.setTextFill(Color.BLACK);
        randomTechTipLabel.setFont(Font.font(20));
        pane.add(randomTechTipLabel, 3, 20, 100, 200);


        pane.add(closeButton, 0, 10);
        closeButton.setOnAction(event -> goBackToPrevScene());

        this.setRoot(pane);
    }

    /**
     * This method eensures that the tech tip string is displayed
     * properly within the TechTipDisplayScene.
     * @param s
     * @return
     */
 private String fitToWindow(String s){
        String[] sentence = s.split(" ");
        String output = "";
        for (int i = 0; i < sentence.length; i++){
            if ( i != 0 && i %10 == 0)
                sentence[i] += "\n";
            output += sentence[i] + " ";
        }
        return  output;
 }

    /**
     * Opens the previous scene (Main Scene).
     */
    private void goBackToPrevScene() {
        ViewNavigator.loadScene("Engine Management and Performance(EMP)", mPrevScene);
    }
}