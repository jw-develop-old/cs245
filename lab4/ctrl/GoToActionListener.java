package ctrl;

/**
 * GoToActionListener.java
 *
 * Class to model an action listener for the goTo button.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College, Spring 2008
 * Lab 3
 * Jan 30, 2007
 */

import java.awt.event.*;

public class GoToActionListener implements ActionListener {

    /**
     * The controller object
     */
    private Controller vc;

    /**
     * Constructor
     * @param vc The controller object
     */
    public GoToActionListener(Controller vc) { this.vc = vc; }

    /**
     * When the button is pressed, call the controller's goTo method.
     * @param ae The action even indicating the number we should move to.
     */
    public void actionPerformed(ActionEvent ae) {
        try {
            vc.goTo(Integer.parseInt(ae.getActionCommand()));
        } catch(Exception e) { }
    }

}
