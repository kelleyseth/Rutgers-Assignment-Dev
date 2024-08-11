package dna;

/**
 * Used to create an STR object which will be used to differentiate the type
 * of STR being read and how many repeats there are in the DNA sequence.
 * 
 * DO NOT EDIT
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */
public class STR {

    private String STR; // String value of the STR
    private int numOfRepeats; // Number of times the STR is repeated

    /**
     * Constructor for STR
     * 
     * @param STR The STR name of the STR Object
     * @param numOfRepeats How many repeats this STR has
     */
    public STR(String STR, int numOfRepeats) {
        this.STR = STR;
        this.numOfRepeats = numOfRepeats;
    }

    /**
     * Gets the String name of the STR
     * 
     * @return The String name of this Object
     */
    public String getSTR() {
        return STR;
    }

    /**
     * Gets the length as an int of how many times this STR is repeated
     * 
     * @return The number of times the STR is repeated
     */
    public int getLength() {
        return numOfRepeats;
    }

    /**
     * Sets the length of the STR
     * 
     * @param numOfRepeats The number of repeats to set the STR to
     */
    public void setLength(int numOfRepeats) {
        this.numOfRepeats = numOfRepeats;
    }

    /**
     * String representation for the STR Object
     * 
     * @param a string that has the name of the STR, and the number of repeats
     */
    public String toString() {
        return "\nSTR Type: " + STR + "\tAmount of Repeats: " + numOfRepeats;
    }

    /**
     * Equals method for the STR Object
     * 
     * @return true if two STR objects hold the same information, false otherwise
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        STR other = (STR) obj;
        if (STR == null) {
            if (other.STR != null)
                return false;
        } else if (!STR.equals(other.STR))
            return false;
        if (numOfRepeats != other.numOfRepeats)
            return false;
        return true;
    }
}
