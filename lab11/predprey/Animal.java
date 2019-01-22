package predprey;

import java.awt.Color;

/**
 * Animal.java
 * 
 * Class to store code common to both foxes and rabbits
 * (and future animals) in a pred-prey simulation
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

public abstract class Animal extends Agent {

    /**
     * Weight gained by eating prey
     */
    protected int weight;

    /**
     * How many units of weight are necessary to reproduce
     */
    protected abstract int growthRate();

    /**
     * How many positiosn away this animal can see
     */
    protected abstract int visualAcuity();

    /**
     * How many positions this animal can move at a time
     */
    protected abstract int speed();

    /***/
    public int weight() { return weight; }

    /**
     *  Inform the agent that it is time to act for this cycle.
     */
    public void act() {
        tryToReproduce();
        if (! scan())
            moveRandom();
        }

    /**
     * Make an new instance an insert it into the grid,
     * if we have enough weight and room.
     */
    protected void tryToReproduce() {
        if (weight >= growthRate()) {
            int i = (int) Math.floor(3 * Math.random()) - 1;
            int j = (int) Math.floor(3 * Math.random()) -1 ;
            if (grid.isOpen(xPos + i, yPos + j)) {

                Animal newAnimal = reproduce(); // new individual
                
                newAnimal.enterGrid(grid, xPos + i, yPos + j);
                grid.setAgentAt(xPos + i, yPos + j, newAnimal);
                weight = 0;
            }
        }       
    }

    /**
     * Produce a new individual of whatever species.
     */
    protected abstract Animal reproduce(); 
    

    /**
     * Scan an  area, returning whether or not this resulted in a move.
     * Loop through the rectangular neighborhood around this 
     * individual which is visible (depending on the visual acuity).
     * If a position is occupied, check if it has a prey. If so,
     * try to move into that position and eat the prey; if
     * the prey is too far away, move as close as possible.
     * On the other hand if the position is occupied by a predator, 
     * move in the opposite direction
     * @return true if we moved, false if not
     */
    protected boolean scan() {
        for (int i = -visualAcuity(); i <= visualAcuity(); i ++)
            for (int j = -visualAcuity(); j < visualAcuity(); j++) {
                if ((i != 0 || j != 0) && grid.isOccupied(xPos + i, yPos + 
j)) {
                    Agent nearby = grid.getAgentAt(xPos + i, yPos + j);
                    int iMove, jMove;// xPos + iMove, yPos + jMove will be
                                        // the position to which we move.
                    if (isPrey(nearby)) {
                        iMove = i;
                        jMove = j;
                    } else if (isPredator(nearby)) {
                        iMove = -i;
                        jMove = -j;
                    }
                    else 
                        continue;
                    if (iMove < -speed()) iMove = -speed();
                    if (iMove > speed()) iMove = speed();
                    if (jMove < -speed()) jMove = -speed();
                    if (jMove > speed()) jMove = speed();
                    if (grid.isOpen(xPos + iMove, yPos + jMove)
                        || isPrey(grid.getAgentAt(xPos + iMove, yPos + jMove))) {
                        if (isPrey(grid.getAgentAt(xPos + iMove, yPos + jMove)))
                            weight += grid.getAgentAt(xPos + iMove, yPos + jMove).weight();
                        grid.setAgentAt(xPos, yPos, null);
                        grid.setAgentAt(xPos + iMove, yPos + jMove, this);
                        xPos += iMove;
                        yPos += jMove;
                        return true;
                    }
                }   
            }

        return false;
    }

    /**
     * Is the other agent a predator of this animal?
     */
    protected boolean isPredator(Agent other) {
         return PreyArbiter.isPredatorOf(other, this);
    }

    /**
     * Is the other agent a prey of this animal?
     */
    protected boolean isPrey(Agent other) {
         return PreyArbiter.isPreyOf(other, this);
    }
    

    
    /**
     * Try to move in a random direction;
     * We'll get here only if our attempt to chase a prey has failed.
     */
    private void moveRandom() {
        int i = (int) Math.floor(3 * Math.random()) - 1;
        int j = (int) Math.floor(3 * Math.random()) -1 ;
        if (grid.isOpen(xPos + i, yPos + j)) {
            grid.setAgentAt(xPos + i, yPos + j, this);
            grid.setAgentAt(xPos, yPos, null);
            xPos += i;
            yPos += j;
        }
    }

}
