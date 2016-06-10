package physics;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *Beams behave like springs, and calculate the forces impacted on Nodes based on
 * the parameters and the distance between the two nodes.
 * @author janne
 */
public class Beam {

    int mass;
    double length;
    double stiffness;
    int strength;

    double materialStiffness;

    double calculatedForce;

    double dampeningFactor = 0.2;
    double dampeningFactor2 = 0; //triangle height
    double dampeningFactor3 = 0; //triangle width newForce/oldForce
    
    boolean isBroken;

    ArrayList<Node> nodes;


//    Node node1;
//    Node node2;
    public Beam(Node node1, Node node2, double materialStiffness, int mass, int strength) {
        this(node1, node2, materialStiffness, mass, strength, 0);
    }

    public Beam(Node node1, Node node2, double materialStiffness, int mass, int strength, double length) {

        this.nodes = new ArrayList();

        addNode(node1);
        addNode(node2);

        if (length == 0) {
            setLengthToNodeDistance();
        } else {
            setLength(length);
        }

        isBroken = false;

        setMaterialStiffness(materialStiffness);
        setStrength(strength);
        setMass(mass);

        for (Node node : nodes) {
            node.addBeam(this);
        }
    }

    public final void addNode(Node node) {
        if (!nodes.contains(node) && nodes.size() < 2 && node != null) {
            nodes.add(node);
        } else {
            throw new IllegalArgumentException("Nodes can not be the same");
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
        
        // newForce = experimentalDampen(newForce, oldForce);
        
        if  (newForce * (newForce - oldForce) * newForce * oldForce < 0) { //  Absolute value of force is decreasing and oldForce has same sign as newForce, (Math.abs(newForce) < Math.abs(oldForce) && oldForce * newForce > 0)
            newForce = newForce * (1 - dampeningFactor);
        }
        

        return newForce;
    }

    void calculateNewState() {
        if (isBroken) {
            calculatedForce = 0;
            return;
        }
        double newForce = materialStiffness / length * (length -  this.getNodeDistance());
                
        if (Math.abs(newForce) > strength) {
            isBroken = true;
            calculatedForce = 0;
            return;
        }
        calculatedForce = dampen(newForce);
    }

    public double getForce() {
        return calculatedForce;
    }

    public Vector getForceVector(Node node) {
        if (node == nodes.get(0)) {
            return this.directionUnitVector().multiply(this.getForce());
        } else if (node == nodes.get(1)) {
            return this.directionUnitVector().multiply(this.getForce() * (-1));
        }

        throw new IllegalArgumentException("Node not in beam");

    }

    private Vector directionUnitVector() {
        return nodes.get(0).getPosition().subtract(nodes.get(1).getPosition()).multiply(1 / this.getNodeDistance());
    }

    public int getMass() {
        return mass;
    }

    public final void setMass(int mass) {

        if (mass > 0) {
            this.mass = mass;
        } else {
            throw new IllegalArgumentException("Mass has to be greater than 0, was " + mass);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public final void setLengthToNodeDistance() {
        double newLength = getNodeDistance();

        setLength(newLength);
    }

    public double getLength() {
        return length;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public double getMaterialStiffness() {
        return materialStiffness;
    }

    public final void setMaterialStiffness(double materialStiffness) {

        if (materialStiffness > 0) {
            this.materialStiffness = materialStiffness;
        } else {
            throw new IllegalArgumentException("Stiffness has to be greater than 0, was " + materialStiffness);
        }
    }

    public int getStrength() {
        return strength;
    }

    public final void setStrength(int strength) {

        if (strength > 0) {
            this.strength = strength;
        } else {
            throw new IllegalArgumentException("Strength has to be greater than 0, was " + strength);
        }
    }

    public double getDampeningFactor() {
        return dampeningFactor;
    }

    public void setDampeningFactor(double dampeningFactor) {
        this.dampeningFactor = dampeningFactor;
    }

    public final void setLength(double length) {

        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length has to be greater than 0, was " + length);
        }
    }

    private double experimentalDampen(double newForce, double oldForce) {
    if (oldForce != 0){
            newForce = newForce - oldForce * dampeningFactor2 * Math.min(1, Math.max(0, (1 - Math.abs(newForce) / Math.abs(oldForce) / dampeningFactor3)));
        }
    return newForce;
    }
}
