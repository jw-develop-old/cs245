package calc;

import java.text.DecimalFormat;

/**
 * Class to model the internals of the calculator as well as
 * printing. The SetUp class is responsible for calling the appropriate
 * method based on which button is pressed, and this calculator records
 * and returns the appropriate data for each key press.
 * 
 * @author sirjwhite
 *
 */
public class Internal {
	
	private CalculatorFace face = SetUp.getFace();
	
	//The current and last entered number, if any.
	private double current = 0;
	private double previous = 0;
	
	//Used to keep track of +/- state.
	private int sign = 1;
	
	//Used in the event of dot(), which adds to given decimal.
	private int decimalCount;
	
	//The mutable for adding a number in inputNumber.
	private Operator inputStyle = (a,b) -> a*10+b*sign;
	
	//Current operation for equals to use to calculate a result,
	//this starting operator is trivial.
	private Operator currentOp = (a,b) -> b;
	
	/**
	 *	Interface to contract two operand action expression, such
	 *	as plus, minus, etc.
	 */
	public interface Operator {double operand(double x,double y);}

	/**
	 * Called when a number button is pressed.
	 * @param num The integer value of the inputted button.
	 */
	public void inputNumber(int num) {
		current = inputStyle.operand(current,num);
		print();
	}
	
	/**
	 * Called when an operand is pressed.
	 * @param op The substance of the operator.
	 */
	public void operand(Operator op) {
		equals();
		currentOp = op;
	}
	
	/**
	 * Called by equals button as well as operand, when
	 * it performs an operation to prepare for a new one.
	 * Performs a "partial clear," caching current as previous
	 * before resetting the number input variables.
	 */
	public void equals() {
		current = currentOp.operand(previous,current);
		print();
		previous = current;
		inputStyle = (a,b) -> a*10+b*sign;
		current = 0;
	}
	
	/**
	 * Begins decimalCount at 1 and inserts new number adding
	 * function to substitute for default.
	 */
	public void dot() {
		inputStyle = (a,b) -> {
			decimalCount *= 10;
			return a+b/decimalCount*sign;
		};
		decimalCount = 1;
	}
	
	/**
	 * Resets the calculator to startup state.
	 */
	public void fullyClear() {
		current = previous = 0;
		inputStyle = (a,b) -> a*10+b*sign;
		currentOp = (a,b) -> b;
		print();
	}
	
	/**
	 * Reverses sign of current number as well as all subsequent
	 * numbers added.
	 */
	public void plusMinus() {
		sign *= -1; current *= -1;
		print();
	}
	
	/**
	 * Helper function to handle print statements of a particular format
	 * using DecimalFormat class.	
	 * @param num The number (current or previous) to print.
	 */
	private void print() {
		DecimalFormat d = new DecimalFormat("#");
		
		//Makes maximum number of decimals correspond to the digits of current.
		d.setMaximumFractionDigits((int) (14-Math.log10(current)));
		face.writeToScreen(d.format(current));
	}
}