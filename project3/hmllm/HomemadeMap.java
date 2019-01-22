package hmllm;

import java.util.Iterator;

/**
 * HomemadeMap
 * 
 * Interface to serve as a simplified version of Java's
 * java.util.Map interface and as an example of the
 * map ADT
 * 
 * @author Thomas VanDrunen
 * CSCI 245, Wheaton College
 * Original: Long, long ago
 * Redone: June 30, 2014
 */



public interface HomemadeMap {

    
    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    void put(String key, String val);

    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    String get(String key);

    /**
     * Test if this map contains an association for this key.
     * @param key The key to test.
     * @return true if there is an association for this key, false otherwise
     */
    boolean containsKey(String key);

    /**
     * Remove the association for this key.
     * @param key The key to remove
     */
    void remove(String key);

    /**
     * Get an iterator for all the keys in this map.
     * @return An iterator over the set of keys.
     */
    Iterator<String> keyIterator();

	
}
