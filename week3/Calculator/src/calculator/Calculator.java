package calculator;

import exceptions.Exceptions;

/**
 *
 * @author cj
 */
public class Calculator extends Converter {
    
    private double ANS;
    
    public Calculator() {
        ANS = 0.0;
    }
    
    public double getANS() {
        return ANS;
    }

    public void setANS(double ANS) {
        this.ANS = ANS;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }
    
    public String add(String value) {
        double finalValue = baseToDec(value);
        setANS(ANS + finalValue);
        return decToBase(Double.toString(getANS()));
    }
    
    public String sub(String value) {
        double finalValue = baseToDec(value);
        setANS(ANS - finalValue);
        return decToBase(Double.toString(getANS()));
    }
    
    public String mul(String value) {
        double finalValue = baseToDec(value);
        setANS(ANS * finalValue);
        return decToBase(Double.toString(getANS()));
    }

    public String div(String value) {
        double finalValue = baseToDec(value);
        if (finalValue == 0)
            return "div by zero!";
        setANS(ANS / finalValue);
        return decToBase(Double.toString(getANS()));
    }
    
    public String inv() {
        if (getANS() == 0) 
            return "div by zero!";
        setANS(1 / ANS);
        return decToBase(Double.toString(getANS()));
    }
}
