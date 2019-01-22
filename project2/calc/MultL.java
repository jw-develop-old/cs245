package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultL implements ActionListener {
	private Master math;
	public MultL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoMult(math);
	}
}