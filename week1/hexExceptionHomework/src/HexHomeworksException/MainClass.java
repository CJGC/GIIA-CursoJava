package HexHomeworksException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author cj
 */
public class MainClass {

    public static Pattern pattern = Pattern.compile("^[0-9abcdfABCDF]+");
    public static Matcher matcher;
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        if(args.length != 1) {
            System.err.println("Is needed an argument of type hexadecimal!");
            return;
        }
        
        matcher = pattern.matcher(args[0]);
        try {
            if(!matcher.matches())
                throw new HexToDecException();
        }
        catch (HexToDecException e) {
            System.err.println(e.getMessage() + "\nPlease type a hexadecimal"
                + "number");
        }
        
        System.out.println("The number " + args[0] + " in the decimal format"
                + " is = " + Integer.parseInt(args[0],16));
    }
    
}

