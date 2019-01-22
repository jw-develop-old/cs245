package calc;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {
	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		Master math = new Master(face);
		face.addNumberActionListener(0, new NumberL(0.0,math));
		face.addNumberActionListener(1, new NumberL(1.0,math));
		face.addNumberActionListener(2, new NumberL(2.0,math));
		face.addNumberActionListener(3, new NumberL(3.0,math));
		face.addNumberActionListener(4, new NumberL(4.0,math));
		face.addNumberActionListener(5, new NumberL(5.0,math));
		face.addNumberActionListener(6, new NumberL(6.0,math));
		face.addNumberActionListener(7, new NumberL(7.0,math));
		face.addNumberActionListener(8, new NumberL(8.0,math));
		face.addNumberActionListener(9, new NumberL(9.0,math));
		face.addActionListener('+', new AddL(math));
		face.addActionListener('-', new MinusL(math));
		face.addActionListener('*', new MultL(math));
		face.addActionListener('/', new DivL(math));
		face.addActionListener('C', new ClearL(math));
		face.addActionListener('=', new EqualsL(math));
		face.addActionListener('.', new DotL(math));
		face.addPlusMinusActionListener(new PlusMinL(math));
	}
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());
	}
}