package predprey;

import java.util.*;
import java.io.*;

/**
 * Class to arbitrate between animal classes to determine who
 * is a preditor of whom.
 * @author Thomas VanDrunen
 */
public class PreyArbiter {
    /**
     * Table which maps each class to a set of classes that
     * are its prey. In discrete math terms, this is a function that
     * maps each species to that species' image under the isPredatorOf
     * relation. The Class class is a way to refer to a class as a whole
     * as an object.
     */
    private static HashMap<Class, HashSet<Class>> predToPrey;
        
    /**
     * Static initializer to populate the pred-to-prey table.
     * First, open the file predprey.dat, which should have a series of
     * lines like "Rabbit Clover" meaning class Rabbit eats class Clover.
     * Break the line up into the two class names, and find the Class
     * objects that correspond to them. Add the prey to the list of
     * prey classes for the predator, making a new table entry if necessary.
     */     
    static {
            try {
                    predToPrey = new HashMap<Class, HashSet<Class>>();

                    // The file that contains the pred/prey relation
                    Scanner file = new Scanner(new FileInputStream("predprey.dat"));

                    // process the file, one line at a time
                    while(file.hasNext()) {
                            // Tokenizer breaks the line into the two strings-- species names
                            StringTokenizer tokey = new StringTokenizer(file.nextLine());

                            Class   // References to the class for each species
                                    pred = Class.forName("predprey." + tokey.nextToken()),
                                    prey = Class.forName("predprey." + tokey.nextToken());

                            // if the predator class doesn't already have an entry in the
                            // table, add one
                            if (!predToPrey.containsKey(pred))  
                                    predToPrey.put(pred, new HashSet<Class>());

                            // add the prey class to the predator class's entry
                            predToPrey.get(pred).add(prey);
                    }
            } catch(Exception re) {
                    System.out.println(re.getMessage());
                    re.printStackTrace();
            }
    }   
        
    /**
     * Determine whether a given species is a predator of another
     * given species. 
     * @param pred The alleged predator (null and unknown classes are ok)
     * @param prey The alleged prey (null and unknown classes are ok
     * @return Whether or not these two species are so related.
     */
    public static boolean isPredatorOf(Agent pred, Agent prey) {
            if (pred == null || prey == null) return false;

            return predToPrey.containsKey(pred.getClass())
                  && predToPrey.get(pred.getClass()).contains(prey.getClass());
    }
        
    /**
     * Determine whether a given species is a prey of another
     * given species. 
     * @param prey The alleged prey (null and unknown classes are ok
     * @param pred The alleged predator (null and unknown classes are ok)
     * @return Whether or not these two species are so related.
     */
    public static boolean isPreyOf(Agent prey, Agent pred) {
        return isPredatorOf(pred, prey);
}
   
    
    
}
