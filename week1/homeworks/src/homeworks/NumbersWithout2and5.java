package homeworks;

/**
 *
 * @author cj
 */
public class NumbersWithout2and5 {
    private int range2;
    private int range5;
    
    NumbersWithout2and5() {
        this.range2 = 2;
        this.range5 = 5;
    };
    
    public void printNumbers() {
        
        for(int i=1; i <= 100; i++) {
            if(this.range2 == i) {
                this.range2 += 10;
                continue;
            }
            if(this.range5 == i) {
                this.range5 += 10;
                continue;
            }
            System.out.print(i+" ");
        }
        
    }
}
