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
        
        /* ------ Objects initialization ------ */
        String userInput = "", msg;
        double x,y;
        
        try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
        }
        catch (NumberFormatException e) {
           System.err.println(e.getMessage() + "\nPlease enter numbers!");
           return;
        }
        
        EvenNumbers evenNumbers = new EvenNumbers(1539,2505);
        Arithmetic arithmetic = new Arithmetic(x,y);
        GuessRandomNumber guessRandomNumber = new GuessRandomNumber();
        Scanner scanIn = new Scanner(System.in);
        NumbersWithoutEnding2and5 numbersWithout2and5 = 
                new NumbersWithoutEnding2and5(1,100);
        BufferedReader dataIn = new BufferedReader
            (new InputStreamReader(System.in));
        
        /* ------ homework 1 printing numbers whitout ending in 2 and 5 ------*/
        numbersWithout2and5.printNumbers();
        System.out.println();
        
        /* ------ homework 2 printing even numbers into an interval ------ */
        evenNumbers.printEvenNumbers();
        System.out.println();
        
        /* ------ homework 3 using command line arguments ------ */
        if(args.length != 2) {
            System.err.println("There must be 2 armuments!");
            return;
        }
        
        System.out.println(x + " + " + y + " = " + arithmetic.add());
        System.out.println(x + " - " + y + " = " + arithmetic.sub());
        System.out.println(x + " * " + y + " = " + arithmetic.mul());
        System.out.println(x + " / " + y + " = " + arithmetic.div());
        
        /* ------ homework 4 getting user input with BufferedReader ------ */
        try {
            System.out.print("\n(BufferedReader input) Type some text: ");
            userInput = dataIn.readLine();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("You've typed: " + userInput);
        
        /* ------ homework 5 getting user input with Scanner ------ */
        System.out.print("\n(Scanner input) Type some text: ");
        userInput = scanIn.nextLine();
        System.out.println("You've typed: " + userInput);
        
        /* ------ homework 6 getting user input with swing ------ */
        userInput = JOptionPane.showInputDialog("Type some text: ");
        msg = "You've type: " + userInput;
        JOptionPane.showMessageDialog(null,msg);
        
        /* ------ homework 7 guessing a random number ------ */
        System.out.println("\nYou are going to guess a number!\n");

        do {
            System.out.print("Type a number: ");
            userInput = scanIn.nextLine();
            msg = guessRandomNumber.checkIfGuessed(userInput);
            System.out.println(msg);
        } while(!guessRandomNumber.isGuessed());
    }
    
}
