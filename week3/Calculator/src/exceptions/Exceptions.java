package exceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cj
 */
public class Exceptions {
    
    private final static Pattern decValuePattern = 
        Pattern.compile("^\\-?[0-9]+(\\.[0-9]+)?$");

    private final static Pattern binValuePattern = 
        Pattern.compile("^[0-1]+(\\.[0-1]+)?$");
    
    private final static Pattern octValuePattern = 
        Pattern.compile("^[0-7]+(\\.[0-7]+)?$");    
    
    private final static Pattern hexValuePattern = 
        Pattern.compile("^[0-9ABCDEF]+(\\.[0-9ABCDEF]+)?$");

    protected static Matcher matcher;
    
    public static void checkLastOperationValue(String value) {
        if(value.equals("assignment") || value.equals("add") || 
           value.equals("sub") || value.equals("mul") || value.equals("div"))
            return;
        throw new IllegalArgumentException("Invalid last operation value");
    }
    
    public static boolean checkDotsOnNumber(String number) {
        int dots = 0;
        for(char ch : number.toCharArray()) {
            if (ch == '.') 
                dots++;
            if (dots > 1)
                return true;
        }
        return false;
    }
    
    public static String checkSyntaxInputValue(String ANS,int base) {
        switch(base) {
            case 10:
                if (checkDecAnsValue(ANS))
                    ANS = "sintax error!";
                break;
            case 2:
                if (checkBinAnsValue(ANS))
                    ANS = "sintax error!";
                break;
            case 8:
                if (checkOctAnsValue(ANS))
                    ANS = "sintax error!";
                break;
            case 16:
                if (checkHexAnsValue(ANS))
                    ANS = "sintax error!";
                break;
            default:
                checkBase(base);
        }
        return ANS;
    }
    
    private static boolean checkDecAnsValue (String value) {
        matcher = decValuePattern.matcher(value);
        return !matcher.matches();
    }
    
    private static boolean checkBinAnsValue (String value) {
        matcher = binValuePattern.matcher(value);
        return !matcher.matches();
    }
    
    private static boolean checkOctAnsValue (String value) {
        matcher = octValuePattern.matcher(value);
        return !matcher.matches();
    }
    
    private static boolean checkHexAnsValue (String value) {
        matcher = hexValuePattern.matcher(value);
        return !matcher.matches();
    }
    
    public static void checkDecValue (String value) {
        matcher = decValuePattern.matcher(value);
        if(! matcher.matches())
            throw new IllegalArgumentException("Invalid value format for"
                    + " decimal number");
    }
    
    public static void checkBinValue (String value) {
        matcher = binValuePattern.matcher(value);
        if(! matcher.matches())
            throw new IllegalArgumentException("Invalid value format for"
                    + " binary number");
    }
    
    public static void checkOctValue (String value) {
        matcher = octValuePattern.matcher(value);
        if(! matcher.matches())
            throw new IllegalArgumentException("Invalid value format for"
                    + " octal number");
    }
    
    public static void checkHexValue (String value) {
        matcher = hexValuePattern.matcher(value);
        if(! matcher.matches())
            throw new IllegalArgumentException("Invalid value format for"
                    + " hexadecimal number");
    }
    
    public static void checkBase (int base) {
        if( base == 2 || base == 8 || base == 16 || base == 10)
            return;
        throw new IllegalArgumentException("Unsupported base");
    }
    
    public static void noValidHexDigit () {
        throw new NullPointerException("Unsupported hexadecimal digit");
    }
}
