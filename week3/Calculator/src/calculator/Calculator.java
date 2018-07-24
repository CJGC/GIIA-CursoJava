package calculator;
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

    public String getANSinStringFormat() {
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
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
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
    }
    
    public String sub(String value) {
        double finalValue = baseToDec(value);
        setANS(ANS - finalValue);
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
    }
    
    public String mul(String value) {
        double finalValue = baseToDec(value);
        setANS(ANS * finalValue);
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
    }

    public String div(String value) {
        double finalValue = baseToDec(value);
        if (finalValue == 0)
            return "div by zero!";
        setANS(ANS / finalValue);
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
    }
    
    public String inv() {
        if (getANS() == 0) 
            return "div by zero!";
        setANS(1 / ANS);
        String output = trimOutput(getANS());
        if(output == "Max val reached!") {
            setANS(0.0);
            return output;
        }
        setANS(Double.parseDouble(output));
        return decToBase(output);
    }
    
    public void assignment(String value) {
        setANS(baseToDec(value));
    }
}
