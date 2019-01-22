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

public class Human extends Animal {

    private static int visualAcuity = 50;

    private static int speed = 2;

    private static int growthRate = 30;

    public Human() {
        weight = 0;
    }
    
    protected int growthRate() { return growthRate; }
     protected int speed() { return speed; }
    protected int visualAcuity() { return visualAcuity; }

    protected Animal reproduce() { return new Human(); }

 
 
    public String getIcon() {
        return "H";
    }

    public Color getColor() { return Color.WHITE; }


}
