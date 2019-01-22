package predprey;

/**
 * Rabbit.java
 * 
 * Class to represent a rabbit in a predator-prey simulation.
 * Rabbits will reproduce when they have eaten enough prey.
 * They will move towards the first prey they see, but away from the
 * first predator they see. If they see neither, they will move randomly.
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

import java.awt.Color;

public class Rabbit extends Animal {

    /**
     * The number of squares away that the fox can see.
     */
    private static int visualAcuity = 3;

    /**
     * The number of squares a rabbit can move at a time.
     */
    private static int speed = 2;

    /**
     * The number of clovers a rabbit must eat before it can reproduce.
     */
    private static int growthRate = 6;
    
    protected int growthRate() { return growthRate; }
    protected int speed() { return speed; }
    protected int visualAcuity() { return visualAcuity; }

   /**
     * Constructor. Indicate initial weight as zero.
     */
    public Rabbit() {
        weight = 0;
    }

    /**
     * Produce a new individual of Rabbit.
     */
    protected Animal reproduce() { return new Rabbit(); }


    /**
     * Get a character representation of this agent.
     * @return A character which will represent this agent in a 
     * display of the grid.
     */
    public String getIcon() { return "y";  }
    
    /**
     * Get an appropriate color to display the icon of this agent.
     * Cyan, because it is easily visible.
     * @return A color in which to display this agent's icon.
     */
    public Color getColor() { return Color.CYAN; }


}
