package pacific;

/**
 * This class represents a single piece of garbage debris that can appear in the
 * Great Pacific Garbage Patch.
 * Contains properties like type, timeToDecompose and density,
 * along with getter and setter methods.
 * 
 * @author Kal Pandit
 * @author Seth Kelley
 */
public class Garbage {

    private String type;
    private double timeToDecompose;
    private double density;

    /**
     * Default constructor. Creates an empty Debris object with arbitrary FishingLine
     * type.
     */
    public Garbage() {
        this.type = "FishingLine";
        this.timeToDecompose = 0;
        this.density = 0;
    }

    /**
     * Takes in a name and initializes default values for all others.
     * 
     * @param type the string type of garbage
     */
    public Garbage(String type) {
        this.type = type;
        this.timeToDecompose = 0;
        this.density = 0;
    }

    /**
     * Constructor that takes in type, timeToDecompose, and density.
     * 
     * @param type            one of five predefined types represented as integers
     * @param timeToDecompose the timeToDecompose, in years
     * @param density         the density of the object in g/cm^3
     */
    public Garbage(String type, double timeToDecompose, double density) {
        this.type = type;
        this.timeToDecompose = timeToDecompose;
        this.density = density;
    }

    /**
     * Returns the type of this set of debris.
     * 
     * @return the type of debris
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the timeToDecompose of this set of debris.
     * 
     * @return the timeToDecompose of debris
     */
    public double getTimeToDecompose() {
        return this.timeToDecompose;
    }

    /**
     * Returns the density of this set of debris.
     * 
     * @return the density of debris
     */
    public double getDensity() {
        return this.density;
    }

    /**
     * Sets the timeToDecompose
     * 
     * @param timeToDecompose The new time to decompose it set as
     */
    public void setTimeToDecompose(double timeToDecompose) {
        this.timeToDecompose = timeToDecompose;
    }

    /**
     * Turns this Debris into a MircoPlastic
     */
    public void makeMicroPlastic() {
        this.type = "MicroPlastic";
        this.timeToDecompose = 50000;
        this.density = 1;
    }

    /**
     * Sets the density of the debris
     * @param density Density of the debris
     */
    public void setDensity(double density) {
        this.density = density;
    }

    /**
     * Checks if one piece of debris equals another.
     * 
     * @param other the other piece of debris
     * @return whether the two are equal
     */
    public boolean equals(Garbage other) {
        Garbage debris = other;
        return timeToDecompose == debris.getTimeToDecompose() && density == debris.getDensity() && type.equals(debris.getType());
    }

    /**
     * Outputs a string representation of this piece of debris.
     * 
     * @return the string representation of this object
     */
    public String toString() {
        return "{" +
                 getType() + ", " +
                getTimeToDecompose() + ", " +
                getDensity() +
                "}";
    }
}
