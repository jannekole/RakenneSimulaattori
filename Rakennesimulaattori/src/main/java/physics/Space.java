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
public class Space {
    double gravity;
    double updateInterval;

    ArrayList<Node> nodes;  
    ArrayList<Beam> beams;
    
    public Space() {
        setGravity(9.81);
        setUpdateInterval(0.0005);
        
        zeroComponents();
    }

    public final void setGravity(double gravity) {
        this.gravity = gravity;
    }
    


    public final void setUpdateInterval(double updateInterval) {
        this.updateInterval = updateInterval;
    }
    
    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addBeam(Beam beam) {
        beams.add(beam);
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Beam> getBeams() {
        return beams;
    }
    
    public double getGravity() {
        return gravity;
    }      

    public double getUpdateInterval() {
        return updateInterval;
    }
    
    public void stepFor(int steps) {
/*               
        long millis = System.currentTimeMillis() % 100000;                
        System.out.print("\nmillis: (before calc): " + millis + " ");
  */      
        for (int i = 0; i < steps; i++) {
            step();
        }
      
/*       
        long millis2 = System.currentTimeMillis() % 100000;
        System.out.print("millis: (after  calc): " + millis2 + " dif: " + (millis2 - millis) + "  ");
        //printStatus();
  */
    }
         
    public void step() {
        
        for (Beam beam : beams) {
            beam.calculateNewState();
        }
        for (Node node : nodes) {
            node.calculateNewState();
        }
    }
    
    public void printStatus() {
        
        for (int i = 0; i  < getNodes().size(); i++) {
            Node node = getNode(i);
            System.out.print("__  Node " + (i + 1) + ": " + node);
            
            
        }
        System.out.print("\n");
    }    


    public Node getNode(int i) {
        return nodes.get(i);
    }

    public final void zeroComponents() {
        nodes = new ArrayList(); 
        beams = new ArrayList(); 
    }
}
