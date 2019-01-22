package predprey;

import foley.*;

/**
 * Names: James White and Andy Peterson.
 */

import simulation.SimulationGrid;

public class GridAdapter implements Island {
    private SimulationGrid<Agent> grid;

    public GridAdapter(SimulationGrid<Agent> grid) { this.grid = grid; }

    // write this method in part D
    public void putOrganism(int x, int y, Organism o) {
    	
    	//Snake
    	if (o instanceof Snake)
    		grid.setAgentAt(x, y, SnakeAdapter.makeAdapter((Snake) o));
    	//Wrapped agent
    	else if (o instanceof AgentAdapter)
    		grid.setAgentAt(x, y, ((AgentAdapter) o).getInternal());
    	else 
    		grid.setAgentAt(x, y, null);
    	
    }

    // write this method in part B
    public int[] putOrganism(int x, int y, char dir, int dist, Organism o) {
    	int[] pair = getDist(x,y,dir,dist);
    	putOrganism(pair[0],pair[1],o);
    	return pair;
    }

    // write this method in part C
    public Organism getOrganism(int x, int y, char dir, int dist) {
    	int[] pair = getDist(x,y,dir,dist);
    	Agent agent = grid.getAgentAt(pair[0],pair[1]);
    	Organism org;
    	if (agent instanceof SnakeAdapter)
    		org = ((SnakeAdapter) agent).getInternal();
    	else if (agent instanceof Agent)
    		org = new AgentAdapter(agent);
    	else
    		return null;
    	return org;
    }
    
    private int[] getDist(int x, int y, char dir, int dist) {
    	switch (dir) {
    	case 'N': y += dist; break;
    	case 'S': y -= dist; break;
    	case 'E': x += dist; break;
    	case 'W': x -= dist; break;
    	}
    	return new int[] {x,y};
    }
    	

}
