package tests;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Step2TestPrevious extends TestCore {

	@Before
	public void setUp() throws Exception {
		reset();
	}

	
	/* What if the 'previous' button is pressed in the initial state? */
	
	private void spuriousPrevious() {
		reset();
		c.previous();		
	}
	
	@Test
	public void a_spuriousPrevious() {
		spuriousPrevious();
		assertEquals(0, window.getCurr());
		assertEquals(0, window.getMax());
		assertEquals("", window.getText());
		
	}
	
	
	/* What if an empty text is committed, followed by a 'previous'? */
	
	private void emptyCommit() {
		reset();
		c.commit();
		c.previous();
	}

	@Test
	public void b_emptyCommit() {
		emptyCommit();
		assertEquals(0, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("", window.getText());
	}

	
	/* What if a non-empty text is committed, followed by a 'previous'? */

	private void nonEmptyCommit() {
		reset();
		window.writeText("hello");
		c.commit();
		c.previous();
	}

	@Test
	public void c_nonEmptyCommit() {
		nonEmptyCommit();
		assertEquals(0, window.getCurr());
		assertEquals(1, window.getMax());
		assertEquals("", window.getText());
		
	}

	
	
	/* What if two commits were performed? */
	
	private void twoCommitsA() {
		reset();
		window.writeText("hello");
		c.commit();
		window.writeText("aloha");
		c.commit();
	}

	
	@Test
	public void d_twoCommitsA() {
		twoCommitsA();
		assertEquals(2, window.getCurr());
		assertEquals(2, window.getMax());
		assertEquals("aloha", window.getText());
	}

	
	/* Follow up: what if 'previous' is then pressed? */
	
	private void twoCommitsB() {
		twoCommitsA();
		c.previous();
	}

	@Test
	public void e_twoCommitsB() {
		twoCommitsB();
		assertEquals(1, window.getCurr());
		assertEquals(2, window.getMax());
		assertEquals("hello", window.getText());
	}

	
	/* Follow up: what if another 'previous' is pressed? */
	
	private void twoCommitsC() {
		twoCommitsB();
		c.previous();
	}

	@Test
	public void f_twoCommitsC() {
		twoCommitsC();
		assertEquals(0, window.getCurr());
		assertEquals(2, window.getMax());
		assertEquals("", window.getText());
	}

	
	/* What if three commits, back up, new commit?
	   (Before the parallel feature is implemented, this will
	   presumably erase the older commit ("howdy"), but attach it 
	   as a sibling after the parallel feature is implemented; however,
	   the observed behavior in this test case should be the same
	   either way.) */
	
	private void eraseCommitA() {
		reset();
		window.writeText("hello");
		c.commit();
		window.writeText("aloha");
		c.commit();
		window.writeText("howdy");
		c.commit();
		c.previous();
		window.writeText("ni hao");
		c.commit();
	}


	@Test
	public void g_eraseCommitA() {
		eraseCommitA();
		assertEquals(4, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("ni hao", window.getText());		
	}

	/* Follow-up: 'previous' should get us back to "aloha" */
	
	private void eraseCommitB() {
		eraseCommitA();
		c.previous();
	}

	@Test
	public void h_eraseCommitB() {
		eraseCommitB();
		assertEquals(2, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("aloha", window.getText());	
	}

	
	/* Follow-up: 'previous' again should get us back to "hello" */
	
	private void eraseCommitC() {
		eraseCommitB();
		c.previous();
	}

	@Test
	public void i_eraseCommitC() {
		eraseCommitC();
		assertEquals(1, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("hello", window.getText());		
	}


	/* Follow-up: 'previous' again should get us back to the blank original */
	
	private void eraseCommitD() {
		eraseCommitC();
		c.previous();
	}

	@Test
	public void j_eraseCommitD() {
		eraseCommitD();
		assertEquals(0, window.getCurr());
		assertEquals(4, window.getMax());
		assertEquals("", window.getText());
	}


	/* Stress test with 10 commits and 10 'previous'es */

	@Test
	public void k_manyCommits() {
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
		
	}
}
