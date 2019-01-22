package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinusL implements ActionListener {
	private Master math;
	public MinusL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoMinus(math);
	}
}