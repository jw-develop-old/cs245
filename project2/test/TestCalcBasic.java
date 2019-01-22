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
        testSequence("1+2=", new String[] {"3", "3"});
    }
    
    @Test
    public void addOp() {
        testSequence("1+2+", new String[] {"3", "3"});
    }
    
    @Test
    public void addThreeNotFinished() {
        testSequence("1+2+4", new String[] {"4", "4"});
    }
    
    @Test
    public void addThreeEq() {
        testSequence("1+2+4=", new String[] {"7", "7"});
    }

    @Test
    public void clear() {
        testSequence("1+5+C4+9=", new String[] {"13", "13"});
    }
    @Test
    public void longentry() {
        testSequence("1216712318", new String[] {"1216712318"});
    }
    @Test
    public void manyequal() {
        testSequence("15+3=13+1=51", new String[] {"51"});
    }
    @Test
    public void massiveentrya() {
        testSequence("121671231812345123123123", new String[] {"121671231812345"});
    }
    @Test
    public void massiveentryb() {
        testSequence("1216712318.1234512345", new String[] {"1216712318.1234"});
    }
    @Test
    public void massiveentryc() {
        testSequence("12.123451234512345", new String[] {"12.123451234512"});
    }
    @Test
    public void massiveadd() {
        testSequence("12.123451234512345+1000=", new String[] {"1012.1234512345"});
    }
}
