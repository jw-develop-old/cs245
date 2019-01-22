package predprey;

import java.awt.Color;

public class Cougar extends Animal {

    private static int visualAcuity = 10;

    private static int speed = 3;

    private static int growthRate = 250;

    public Cougar() {
        weight = 0;
    }
    
    protected int growthRate() { return growthRate; }
     protected int speed() { return speed; }
    protected int visualAcuity() { return visualAcuity; }
 
 
    
 
    public String getIcon() {
        return "m";
    }

    public Color getColor() { return Color.YELLOW; }

    protected Animal reproduce() { return new Cougar(); }



}
