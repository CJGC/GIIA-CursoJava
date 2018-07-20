package exceptions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cj
 */
public class Exceptions {
    
    protected static Pattern decValuePattern = 
        Pattern.compile("\\-?[0-9]+(\\.[0-9]+)?$");

    protected static Pattern binValuePattern = 
        Pattern.compile("^[0-1]+(\\.[0-1]+)?$");
    
    protected static Pattern octValuePattern = 
        Pattern.compile("^[0-7]+(\\.[0-7]+)?$");    
    
    protected static Pattern hexValuePattern = 
        Pattern.compile("^[0-9ABCDEF]+\\.[0-9ABCDEF]+$");
    
    protected static Matcher matcher;
    
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
