package physics;

import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Allows the handling and calculation of coordinates, forces etc. as vectors.
 *
 * @author janne
 */
public class Vector {

    double x;
    double y;

    /**
     *
     * @param x The horizontal position
     * @param y The vertical position
     */
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

        return "x: " + df.format(x) + "; y: " + df.format(y) + "";
    }

    /**
     *Gives the length of a vector (sqrt(x^2 + y^2))
     * 
     * @return The length
     */
    public double length() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     *Gives the distance between two points.
     * @param otherV The position of the other point (as a Vector)
     * @return The distance
     */
    public double distance(Vector otherV) {
        Vector difference = this.subtract(otherV);
        return difference.length();
    }

    /**
     *Performs vector addition of two vectors.
     * 
     * @param otherV The Vector to be added to this vector.
     * @return The resulting vector.
     */
    public Vector add(Vector otherV) {
        return new Vector(x + otherV.getX(), y + otherV.getY());
    }

    /**
     *Performs vector subtraction of two vectors.
     * 
     * @param otherV The Vector to be subtracted from this vector.
     * @return The resulting vector.
     */
    public Vector subtract(Vector otherV) {
        return new Vector(x - otherV.getX(), y - otherV.getY());
    }

    /**
     *Performs scalar multiplication of the vector.
     * 
     * @param scalar The scalar that the vector will be multiplied by.
     * @return The resulting vector.
     */
    public Vector multiply(double scalar) {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
