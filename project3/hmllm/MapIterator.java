/**
 * 
 */
package hmllm;

import java.util.Iterator;

/**
 * @author jameswhite
 *
 */
public class MapIterator implements Iterator<String> {

	Node current;
	
	/**
	 * @param previous
	 * @param current
	// */
	public MapIterator(Node head) {
		this.current = head;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if (current.getLink() == null)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public String next() {
		if (this.hasNext()) {
			current = current.getLink();
			return current.getKey();
		}
		return null;
	}
}
