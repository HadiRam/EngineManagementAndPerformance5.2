package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * The <code>Model</code> class represents the business logic (data and calculations) of the application.
 * In the EMP app, it will be responsible for finding the OBD2 code that is being searched for,
 * the initial tech tip that will be displayed at the start screen of the program, and identifying which categories of
 * background images should be displayed, dependent on what the preferences of the user are.
 * It is also responsible for saving the users "Garage" to the binary file, so that next time they want to find an obd2
 * code all they have to do is select the vehicle and place the code in the search bar to find a manufacturer specific
 * code.
 *
 * @author Jose I Villa
 * @version 1.0
 */
public class Model {

    public static final String BINARY_FILE = "Vehicles.dat";
    public static ObservableList<String> codeList = FXCollections.observableArrayList();
    public static String codeFound = "";
    public static  int codeIndex;
    /**
     * Determines whether the binary file exists and has data (size/length > 0).
     * @return True if the binary file exists and has data, false otherwise.
     */
    public static boolean binaryFileHasData()
    {
        // TODO: Create a File reference to the binary file
        // TODO: Return whether the binary file exists and has > 0 size
        File binaryFile = new File(BINARY_FILE);
        return (binaryFile.exists() && binaryFile.length() > 0);
    }

    /**
     * This method is responsible for populating a list that will contain all the codes that will be searched for and passed
     * onto the <code>obd2CodeScene</code> so that searching for a vehicle code will be a smoother process.
     * @return the list of all generic OBD2 codes populated from the csv file
     */
    public static boolean populateCodeList(){
        try {
            String output = "";
            String[] temp= new String[2];
            Scanner infile = new Scanner(new File("C:\\Users\\w7250986\\IdeaProjects\\EngineManagementAndPerformance3.0\\res\\obd-trouble-codes.csv"));
            while (infile.hasNextLine()) {
                temp = infile.nextLine().split(",");
                codeList.add( temp[0].replaceAll("\""," ").trim() + "," + temp[1].replaceAll("\""," ").trim());
            }
            codeList.add("The code you searched for was not found");
        }catch(IOException e)
        {
            e.getMessage();
            return false;
        }
        return true;
    }
    /**
     * This method is responsible for populating a list that will contain all the codes that will be searched for and passed
     * onto the <code>obd2CodeScene</code> so that searching for a vehicle code will be a smoother process.
     * @param code
     * @return
     */
    public static int findCode(String code){
        codeIndex = -1;
        String[]temp;
        String current;
        code =  code.toUpperCase();
        for ( int i = 0; i < codeList.size(); i++) {
            current = codeList.get(i);
            temp = current.split(",");
            if (code.equalsIgnoreCase(temp[0])){
                codeFound = codeList.get(i);
                codeIndex = i;
                return i;
            }
        }
        codeIndex = codeList.size();
        return codeList.size();
    }

    /**
     * Populates the list of all Vehicles from the binary file. This will be called every time besides the first
     * since the list of Vehicles will start empty.
     * @return The list of all Vehicles populated from the binary file
     */
    public static ObservableList<Vehicle> populateListFromBinaryFile()
    {
        ObservableList<Vehicle> allVehicles= FXCollections.observableArrayList();
        // TODO: Create a File reference to the binary file
        // TODO: Check to see if the binary file exists
        // TODO: Instantiate an ObjectInputStream reference to the binary file for reading
        // TODO: Create a temp array of Vehicle objects to read from the binary file
        // TODO: Initialize the temp array from the binary file reader.
        // TODO: Add the temp array to the collection of allVehicles(list)
        // TODO: Close the binary file reader.
        File binaryFile = new File(BINARY_FILE);
        try{
            if (binaryFile.exists()) {
                ObjectInputStream infile = new ObjectInputStream(new FileInputStream(binaryFile));
                Vehicle[] tempArray = (Vehicle[])infile.readObject();
                allVehicles.addAll(tempArray);
                infile.close();
            }
        }catch(IOException | ClassNotFoundException e){
            System.err.println("Problem encountered when trying to traverse file");
            System.out.println(e.getMessage());
        }
        return allVehicles;
    }

    /**
     * Saves the list of all influencers to the binary file. This will be called each time the application stops,
     * which occurs when the user exits/closes the app.  Note this method is called in the View, by the controller,
     * during the stop() method.
     * @return True if the data were saved to the binary file successfully, false otherwise.
     */
    public static boolean writeDataToBinaryFile(ObservableList<Vehicle> allVehicleList)
    {
        // TODO: Create a File reference to the binary file
        // TODO: Instantiate an ObjectOutputStream reference to the binary file for writing
        // TODO: Create a temp array of Influencer objects to read from the binary file (length should match list size)
        // TODO: Loop through the temp array and initialize each element to the corresponding element in the list
        // TODO: Write the temp array object to the binary file writer
        // TODO: Close the binary file writer and return true.
        // TODO: If an exception occurs, print its message and return false.
        File binaryFile = new File(BINARY_FILE);
        try{
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            Vehicle[] tempArray = new Vehicle[allVehicleList.size()];
            // fill array with all elements in list
            for (int i = 0; i < tempArray.length; i++)
            {
                tempArray[i] = allVehicleList.get(i);
            }
            fileWriter.writeObject(tempArray);
            fileWriter.close();
        }catch(IOException e){
            return false;
        }
        return true;
    }

}
