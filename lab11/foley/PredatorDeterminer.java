/**
 * PredatorDeterminer.java
 * 
 * Interface to specify the messages understood
 * by the object which determines what's a prey of what.
 *
 * @author Vijay Foley
 * SCSI 245, Wheatland College, Spring 2007
 * Lab 11
 * April 5, 2007
 */

package foley;

public interface PredatorDeterminer {
	
    /**
     * Does a prey on b?
     * @return true if a is a predator of b
     */
    boolean preysOn(Organism a, Organism b);
}