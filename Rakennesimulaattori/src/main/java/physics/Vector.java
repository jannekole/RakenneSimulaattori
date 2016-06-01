package physics;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author janne
 */
public class Vector {
 
    double x;
    double y;
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }    
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("00.0000");

        return "x: " + df.format(x) + " y: " + df.format(y) + "";
    }
    
    
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));    
    }
    
    public double distance(Vector otherV) {
        Vector difference = this.subtract(otherV);       
        return difference.length();
    }
    
    public Vector add(Vector otherV) {
        return new Vector(x + otherV.getX(), y + otherV.getY());
    }
    public Vector subtract(Vector otherV) {
        return new Vector(x - otherV.getX(), y - otherV.getY());
    }    

    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }


}
