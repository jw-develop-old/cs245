package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlusMinL implements ActionListener {
	private Master math;
	public PlusMinL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoPlusMin();
	}
}