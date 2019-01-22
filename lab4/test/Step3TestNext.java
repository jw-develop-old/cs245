package tests;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Step3TestNext extends TestCore {


	@Before
	public void setUp() throws Exception {
		reset();
	}

	/* What if the 'next' button is pressed in the initial state? */
	
	private void spuriousNext() {
		c.next();
	}

	@Test
	public void a_spuriousNext() {
		spuriousNext();
		assertEquals(0, window.getCurr());
		assertEquals(0, window.getMax());
		assertEquals("", window.getText());
	}

	/* Commit empty, then 'previous', 'next' */
	
	private void emptyCommit() {
		reset();
		c.commit();
		c.previous();
		c.next();
	}

	@Test
	public void b_emptyCommit() {
		emptyCommit();
		assertEquals(1, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("", window.getText());
	}


	/* Follow-up: Back up again. */
	
	private void previousAfterNext() {
		emptyCommit();
		c.previous();
	}


	@Test
	public void c_previousAfterNext() {
		previousAfterNext();
		assertEquals(0, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("", window.getText());
	}

	
	/* Commit some text, then 'previous' and 'next' */
	
	private void nonEmptyCommit() {
		reset();
		window.writeText("hello");
		c.commit();
		c.previous();
		c.next();		
	}

	@Test 
	public void d_nonEmptyCommit() {
		nonEmptyCommit();
		assertEquals(1, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("hello", window.getText());		
	}

	
	/* Follow-up: Back up again */
	
	private void previousAfterNextNonEmpty() {
		nonEmptyCommit();
		c.previous();
	}
	
	@Test
	public void e_previousAfterNextNonEmpty() {
		previousAfterNextNonEmpty();
		assertEquals(0, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("", window.getText());
	}
	
	/* Three commits, then three 'previous'es (then thre 'next's) 
	   Create chain:
	   
	   0: ""
	     |
	   1: "hello"
	     |
	   2: "aloha"
	     |
	   3: "ni hao"
	   
	   */
	
	private void threeCommits() {
		reset();
		window.writeText("hello");
		c.commit();
		window.writeText("aloha");
		c.commit();
		window.writeText("ni hao");
		c.commit();
		c.previous();
		c.previous();
		c.previous();
	}

	@Test
	public void f_threeCommits() {
		threeCommits();
		assertEquals(0, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("", window.getText());
		c.next();
		assertEquals(1, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("hello", window.getText());
		c.next();
		assertEquals(2, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("aloha", window.getText());
		c.next();
		assertEquals(3, window.getCurr());
		assertEquals(3, window.getMax());
		assertEquals("ni hao", window.getText());
	}

	/* Follow-up: Replace bottom revision with a new commit.
	   (This will do something different internally before and
	   after the parallel feature is implemented, but the observable
	   behavior of this test should be the same whether or no
	   parallel is implemented.)
	   	   
	   0: ""
	     |
	   1: "hello"
	     |
	   2: "aloha"
	     |
	   4: "salve"

	 */

	private void eraseCommit() {
		threeCommits();
		c.next();
		c.next();
		window.writeText("salve");
		c.commit();
		c.previous();
		c.next();
	}

	
	@Test
	public void g_eraseCommit() {
		eraseCommit();
		assertEquals(4, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("salve", window.getText());
		
	}

	/* Follow-up: After all that, what if 'next' is pressed again? */
	
	private void lateSpurious() {
		eraseCommit();
		c.next();
	}
	
	@Test
	public void h_lateSpurious() {
		lateSpurious();
		assertEquals(4, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("salve", window.getText());
	}
	
	/* Follow-up: go up to the top and commit something on
	  top of the first (non-empty) version.
	 */
	
	@Test
	public void h_longEraseCommit() {
		eraseCommit();
		for (int i = 0; i < 3; i++)
			c.previous();
		assertEquals(0, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("", window.getText());
		window.writeText("howdy");
		c.commit();		
		assertEquals(5, window.getCurr());
		assertEquals(5, window.getMax());
		assertEquals("howdy", window.getText());
		c.previous();
		assertEquals(0, window.getCurr());
		assertEquals(5, window.getMax());
		assertEquals("", window.getText());
		c.next();
		assertEquals(5, window.getCurr());
		assertEquals(5, window.getMax());
		assertEquals("howdy", window.getText());
	}

	/* Stress test with 10 commits, 10 'previous'es, and 10 'next's */
	
	@Test
	public void i_manyCommits() {
		reset();
		for (int i = 1; i < 11; i++) {
			c.commit();
			assertEquals(i, window.getCurr());
			assertEquals(i, window.getMax());
			assertEquals("", window.getText());			
		}
		for (int i = 9; i >= 0; i--) {
			c.previous();
			assertEquals(i, window.getCurr());
			assertEquals(10, window.getMax());
			assertEquals("", window.getText());	
		}
		for (int i = 1; i < 11; i++) {
			c.next();
			assertEquals(i, window.getCurr());
			assertEquals(10, window.getMax());
			assertEquals("", window.getText());			
		}
	}
	
}
