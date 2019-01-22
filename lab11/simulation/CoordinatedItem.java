package simulation;

public class CoordinatedItem<A> {
    
    private A item;
    private int x;
    private int y;
    private double distance;  // may or may not be relevant
    private boolean distanceSet;
    
    public CoordinatedItem(A item, int x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
        distanceSet = false;
    }

    public CoordinatedItem(A item, int x, int y, double distance) {
        this(item, x, y);
        this.distance = distance;
        distanceSet = true;
    }
    
    public A getItem() { return item; }
    public int getX() { return x; }
    public int getY() { return y; }
    public double getDistance() { 
        assert distanceSet;
        return distance;
    }
}