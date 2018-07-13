package homeworks;

/**
 *
 * @author cj
 */
public class Arithmetic {
    
    private double x;
    private double y;
    
    Arithmetic(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    public double add() {
        return this.x + this.y;
    }
 
    public double sub() {
        return this.x - this.y;
    }

    public double mul() {
        return this.x * this.y;
    }
 
    public double div() {
        if(y == 0) { 
            System.err.print("Division by zero!");
            return -1.0;
        }
        return this.x / this.y;
    }
}
