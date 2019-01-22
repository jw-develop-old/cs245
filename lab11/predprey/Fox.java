package predprey;

/**
 * Fox.java
 * 
 * Class to represent a fox in a predator-prey simulation.
 * Foxes will reproduce when they have eaten enough prey.
 * They will move toward the first prey they see; if they see none,
 * they will move randomly.
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

import java.awt.Color;

public class Fox extends Animal {

    /**
     * The number of squares away that the fox can see.
     */
    private static int visualAcuity = 4;

    /**
     * The number of squares a fox can move at a time.
     */
    private static int speed = 1;

    /**
     * The number of rabbits a fox must eat before it can reproduce.
     */
    private static int growthRate = 20;

    protected int growthRate() { return growthRate; }
    protected int speed() { return speed; }
    protected int visualAcuity() { return visualAcuity; }
   
    
    /**
     * Constructor. Indicate initial weight as zero.
     */
    public Fox() {
        weight = 0;
    }

 
    /**
     * Produce a new individual of Fox.
     */
    protected Animal reproduce() { return new Fox(); }



    /**
     * Get a character representation of this agent.
     * @return A character which will represent this agent in a 
     * display of the grid.
     */
    public String getIcon() {
        return "X";
    }

   /**
     * Get an appropriate color to display the icon of this agent.
     * Orange, to look like a fox.
     * @return A color in which to display this agent's icon.
     */
    public Color getColor() { return Color.ORANGE; }

}
