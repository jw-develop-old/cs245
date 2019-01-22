package predprey;

import java.awt.Color;

/**
 * Bear.java
 * 
 * Class to represent a bear in a predator-prey simulation.
 * Bears will reproduce when they have eaten enough prey.
 * They will move towards the first prey they see, but away from the
 * first predator they see. If they see neither, they will move randomly.
 *
 * @author Thomas VanDrunen
 * Wheaton College, fall 2006
 * Nov 3, 2006
 */

public class Bear extends Animal {

    private static int visualAcuity = 20;

    private static int speed = 1;

    private static int growthRate = 70;

    public Bear() {
        weight = 0;
    }
    
    protected int growthRate() { return growthRate; }
     protected int speed() { return speed; }
    protected int visualAcuity() { return visualAcuity; }

    protected Animal reproduce() { return new Bear(); }

 
 
    public String getIcon() {
        return "B";
    }

    public Color getColor() { return Color.WHITE; }


}
