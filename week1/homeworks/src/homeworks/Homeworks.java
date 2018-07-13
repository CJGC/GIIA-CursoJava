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
        NumbersWithout2and5 numbersWithout2and5 = new NumbersWithout2and5();
        EvenNumbers evenNumbers = new EvenNumbers(1539,2505);
        
        numbersWithout2and5.printNumbers();
        System.out.println();
        evenNumbers.printEvenNumbers();
        System.out.println();
    }
    
}
