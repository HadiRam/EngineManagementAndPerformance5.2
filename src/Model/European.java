package Model;

import java.io.Serializable;
import java.util.Objects;
/**
 *Concrete Child class of Vehicle, for vehicles manufactured within Europe. This class constructs a new vehicle object with
 *variables taken from user inputs from the addVehicle or modifyVehicle scene.
 */
public class European extends Vehicle implements Serializable, Comparable {
    private final long serialVersionUID = 1212201911l;
    private String mEUROPEAN = "Europe";


    /**
     * Constructs a Vehicle manufactured within Europe, given the following parameters.
     * @param year
     * @param cylinders
     * @param make
     * @param model
     * @param displacement
     * @param EUROPEAN
     */
    public European(int year, int cylinders, String make, String model, double displacement, String EUROPEAN) {
        super(year, cylinders, make, model, displacement);
        mEUROPEAN = EUROPEAN;
    }

    /**
     * toString method which generates a string that contains the Domestic vehicle object's specs, such as
     * year, make, model, cylinders, displacement, and place manufactured.
     * @return
     */
    @Override
    public String toString() {
        return "Vehicle Spec's:\n" + getYear() + " " + getMake() +" " + getModel() +"\nCylinders: " + getCylinders() +
                "\nDisplacement: "+ getDisplacement() + " L" + " \n(Manufactured In Europe)";

    }


    /**
     * compareTo interface which is implemented in order to compare each variable within a constructed object
     * to other objects within a list. The comparison is used to sort each object alphabetically within a list.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Vehicle other = (Vehicle) o;

        if(o instanceof Vehicle) {

            int makeDiff = getMake().compareTo(other.getMake());

            if (makeDiff != 0)
                return makeDiff;

            int modelDiff = getModel().compareTo(other.getModel());

            if (modelDiff != 0)
                return modelDiff;

            int cylindersDiff = (int) (getCylinders() - other.getCylinders());

            if (cylindersDiff != 0)
                return cylindersDiff;

            int yearDiff = (int) (getYear() - other.getYear());

            if (yearDiff != 0)
                return yearDiff;

            int displacementDiff = (int) (getDisplacement() - other.getDisplacement());

            if (displacementDiff != 0)
                return displacementDiff;


        }

        return -1;
    }

    /**
     * Method to return the mEUROPEAN variable.
     * @return
     */
    public String getEUROPEAN() {
        return mEUROPEAN;
    }

    /**
     * Method to set the mEuropean variable to another string
     * @param EUROPEAN
     */
    public void setEUROPEAN(String EUROPEAN) {
        mEUROPEAN = EUROPEAN;
    }

    /**
     * equals method to compare objects for equality.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        European european = (European) o;
        return mEUROPEAN.equals(european.mEUROPEAN);
    }

    /**
     * Returns hashcode for mEUROPEAN variable.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(mEUROPEAN);
    }
}
