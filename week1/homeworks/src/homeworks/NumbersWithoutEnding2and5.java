package homeworks;

/**
 *
 * @author cj
 */
public class NumbersWithoutEnding2and5 {
    private int range2;
    private int range5;
    private int start;
    private int end;
    
    NumbersWithoutEnding2and5(int start,int end) {
        this.range2 = 2;
        this.range5 = 5;
        this.start = start;
        this.end = end;
    }; 

    public void printNumbers() {
        
        for(int i=this.start; i <= this.end; i++) {
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
