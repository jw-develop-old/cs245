package ctrl;


/**
 * Controller.java
 *
 * Class to define an object to control the versions in this
 * revision control system.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College, Spring 2008
 * Lab 3
 * Jan 30, 2007
 */


public class Controller {
	
	/**
	 * Counting the versions
	 */
	private int vercount = 0;

    /**
     * The window that this controls.
     */
    private VCWindow window;
    
    /**
     * The number of the previous text
     */
    private TextVers lastcall = null;

    /**
     * The number of the current text
     */
    private TextVers current = new TextVers(0, "", null);
    
    /**
     * The index of the next text
     */
    private TextVers next = null;
    
    /**
     * Constructor
     * @param window The window this is to control
     */
    public Controller(VCWindow window) {
        this.window = window;
        window.writeText("");
        window.setCurrentVersion(0);
        window.setMaxVersion(0);
    }

    /**
     * Commit the current text to a new version
     */
    public void commit() {
       	vercount++;
    	TextVers temp = new TextVers(vercount, window.getText(), current);
       	current = temp;
       	next = null;
       	lastcall = current.getLink();
       	lastcall.setNext(current);
    	window.setMaxVersion(vercount);
    	window.setCurrentVersion(vercount);
    }

    /**
     * Move ahead to the next version, if one exists.
     */
    public void next() { 
    	if (next != null ) {
    	lastcall = current;
    	current = next;
    	window.writeText(current.getText());
    	window.setCurrentVersion(current.getVernum());
    	next = current.getNext();
    	}
    }

    /**
     * Revert to the version previous to the current one, if any exists.
     */
    public void previous() { 
    	if (lastcall != null) {
    	next = current;
    	current = lastcall;
    	lastcall = lastcall.getLink();
    	window.writeText(current.getText());
    	window.setCurrentVersion(current.getVernum());
    	}
    }

    /**
     * Move to a "sibling" version to the current one-- another version
     * that originated as a revision of the current version's parent.
     */
    public void parallel() { 
    	next = current.getNext();
    	window.writeText(current.getText());
    	window.setCurrentVersion(current.getVernum());
    }

    /**
     * Jump to another version, by number.
     * @param num The number of the version to which to jump.
     */
    public void goTo(int num) { 
    }

}
