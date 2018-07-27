package calculator;

import exceptions.Exceptions;
/**
 *
 * @author cj
 */
public class Calculator extends Converter {
    
    private double ANS;
    private boolean firstOperation;
    
    public Calculator() {
        ANS = 0.0;
        firstOperation = true;
    }
    
    public double getANS() {
        return ANS;
    }

    public String getANSinStringFormat() {
        String trimmedANS = trimOutput(ANS);
        if(trimmedANS != "Max val reached!") {
            trimmedANS = decToBase(trimmedANS);
            return trimmedANS;
        }
        else setANS(0.0);
        return trimmedANS;
    }
    
    public void setANS(double ANS) {
        this.ANS = ANS;
    }

    public boolean isFirstOperation() {
        return firstOperation;
    }

    public void setFirstOperation(boolean firstOperation) {
        this.firstOperation = firstOperation;
    }
    
    public int getBase() {
        return base;
    }
    
    public void setBase(int base) {
        this.base = base;
    }
    
    private String doOperation(String operator,String value) {
        double finalValue = ANS;
        if(operator != "inv")
            finalValue = baseToDec(value);
        
        if(!isFirstOperation())
            switch(operator) {
                case "add":
                    setANS(ANS + finalValue);
                    break;
                case "sub":
                    setANS(ANS - finalValue);
                    break;
                case "mul":
                    setANS(ANS * finalValue);
                    break;
                case "div":
                    if (finalValue == 0) return "div by zero!";
                    setANS(ANS / finalValue);
                    break;
                case "inv":
                    if (finalValue == 0) return "div by zero!";
                    setANS(1 / finalValue);
                    break;
            }
        else {
            setFirstOperation(false);
            setANS(finalValue);
        }
        
        String trimmedANS = trimOutput(ANS);
        if(trimmedANS != "Max val reached!") {
            trimmedANS = decToBase(trimmedANS);
            setANS(Double.parseDouble(trimmedANS));
        }
        else setANS(0.0);
        return trimmedANS;
    }
    
    public String add(String value) {
        return doOperation("add",value);
    }
    
    public String sub(String value) {
        return doOperation("sub",value);
    }
    
    public String mul(String value) {
        return doOperation("mul",value);
    }

    public String div(String value) {
        return doOperation("div",value);
    }
    
    public String inv() {
        return doOperation("inv",Double.toString(ANS));
    }
    
    public void assignment(String value) {
        setANS(baseToDec(value));
        setFirstOperation(false);
    }
}
