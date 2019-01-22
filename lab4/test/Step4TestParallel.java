package tests;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class Step4TestParallel extends TestCore {

	@Before
	public void setUp() throws Exception {
		reset();
	}

	/* What if the 'parallel' button is pressed in the initial state? */
	
	private void spuriousParallel() {
		reset();
		c.parallel();
	}

	@Test
	public void a_spuriousParallel() {
		spuriousParallel();
		assertEquals(0, window.getCurr());
		assertEquals(0, window.getMax());
		assertEquals("", window.getText());
	}

	
	/* What if 'parallel' is pressed on a version with no siblings? */

	private void trivialParallel() {
		reset();
		window.writeText("hello");
		c.commit();
		c.previous();
		c.next();
		c.parallel();
	}

	@Test
	public void b_trivialParallel() {
		trivialParallel();
		assertEquals(1, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("hello", window.getText());		
	}

	
	/* Follow-up: make two parallel versions:
	     0: ""
	       |
	     1: "hello"
	       |
	     3: "salve"   -   2: "aloha"
	       
	 
	 */
	
	private void twinParallel() {
		trivialParallel();
		window.writeText("aloha");
		c.commit();
		c.previous();
		window.writeText("salve");
		c.commit();
		c.previous();
		c.next();
	}

	
	@Test
	public void c_twinParallel() {
		twinParallel();
		assertEquals(3, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("salve", window.getText());
		c.parallel();
		assertEquals(2, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("aloha", window.getText());
		c.parallel();
		assertEquals(3, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("salve", window.getText());
	}
	

	/* Follow-up: add a third
	 	 0: ""
	       |
	     1: "hello"
	       |
	     4: "howdy"   -   2: "aloha"  -  3: "salve"

	 */
	
	private void thirdParallel() {
		twinParallel();
		c.previous();
		window.writeText("howdy");
		c.commit();
	}

	@Test
	public void d_thirdParallel() {
		thirdParallel();
		assertEquals(4, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("howdy", window.getText());
		c.parallel();
		assertEquals(2, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("aloha", window.getText());
		c.parallel();
		assertEquals(3, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("salve", window.getText());
		c.parallel();
		assertEquals(4, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("howdy", window.getText());
	}

	/*
	  Follow-up: make cousin versions.
	  0: ""
	       |
	     1: "hello"
	       |
	     4: "howdy"   -   2: "aloha"  -  3: "salve"
           |                |
         5: "hola"        6: "privet"
	 */
	
	private void cousins() {
		thirdParallel();
		window.writeText("hola");
		c.commit();
		c.previous();
		c.parallel();
		window.writeText("privet");
		c.commit();
		c.previous();
	}

	@Test
	public void e_cousins() {
		cousins();
		assertEquals(2, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("aloha", window.getText());
		c.parallel();
		c.parallel();
		c.next();
		assertEquals(5, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("hola", window.getText());
		c.previous();
		c.parallel();
		c.next();
		assertEquals(6, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("privet", window.getText());
		// now check for missing cousin
		c.previous();
		c.parallel();
		c.next();
		assertEquals(3, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("salve", window.getText());
		c.parallel();
		c.next();
		assertEquals(5, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("hola", window.getText());
	}


}
