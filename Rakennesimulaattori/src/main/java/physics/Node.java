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
    
    Vector positionV;
    Vector velocityV;
    Vector accelerationV;
    
    
    public Node(Vector position, float gravity, float updateInterval) {
        this.positionV = position;
        this.velocityV = new Vector(0, 0);
        this.accelerationV = new Vector(0, 0);
        
        this.gravity = gravity;
        this.updateInterval = updateInterval;
    }
    
    
    public void setPosition(Vector position) {
        this.positionV = position;
    }
           
    public Vector getPosition() {
        return positionV;
    }
    

    void addBeam(Beam beam) {
        beams.add(beam);
    }
    
    
    
    public Vector accelerationVector() {
        return new Vector(-10, 0); // Not completed!
    }
    
    public void calculateNewState() {
        
        Vector velocityDifferenceV = accelerationVector().multiply(updateInterval);
        Vector averageVelocityV = velocityV.add(velocityDifferenceV.multiply(0.5));
        
        Vector newPositionV = positionV.add(averageVelocityV.multiply(updateInterval));
        Vector newVelocity = velocityV.add(velocityDifferenceV);
        
        setPosition(newPositionV); 
        this.setVelocityVector(newVelocity); 
    }
    
    

    private void setVelocityVector(Vector velocityVector) {
        this.velocityV = velocityVector;
    }
}
