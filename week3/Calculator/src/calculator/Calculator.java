package calculator;
/**
 *
 * @author cj
 */
final class Calculator extends Converter {
    
    private double ANS;

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
        ANS += finalValue;
        return decToBase(Double.toString(ANS));
    }
    
    public String sub(String value) {
        double finalValue = baseToDec(value);
        ANS -= finalValue;
        return decToBase(Double.toString(ANS));
    }
    
    public String mul(String value) {
        double finalValue = baseToDec(value);
        ANS *= finalValue;
        return decToBase(Double.toString(ANS));
    }

    public String div(String value) {
        double finalValue = baseToDec(value);
        if (finalValue == 0)
            return "div by zero!";
        ANS /= finalValue;
        return decToBase(Double.toString(ANS));
    }
    
    public String inv() {
        if (ANS == 0) 
            return "div by zero!";
        ANS = 1/this.ANS;
        return decToBase(Double.toString(ANS));
    }
}
