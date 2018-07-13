package homeworks;

/**
 *
 * @author cj
 */
public class EvenNumbers {
    
    private int start;
    private int end;
    
    EvenNumbers(int start,int end) {
        this.start = start;
        this.end = end;
    };
    
    public void printEvenNumbers() {
        
        for(int i=this.start; i <= this.end; i++)
            if(i % 2 == 0)
                System.out.print(i+" ");
    
    }
}
