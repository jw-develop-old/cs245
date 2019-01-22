package predprey;

import foley.Organism;

public class AgentAdapter implements Organism {


    private Agent internal;

    public AgentAdapter(Agent internal) { this.internal = internal; }

    // these two methods shouldn't be called...
    public void go() {
        internal.act();
    }
    public char drawPicture() {
        return internal.getIcon().charAt(0);
    }

    // Here's the one we care about

    public Agent getInternal() { return internal; }
    
}
