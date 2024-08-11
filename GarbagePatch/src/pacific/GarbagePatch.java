package pacific;

import java.util.ArrayList;

/**
 * Simulates the Great Pacific Garbage Patch using union-find, OOP, and
 * ArrayLists.
 * Contains methods to read in sample data, simulate current, model debris
 * breakdown, and analyze garbage size.
 * 
 * To succeed on this assignment:
 * - READ the description on the course site.
 * - READ the provided classes (Garbage, Pile), etc. to make sense
 * of how they work + how to work with them (getters/setters/constructors).
 * - START EARLY, it takes time to understand
 * these assignments.
 * - Come to office hours if you need help (times on course site).
 * - Did we say come to office hours? *Come to office hours!*
 * 
 * @author Seth Kelley
 * @author Kal Pandit
 */
public class GarbagePatch {
    private String[][] currentFlow; // [i][j] corresponds to a direction
    private Pile[][] garbagePositions; // [i][j] corresponds to a Pile
    private ArrayList<Garbage> garbageData; // .get(i) corresponds to a Garbage object

    /**
     * Default constructor. Initializes an empty garbageData list.
     */
    public GarbagePatch() {
        garbageData = new ArrayList<>();
        currentFlow = null;
        garbagePositions = null;
    }

    /**
     * Constructor for the Garbage Patch, initializes the 2d array for
     * the current flow and the placement for the garbage via input files
     * 
     * @param currentFile     Input file for the size of the 2d array and direction
     *                        of the current (current file has x and y sizes on each
     *                        line followed by the directions)
     * @param garbageFile     Input that contains positions of garbage [i][j]. Add
     *                        to the surface of each position.
     * @param garbageDataFile Input file for the garbage data (each line holds the
     *                        type of garbage with its time to decompse then
     *                        density)
     */
    public GarbagePatch(String currentFile, String garbageFile, String garbageDataFile) {

        /* DO NOT EDIT */

        // Setup for current array
        createCurrentArray(currentFile);

        // Setup for garbage position array
        garbageData = new ArrayList<>();
        createGarbageDataList(garbageDataFile);
        createGarbageArray(garbageFile);

    }

    /**
     * Reads info from a file to setup and initialize the currentFlow instance
     * variable.
     * 
     * Directions are "north", "south", "west", "east", "vortex" - nothing else.
     * 
     * @param file Input file with required data for the 2D array
     */
    public void createCurrentArray(String file) {

        /* WRITE CODE HERE */

    }

    /**
     * Creates a garbageData list to contain information about
     * each piece of garbage.
     *
     * @param file the input file name.
     */
    public void createGarbageDataList(String file) {

    }

    /**
     * Helper method to find a piece of garbage in
     * the data list by type name.
     * 
     * Returns a Garbage object: the piece of garbage.
     * 
     * @param type the type to search for
     * @return the piece of garbage, null if not found
     */
    public Garbage findGarbageFromData(String type) {

    }

    /**
     * Reads info from file to setup and initialize the garbagePositions
     * instance variable and all piles within it.
     * 
     * USE garbageData to grab information about each piece of garbage.
     * 
     * @param file Input file with required data for the array
     */
    public void createGarbageArray(String file) {

    }

    /**
     * Return true if garbagePositions[i][j] has items, false otherwise.
     * 
     * @param i i in [i][j]
     * @param j j in [i][j]
     * @return the result
     */
    public boolean hasGarbage(int i, int j) {

    }

    /**
     * Inserts an item into the pile in accordance with its density.
     * 
     * Insert just before the first position where item's density <= y's density
     * for an already existing item y. This should be sorted in ascending order of
     * density.
     * 
     * REQUIRES direct use/traversal of the debris pile ArrayList.
     * IF no item exists at [i][j] add the new item to surface.
     *
     * @param pileArr the pile array. The driver calls this on garbagePositions BUT
     *                you can use this parameter to work with this on another array.
     * @param item    the item to insert
     * @param i       row in the [i][j] coordinate of the garbage pile to insert at
     * @param i       col in the [i][j] coordinate of the garbage pile to insert at
     */
    public void pileGarbageByDensity(Pile[][] pileArr, Garbage item, int i, int j) {

    }

    /**
     * Simulates current after ONE movement.
     * Move pieces of garbage in the four cardinal directions,
     * "vortex" direction pieces stay where they are.
     * 
     * currentFlow[i][j] refers to the same position as
     * garbagePositions[i][j].
     */
    public void simulateCurrent() {

    }

    /**
     * Simulate "steps" steps of simulating the current.
     * 
     * @param steps the number of steps through current
     */
    public void simulateCurrent(int steps) {

    }

    public void simulateGarbageBreakdown(double time) {

    }

    private void unionItem(WeightedQuickUnionUF wqu, int rows, int cols, int curRow, int curCol, int r, int c) {

    }

    /**
     * Examines all cells and conducts weighted quick-union on neighbors.
     * Neighbors = cardinal (N, S, E, W) and both contain garbage.
     * RETURN the number of patches.
     * 
     * ONLY examine the top layer: no need to go into a pile.
     * If the pile contains no garbage, don't conduct union on it.
     * 
     * @return the number of patches
     */
    public int numberOfPatches() {

    }

    public void printCurrent() {
        StdOut.println("Current:");
        if (currentFlow == null) {
            StdOut.println("EMPTY - the instance variable holding current is null.");
            return;
        }
        for (int i = 0; i < currentFlow.length; i++) {
            for (int j = 0; j < currentFlow[0].length; j++) {
                StdOut.printf("%-13s", currentFlow[i][j] + " ");
            }
            StdOut.println();
        }
    }

    public void printGarbageData() {
        StdOut.println("Garbage Data:");
        if (garbageData.size() == 0) {
            StdOut.println("EMPTY - the list garbage data has no item.");
            return;
        }
        for (int i = 0; i < garbageData.size(); i++) {
            StdOut.println(i + ": " + garbageData.get(i) + " ");
        }
        StdOut.println();
    }

    public void printGarbagePositions() {
        StdOut.println("Garbage Positions:");
        if (garbagePositions == null) {
            StdOut.println("EMPTY - the instance variable holding garbage positions is null.");
            return;
        }

        boolean foundInvalidDepth = false;
        int d = 0;
        while (!foundInvalidDepth) {
            StdOut.println("Depth " + d);
            int invalidCells = 0;
            for (int i = 0; i < garbagePositions.length; i++) {
                for (int j = 0; j < garbagePositions[0].length; j++) {
                    // Put these checks to avoid NPE
                    if (garbagePositions[i][j] == null) {
                        invalidCells++;
                        StdOut.printf("%-29s", "NULL ");
                        continue;
                    } else if (garbagePositions[i][j].getDebrisPile() == null) {
                        invalidCells++;
                        StdOut.printf("%-29s", "NO PILE ");
                        continue;
                    } else if (garbagePositions[i][j].getDebrisPile().size() == 0) {
                        invalidCells++;
                        StdOut.printf("%-29s", "EMPTY ");
                        continue;
                    }

                    if (garbagePositions[i][j].getDebrisPileSize() > d) {
                        StdOut.printf("%-29s", garbagePositions[i][j].getDebrisPile().get(d).toString() + " ");
                    } else {
                        invalidCells++;
                        StdOut.printf("%-29s", "- ");
                    }
                }
                StdOut.println();
                if (invalidCells == garbagePositions.length * garbagePositions[0].length) {
                    StdOut.println("NOTE: Depth " + d + " does not have any valid cells!");
                    foundInvalidDepth = true;
                }
            }
            d++;
        }
    }

    public String[][] getCurrentFlow() {
        return this.currentFlow;
    }

    public void setCurrentFlow(String[][] currentFlow) {
        this.currentFlow = currentFlow;
    }

    public Pile[][] getGarbagePositions() {
        return this.garbagePositions;
    }

    public void setGarbagePositions(Pile[][] garbagePositions) {
        this.garbagePositions = garbagePositions;
    }

    public ArrayList<Garbage> getGarbageData() {
        return this.garbageData;
    }

    public void setGarbageData(ArrayList<Garbage> garbageData) {
        this.garbageData = garbageData;
    }

}
