package seam;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Seam Carver shows the process of looking at a picture and finding the seam
 * (think of this as a river) along the width or height.
 * 
 * The seam that is of interest will be the seam that has the lowest energy
 * because that will be the least noticable part of the picture.
 * 
 * To find a seam with the lowest energy we first calculate every pixels energy
 * from the adjacent pixels based on the RGB values unless its a border pixel
 * (which is set to 1000)
 * 
 * These values go into a 2D array to evaluate the seam with the lowest energy
 * path from border to border
 * 
 * Now when we find the seam with the lowest energy, we can remove it (amongst
 * other things) to result in a smaller picture (albeit by 1 seam spoken of
 * here) to have a picture that to the eye will not have noticable changes
 * 
 * @author Navya Sharma
 * @author Seth Kelley
 */

public class SeamCarver {

    private Picture seamPicture; // The picture that will be altered
    private int height; // height of the picture
    private int width; // width of the picture
    private int[] seam; // seam of interest
    private double[][] energy; // 2D array that holds the energy of every pixel

    /**
     * Constructor for the SeamCarver Object.
     * Initializes seamPicture, height and width
     * 
     * @param picture Original picture
     */
    public SeamCarver(Picture picture) {

        /* DO NOT EDIT */

        seamPicture = new Picture(picture);
        height = picture.height();
        width = picture.width();

    }

    /**
     * For any given x, y (location of the pixel) will calculate the energy of the
     * pixel based off of the RGB values of the adjacent pixels
     * 
     * @param x x location of the pixel
     * @param y y location of the pixel
     * @return Returns the energy of the (x,y) pixel
     */
    public double calcEnergy(int x, int y) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Populates every cell in the 2D energy array (instance variable) with its
     * energy value
     */
    public void populateEnergyArray() {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Looks through the entire picture to find the seam with the lowest energy
     * 
     * @return Returns an int array containing all of the horizontal indicies
     */
    public int[] findHorizontalSeam() {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Given a seam array, will remove the seam from the image
     * 
     * @param seam The seam to be remove from the picture
     */
    public void removeHorizontalSeam(int[] seam) {

        /* WRITE YOUR CODE HERE */
        
    }

    /**
     * Add a horizontal seam to the picture
     * 
     * @param seam The horizontal seam to be added
     */
    public void addHorizontalSeam(int[] seam) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Remove N horizontal seams from the picture
     * 
     * @param n The number of seams to be removed
     */
    public void removeNHorizontalSeams(int n) {

        /* DO NOT EDIT */

        // Loop to remove N seams from the picture
        for (int i = 0; i < n; i++) {
            populateEnergyArray(); // populates the energy array
            removeHorizontalSeam(findHorizontalSeam()); // Removes the found seam
        }
    }

    /**
     * Add N horizontal seams to the picture
     * 
     * @param n The number of seams to be added
     */
    public void addNHorizontalSeams(int n) {

        /* DO NOT EDIT */

        int[] s; // Holds the seam to be added

        for (int i = 0; i < n; i++) {
            s = findHorizontalSeam(); // Stores seam
            addHorizontalSeam(s); // Adds seam from picture
            addHorizontalEnergySeam(s); // Adds a new seam to energy array
        }
    }

    /**
     * Displays the picture to select an area to pontentially remove
     * Will get the pixels covered in the drawn area via "getXs" "getYs" methods
     * 
     * All the pixels in is area will set their value in the energy array to
     * -1000
     * 
     * Find the number of seams to remove (distance between min and max values of
     * drawn area)
     * 
     * Finally find the seam to remove(findSeam method), remove it from the
     * picture(removeSeam method), remove it from energy array(removeEnergySeam)
     * 
     * This ensures we find the next lowest seam
     * 
     * (No need to repopulate the energy array via method call since we know the
     * positions we want to remove)
     * 
     * (No need to call removeNSeams method either)
     */
    public void magicEraserHorizontal() {

        showSeamPicture(); // Show picture to select area to remove
        ArrayList<Integer> xCoords = seamPicture.getXs(); // List of x coordinates
        ArrayList<Integer> yCoords = seamPicture.getYs(); // List of y coordinates

        /* WRITE YOUR CODE HERE */
        /* EXTRA CHALLENGE - NOT GRADED */

    }

    /**
     * Given an int array, will remove the array indicies from the energy 2d array
     * Only to be used in magicEraser method
     * 
     * @param s Array holding the indicies to be removed
     */
    public void removeHorizontalEnergySeam(int[] s) {

        /* WRITE YOU CODE HERE */

    }

    /**
     * Increases the size of the energy array while also making the param seam have
     * the max value as well as the following seam
     * 
     * This makes it useful to find the next lowest seam without having to
     * repopulate the energy array
     * 
     * Only used on add N seams method
     * 
     * @param s
     */
    public void addHorizontalEnergySeam(int[] s) {

        /* WRITE YOUR CODE HERE */

    }

    /* DO NOT EDIT BELOW METHODS */

    /**
     * Shows the populated seam instance variable on the picture
     * which is highlighted red
     */
    public void showHorizontalSeam() {
        // If seam has not indicies return
        if (seam.length == 0)
            return;

        Picture p = new Picture(seamPicture); // Temp picture to show seam

        // Sets seam indicies to red
        for (int i = 0; i < width; i++) {
            p.set(i, seam[i], Color.RED);
        }
        p.show();
    }

    /**
     * Looks through the entire picture to find the seam with the lowest energy
     * 
     * @return Returns an int array containing all of the vertical indicies
     */
    public int[] findVerticalSeam() {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Given a seam array, will remove the seam from the image
     * 
     * @param seam The seam to be remove from the picture
     */
    public void removeVerticalSeam(int[] seam) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Add a vertical seam to the picture
     * 
     * @param seam The vertical seam to be added
     */
    public void addVerticalSeam(int[] seam) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Remove N vertical seams from the picture
     * 
     * @param n The number of seams to be removed
     */
    public void removeNVerticalSeams(int n) {

        /* DO NOT EDIT */

        for (int i = 0; i < n; i++) {
            populateEnergyArray();
            removeVerticalSeam(findVerticalSeam());
        }
    }

    /**
     * Add N vertical seams to the picture
     * 
     * @param n The number of seams to be added
     */
    public void addNVerticalSeams(int n) {

        /* DO NOT EDIT */

        int[] s; // Holds the seam to be added

        for (int i = 0; i < n; i++) {
            s = findVerticalSeam(); // Stores seam
            addVerticalSeam(s); // Adds seam from picture
            addVerticalEnergySeam(s); // Adds a new seam to energy array
        }
    }

    /**
     * Displays the picture to select an area to pontentially remove
     * Will get the pixels covered in the drawn area via "getXs" "getYs" methods
     * 
     * All the pixels in is area will set their value in the energy array to
     * -1000
     * 
     * Find the number of seams to remove (distance between min and max values of
     * drawn area)
     * 
     * Finally find the seam to remove(findSeam method), remove it from the
     * picture(removeSeam method), remove it from energy array(removeEnergySeam)
     * 
     * This ensures we find the next lowest seam
     * 
     * (No need to repopulate the energy array via method call since we know the
     * positions we want to remove)
     * 
     * (No need to call removeNSeams method either)
     */
    public void magicEraserVertical() {

        showSeamPicture(); // Show picture to select area to remove
        ArrayList<Integer> xCoords = seamPicture.getXs(); // List of x coordinates
        ArrayList<Integer> yCoords = seamPicture.getYs(); // List of y coordinates

        /* WRITE YOUR CODE HERE */
        /* EXTRA CHALLENGE - NOT GRADED */

    }

    /**
     * Given an int array, will remove the array indicies from the energy 2d array
     * Only to be used in magicEraser method
     * 
     * @param s Array holding the indicies to be removed
     */
    public void removeVerticalEnergySeam(int[] s) {

        /* WRITE YOUR CODE HERE */

    }

    /**
     * Increases the size of the energy array while also making the param seam have
     * the max value as well as the following seam
     * 
     * This makes it useful to find the next lowest seam without having to
     * repopulate the energy array
     * 
     * Only used on add N seams method
     * 
     * @param s
     */
    public void addVerticalEnergySeam(int[] s) {

        /* WRITE YOUR CODE HERE */

    }

    /* DO NOT EDIT METHODS BELOW */

    /**
     * Shows the populated seam instance variable on the picture
     * which is highlighted red
     */
    public void showVerticalSeam() {
        // If seam has not indicies return
        if (seam.length == 0)
            return;

        Picture p = new Picture(seamPicture); // Temp picture to show seam

        // Sets seam indicies to red
        for (int i = 0; i < height; i++) {
            p.set(seam[i], i, Color.RED);
        }
        p.show();
    }

    /**
     * Getter for seamPicture
     * 
     * @return Returns the seamPicture instance variable
     */
    public Picture getSeamPicture() {
        return seamPicture;
    }

    /**
     * Getter for height
     * 
     * @return Returns the height instance variable
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for width
     * 
     * @return Returns the width instance variable
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gettter for energy array
     * 
     * @return Returns the energy array instance variable
     */
    public double[][] getEnergy() {
        return energy;
    }

    /**
     * Prints the numbers in the energy array limited to 2 decimal places
     * I would only run this on small images due to terminal size not being large
     * enough to show 100s of 1000s of rows/cols
     */
    public void printEnergyArray() {
        DecimalFormat df = new DecimalFormat("0.00");

        for (int i = 0; i < energy.length; i++) {
            for (int k = 0; k < energy[0].length; k++) {
                StdOut.printf("%-15s", df.format(energy[i][k]));
            }
            StdOut.println();
        }
    }

    /**
     * Shows the seam picture
     */
    public void showSeamPicture() {

        getSeamPicture().show();
    }

    /**
     * Displays the energy of the picture
     */
    public void showEnergyPicture() {

        Picture p = new Picture(width, height);

        // Loop to assign every pixel in picture
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                int e = (int) (Math.round(energy[i][k])); // int value of energy
                Color c = new Color(e); // Color representation of enery
                int b = c.getBlue(); // Gets blue value (highest value of rgb from c)
                Color w = new Color(b, b, b); // Making greyscale color
                p.set(k, i, w); // Setting pixel to greyscale

            }
        }
        p.show();
    }
}
