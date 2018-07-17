package diamondHomework;

import java.io.IOException;

/**
 *
 * @author cj
 */
public class MainClass {

    public static void printDiamond(int tiers, int tier) {
        int lenghtLine = tiers + (tiers - 1);
        int spaces = lenghtLine - ((2*tier)+1);
        int middleSpaces = spaces/2;
        String line = "";
        
        // ------------- building the line -------------
        for(int i=0; i < lenghtLine; i++) {
            if(i >= middleSpaces && i < (lenghtLine - middleSpaces) )
                line += "*";
            else
                line += " ";
        }
        
        System.out.println(line);
        if(tier < tiers-1) {
            printDiamond(tiers,tier+1);
            System.out.println(line);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int tiers;
        try {
            tiers = Integer.parseInt(args[0]);
            if(args.length != 1)
                throw new IllegalArgumentException("More than one argument...");
            else if(!(tiers > 0))
                throw new IOException("Negative argument...");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage() + "\nPlease enter one argument!");
            return;
        }
        catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + "\nPlease enter only one "
                    + "positive integer!");
            return;
        }
        catch (IOException e) {
            System.err.println(e.getMessage() + "\nPlease enter a non negative"
                    + " integer!");
            return;
        }
        
        int tier = 0;
        printDiamond(tiers,tier);
    }
    
}
