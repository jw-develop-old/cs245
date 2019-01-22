package tests;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



public class Step5TestGoTo extends TestCore {

	@Before
	public void setUp() throws Exception {
		reset();
		// the long navigation of the step 4 tests
		window.writeText("hello");
		c.commit();
		c.previous();
		c.next();
		c.parallel();
		window.writeText("aloha");
		c.commit();
		c.previous();
		window.writeText("salve");
		c.commit();
		c.previous();
		c.next();
		c.parallel();
		c.parallel();
		c.previous();
		window.writeText("howdy");
		c.commit();
		c.parallel();
		c.parallel();
		c.parallel();
		window.writeText("hola");
		c.commit();
		c.previous();
		c.parallel();
		window.writeText("privet");
		c.commit();

	
	}

	@Test
	public void a_test0() {
		c.goTo(0);
		assertEquals(0, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("", window.getText());
	}

	@Test
	public void b_test1() {
		c.goTo(1);
		assertEquals(1, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("hello", window.getText());
	}

	@Test
	public void c_test2() {
		c.goTo(2);
		assertEquals(2, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("aloha", window.getText());
	}

	@Test
	public void d_test3() {
		c.goTo(3);
		assertEquals(3, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("salve", window.getText());
	}

	@Test
	public void e_test4() {
		c.goTo(4);
		assertEquals(4, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("howdy", window.getText());
	}
	@Test
	public void f_test5() {
		c.goTo(5);
		assertEquals(5, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("hola", window.getText());
	}

	@Test
	public void g_test6() {
		c.goTo(6);
		assertEquals(6, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("privet", window.getText());
	}

	@Test 
	public void g_testAboveMax() {
		c.goTo(8);
		assertEquals(6, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("privet", window.getText());	
	}
	
	@Test
	public void h_testBelowZero() {
		c.goTo(-1);
		assertEquals(6, window.getCurr());
		assertEquals(6, window.getMax());
		assertEquals("privet", window.getText());	
	}
	

}

