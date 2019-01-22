package hmllm;

import java.util.Iterator;

/**
 * HomemadeLLMap
 * 
 * An implementation of the HomemadeMap class that uses
 * a completely-homemade linked list on the inside.
 * 
 * @author Thomas VanDrunen
 * CSCI 245, Wheaton College
 * June 30, 2014
 */

public class HomemadeLLMap implements HomemadeMap {

	private Node head;
	
    /**
	 * @param head The start of the list.
	 * Constructor to make a map.
	 */
	public HomemadeLLMap() {
		head = new Node(null,null);
	}

	/**
     * Test whether an association exists for this key.
     * @param key The key to remove
     * @return true if there is an association for this key, false otherwise
     */
    public boolean containsKey(String key) {
    	return this.search(key) != null;
    }
   

    /**
     * Add an association to the map.
     * @param key The key to this association
     * @param val The value to which this key is associated
     */
    public void put(String key, String val) {
    	if (this.search(key) == null) {
    		Node temp = head;
	    	Node n = new Node(key,val);
	    	while (temp.getLink() != null)
	    		temp = temp.getLink();
	    	temp.setLink(n);
    	}
    	else
    		this.search(key).setVal(val);
    }
    
	/**
     * Searches map for a key.
     * @param key The key to remove
     * @return the data assoc. with the key, or null if no such data.
     */
    private Node search(String key) {
    	Node temp = head;
    	while (temp.getLink() != null) {
    		temp = temp.getLink();
    		if (temp.getKey().equals(key))
    			return temp;
    	}
    	return null;
    }
    
    /**
     * Get the value for a key.
     * @param key The key whose value we're retrieving.
     * @return The value associated with this key, null if none exists
     */
    public String get(String key) {
    	if (this.search(key) != null)
    		return (this.search(key)).getVal();
    	return null;
    }

    /**
     * Get an iterator for all the keys in this map.
     * @return An iterator over the set of keys.
     */
    public Iterator<String> keyIterator() {
    	return new MapIterator(head);
    }

    
    /**
     * Remove the association for this key.
     * @param key The key to remove
     */   
    public void remove(String key) {
    	if (!this.containsKey(key))
    		return;
    	Node current = head;
    	while (current.getLink().getKey() != key)
    		current = current.getLink();
    	current.setLink(current.getLink().getLink());
    }

	/**
	 * @return the head
	 */
	public Node getHead() {
		return head;
	}
	
}
