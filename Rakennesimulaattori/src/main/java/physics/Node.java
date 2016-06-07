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
import java.util.ArrayList;


public class Node {
    
    float gravity;
    float updateInterval;
    
    ArrayList<Beam> beams = new ArrayList();
    
    Vector startPositionV;
    Vector positionV;
    Vector velocityV;
    Vector accelerationV;
    
    boolean xStationary;
    boolean yStationary;
    
    
    public Node(Vector position, float gravity, float updateInterval) {
        setPosition(position);
        this.velocityV = new Vector(0, 0);
        this.accelerationV = new Vector(0, 0);
        
        this.gravity = gravity;
        this.updateInterval = updateInterval;
        
        this.xStationary = false;
        this.yStationary = false;
    }
    
    
    public void setPosition(Vector position) {
        this.startPositionV = position;
        this.positionV = position;
    }
           
    public Vector getPosition() {
        return positionV;
    }
    

    public void addBeam(Beam beam) {
        beams.add(beam);
    }
    
    
    
    public Vector accelerationVector() {
        return forceSum().multiply(1 / massSum());
    }
    
    public void calculateNewState() {
        
        
        
        Vector velocityDifferenceV = accelerationVector().multiply(updateInterval);
        Vector averageVelocityV = velocityV.add(velocityDifferenceV.multiply(0.5));
        
        Vector newPositionV = positionV.add(averageVelocityV.multiply(updateInterval));
        Vector newVelocityV = velocityV.add(velocityDifferenceV);
        
        if (isXStationary()) {
            newPositionV.setX(startPositionV.getX());
            newVelocityV.setX(0);
        }
        if (isYStationary()) {
            newPositionV.setY(startPositionV.getY());
            newVelocityV.setY(0);
        }
        
        positionV = newPositionV; 
        setVelocityV(newVelocityV); 
    }

    public Vector getVelocityV() {
        return velocityV;
    }
    
    

    private void setVelocityV(Vector velocityVector) {
        this.velocityV = velocityVector;
    }

    
    public Vector forceSum() {
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

    public boolean isXStationary() {
        return xStationary;
    }

    public void setXStationary(boolean xStationary) {
        this.xStationary = xStationary;
    }

    public boolean isYStationary() {
        return yStationary;
    }

    public void setYStationary(boolean yStationary) {
        this.yStationary = yStationary;
    }

}
