package dicehomework;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author cj
 */
public class MainClass {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        PairOfDies pairOfDies = new PairOfDies();
        System.out.println("Rolling the dices!");
        pairOfDies.roll();
        TimeUnit.SECONDS.sleep(1);
        System.out.print("Dice 1 has: " + pairOfDies.getDice1());
        System.out.print("\nDice 2 has: " + pairOfDies.getDice2());
        System.out.println();
    }
    
}
