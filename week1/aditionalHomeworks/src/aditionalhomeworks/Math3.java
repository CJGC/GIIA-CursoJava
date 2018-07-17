package aditionalhomeworks;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author cj
 */
final class Math3 implements EndSide{
    private final DecimalFormat df;
    
    Math3() {
        df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
    }
    
    public void printDoubleArray(double array[]) {
        for(double num : array) 
            System.out.print(df.format(num) + " ");
    }
    
    public void printIntArray(int array[]) {
        for(int num : array) 
            System.out.print(num + " ");
    }
    
    @Override
    public int min(int []array) {
        if(!(array.length > 0)) {
            System.err.println("The array has not been initialized!");
            return 0;
        }
        return array[0];
    }

    @Override
    public int max(int []array) {
        if(!(array.length > 0)) {
            System.err.println("The array has not been initialized!");
            return 0;
        }
        return array[array.length - 1];
    }

    @Override
    public double min(double []array) {
        if(!(array.length > 0)) {
            System.err.println("The array has not been initialized!");
            return 0;
        }
        String min = df.format(array[0]);
        return Double.parseDouble(min);
    }

    @Override
    public double max(double []array) {
        if(!(array.length > 0)) {
            System.err.println("The array has not been initialized!");
            return 0;
        }
        String max = df.format(array[array.length - 1]);
        return Double.parseDouble(max);
    }
    
}
