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
    
    Vector position;
    Vector velocityVector;
    Vector accelerationVector;
    
    
    public Node(Vector position, float gravity, float updateInterval){
        this.position = position;
        this.velocityVector = new Vector(0, 0);
        this.accelerationVector = new Vector(0, 0);
        this.gravity = gravity;
        this.updateInterval = updateInterval;
    }
    
    
    void setPosition(Vector position){
        this.position = position;
    }
           
    Vector getPosition(){
        return position;
    }
    

    void addBeam(Beam beam) {
        beams.add(beam);
    }
    
    
    
    public Vector accelerationVector(){
        return new Vector(-10, 20); // Not completed!
    }
    
    public void calculateNewState(){
        
        Vector velocityDifferenceV = accelerationVector().multiply(updateInterval);
        Vector averageVelocityV = velocityVector.add(velocityDifferenceV.multiply(0.5));
        
        Vector newPositionV = position.add(averageVelocityV.multiply(updateInterval));
        Vector newVelocity = velocityVector.add(velocityDifferenceV);
        
        setPosition(newPositionV); 
        this.setVelocityVector(newVelocity); 
    }
    
    

    private void setVelocityVector(Vector velocityVector) {
        this.velocityVector = velocityVector;
    }
}
