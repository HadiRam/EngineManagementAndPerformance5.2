package Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Concrete Child class of Vehicle, for vehicles manufactured in Europe. This class constructs a new vehicle object with
 * variables taken from user inputs from the addVehicle or modifyVehicle scene.
 */
public class Unkown extends Vehicle implements Serializable, Comparable {
    private  String mUnkown = "Unkown";


    /**
     * toString method which generates a string that contains the Domestic vehicle object's specs, such as
     * year, make, model, cylinders, displacement, and place manufactured.
     * @return
     */
    @Override
    public String toString() {
        return "Vehicle Spec's:\n" + getYear() + " " + getMake() +" " + getModel() +"\nCylinders: " + getCylinders() +
        "\nDisplacement: "+ getDisplacement() + " L" + " \n(Place Manufactured is Unknown)";
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
     * Constructs a vehicle where the location manufactured is unknown.
     * @param year
     * @param cylinders
     * @param make
     * @param model
     * @param displacement
     * @param unkown
     */
    public Unkown(int year, int cylinders, String make, String model, double displacement, String unkown) {
        super(year, cylinders, make, model, displacement);
        mUnkown = unkown;
    }

    /**
     * Returns unknown variable
     * @return
     */
    public String getUnkown() {
        return mUnkown;
    }

    /**
     * Sets unknown variable to a different string
     * @param unkown
     */
    public void setUnkown(String unkown) {
        mUnkown = unkown;
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
        Unkown unkownp = (Unkown) o;
        return mUnkown.equals(unkownp.mUnkown);
    }

    /**
     * returns hashCode for unkown variable.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(mUnkown);
    }
}
