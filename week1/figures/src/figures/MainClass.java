/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package figures;

/**
 *
 * @author cj
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Square square = new Square();
        Circle circle = new Circle();
        
        square.setName("square 1");
        square.setL(5);
        
        circle.setName("circle 1");
        circle.setR(2);
        
        System.out.println("Area of " + square.getName() + " with side "
            + square.getL() + " = " + square.getArea());
        
        System.out.println("\nArea of " + circle.getName() + " with radius "
            + circle.getR() + " = " + circle.getArea());
    }
    
}
