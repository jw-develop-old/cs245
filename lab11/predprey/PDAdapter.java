package predprey;

import foley.*;

public class PDAdapter implements PredatorDeterminer {
    private static PDAdapter instance = new PDAdapter();

    private PDAdapter() {}

    public static PDAdapter getInstance() { return instance; }

    // Write this in part E
    public boolean preysOn(Organism a, Organism b) {
    	if (a == null || b == null)
    		return false;
    	Agent x = adapt(a);
    	Agent y = adapt(b);
        return PreyArbiter.isPredatorOf(x,y);
        
    }

    private Agent adapt(Organism thing) {
    	
    	//Snake
    	if (thing instanceof Snake)
    		return SnakeAdapter.makeAdapter((Snake) thing);
    	
    	//Wrapped agent
    	else
    		return ((AgentAdapter) thing).getInternal();
    }
}