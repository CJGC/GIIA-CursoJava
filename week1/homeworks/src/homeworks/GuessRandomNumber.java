package homeworks;
import java.util.Random;
/**
 *
 * @author cj
 */
public class GuessRandomNumber {
    private int randomNumber;
    private boolean guestState;
    private int times;
    
    GuessRandomNumber() {
        this.guestState = false;
        Random random = new Random();
        this.randomNumber = random.nextInt(100) + 1;
        this.times = 0;
    }

    private int getTimes() {
        return times;
    }

    private void setTimes(int times) {
        this.times = times;
    }
    
    public String checkIfGuessed(String userInput) {
        int input;
        try {
            input = Integer.parseInt(userInput);
        }
        catch(NumberFormatException e) {
            System.err.println(e.getMessage() + "\nPlease enter a number!");
            return "";
        }

        setTimes(++this.times);
        if(input == this.randomNumber) {
            this.guestState = true;
            return "Congrats!, you've guessed the number in " +
                    this.getTimes() + " times!";
        }
        else if(input < this.randomNumber)
            return "The number " + input + " is lower than!";
        else
            return "The number " + input + " is greater than!";
    }
    
    public boolean isGuessed() {
        return this.guestState;
    }
    
}
