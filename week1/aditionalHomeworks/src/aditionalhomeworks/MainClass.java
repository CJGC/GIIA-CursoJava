package aditionalhomeworks;
import java.util.Random;
/**
 *
 * @author cj
 */
public class MainClass {

    public static Random random = new Random();
    
    public static double[] buildDoubleArray(int size) {
        double array[] = new double[size];
        for(int i=0; i<size; i++)
            array[i] = 10*random.nextDouble();
        return array;
    }
    
    public static int[] buildIntArray(int size) {
        int array[] = new int[size];
        for(int i=0; i<size; i++)
            array[i] = random.nextInt(10) + 1;
        return array;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // --------- additional homework 1 ---------
        Invoice invoice = new Invoice();
        invoice.setEmisor("Tech solutions");
        invoice.setClient("Marco Antonio Alvarez");
        invoice.setPesos(55000);
        invoice.printInvoice();
        
        // --------- additional homework 2 ---------
        double array1[];
        array1 = buildDoubleArray(10);
        Math2.printArray(array1);
        System.out.println("\nThe min: " + Math2.df.format(Math2.min(array1)));
        System.out.println("The max: " + Math2.df.format(Math2.max(array1)));
        System.out.println("The summation: " + 
            Math2.df.format(Math2.summation(array1)));
        System.out.println("The arithmetic mean: " + 
            Math2.df.format(Math2.arithmeticMean(array1)));
        System.out.println("The geometric mean: " +
            Math2.df.format(Math2.geometricMean(array1)));
        
        // --------- additional homework 3 ---------
        RealArray realArray = new RealArray(10);
        System.out.println("\nMin in realArray object: " + realArray.min());
        System.out.println("Max in realArray object: " + realArray.max());
        System.out.println("summation in realArray object: " + 
            realArray.summation());
        
        // --------- additional homework 4 ---------
        double array2[];
        int array3[];
        array2 = buildDoubleArray(10);
        array3 = buildIntArray(10);
        
        Math3 math3 = new Math3();
        System.out.println("\nContent in the double array");
        math3.printDoubleArray(array2);
        System.out.println("\nContent in the int array");
        math3.printIntArray(array3);
        
        System.out.println("\nThe start side in the double array: " + 
            math3.min(array2));
        System.out.println("The end side in the double array: " + 
            math3.max(array2));
        System.out.println("The start side in the int array: " + 
            math3.min(array3));
        System.out.println("The end side in the int array: " + 
            math3.max(array3));
    }
    
}
