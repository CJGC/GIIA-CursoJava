package dicehomework;
import java.util.Random;

/**
 *
 * @author cj
 */
public class PairOfDies implements Dice{
    
    private int dice1;
    private int dice2;
    private final Random random;
    
    PairOfDies() {
        random = new Random();
    }
    
    @Override
    public void roll() {
        dice1 = random.nextInt(6) + 1;
        dice2 = random.nextInt(6) + 1;
    }

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

}
