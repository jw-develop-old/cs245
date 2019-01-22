package tests;

import javax.swing.JButton;

import revctrl.VCWindow;

public class MockVCWindow implements VCWindow {

	private int curr;
	private int max;
	private String text;
	
	public void setCurrentVersion(int version) {
		curr = version;
	}

	public int getCurr() { return curr; }
	public int getMax() { return max; }
	
	public void setMaxVersion(int version) {
		max = version;
	}

	public void writeText(String txt) {
		text = txt;
	}

	public String getText() {
		return text;
	}

	public JButton getCommitButton() {
		return null;
	}

	public JButton getPreviousButton() {
		return null;
	}

	public JButton getNextButton() {
		return null;
	}

	public JButton getParallelButton() {
		return null;
	}

	public JButton getGoToButton() {
		return null;
	}

}
