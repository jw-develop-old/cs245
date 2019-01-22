package simulation;

/**
 * PredPreyGrid.java
 * 
 * Class to represent a grid in a biological simulation,
 * the "model" in terms of M/C/V.
 * This object wears two hats in the sense that is exposes
 * a different interface to different objects in the system.
 * The viewer/controller sees this as a Simulator,
 * and the agents (grid inhabitants) and related classes see it
 * as an AgentGrid.
 *
 * @author Thomas VanDrunen
 */

import java.util.*;
import java.awt.Color;

public class SimulationGrid<A extends Agent>  implements Model {

    /**
     * The two-dimensional array of agents.
     */
    private A[][] agents;
    
    /**
     * The dimensions of the grid.
     */
    private int width, height;

    private boolean isTorodial;
    
    
    /**
     * Constructor.
     * Initialize the instance variables according to the given size.
     */
    public SimulationGrid(int width, int height, A[][] agents, boolean isTorodial) {
        this.width = width;
        this.height = height;
        this.agents = agents; // new A[height][width];
        this.isTorodial = isTorodial;
    }

    public SimulationGrid(int width, int height, A[][] agents) { 
        this(width, height, agents, false);
    }

    /**
     * Find the width of this grid.
     * Getter method for width.
     * @return the width of this grid.
     */
    public int width() {
        return width;
    }
  
    
    /**
     * Find the height of this grid.
     * Getter method for height.
     * @return the height of this grid.
     */
    public int height() {
        return height;
    }

    // I feel like the text two really should be private (originally
    // they were) but I made them public for the Skeet hack for
    // avoiding over flow.
    
    public int xNorm(int x) {
        if (x >= width) return x %width;
        while(x < 0) x = x + width;
        return x;
     }

    public int yNorm(int y) {
        if (y >= height) return y %height;
        while(y < 0) y = y + height;
        return y;
    }   


    /**
     * Determine if a position is occupied by an agent.
     * Just check the position in the array.
     * @param xPos The x-position in which we're looking
     * @param yPos The y-position in which we're looking
     * @return Whether or not there is an agent at the specified position.
     */
    public boolean isOccupied(int xPos, int yPos) {
        if (isTorodial) {
            xPos = xNorm(xPos);
            yPos = yNorm(yPos);
        }

        if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= height)
            return false;
        else
            return agents[xPos][yPos] != null;
    }

    /**
     * Determine if a position is open to be occupied by an agent.
     * @param xPos The x-position in which we're looking
     * @param yPos The y-position in which we're looking
     * @return Whether or not an agent can be placed at the specified position.
     */
    public boolean isOpen(int xPos, int yPos) {
        if (isTorodial) {
            xPos = xNorm(xPos);
            yPos = yNorm(yPos);
        }
        if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= height)
            return false;
        else
            return agents[xPos][yPos] == null;        
    }

    /**
     * Find the agent, if any, at a position.
     * Return whatever is in the position in the array.
     * @param xPos The x-position in which we're looking
     * @param yPos The y-position in which we're looking
     * @return The agent at the position, if any (null otherwise)
     */
    public A getAgentAt(int xPos, int yPos) {

        if (isTorodial) {
            xPos = xNorm(xPos);
            yPos = yNorm(yPos);
        }

        if (xPos < 0 || xPos >= width || yPos < 0 || yPos >= height)
            return null;
        else
            return agents[xPos][yPos];
    }

    /**
     * Set the agent at a given position.
     * @param xPos The x-position in which we're looking
     * @param yPos The y-position in which we're looking
     * @param agent The agent to place there
     */
    public void setAgentAt(int xPos, int yPos, A agent) {

        if (isTorodial) {
            xPos = xNorm(xPos);
            yPos = yNorm(yPos);
        }
        agents[xPos][yPos] = agent;
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        int xd = x2 - x1;
        int yd = y2 - y1;
        return Math.sqrt(xd * xd + yd * yd);
    }
    
    public ArrayList<CoordinatedItem<A>> findNeighbors(final int xPos, final int yPos, A requester, double radius) {
        int intRad = (int) Math.ceil(radius);
        ArrayList<CoordinatedItem<A>> toReturn = new ArrayList<CoordinatedItem<A>>();
        for (int i = xPos - intRad; i <= xPos + intRad; i++)
            for (int j = yPos - intRad; j <= yPos + intRad; j++) {
                double distance = distance(xPos, yPos, i, j);
                if (distance > radius) continue;
                A current = getAgentAt(i, j);
                if (current != null && current != requester)
                    toReturn.add(new CoordinatedItem<A>(current, i, j, distance));
            }
        Collections.sort(toReturn, new Comparator<CoordinatedItem<A>>() {
            public int compare(CoordinatedItem<A> o1, CoordinatedItem<A> o2) {
                if (o1.getDistance() < o2.getDistance()) return -1;
                else if (o2.getDistance() < o1.getDistance()) return 1;
                else return 0;
            }
        });
        return toReturn;
    }
 
    public Color getColorAt(int x, int y) {
        A agent = getAgentAt(x, y);
        if (agent == null) return Color.BLACK;
        else return agent.getColor();
    }
   
    public String getIconAt(int x, int y) {
        A agent = getAgentAt(x, y);
        if (agent == null) return " ";
        else return agent.getIcon();
    }

}