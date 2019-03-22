package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import calc.CalculatorFace;

import static org.junit.Assert.*;


public abstract class TestCalcAbs {

    protected static class TestFace implements CalculatorFace {

        private HashMap<Character, ActionListener> charMap;
        private String screenState;
        private int idGen;
        
        public TestFace() {
            charMap = new HashMap<Character, ActionListener>();
            screenState = "";
            idGen = ActionEvent.ACTION_FIRST;
        }

        public String viewScreen() { return screenState; }
        public void press(char c) { 
            if (charMap.containsKey(c)) {
                int id = idGen++;
                if (idGen >= ActionEvent.ACTION_LAST)
                    idGen = ActionEvent.ACTION_FIRST;
                charMap.get(c).actionPerformed(new ActionEvent(this, id, 
                        "" + c, System.currentTimeMillis(), 0)); 
            }
        }
        public void press(String s) {
            for (char c : s.toCharArray())
                press(c);
        }
        
        public void writeToScreen(String display) {
            screenState = display;
        }

        public void addActionListener(char button, ActionListener listener) {
            charMap.put(button, listener);
        }

        public void addNumberActionListener(int button, ActionListener listener) {
            if (button >= 0 && button <= 9)
                charMap.put((char) ('0' + button), listener);
        }

        public void addPlusMinusActionListener(ActionListener listener) {
            charMap.put(CalculatorFace.PLUS_MINUS, listener);
        }
        
    }
    
    protected TestFace freshCalc() {
        TestFace f = new TestFace();
        calc.SetUp.setUpCalculator(f);
        return f;
    }
    
    protected void testSequence(String buttonSeq, String[] expected) {
        TestFace f = freshCalc();
        f.press(buttonSeq);
        String screen = f.viewScreen();
        boolean foundMatch = false;
        for (String ex : expected)
            foundMatch |= ex.equals(screen);
        //assertEquals(expected, f.viewScreen());
        assertTrue("expected: <" + expected[0] + "> but was:<" + screen + ">", foundMatch);
    }
}
