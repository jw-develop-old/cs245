package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DivL implements ActionListener {
	private Master math;
	public DivL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoDiv(math);
	}
}