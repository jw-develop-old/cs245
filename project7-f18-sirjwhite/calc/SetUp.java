package calc;

import java.awt.event.ActionListener;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author sirjwhite
 * 
 * Adapted from: 
 * Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {
	
	/**
	 * Instance variables for helper methods as well as to be passed to
	 * Internal through a getter.
	 */
	private static CalculatorFace one_face;
	private static Internal internal;
	
	/**
	 * Method for initializing the calculator states and
	 * connecting them to the calculator face. The implementation favors
	 * editability (eg. adding new buttons) over initial readability.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		
		//The states of the new calculator.
		one_face = face;
		internal = new Internal();

		//Misc. Buttons.
		addB('C',e -> internal.fullyClear());
		addB('.',e -> internal.dot());
		addB('=',e -> internal.equals());
		addB(CalculatorFace.PLUS_MINUS,e -> internal.plusMinus());
		
		//Operand Buttons.
		addB('+',(a,b) -> a+b );
		addB('-',(a,b) -> a-b );
		addB('*',(a,b) -> a*b );
		addB('/',(a,b) -> a/b );
		
		//Number Buttons
		for (int i=0;i<10;i++) {
			final int toadd = i;
			addB(i,e -> internal.inputNumber(toadd));
		}
	}
	
	public static CalculatorFace getFace() {return one_face;}
	
	/**
	 * Button-adding helper classes for misc., operands, and numbers
	 * respectively to assist readability.
	 */
	private static void addB(char arg, ActionListener action)
		{one_face.addActionListener(arg,action);}
	private static void addB(char arg, Internal.Operator op)
		{one_face.addActionListener(arg,e->internal.operand(op));}
	private static void addB(int arg, ActionListener action) 
		{one_face.addNumberActionListener(arg,action);}
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());}
}
