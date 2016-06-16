package physics;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *Space contains all Nodes and Beams, and stores universal constants that apply to them all.
 * @author janne
 */
public class Space {

    double gravity;
    double updateInterval;

    ArrayList<Node> nodes;
    ArrayList<Beam> beams;

    public Space() {
        setGravity(9.81);
        setUpdateInterval(0.0001);

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

    /**
     *Calculates the new state of every beam and node for the given number of times.
     * 
     * @param steps 
     */
    public void stepFor(int steps) {

        for (int i = 0; i < steps; i++) {
            step();
        }
    }
    
    /**
     *Calculates the new state of every beam and node.
     */
    public void step() {

        for (Beam beam : beams) {
            beam.calculateNewState();
        }
        for (Node node : nodes) {
            node.calculateNewState();
        }
    }

    /**
     *
     */
    public void printStatus() {

        for (int i = 0; i < getNodes().size(); i++) {
            Node node = getNode(i);
            System.out.print("__  Node " + (i + 1) + ": " + node);

        }
        System.out.print("\n");
    }

    public Node getNode(int i) {
        return nodes.get(i);
    }

    /**
     *This method deletes all the components in Space. 
     */
    public final void zeroComponents() {
        nodes = new ArrayList();
        beams = new ArrayList();
    }
}
