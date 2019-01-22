package calc;

import java.text.DecimalFormat;

public class Master {
	DecimalFormat p = new DecimalFormat("##############0.##############");
	private CalculatorFace calc;
	private double total = 0;
	private double previous = 0;
	private int operand = 4;
	private double period = 0;
	private int entries = 0;
	private boolean negative = false;
	private boolean justequal = false;
	public Master(CalculatorFace calc) {
		super();
		this.calc = calc;
	}
	public void AddNum(double x) {
		entries++;
		if ((total == 0) && (period == 0))
			entries = 0;
		if (justequal == true) {
			previous = 0;
			total = 0;
			justequal = false;
		}
		if (entries >=15)
			return;
		if (negative == true && period == 0) {
			total = total*10 - x;
			if (p.format(total).length() > 15)
				calc.writeToScreen(p.format(total).substring(15));
			else
				calc.writeToScreen(p.format(total));
		}
		else if (negative == true) {
			total = total -x/period;
			period *= 10;
			if (p.format(total).length() > 15)
				calc.writeToScreen(p.format(total).substring(15));
			else
				calc.writeToScreen(p.format(total));
		}
		else if (period == 0) {
			total = total*10 + x;
			if (p.format(total).length() > 15)
				calc.writeToScreen(p.format(total).substring(15));
			else
				calc.writeToScreen(p.format(total));
		}
		else {
			total = total+x/period;
			period *= 10;
			if (p.format(total).length() > 15)
				calc.writeToScreen(p.format(total).substring(15));
			else
				calc.writeToScreen(p.format(total));
		}
	}
	public void DoEquals() {
		switch(operand) {
			case 0:
				total = total+previous;
				break;
			case 1:
				total = total-previous;
				break;
			case 2:
				total = total*previous;
				break;
			case 3:
				total = total/previous;
				break;
		}
		if (p.format(total).length() > 15)
			calc.writeToScreen(p.format(total).substring(15));
		else
			calc.writeToScreen(p.format(total));
		justequal = true;
	}
	public void DoClear() {
		total = 0;
		previous = 0;
		period = 0;
		justequal = false;
		negative = false;
		if (p.format(total).length() > 15)
			calc.writeToScreen(p.format(total).substring(15));
		else
			calc.writeToScreen(p.format(total));
	}
	public void DoAdd(Master m) {
		if (previous !=0)
			m.DoEquals();
		previous = total;
		justequal = false;
		total = 0;
		period = 0;
		operand = 0;
	}
	public void DoMinus(Master m) {
		if (previous !=0)
			m.DoEquals();
		previous = total;
		justequal = false;
		total = 0;
		period = 0;
		operand = 1;
	}
	public void DoMult(Master m) {
		if (previous !=0)
			m.DoEquals();
		previous = total;
		justequal = false;
		total = 0;
		period = 0;
		operand = 2;
	}
	public void DoDiv(Master m) {
		if (previous !=0)
			m.DoEquals();
		previous = total;
		justequal = false;
		total = 0;
		period = 0;
		operand = 3;
	}
	public void DoPeriod() {
		if ((period == 0) && (entries <15)) {
			if (total == 0)
				entries = 0;
			entries++;
			period = 10;
			if (p.format(total).length() > 15)
				calc.writeToScreen(p.format(total).substring(15));
			else
				calc.writeToScreen(p.format(total));
		}
	}
	public void DoPlusMin() {
		total = -total;
		negative = !negative;
		justequal = false;
		if (p.format(total).length() > 15)
			calc.writeToScreen(p.format(total).substring(15));
		else
			calc.writeToScreen(p.format(total));
	}
}