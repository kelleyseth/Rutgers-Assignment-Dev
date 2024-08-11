package dna;

import java.util.Arrays;

/**
 * Profile Class to store the the profile of the person which includes
 * any information they will have such as name, STRs, and DNA sequence.
 * 
 * DO NOT EDIT
 * 
 * @author Seth Kelley
 * @author Aastha Gandhi
 */
public class Profile {
    // Instance variables
    private String name; // Person's name
    private STR[] C1_STRs; // Person's array of a chromosome STRs
    private STR[] C2_STRs; // Person's array of a chromosome STRs
    private String sequence1; // Person's full DNA sequence from a chromosome
    private String sequence2; // Person's full DNA sequence from a chromosome

    // Default Constructor
    public Profile() {
        this.name = "None";
        this.C1_STRs = new STR[0];
        this.C2_STRs = new STR[0];
        this.sequence1 = "";
        this.sequence2 = "";
    }

    // Constructor
    public Profile(String name, STR[] C1_STRs, STR[] C2_STRs, String sequence1, String sequence2) {
        this.name = name;
        this.C1_STRs = C1_STRs;
        this.C2_STRs = C2_STRs;
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;

    }

    // toString
    public String toString() {
        String str = "\n____________________\n";
        str += "\nName: " + name + "\n";
        str += "\nSequence 1 STRs: " + Arrays.toString(C1_STRs).replace("[", "").replace("]", "").replace(",", "")
                + "\n";
        str += "\nSequence 2 STRs: " + Arrays.toString(C2_STRs).replace("[", "").replace("]", "").replace(",", "")
                + "\n";
        str += "\nChromosome 1 sequence: " + sequence1 + "\n";
        str += "Chromosome 2 sequence: " + sequence2;
        str += "\n____________________\n";

        return str;
    }

    // Returns person's name
    public String getName() {
        return name;
    }

    // Returns the STR array
    public STR[] getC1STRs() {
        return C1_STRs;
    }

    // Returns the STR array
    public STR[] getC2STRs() {
        return C2_STRs;
    }

    // Returns the full DNA sequence
    public String getSequence1() {
        return sequence1;
    }

    // Returns the full DNA sequence
    public String getSequence2() {
        return sequence2;
    }

    // Sets the STR array of the person's profile object
    public void setC1STRs(STR[] C1_STRs_array) {
        this.C1_STRs = C1_STRs_array;
        return;

    }

    // Sets the STR array of the person's profile object
    public void setC2STRs(STR[] C2_STRs_array) {
        this.C2_STRs = C2_STRs_array;
        return;
    }

    // Returns true if this object equals other, false otherwise
    public boolean equals(Object other) {
        if (other == null || !(other instanceof Profile)) {
            return false;
        }
        Profile o = (Profile) other;
        return name.equals(o.name) &&
                Arrays.equals(C1_STRs, o.getC1STRs()) &&
                Arrays.equals(C2_STRs, o.getC2STRs()) &&
                sequence1.equals(o.getSequence1()) &&
                sequence2.equals(o.getSequence2());
    }

}