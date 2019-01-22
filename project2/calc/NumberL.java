package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberL implements ActionListener {
	private double number;
	private Master math;
	public NumberL(double num, Master mat) {
		this.number = num;
		this.math = mat;
	}
	public void actionPerformed(ActionEvent e) {
		math.AddNum(number);
	}
}