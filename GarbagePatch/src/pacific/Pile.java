package pacific;

import java.util.ArrayList;

/**
 * Stores a single pile of garbage held at position [i][j] of the
 * main garbage array. 
 * 
 * Implements a modified "last-in, first-out" structure.
 * 
 * While a pile can contain a single piece of garbage, it IS NOT a
 * single piece of garbage in and of itself.
 * 
 * @author Kal Pandit
 * @author Seth Kelley
 */
public class Pile {
    // We use an ArrayList to represent the pile.
    private ArrayList<Garbage> debrisPile;
    
    /**
     * Creates a new garbage pile object with an empty pile.
     */
    public Pile() {
        debrisPile = new ArrayList<>();
    }

    /**
     * Inserts a piece of garbage at index 0 AND SHIFTS all
     * current elements (if any) by one to the right.
     * 
     * @param newItem the item to add
     */
    public void addItemToSurface(Garbage newItem) {
        // THIS DIFFERS from debrisPile.add(x): (index, x) shifts as well
        debrisPile.add(0, newItem);
    }

    /**
     * Inserts a piece of garbage at index i AND SHIFTS all
     * current elements (if any) by one to the right.
     * 
     * @param i the index to shift at
     * @param newItem the item to add
     */
    public void addAndShiftPosition(int i, Garbage newItem) {
        // THIS DIFFERS from debrisPile.add(x): (index, x) shifts as well
        debrisPile.add(i, newItem);
    }

    /**
     * Removes the item from the garbage pile that is closest
     * to the surface.
     * @return the item removed
     */
    public Garbage removeItemAtSurface() {
        return debrisPile.remove(0);
    }

    /**
     * Removes the item from the garbage pile at position i.
     * All successive elements are shifted 1 left.
     * @param i the position to remove from
     * @return the item removed
     */
    public Garbage removeItemAtPosition(int i) {
        return debrisPile.remove(i);
    }

    /**
     * Gets the item closest to the surface (depth 0).
     * Does NOT remove the item.
     * @return the item closest to the surface
     */
    public Garbage getItemAtSurface() {
        return debrisPile.get(0);
    }

    /**
     * Gets the item at a position (depth i).
     * Does NOT remove the item.
     * @return the item at depth i
     */
    public Garbage getItemAtPosition(int i) {
        return debrisPile.get(i);
    }

    /**
     * Gets the entire ArrayList representing the trash pile
     * @return the trash pile
     */
    public ArrayList<Garbage> getDebrisPile() {
        return debrisPile;
    }

    /**
     * Gets the size of the debris pile.
     * @return the debris pile's size
     */
    public int getDebrisPileSize() {
        return debrisPile.size();
    }

    /**
     * Setter method for the trash pile.
     * @param debrisPile the new trash pile
     */
    public void setDebrisPile(ArrayList<Garbage> debrisPile) {
        this.debrisPile = debrisPile;
    }
}