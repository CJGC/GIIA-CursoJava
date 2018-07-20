package calculator;
import exceptions.Exceptions;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cj
 */
public class Converter {
    protected int base;
    
    Converter() {
        base = 10;
    }
    
    private String hexRules(int number) {
        if(number < 10)
            return Integer.toString(number);
        
        switch(number) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                Exceptions.noValidHexDigit();
                return "";
        }
    }
    
    private String conv(String value, int base) {
        String[] number = value.split("\\.");
        String finalNumber = "";
        int intePart = Integer.parseInt(number[0]);
        double doubPart = Double.parseDouble("0." + number[1]);
        doubPart = Math.floor(doubPart * 1e2) / 1e2;
        
        // ------ Integer part conversion ------
        switch (base) {
            case 2:
                finalNumber += Integer.toBinaryString(intePart) + ".";
            case 8:
                finalNumber += Integer.toOctalString(intePart) + ".";
            case 16:
                finalNumber += Integer.toHexString(intePart) + ".";
        }
        
        // ------- double part conversion -------
        List<Double> values;
        values = new ArrayList<>();
        
        while (!(values.contains(doubPart)) && doubPart != 0.00) {
            values.add(doubPart);
            doubPart *= base;
            intePart = (int) (Math.floor(doubPart * 1e0) / 1e0);
            doubPart -= intePart;
            doubPart = Math.floor(doubPart * 1e2) / 1e2;
            
            if (base != 16)
                finalNumber += intePart;
            else
                finalNumber += hexRules(intePart);
        }
        
        return finalNumber;        
    }
    
    public String decToBase(String value) {
        Exceptions.checkBase(base);
        
        switch (this.base) {            
            case 10:
                Exceptions.checkDecValue(value);
                return value;
            case 2:
                Exceptions.checkBinValue(value);
                return conv(value,2);
            case 8:
                Exceptions.checkOctValue(value);
                return conv(value,8);
            case 16:
                Exceptions.checkHexValue(value);
                return conv(value,16);
            default:
                return "";
        }
    }
    
    private int decodeCharHex(char ch) {
        switch (ch) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            default:
                return Character.getNumericValue(ch);
        }
    }
    
    public double baseToDec(String value) {
        Exceptions.checkBase(this.base);
        
        switch (this.base) {
            case 10:
                Exceptions.checkDecValue(value);
                return Double.parseDouble(value);
            case 2:
                Exceptions.checkBinValue(value);
                break;
            case 8:
                Exceptions.checkOctValue(value);
                break;
            case 16:
                Exceptions.checkHexValue(value);
                break;
        }
        
        String[] number = value.split("\\.");
        String intePart = number[0];
        String doubPart = number[1];
        double finalNumber = 0.0;
        
        // ------ integer part conversion ------
        int integer = Integer.parseInt(intePart,this.base);
        
        // ------ double part conversion ------
        int i = -1;
        for(char ch : doubPart.toCharArray()) {
            if(this.base != 16)
                finalNumber += Character.getNumericValue(ch) * 
                    Math.pow(this.base,i);
            else
                finalNumber += decodeCharHex(ch) * Math.pow(this.base,i);
            i--;
        }
        
        finalNumber += integer;
        return finalNumber;
    }
    
}
