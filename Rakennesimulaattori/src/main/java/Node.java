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
    
    ArrayList<Beam> beams = new ArrayList();
    Vector position;
    
    public Node(Vector position){
        this.position = position;
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
        return new Vector(0, 0); // Not completed!
    }
}
