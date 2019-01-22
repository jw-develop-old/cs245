/**
 * Organism.java
 * 
 * Interface to specify the messages an organism
 * in the predator-prey simulation should respond to.
 *
 * @author Vijay Foley
 * SCSI 245, Wheatland College, Spring 2007
 * Lab 11
 * April 5, 2007
 */

package foley;

public interface Organism {
	
    /**
     * Tell this organism it's time to do something.
     */
    public void go();
    
    /**
     * Give a character to look like this organism
     */
    public char drawPicture();
	
}