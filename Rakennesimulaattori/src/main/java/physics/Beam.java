package physics;

import java.util.ArrayList;

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
    
    double calculatedForce;
    
    double dampeningFactor = 0.1; 
    
    boolean isBroken;
    
    
    
    ArrayList<Node> nodes;
    
//    Node node1;
//    Node node2;
   
    public Beam(Node node1, Node node2, double materialStiffness, int mass, int strength) {
        this(node1, node2, materialStiffness, mass, strength, 0);
    }
 
    public Beam(Node node1, Node node2, double materialStiffness, int mass, int strength, double length) {
        

        
        this.nodes = new ArrayList();
        
        this.nodes.add(node1);
        this.nodes.add(node2);
        
        if (length == 0) {
            setLengthToNodes();
        }
        else {
            this.length = length;
        }
        
        isBroken = false;
        
        
        
        this.strength = strength;
        this.stiffness = materialStiffness / this.length;
        this.mass = mass;
        
        for (Node node : nodes) {
            node.addBeam(this);
        }
          

    }    
     
    
        

       
            
       
        
    
    
    public double getNodeDistance() {
        return nodes.get(0).getPosition().distance(nodes.get(1).getPosition());
    }
    
    public Vector beamVector() {
        
        return nodes.get(0).getPosition().subtract(nodes.get(1).getPosition()); 
    }

    private double dampen(double newForce) {
        double oldForce = calculatedForce;
        if (Math.abs(newForce) < Math.abs(oldForce)) {
            newForce = newForce * (1 - dampeningFactor);
        }

        return newForce;
    }

    void calculateNewState() {
        if (isBroken) {
            calculatedForce = 0;
            return;
        }
        double newForce = stiffness * (length -  this.getNodeDistance());
        
        //System.out.print("length" + length + " dist: " + this.getNodeDistance());
        
        if (Math.abs(newForce) > strength) {
            isBroken = true;
            calculatedForce = 0;
            return;
        }
        calculatedForce = dampen(newForce);
        //System.out.print("calc f" + calculatedForce);
        
        
        
    }    
    
    
    public double getForce() {

        return calculatedForce;
    }

    public Vector getForceVector(Node node) {
        if (node == nodes.get(0)) {
            return this.directionUnitVector().multiply(this.getForce());
        }
        else if (node == nodes.get(1)) {
            return this.directionUnitVector().multiply(this.getForce() * (-1));
        }

        return new Vector(0,0);
        
    }

    private Vector directionUnitVector() {
        return nodes.get(0).positionV.subtract(nodes.get(1).positionV).multiply(1 / this.getNodeDistance());      
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    private void setLengthToNodes() {
        length = getNodeDistance();
    }

    public double getLength() {
        return length;
    }

    public boolean isBroken() {
        return isBroken;
    }

    
}
