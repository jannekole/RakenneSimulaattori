package physics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * Node represents the connections between Beams. This class calculates the
 * position of the nodes based on the given parameters and the effects of Beams.
 *
 * @author janne
 */
public class Node {

    private ArrayList<Beam> attachedBeams = new ArrayList();

    private Vector positionV;
    private Vector velocityV;

    private boolean xConstantVelocity;
    private boolean yConstantVelocity;
    
    private Space space;

    /**
     * Constructs a new Node.
     * @param position The initial position of the node.
     * @param space The Space where the Node gets universal parameters from.
     */
    public Node(Vector position, Space space) {

        setPosition(position);

        setInitialVelocity(new Vector(0, 0, 0));
        
        if (space != null) {
            this.space = space;
        } else {
            throw new IllegalArgumentException("Space cannot be null.");
        }

        this.xConstantVelocity = false;
        this.yConstantVelocity = false;
    }

    /**
     * Sets the position of the Node.
     * @param position the position Vector.
     */
    public final void setPosition(Vector position) {
        if (position != null) {
            this.positionV = position;
        } else {
            throw new IllegalArgumentException("Position vector cannot be null.");
        }
    }

    public Vector getPositionV() {
        return positionV;
    }

    /**
     * Adds a Beam to the Node.
     * @param beam the Beam to be added.
     */
    public void addBeam(Beam beam) {
        if (beam != null) {
            attachedBeams.add(beam);
        } else {
            throw new IllegalArgumentException("Beam cannot be null.");
        }        
    }

    /**
     * Sets the initial velocity of the Node. Nodes are initially stationary by default.
     * @param initialVelocityV the velocity Vector
     */
    public final void setInitialVelocity(Vector initialVelocityV) {
        if (initialVelocityV != null) {
            this.velocityV = initialVelocityV;
        } else {
            throw new IllegalArgumentException("Initial velocity vector cannot be null.");
        }
    }

    private Vector accelerationV() {
        Vector accelerationV = forceSum().multiply(1 / massSum());  // F = ma --> a = F/m 
        
        if (isXConstantVelocity()) {
            accelerationV.setX(0);
        }
        if (isYConstantVelocity()) {
            accelerationV.setY(0);
        }        
        
        return accelerationV;
    }

    /**
     *Calculates the new state of the Node. This updates the position and velocity vectors.
     */
    public void calculateNewState() {

        double updateInterval = space.getUpdateInterval();
        
        Vector velocityDifferenceV = accelerationV().multiply(updateInterval);          // the change in velocity is equal to acceleration multiplied by time accelerated
        Vector averageVelocityV = velocityV.add(velocityDifferenceV.multiply(0.5));     // to calculate the position, average velocity during the interval is needed instead of the final velocity

        Vector newPositionV = positionV.add(averageVelocityV.multiply(updateInterval)); 
        Vector newVelocityV = velocityV.add(velocityDifferenceV);

        positionV = newPositionV;
        velocityV = newVelocityV;
    }

    /**
     * Returns the velocity vector of the Node.
     * @return the velocity Vector
     */
    public Vector getVelocityV() {
        return velocityV;
    }

    /**
     * Returns the sum of all forces (including gravitational) applied on to the Node. 
     * @return the sum of all forces
     */
    public Vector forceSum() {
        Vector sum = new Vector(0, 0, 0);
        for (Beam beam : attachedBeams) {
            sum = sum.add(beam.getForceVector(this));
        }
        sum = sum.add(gravityForce());
        return sum;
    }

    /**
     * Returns half of the sum of the masses of the beams that are connected to the Node. Only half is returned because the other half of the Beam's mass is counted towards the Node at the other end. 
     * @return the mass
     */
    public double massSum() {
        int sum = 0;
        for (Beam beam : attachedBeams) {
            sum += beam.getMass();
        }
        return sum / 2;
    }

    private Vector gravityForce() {
        double gravityF = -space.getGravity() * massSum();
        return new Vector(0, gravityF, 0);
    }

    @Override
    public String toString() {
        return "position: " + getPositionV().toString() + "  speed: " + getVelocityV().toString() + " acc: " + accelerationV().toString() + " ";
    }

    public boolean isXConstantVelocity() {
        return xConstantVelocity;
    }

    public void setXConstantVelocity(boolean xConstantVelocity) {
        this.xConstantVelocity = xConstantVelocity;
    }

    public boolean isYConstantVelocity() {
        return yConstantVelocity;
    }

    public void setYConstantVelocity(boolean yConstantVelocity) {
        this.yConstantVelocity = yConstantVelocity;
    }
}
