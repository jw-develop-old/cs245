/**
 * 
 */
package hmllm;

/**
 * @author jameswhite
 * A class consisting of the information about an association.
 */
public class Node {
	
	private String key;
	
	private String val;
	
	private Node link;
	
	/**
	 * @param key The key to a particular val.
	 * @param val The val linked to by the key.
	 * @param link The reference to the next Node in the list.
	 * Constructor to create a node from two objects.
	 */
	public Node(String key, String val) {
		this.key = key;
		this.val = val;
		this.link = null;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the val
	 */
	public String getVal() {
		return val;
	}

	/**
	 * @param val the val to set
	 */
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * @return the link
	 */
	public Node getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(Node link) {
		this.link = link;
	}
}
