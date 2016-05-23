/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author janne
 */

public class Main {
    public static void main(String[] args) {
        System.out.print("success2");
        Node node1 = new Node(new Vector(0, 0));
        Node node2 = new Node(new Vector(100, 0));
        Beam beam = new Beam(node1, node2);
        System.out.print(beam.forceVector().getX() + "toimii");
        
        
        
    }
}
