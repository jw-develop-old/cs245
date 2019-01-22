package calc;

/**
 * CalculatorFace.java
 *
 * Interface CalculatorFace to define how the 
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * Original: Jan 19 2007
 * Redone: June 27, 2014
 */

import java.awt.event.ActionListener;

public interface CalculatorFace {

	/**
	 * The character of the plus/minus sign.
	 */
    public static final char PLUS_MINUS =  177;

    /**
     * Print a string to the screen portion of the calculator face.
     * @param display The String to be displayed.
     */
    public void writeToScreen(String display);

    /**
     * Add an action listener to the indicated button. This
     * accepts characters 0 1 2 3 4 5 6 7 8 9 . {+/-} + - * / = and C
     * to indicate the button. Note PLUS_MINUS can be used for the
     * {+/-} character. 
     * @param button The character indicating the button to which
     * to attach an action listener.
     * @param listener The action listener to attach.
     * @throws NoSuchButtonException if a character other than the ones
     * listed above are given.
     */
    public void addActionListener(char button, ActionListener listener);
    
    /**
     * Add an action listener to the indicated number button. This
     * accepts ints 0 through 9 (inclusive). This is provided in case
     * it is found to be more convenient to name number buttons by
     * int rather than by character.
     * @param button The int indicating the number button to which
     * to attach an action listener.
     * @param listener The action listener to attach.
     * @throws NoSuchButtonException if an int outside the range
     * listed above are given.
     */
    public void addNumberActionListener(int button, ActionListener listener);

    /**
     * Add an action listener to the plus/minus button. This is provided in
     * case it is found to be more convenient than using addActionListener
     * with PLUS_MINUS or 177.
     * @param listener The action listener to attach.
     */
    public void addPlusMinusActionListener(ActionListener listener);
    
}
