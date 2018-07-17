package interfacehomework;
import java.util.Random;
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
        Random random = new Random();
        int randomNumber = random.nextInt(1) + 0;
        Time time1, time2;
        
        if (randomNumber == 0) {
            time1 = new Time();
            TimeUnit.SECONDS.sleep(random.nextInt(1) + 0);
            time2 = new Time();
        }
        else{
            time2 = new Time();
            TimeUnit.SECONDS.sleep(randomNumber);
            time1 = new Time();
        }
        
        if (time1.isGreater(time2) )
            System.out.println("Time 1 is greater than Time 2");
        else if(time1.isLess(time2))
            System.out.println("Time 1 is less than time 2");
        else if(time2.isEqual(time1))
            System.out.println("Time 1 is equal than time 2");
    }
    
}
