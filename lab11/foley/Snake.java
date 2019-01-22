/**
 * Snake.java
 * 
 * Class to model a Snake in the predator-prey simulation.
 *
 * @author Vijay Foley
 * SCSI 245, Wheatland College, Spring 2007
 * Lab 11
 * April 5, 2007
 */

package foley;

public class Snake implements Organism {

    /**
     * The territory where this snake lives with other organisms.
     */
    private Island island;

    /**
     * Object to decide whether or not another organism is
     * this snake's predator or prey.
     */
    private PredatorDeterminer pd;

    /**
     * This snake's coordinates on the island.
     */
    private int myX;
    private int myY;

    /** getter methods */
    public int myX() { return myX; }
    public int myY() { return myY; }

    /**
     * The coordinates of this snake's egg.
     */
    private int eggX;
    private int eggY;

    /**
     * The number of days until the current eggs will hatch,
     * -1 if there are no eggs laid.
     */
    private int daysTillHatch;

    /**
     * Constructor. Initialize the reference to the island, pred determiner,
     * and coordinates to the given stuff, and initialize daysTillHatch
     * to indicate no eggs have been laid.
     */
    public Snake(Island island, PredatorDeterminer pd, int myX, int myY) {
        this.island = island;
        this.pd = pd;
        this.myX = myX;
        this.myY = myY;
        daysTillHatch = -1;
    }

    /**
     * Move the snake on this day.
     */
    public void go() {
        // if an egg is ready to hatch,
        // put a new snake where the egg is (killing whatever might already
        // be at those coordinates)
        if (daysTillHatch == 0) {
            island.putOrganism(eggX, eggY, new Snake(island, pd, eggX, eggY));
            daysTillHatch = -1;
        }
        else if (daysTillHatch > 0)
            daysTillHatch--;

        char[] directions = { 'N', 'S', 'E', 'W' };
        // look one space nearby
        for (int i = 0; i < directions.length; i++) {
            Organism neighbor = island.getOrganism(myX, myY, directions[i], 1);
            // if it is a prey, eat it by moving into its place and
            // laying an egg (if there are currently no unhatched eggs)
            // in our old position
            if (pd.preysOn(this, neighbor)) {
                int[] newCoords = island.putOrganism(myX, myY, directions[i], 1, this);
                island.putOrganism(myX, myY, null);
                if (daysTillHatch == -1) {
                    eggX = myX;
                    eggY = myY;
                    daysTillHatch = 5;
                }
                myX = newCoords[0];
                myY = newCoords[1];
                return;
            }
            // if it is a predator, kill it by removing it, but don't
            // move into it.
            if (pd.preysOn(neighbor, this)) {
                island.putOrganism(myX, myY, directions[i], 1, null);
                return;
            }
        }
        // look two spaces away
        for (int i = 0; i < directions.length; i++) {
            Organism neighbor = island.getOrganism(myX, myY, directions[i], 2);
            // if it is a prey and the space closer is open, move to it
            if (pd.preysOn(this, neighbor) && 
                island.getOrganism(myX, myY, directions[i], 1) == null) {
                int[] newCoords = island.putOrganism(myX, myY, directions[i], 1, this);
                island.putOrganism(myX, myY, null);
                myX = newCoords[0];
                myY = newCoords[1];
                return;
            }
        }
        // try to move randomly
        char direction = directions[(int)Math.floor(Math.random() * 4)];
        if (island.getOrganism(myX, myY, direction, 1) == null) {
            try {
                int[] newCoords = island.putOrganism(myX, myY, direction, 1, this);
                island.putOrganism(myX, myY, null);
                myX = newCoords[0];
                myY = newCoords[1];
            } catch (ArrayIndexOutOfBoundsException aioobe) {
                // fail to move
            }
        }      	
    }
    
    
    /**
     * Return a character to serve as a picture for the snake.
     */
    public char drawPicture() { return 's'; }
    

}
