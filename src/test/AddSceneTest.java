import Controller.Controller;
import Model.Asian;
import Model.Vehicle;
import View.AddScene;
import View.MainScene;
import View.ViewNavigator;
import javafx.scene.Scene;
import javafx.scene.shape.Cylinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class AddSceneTest {

    private Scene mPrevScene;
    private Scene MainScene = mPrevScene;


    @BeforeEach
    void setUp() {
       Controller.getInstance().getAllVehicles().clear();
    }


    @Test
    void save(){
        Vehicle o = new Asian(1999, 6, "Honda", "Civic", 200, "Asian");
        Controller.getInstance().getAllVehicles().add(0,o);
        Controller.getInstance().saveData();
        assertEquals(o,Controller.getInstance().getAllVehicles().get(0));

    }
    
    @Test
    void goBackToPrevScene(){
        assertEquals(MainScene,mPrevScene);
        
        
    }




}