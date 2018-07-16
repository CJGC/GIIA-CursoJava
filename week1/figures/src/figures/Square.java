package figures;
/**
 *
 * @author cj
 */
public class Square extends Shape {
    
    private String name;
    private double l;
    
    @Override
    public double getArea(){
        return l*l;
    }
    
    public void setL(double l) {
        if(l < 0) {
            System.err.println("Side can not be negative!");
            this.l = 0;
            return;
        }
       this.l = l;
    }

    public double getL() {
        return l;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
