package Model;

import java.io.Serializable;
/**
 * Abstract parent class, used for creating different types of vehicle objects. This class is used and refernced in order to create
 * vehicle objects through the use of it's child classes - Asian, Domestic, European and Unknown.
 */
public abstract class Vehicle implements Serializable, Comparable {
    private final long serialVersionUID = 1212201911l;
    private int mYear, mCylinders;
    private String mMake, mModel, placeManufactured;
    private double mDisplacement;

    /**
     * Constructs a generic version of a vehicle given the following parameters,
     * does not determince the place manufactured.
     * @param year
     * @param cylinders
     * @param make
     * @param model
     * @param displacement
     */
    public Vehicle(int year, int cylinders, String make, String model, double displacement) {
        mYear = year;
        mCylinders = cylinders;
        mMake = make;
        mModel = model;
        mDisplacement = displacement;
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

            int makeDiff = mMake.compareTo(other.mMake);

            if (makeDiff != 0)
                return makeDiff;

            int modelDiff = mModel.compareTo(other.mModel);

            if (modelDiff != 0)
                return modelDiff;

            int cylindersDiff = (int) (mCylinders- other.mCylinders);

            if (cylindersDiff != 0)
                return cylindersDiff;

            int yearDiff = (int) (mYear - other.mYear);

            if (yearDiff != 0)
                return yearDiff;

            int displacementDiff = (int) (mDisplacement - other.mDisplacement);

            if (displacementDiff != 0)
                return displacementDiff;
        }

        return -1;



    }

    /**
     * returns placeManufactured variable
     * @return
     */
    public String getPlaceManufactured() {
        return placeManufactured;
    }

    /**
     * Sets place manufactured variable to another string.
     * @param placeManufactured
     */
    public void setPlaceManufactured(String placeManufactured) {
        this.placeManufactured = placeManufactured;
    }

    /**
     * Returns year variable.
     * @return
     */
    public int getYear() {
        return mYear;
    }

    /**
     * sets the year variable to another integer
     * @param year
     */
    public void setYear(int year) {
        mYear = year;
    }

    /**
     * returns cylinders variable
     * @return
     */
    public int getCylinders() {
        return mCylinders;
    }

    /**
     * sets the cylinders to another integer
     * @param cylinders
     */
    public void setCylinders(int cylinders) {
        mCylinders = cylinders;
    }

    /**
     * returns the make variable
     * @return
     */
    public String getMake() {
        return mMake;
    }

    /**
     * Sets the make to another string.
     * @param make
     */
    public void setMake(String make) {
        mMake = make;
    }

    /**
     * returns models
     * @return
     */
    public String getModel() {
        return mModel;
    }

    /**
     * sets the model variable to another string
     * @param model
     */
    public void setModel(String model) {
        mModel = model;
    }

    /**
     * returns the displacement variable
     * @return
     */
    public double getDisplacement() {
        return mDisplacement;
    }

    /**
     * sets the displacement variable to another double.
     * @param displacement
     */
    public void setDisplacement(double displacement) {
        mDisplacement = displacement;
    }

    /**
     * toString method which generates a string that contains the Domestic vehicle object's specs, such as
     * year, make, model, cylinders, displacement, and place manufactured.
     * @return
     */
    @Override
    public String toString() {

        return "Vehicle Spec's:\n" + mYear + " " + mMake +" " + mModel +"\nCylinders: " + mCylinders +
                "\nDisplacement: "+ mDisplacement + " L" + placeManufactured;

    }
}
