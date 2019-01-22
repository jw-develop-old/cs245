package ctrl;

/**
 * NextActionListener.java
 *
 * Class to model an action listener for the next button.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College, Spring 2008
 * Lab 3
 * Jan 30, 2007
 */

import java.awt.event.*;

public class NextActionListener implements ActionListener {

    /**
     * The controller object
     */
    private Controller vc;

    /**
     * Constructor
     * @param vc The controller object
     */
    public NextActionListener(Controller vc) { this.vc = vc; }

    /**
     * When the button is pressed, call the controller's parallel method.
     * @param ae unused
     */
    public void actionPerformed(ActionEvent ae) {
        vc.next();
    }

}
