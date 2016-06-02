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
    float gravity = 9.81f;
    float updateInterval = 0.1f;
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
      
    
    public void step() {
        for (Node node : nodes) {

            node.calculateNewState();
        }
        
        

    }

    public Node getNode(int i) {
        return nodes.get(i);
    }
}
