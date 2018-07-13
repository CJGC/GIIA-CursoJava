package homeworks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author cj
 */
public class Homeworks {

    public static void main(String[] args) {
        NumbersWithoutEnding2and5 numbersWithout2and5 = 
                new NumbersWithoutEnding2and5(1,100);
        
        // ------ homework 1 printing numbers whitout ending in 2 and 5 ------
        numbersWithout2and5.printNumbers();
        System.out.println();
        
        // ------ homework 2 printing even numbers into an interval ------
        EvenNumbers evenNumbers = new EvenNumbers(1539,2505);
        evenNumbers.printEvenNumbers();
        System.out.println();
        
        // ------ homework 3 using command line arguments ------
        if(args.length != 2) {
            System.err.println("There must be 2 armuments!");
            return;
        }
        
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        
        Arithmetic arithmetic = new Arithmetic(x,y);
        System.out.println(x+" + "+y+" = " +arithmetic.add());
        System.out.println(x+" - "+y+" = " +arithmetic.sub());
        System.out.println(x+" * "+y+" = " +arithmetic.mul());
        System.out.println(x+" / "+y+" = " +arithmetic.div());
        
        // ------ homework 4 getting user input with BufferedReader ------
        BufferedReader dataIn = new BufferedReader
            (new InputStreamReader(System.in));
        String userInput = "";
        try {
            System.out.print("\n(BufferedReader input) Type some text: ");
            userInput = dataIn.readLine();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("\nYou've typed: "+userInput);
        
        // ------ homework 5 getting user input with Scanner ------
        Scanner scanIn = new Scanner(System.in);
        System.out.print("\n(Scanner input) Type some text: ");
        userInput = scanIn.nextLine();
        System.out.println("\nYou've typed: "+userInput);
        
        // ------ homework 6 getting user input with swing ------
        userInput = JOptionPane.showInputDialog("Type some text: ");
        String msg = "You've type: " + userInput;
        JOptionPane.showMessageDialog(null,msg);
    }
    
}
