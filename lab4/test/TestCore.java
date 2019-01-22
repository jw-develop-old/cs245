package tests;

import revctrl.Controller;

public class TestCore {

	protected MockVCWindow window;
	protected Controller c;

	public TestCore() {
		super();
	}

	public void reset() {
		window = new MockVCWindow();
		c = new Controller(window);
	}

	
	
}