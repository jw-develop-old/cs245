package predprey;
/**
 * Agent.java
 *
 * Interface to represent an agent in the biological simulation.
 * An agent is any organism living on the island.
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

import simulation.SimulationGrid;

public abstract class Agent implements simulation.Agent {

    /**
     * The game grid we can interact with
     */
    protected SimulationGrid<Agent> grid;
    
    /**
     * This cell's x and y positions.
     */
    protected int xPos, yPos;


    /**
     * Inform the agent that it has been placed on the grid.
     * @param grid The grid in which the agent has been placed.
     * @param xPos The agent's x-position in the grid
     * @param yPos The agent's y-position in the grid
     */
    void enterGrid(SimulationGrid<Agent> grid, int xPos, int yPos){
        this.grid = grid;
        this.xPos = xPos;
        this.yPos = yPos;
    }


    /**
     * Inform the agent that it is time to act for this cycle.
     */
    public abstract void act();

    /**
     * Report on the weight of this agent.
     * @return This object's weight.
     */
    public abstract int weight();

    // next two are unnecessary since they're "inherited" from simulation.Agent

    /**
     * Get a character representation of this agent.
     * @return A character which will represent this agent in a 
     * display of the grid.
     */
    public abstract String getIcon();

    /**
     * Return a color appropriate for displaying this agent's icon
     * in color displays.
     * @return A color in which to display the icon.
     */
    public abstract java.awt.Color getColor();
}

