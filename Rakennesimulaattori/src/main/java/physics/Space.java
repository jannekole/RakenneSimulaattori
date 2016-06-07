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
    float gravity = 10f;


    public static float updateInterval = 0.0005f;
    ArrayList<Node> nodes = new ArrayList(); 
    ArrayList<Beam> beams = new ArrayList(); 
    
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
    
    public float getGravity() {
        return gravity;
    }      

    public float getUpdateInterval() {
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
        
//        long millis = System.currentTimeMillis() % 100000;                
  //      System.out.print(" before: " + millis + " ");

        for (Beam beam : beams) {
            beam.calculateNewState();
        }
        
        for (Node node : nodes) {
            
            node.calculateNewState();
        }
//        long millis2 = System.currentTimeMillis() % 100000;
  //      System.out.print(" after: " + millis2 + " dif: " + (millis2 - millis) + " / ");  

        
        

    }
    
    public void printStatus() {
        
        for (int i = 0; i  < getNodes().size(); i++) {
            Node node = getNode(i);
            System.out.print("__  Node " + (i + 1) + ": ");
            printNode(node);
            
        }
        System.out.print("\n");
    }    

    public void printNode(Node node) {
        System.out.print("position: " + node.getPosition().toString() + "  speed: " + node.getVelocityV().toString() + " acc: " + node.accelerationVector().toString() + " ");
    }

    public Node getNode(int i) {
        return nodes.get(i);
    }

    public void reload() {
        
    }


}
