/**
 * Island.java
 * 
 * Interface to specify the messages the Island object
 * shoudl respond to in the predator-prey simulation.
 *
 * @author Vijay Foley
 * SCSI 245, Wheatland College, Spring 2007
 * Lab 11
 * April 5, 2007
 */

package foley;

public interface Island {
        
    /**
     * Put a given organism in at a given set of coordinates
     * in this island (overwriting whatever was there)
     * @param x The x-coordinate at which to place o
     * @param y The y-coordinate at which to place o
     * @param o The organism to place
     */
    void putOrganism(int x, int y, Organism o);
    
    /**
     * Put a given organism a certain distance in a certain 
     * direction from a given set of coordinates in the island,
     * and report back the destination of that placed organism.
     * @param x The x-coordinate from which to base this placement
     * @param y The y-coordinate from which to place this basement
     * @param dir The direction (N, S, E, or W) from the given coordinates
     * @param dist The distance from the coordinates in the specified direction
     * @param o The organism to place
     * @return The coordinates where the organism ended up as an array
     * of size 2, with the x-coordinate in pos 0 and the y-coordinate in
     * pos 1.
     */
    int[] putOrganism(int x, int y, char dir, int dist, Organism o);
    
    /**
     * Get an organism a given distance in a given direction from
     * a given set of coordinates.
     * @param x The x-coordinate from which to base this
     * @param y The y-coordinate from which to base this
     * @param dir The direction (N, S, E, or W) from the given coordinates
     * @param dist The distance from the coordinates in the specified direction
     * @return The organism at the given coordinates
     */
    Organism getOrganism(int x, int y, char dir, int dist);
    
        
        
}
