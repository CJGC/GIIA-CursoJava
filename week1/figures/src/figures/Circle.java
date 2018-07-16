package figures;
/**
 *
 * @author cj
 */
public class Circle extends Shape{
    private String name;
    private double r;
    
    @Override
    public double getArea(){
        return Math.PI*Math.pow(r,2);
    }
    
    public double getR() {
        return r;
    }
    
    public void setR(double r) {
        if(r < 0) {
            System.err.println("Radius can not be negative!");
            this.r = 0;
            return;
        }
        this.r = r;
    }

    @Override
    public String getName(){
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
