package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DotL implements ActionListener {
	private Master math;
	public DotL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoPeriod();
	}
}