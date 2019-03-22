package calc;

/**
 * NoSuchButtonException
 *
 * Class to indicate the attempt to attach an action listener
 * to a non-existent button in a calculator face.
 * 
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
 */

public class NoSuchButtonException extends RuntimeException {

	/**
	 * Generated id for serialization.
	 */
	private static final long serialVersionUID = -1501722869525510404L;

	/**
	 * Constructor to make an appropriate message for a character
	 * that does not correspond to any button.
	 * @param c The character attempted to indicate a calculator face button.
	 */
	public NoSuchButtonException(char c) {
		super("There is no button corresponding to character " + c + " ("
				+ ((int) c) + ").");
	}
	
	/**
	 * Constructor to make an appropriate message for an int for which
	 * there is no number button.
	 * @param x The number attempted to indicate a calculator face button.
	 */
	public NoSuchButtonException(int x) {
		super("There is no #" + x + " button.");
	}
}
