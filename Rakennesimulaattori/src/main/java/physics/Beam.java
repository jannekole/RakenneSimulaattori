package physics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author janne
 */
public class Beam {
    int mass;
    double length;
    double stiffness;
    int strength;
    
    boolean isBroken;
    
    Node node1;
    Node node2;
    
    public Beam(Node node1, Node node2, double length, double materialStiffness, int mass, int strength) {
        this.node1 = node1;
        this.node2 = node2;
        
        isBroken = false;
        
        this.length = length;
        this.strength = strength;
        this.stiffness = materialStiffness / length;
        this.mass = mass;
        
        node1.addBeam(this);
        node2.addBeam(this);
        
    }
    
    public double distance() {
        return node1.getPosition().distance(node2.getPosition());
    }
    
    public Vector beamVector() {
        
        return node1.getPosition().subtract(node1.getPosition()); 
    }
    
    public double force() {
        if (isBroken) {
            return 0;
        }
        double force = stiffness * (length -  this.distance());
        if (Math.abs(force) > strength) {
            isBroken = true;
            return 0;
        }
        return force;
    }

    public Vector getForceVector() {
        return this.directionVector().multiply(this.force());  
    }

    private Vector directionVector() {
        return node1.positionV.subtract(node2.positionV).multiply(1 / this.distance());      
    }

   

    
}
