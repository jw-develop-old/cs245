package predprey;

import java.awt.Color;
import foley.*;
import simulation.SimulationGrid;

import java.util.HashMap;

public class SnakeAdapter extends Agent {

    /**
     * The snake object being wrapped/adapted
     */
    private Snake internal;

    // Look over and understand these things
    // in part D, but don't worry about them until then
    
    private static HashMap<Snake,SnakeAdapter> snakes = 
        new HashMap<Snake,SnakeAdapter>();

    public static SnakeAdapter makeAdapter(Snake snake) { 
        if (snakes.containsKey(snake))
            return snakes.get(snake);
        else 
            return new SnakeAdapter(snake);
    }

    public SnakeAdapter() {
        System.out.println("initial SnakeAdapter constructor");
    }

    private SnakeAdapter(Snake internal) { 
        System.out.println("later SnakeAdapter constructor");
        if (snakes.containsKey(internal)) {
            System.out.println("...and this is a problem");
            (new Exception()).printStackTrace();
        }
        this.internal = internal; 
        snakes.put(internal, this);
    }
    // end part D stuff
    
    // fill in this method in part F
    public void enterGrid(SimulationGrid<Agent> grid, int xPos, int yPos) {
    	Snake s = new Snake(new GridAdapter(grid), PDAdapter.getInstance(), xPos, yPos);
    	internal = s;
    	snakes.put(s, this);
    }

    // fill these in in part A
    public void act() {internal.go();}

    public int weight() { return 0; }

    public String getIcon() { return ""+internal.drawPicture(); }

    public Color getColor() { return Color.RED; }

    public Snake getInternal() { return internal; }

}
