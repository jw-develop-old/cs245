/**
 * 
 */
package ctrl;

/**
 * @author James White and Lina Widodo
 *
 */
public class TextVers {
	int vernum; // the number of the file
	String text; // the saved text
	TextVers link; // the object of the links
	TextVers next; // the next link
	//arrayList branch[];
	/**
	 * @param vernum The number of the file
	 * @param text The saved text
	 * @param link The object of link
	 */
	public TextVers(int vernum, String text, TextVers link) {
		super();
		this.vernum = vernum;
		this.text = text;
		this.link = link;
	}

	public int getVernum() {
		return vernum;
	}
	public void setVernum(int vernum) {
		this.vernum = vernum;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TextVers getLink() {
		if (link !=null);
		return link;
	}
	public void setLink(TextVers link) {
		this.link = link;
	}
	public TextVers getNext() {
		return next;
	}
	public void setNext(TextVers next) {
		this.next = next;
	}
}
