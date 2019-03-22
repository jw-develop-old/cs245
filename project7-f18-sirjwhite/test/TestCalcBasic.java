package test;


import org.junit.Test;

import calc.CalculatorFace;

public class TestCalcBasic extends TestCalcAbs {

    @Test
    public void singleDigit() {
        testSequence("5", new String[] {"5"});
    }
    
    @Test
    public void tripleDigit() {
        testSequence("213", new String[] {"213"});
    }
    
    @Test
    public void decimal() {
        testSequence("12.7", new String[] {"12.7"});
    }
    
    @Test
    public void negativePre() {
        testSequence(CalculatorFace.PLUS_MINUS + "15", new String[] {"-15"});
    }
    
    @Test
    public void negativeMid() {
        testSequence("1" + CalculatorFace.PLUS_MINUS + "5", new String[] {"-15"});
    }

    @Test
    public void negativePost() {
        testSequence("15" + CalculatorFace.PLUS_MINUS, new String[] {"-15"});
    }

    @Test
    public void addEq() {
        testSequence("1+2=", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addOp() {
        testSequence("1+2+", new String[] {"3", "3.0"});
    }
    
    @Test
    public void addThreeNotFinished() {
        testSequence("1+2+4", new String[] {"4", "4.0"});
    }
    
    @Test
    public void addThreeEq() {
        testSequence("1+2+4=", new String[] {"7", "7.0"});
    }

    @Test
    public void clear() {
        testSequence("1+5C4+9=", new String[] {"13", "13.0"});
    }
}
