package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualsL implements ActionListener {
	private Master math;
	public EqualsL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoEquals();
	}
}