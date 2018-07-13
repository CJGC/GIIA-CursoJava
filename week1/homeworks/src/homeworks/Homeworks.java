package homeworks;

/**
 *
 * @author cj
 */
public class Homeworks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NumbersWithout2and5 numbersWithout2and5 = new NumbersWithout2and5(1,100);
        EvenNumbers evenNumbers = new EvenNumbers(1539,2505);
        
        // ------ homework 1 ------
        numbersWithout2and5.printNumbers();
        System.out.println();
        
        // ------ homework 2 ------
        evenNumbers.printEvenNumbers();
        System.out.println();
        
        // ------ homework 3 ------
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
    }
    
}
