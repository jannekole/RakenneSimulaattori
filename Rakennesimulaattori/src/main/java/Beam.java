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
    
    Node node1;
    Node node2;
    
    public Beam(Node node1, Node node2, double length, double stiffness, int mass, int strength){
        this.node1 = node1;
        this.node2 = node2;
        
        this.length = length;
        this.strength = strength;
        this.stiffness = stiffness;
        this.mass = mass;
        
        node1.addBeam(this);
        node2.addBeam(this);
        
    }
    
    public double distance(){
        return node1.getPosition().distance(node2.getPosition());
    }
    
    public Vector beamVector(){
        
        return node1.getPosition().subtract(node1.getPosition()); 
    }
    
    public double force(){
        double force = stiffness * (length -  this.distance());
        return force;
    }

    Vector forceVector() {
        return this.directionVector().multiply(this.force());  
    }

    private Vector directionVector() {
        return node1.position.subtract(node2.position).multiply(1 / this.distance());      
    }

   

    
}
