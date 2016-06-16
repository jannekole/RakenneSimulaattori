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

    private double gravity;
    private double updateInterval;

    private ArrayList<Beam> beams = new ArrayList();

    private Vector startPositionV;
    private Vector positionV;
    private Vector velocityV;
    private Vector accelerationV;

    private Vector initialVelocityV;

    private boolean xConstantVelocity;
    private boolean yConstantVelocity;

    /**
     *
     * @param position The initial position of the node.
     * @param gravity The gravitational acceleration in m/s^2.
     * @param updateInterval The time between each new calculation.
     */
    public Node(Vector position, double gravity, double updateInterval) {

        setPosition(position);

        setInitialVelocity(new Vector(0, 0));

        this.accelerationV = new Vector(0, 0);

        this.gravity = gravity;
        setUpdateInterval(updateInterval);

        this.xConstantVelocity = false;
        this.yConstantVelocity = false;
    }

    public final void setUpdateInterval(double updateInterval) {
        if (updateInterval > 0) {
            this.updateInterval = updateInterval;
        } else {
            throw new IllegalArgumentException("UpdateInterval must be greater than 0.");
        }
    }

    public final void setPosition(Vector position) {
        if (position != null) {
            this.startPositionV = position;
            this.positionV = position;
        } else {
            throw new IllegalArgumentException("Position vector cannot be null.");
        }
    }

    public Vector getPosition() {
        return positionV;
    }

    public void addBeam(Beam beam) {
        beams.add(beam);
    }

    public final void setInitialVelocity(Vector initialVelocityV) {
        if (initialVelocityV != null) {
            this.initialVelocityV = initialVelocityV;
            this.velocityV = initialVelocityV;
        } else {
            throw new IllegalArgumentException("Initial velocity vector cannot be null.");
        }
    }

    private Vector accelerationVector() {
        return forceSum().multiply(1 / massSum());
    }

    /**
     *Calculates the new state of the Node.
     */
    public void calculateNewState() {

        Vector velocityDifferenceV = accelerationVector().multiply(updateInterval);
        Vector averageVelocityV = velocityV.add(velocityDifferenceV.multiply(0.5));

        Vector newPositionV = positionV.add(averageVelocityV.multiply(updateInterval));
        Vector newVelocityV = velocityV.add(velocityDifferenceV);

        if (isXConstantVelocity()) {
            newPositionV.setX(positionV.getX() + initialVelocityV.getX() * updateInterval);
            newVelocityV.setX(0);
        }
        if (isYConstantVelocity()) {
            newPositionV.setY(positionV.getY() + initialVelocityV.getY() * updateInterval);
            newVelocityV.setY(0);
        }

        positionV = newPositionV;
        velocityV = newVelocityV;
    }

    public Vector getVelocityV() {
        return velocityV;
    }

    Vector forceSum() {
        Vector sum = new Vector(0, 0);
        for (Beam beam : beams) {
            sum = sum.add(beam.getForceVector(this));
        }
        sum = sum.add(gravityForce());
        return sum;
    }

    public double massSum() {
        int sum = 0;
        for (Beam beam : beams) {
            sum += beam.getMass();
        }
        return sum / 2;     //mass is halved because only one end of the beam is taken into account
    }

    private Vector gravityForce() {
        double gravityF = -gravity * massSum();
        return new Vector(0, gravityF);
    }

    @Override
    public String toString() {
        return "position: " + getPosition().toString() + "  speed: " + getVelocityV().toString() + " acc: " + accelerationVector().toString() + " ";
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
