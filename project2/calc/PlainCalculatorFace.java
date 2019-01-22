package calc;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

/**
 * PlainCalculatorFace
 * 
 * Class to display a window showing a calculator
 * to the user.
 *
 * IMPORTANT: Do not make your submitted calculator project
 * dependent on any modifications to this class. In fact, the
 * recommendation is that you do not modify this class at all (one
 * exception below). In fact, you do not need to look at the contents 
 * of this class at all (it wasn't written to be read by second-semester 
 * programming students).
 * 
 * If you do choose to modify this file (for debugging purposes, for
 * example), know that if you submit your modified version it will
 * be ignored at grading. (In fact, we will not use this class at
 * all when grading.) A better debugging strategy would be to make
 * a separate class that implements CalculatorFace.
 * 
 * One legitimate reason to modify this for your own testing is to
 * adjust the size and position of the window to improve its appearance
 * on your computer if you choose to work on your own machine rather
 * than the lab machines.
 * 
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * Redone: June 27, 2014
 */


public class PlainCalculatorFace implements CalculatorFace {

	/* *** Modify these to improve the window's appearance when
	       developing in environments other than the lab machines *** */
	
    private static final int WINDOW_Y_POSITION = 100;
	private static final int WINDOW_X_POSITION = 100;
	private static final int WINDOW_HEIGHT = 200;
	private static final int WINDOW_WIDTH = 250;

	
	/* You can ignore everything below. */
	
	
	private JTextField[] screen;
    private HashMap<Character, JButton> buttonMap;
    private JFrame window;

    public PlainCalculatorFace() {
        buttonMap = new HashMap<Character, JButton>();
        window = new JFrame("Calculator");
        JPanel top = new JPanel();
        JPanel bottomNum = new JPanel();

        bottomNum.setLayout(new GridLayout(4, 3));
        char[] labels = {'7', '8', '9', '4', '5', '6', '1', '2', '3', '0', '.', 
                         CalculatorFace.PLUS_MINUS};
        for (int i = 0; i < labels.length; i++) {
            JButton jbutt = new JButton("" + labels[i]);
            bottomNum.add(jbutt);
            buttonMap.put(labels[i], jbutt);
        }
        
        JPanel bottomOp = new JPanel();
        bottomOp.setLayout(new GridLayout(4, 1));
        buttonMap.put('C', new JButton("C"));
        bottomOp.add(buttonMap.get('C'));

        JPanel opTop = new JPanel();
        opTop.setLayout(new GridLayout(1,2));
        buttonMap.put('+', new JButton("+"));
        opTop.add(buttonMap.get('+'));
         buttonMap.put('-', new JButton("-"));
        opTop.add(buttonMap.get('-'));
        bottomOp.add(opTop);

        JPanel opBot = new JPanel();
        opBot.setLayout(new GridLayout(1,2));
        buttonMap.put('*', new JButton("*"));
        opBot.add(buttonMap.get('*'));
         buttonMap.put('/', new JButton("/"));
        opBot.add(buttonMap.get('/'));
        bottomOp.add(opBot);

        buttonMap.put('=', new JButton("="));
        bottomOp.add(buttonMap.get('='));
       
        JPanel screenPanel = new JPanel();
        screenPanel.setLayout(new GridLayout(1, 15));
        screen = new JTextField[15];
        for (int i = 0; i < screen.length; i++) {
            screen[i] = new JTextField(1);
            screen[i].setEditable(false);
            screenPanel.add(screen[i]);
        }
        top.add(screenPanel);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout());
        bottom.add(bottomNum);
        bottom.add(bottomOp);

        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(top, BorderLayout.NORTH);
        contentPane.add(bottom, BorderLayout.CENTER);
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setLocation(WINDOW_X_POSITION, WINDOW_Y_POSITION);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public void writeToScreen(String display) {
        //screen.setText(display);
        for (int i = 0; i < 15; i++)
            if (i < display.length())
                screen[15-1-i].setText("" + display.charAt(display.length() - 1 - i));
            else screen[15-1-i].setText(" ");
    }

   
	public void addActionListener(char button, ActionListener listener) {
		if (! buttonMap.containsKey(button))
			throw new NoSuchButtonException(button);
		buttonMap.get(button).addActionListener(listener);
	}

	public void addNumberActionListener(int button, ActionListener listener) {
		if (button < 0 || button >= 10)
			throw new NoSuchButtonException(button);
		buttonMap.get(("" + button).charAt(0)).addActionListener(listener);
	}

	public void addPlusMinusActionListener(ActionListener listener) {
		buttonMap.get(CalculatorFace.PLUS_MINUS).addActionListener(listener);
	}


    public static void main(String[] args) {
        new PlainCalculatorFace();
    }

	
}

