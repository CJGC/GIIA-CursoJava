package aditionalhomeworks;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/**
 *
 * @author cj
 */
final class Math2 {
    
    public static DecimalFormat df = new DecimalFormat("#.##");
    
    Math2() {
        df.setRoundingMode(RoundingMode.CEILING);
    }
    
    public static double min(double array[]) {
        double min = Double.MAX_VALUE;
        for(double num : array)
            if(min > num)
                min = num;
        return min;
    }
    
    public static double max(double array[]) {
        double max = Double.MIN_VALUE;
        for(double num : array)
            if(max < num)
                max = num;
        return max;
    }
    
    public static double summation(double array[]) {
        double summation = 0;
        for(double num : array)
            summation += num;
        return summation;
    }
    
    public static double arithmeticMean(double array[]) {
        double arithmeticMean = 0;
        for(double num : array)
            arithmeticMean += num;
        return arithmeticMean /= array.length;
    }
    
    public static double geometricMean(double array[]) {
        double geometricMean = 1;
        for(double num : array)
            geometricMean *= num;
        return Math.pow(geometricMean, 1/array.length);
    }
    
    public static void printArray(double array[]) {
        for(double num : array) 
            System.out.print(df.format(num) + " ");
    }
}
