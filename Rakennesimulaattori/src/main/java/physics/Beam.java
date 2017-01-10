package physics;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Beams behave like springs, and calculate the forces impacted on Nodes based
 * on the parameters and the distance between the two nodes.
 *
 * @author janne
 */
public class Beam {

    private double mass;
    private double length;
    private double strength;
    private double materialStiffness;
    
    private double calculatedForce;
    
    private boolean isBroken;
    
    private double dampeningFactor = 0.5;
    
    private boolean useExperimentalDampening = false;
    
    private double experimentalDampeningFactor1 = 0; //  triangle height
    private double experimentalDampeningFactor2 = 0; //  triangle width newForce/oldForce

    ArrayList<Node> nodes;

    /**
     * Constructs a Beam with the given parameters that is attached to the given Nodes. Sets the length of the beam to the distance between the Nodes.
     * @param node1 A node to attach to the Beam.
     * @param node2 A node to attach to the Beam.
     * @param materialStiffness The stiffness of the Beam. This parameter gives the stiffness of the material, so a longer Beam with the same materialStiffness will stretch/compress more with the same force.
     * @param mass The mass of the Beam
     * @param strength The maximum force that the Beam can take before breaking.
     */
    public Beam(Node node1, Node node2, double materialStiffness, double mass, double strength) {
        this(node1, node2, materialStiffness, mass, strength, 0);
    }
    /**
     * Constructs a Beam with the given parameters that is attached to the given Nodes.
     * @param node1 A node to attach to the Beam.
     * @param node2 A node to attach to the Beam.
     * @param materialStiffness The stiffness of the Beam. This parameter gives the stiffness of the material, so a longer Beam with the same materialStiffness will stretch/compress more with the same force.
     * @param mass The mass of the Beam
     * @param strength The maximum force that the Beam can take before breaking.
     * @param length Sets the length of the Beam. If set to 0, the Beam will set the length to match the distance between the attached nodes.
     */
    public Beam(Node node1, Node node2, double materialStiffness, double mass, double strength, double length) {

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

    private void addNode(Node node) {
        if (!nodes.contains(node) && nodes.size() < 2 && node != null) {
            nodes.add(node);
        } else {
            throw new IllegalArgumentException("Nodes can not be the same");
        }
    }

    /**
     * @return Returns the distance between the two nodes attached to the Beam.
     */
    private double getNodeDistance() {
        return nodes.get(0).getPositionV().distance(nodes.get(1).getPositionV());
    }

    private double dampen(double newForce) {
        double oldForce = calculatedForce;
        
        if (useExperimentalDampening) {
            newForce = experimentalDampen(newForce, oldForce);
        }
        if ((newForce - oldForce) * newForce < 0) { //  Absolute value of force is decreasing and oldForce has same sign as newForce, or (Math.abs(newForce) < Math.abs(oldForce) && oldForce * newForce > 0)
            newForce = newForce * (1 - dampeningFactor);
        }
        return newForce;
    }

    /**
     * Calculates the new state of the Beam. This updates the force of the Beam, and if the force exceeds the Beam's strength, the Beam breaks and the force is set to zero. A broken Beam always has a force of zero.
     */
    public void calculateNewState() {
        double newForce = materialStiffness / length * (length - this.getNodeDistance());

        if (Math.abs(newForce) > strength) {
            isBroken = true;
        }
        
        if (isBroken) {
            calculatedForce = 0;
        } else {
            calculatedForce = dampen(newForce);
        }
    }

    public double getForce() {
        return calculatedForce;
    }

    /**
     * Returns the force that is applied to the given node.
     * @param node One of the Nodes that is attached to the Beam.
     * @return The Force vector that represents the force applied by this beam.
     */
    public Vector getForceVector(Node node) {
        if (node == nodes.get(0)) {
            return this.directionUnitVector().multiply(this.getForce());
        } else if (node == nodes.get(1)) {
            return this.directionUnitVector().multiply(this.getForce() * (-1));
        } else {
            throw new IllegalArgumentException("Node not in beam");
        }
        
    }

    private Vector directionUnitVector() {
        return nodes.get(0).getPositionV().subtract(nodes.get(1).getPositionV()).multiply(1 / this.getNodeDistance());
    }

    public double getMass() {
        return mass;
    }

    /**
     * Sets the mass of the Beam.
     * @param mass must be greater than 0.
     */
    public final void setMass(double mass) {

        if (mass > 0) {
            this.mass = mass;
        } else {
            throw new IllegalArgumentException("Mass has to be greater than 0, was " + mass);
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    /**
     *Sets the length of the Beam to match the distance between the Nodes.
     */
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

    /**
     * Sets the stiffness of the Beam. 
     * @param materialStiffness This parameter gives the stiffness of the material, so a longer Beam with the same materialStiffness will stretch/compress more with the same force. Must be greater than 0.
     */
    public final void setMaterialStiffness(double materialStiffness) {

        if (materialStiffness > 0) {
            this.materialStiffness = materialStiffness;
        } else {
            throw new IllegalArgumentException("Stiffness has to be greater than 0, was " + materialStiffness);
        }
    }

    public double getStrength() {
        return strength;
    }

    /**
     * Sets the strength of the Beam. 
     * @param strength The maximum force that the Beam can withstand. Must be greater than 0.
     */
    public final void setStrength(double strength) {

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

    /**
     * Sets the resting length of the Beam.
     * @param length must be greater than 0
     */
    public final void setLength(double length) {

        if (length > 0) {
            this.length = length;
        } else {
            throw new IllegalArgumentException("Length has to be greater than 0, was " + length);
        }
    }

    private double experimentalDampen(double newForce, double oldForce) {
        if (oldForce != 0) {
            newForce = newForce - oldForce * experimentalDampeningFactor1 * Math.min(1, Math.max(0, (1 - Math.abs(newForce) / Math.abs(oldForce) / experimentalDampeningFactor2)));
        }
        return newForce;
    }
}
