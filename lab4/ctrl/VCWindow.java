package ctrl;

/**
 * VCWindow.java
 *
 * Interface VCWindow to specify the operations of the object modeling
 * the window for the version control program
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College, Spring 2007
 * Lab 3
 * Jan 23, 2007
 */


import javax.swing.*;


public interface VCWindow {

    /**
     * Indicate the version number of the version of the text being displayed
     * (besides any uncommitted changes).
     * @param version The version number.
     * POSTCONDITION: The "current" indicator in the upper left of
     * the window is updated.
     */
    void setCurrentVersion(int version);

    /**
     * Indicate the highest version number yet committed.
     * @param version The version number.
     * POSTCONDITION: The "max" indicator in the upper right of
     * the window is updated.
     */
    void setMaxVersion(int version);

    /**
     * Write text to the main text area of the VC screen.
     * @param txt The text being written.
     * POSTCONDITION: The given text is written to the text area.
     */
    void writeText(String txt);

    /**
     * Retrieve the text from the VC screen
     * @return The text currently appearing in the screen
     */
    String getText();

    /**
     * Accessor method for the "commit" button.
     * @return A reference to the "commit" button.
     */
    JButton getCommitButton();

    /**
     * Accessor method for the "previous" button.
     * @return A reference to the "previous" button.
     */
    JButton getPreviousButton();

    /**
     * Accessor method for the "next" button.
     * @return A reference to the "next" button.
     */
    JButton getNextButton();

    /**
     * Accessor method for the "parallel" button.
     * @return A reference to the "parallel" button.
     */
    JButton getParallelButton();

    /**
     * Accessor method for the "go to" button; when this
     * button is pressed, the ActionEvent it returns has its
     * command (retrieved from "getActionCommand")  which contains
     * the number in the accompanying field.
     * @return A reference to the "go to" button.
     */
    JButton getGoToButton();

}
