package Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Concrete Child class of Vehicle, for vehicles manufactured domestically. This class constructs a new vehicle object with
 * variables taken from user inputs from the addVehicle or modifyVehicle scene.
 */
public class Domestic extends Vehicle implements Serializable, Comparable {
    private  String mDOMESTIC = "Domestic";
    private final long serialVersionUID = 1212201911l;

    /**
     * Constructs a Vehicle manufactured within domestically, given the following parameters.
     * @param year
     * @param cylinders
     * @param make
     * @param model
     * @param displacement
     * @param DOMESTIC
     */
    public Domestic(int year, int cylinders, String make, String model, double displacement, String DOMESTIC) {
        super(year, cylinders, make, model, displacement);
        mDOMESTIC = DOMESTIC;
    }

    /**
     * toString method which generates a string that contains the Domestic vehicle object's specs, such as
     * year, make, model, cylinders, displacement, and place manufactured.
     * @return
     */

    @Override
    public String toString() {
        return "Vehicle Spec's:\n" + getYear() + " " + getMake() +" " + getModel() +"\nCylinders: " + getCylinders() +
                "\nDisplacement: "+ getDisplacement() + " L" + " \n(Manufactured In America)";

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
     * Method to return the mDomestic variavle.
     * @return
     */
    public String getDOMESTIC() {
        return mDOMESTIC;
    }

    /**
     * Method to set the mDomestic variable to another string
     * @param DOMESTIC
     */
    public void setDOMESTIC(String DOMESTIC) {
        mDOMESTIC = DOMESTIC;
    }

    /**
     * equals method to compare objects for equality
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Domestic domestic = (Domestic) o;
        return mDOMESTIC.equals(domestic.mDOMESTIC);
    }

    /**
     * Returns hashcode for the mDomestic variable.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(mDOMESTIC);
    }
}
