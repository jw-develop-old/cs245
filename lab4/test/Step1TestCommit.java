package tests;


import org.junit.Test;
import static org.junit.Assert.*;

import revctrl.Controller;

public class Step1TestCommit {

	
	@Test
	public void a_emptyWindow() {
		MockVCWindow window = new MockVCWindow();
		/*Controller c =*/ new Controller(window);
		assertEquals(0, window.getCurr());
		assertEquals(0, window.getMax());
		assertEquals("", window.getText());
	
	}
	
	@Test
	public void b_textEntered() {
		MockVCWindow window = new MockVCWindow();
		/*Controller c =*/ new Controller(window);
		window.writeText("hello");
		assertEquals(0, window.getCurr());
		assertEquals(0, window.getMax());
		assertEquals("hello", window.getText());
	}
	
	@Test
	public void c_commitNoCrash() {
		MockVCWindow window = new MockVCWindow();
		Controller c = new Controller(window);
		c.commit();
	}
	
	@Test
	public void d_committed() {
		MockVCWindow window = new MockVCWindow();
		Controller c = new Controller(window);
		window.writeText("hello");
		c.commit();
		assertEquals(1, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("hello", window.getText());
	}

}
