package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddL implements ActionListener {
	private Master math;
	public AddL(Master mat) {
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.DoAdd(math);
	}
}