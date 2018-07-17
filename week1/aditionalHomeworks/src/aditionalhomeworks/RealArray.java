package aditionalhomeworks;
import java.util.Random;
/**
 *
 * @author cj
 */
public class RealArray implements Statistics {
    private final double array[];
    private final int arraySize;
    private final Random random;
    
    RealArray(int size) {
        array = new double[size];
        arraySize = size;
        random = new Random();
        for(int i=0; i < size; i++)
            array[i] = random.nextInt(10) + 1;
    }
    
    public int getArraySize() {
        return arraySize;
    }
    
    @Override
    public double min() {
        double min = Double.MAX_VALUE;
        for(double num : array)
            if(min > num)
                min = num;
        return min;
    }
    
    @Override
    public double max() {
        double max = Double.MIN_VALUE;
        for(double num : array)
            if(max < num)
                max = num;
        return max;
    }
    
    @Override
    public double summation() {
        double summation = 0;
        for(double num : array)
            summation += num;
        return summation;
    }
}
