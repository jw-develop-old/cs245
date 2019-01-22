package predprey;

/**
 * Clover.java
 * 
 * Class to represent a clover plant in a predator-prey simulation.
 * Clovers' only action is to reproduce (instantiate a new clover instance/
 * individual and place it in an open position adjacent to its own) every
 * three ticks.
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

import java.awt.Color;

public class Clover extends Agent {

    /**
     * How long has it been since this plant grew?
     */
    private int cyclesSinceGrowth;

    /**
     * Number of cycles until the clover can reproduce
     */
    private static int growthRate = 3;

    /**
     * Constructor. Indicate that this clover has never grown.
     */
    public Clover() {
        cyclesSinceGrowth = 0;
    }


    /**
     * Inform the agent that it is time to act for this cycle.
     * If this clover has not grown for 3 cycles, try to grow in a random direction.
     */
    public void act() {
        cyclesSinceGrowth++;
        if (cyclesSinceGrowth >= growthRate) {
            int i = (int) Math.floor(3 * Math.random()) - 1;  // Intended position
            int j = (int) Math.floor(3 * Math.random()) -1 ;  //   for new instance
            if (grid.isOpen(xPos + i, yPos + j)) {
                Clover newClover = new Clover();     // the new instance
                newClover.enterGrid(grid, xPos + i, yPos + j);
                grid.setAgentAt(xPos + i, yPos + j, newClover);
                cyclesSinceGrowth = 0;
            }
        }
    }

    /**
     * Report on the weight of this agent.
     * A clover always weight one unit.
     * @return This object's weight.
     */
    public int weight() { return 1; }

    /**
     * Get a character representation of this agent.
     * @return A character which will represent this agent in a 
     * display of the grid.
     */
    public String getIcon() {
        return "#";
    }
    

    /**
     * Get an appropriate color to display the icon of this agent.
     * Green, since this is a plant.
     * @return A color in which to display this agent's icon.
     */
    public Color getColor() { return Color.GREEN; }


}


