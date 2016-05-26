package main;


import physics.Node;
import physics.Beam;
import physics.Vector;

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
        System.out.print("success2\n");
        Node node1 = new Node(new Vector(0, 0), 10f, 0.1f);
        Node node2 = new Node(new Vector(100, 0), 10f, 0.1f);
        Beam beam = new Beam(node1, node2, 120, 200, 200, 200);
        
        System.out.print("beam force: x:" + beam.getForceVector().getX() + ", y:" + beam.getForceVector().getY() + ", node1 x:" + node1.getPosition().getX() + " y:" + node1.getPosition().getY() + ", node2 x:" + node2.getPosition().getX() + " y:" + node2.getPosition().getY() + "\n");
        
        node1.calculateNewState();
 //       node2.calculateNewState();
        System.out.print("beam force: x:" + beam.getForceVector().getX() + ", y:" + beam.getForceVector().getY() + ", node1 x:" + node1.getPosition().getX() + " y:" + node1.getPosition().getY() + ", node2 x:" + node2.getPosition().getX() + " y:" + node2.getPosition().getY() + "\n");
        
        node1.calculateNewState();
//        node2.calculateNewState();
        System.out.print("beam force: x:" + beam.getForceVector().getX() + ", y:" + beam.getForceVector().getY() + ", node1 x:" + node1.getPosition().getX() + " y:" + node1.getPosition().getY() + ", node2 x:" + node2.getPosition().getX() + " y:" + node2.getPosition().getY() + "\n");
        
        node1.calculateNewState();
//        node2.calculateNewState();

        System.out.print("beam force: x:" + beam.getForceVector().getX() + ", y:" + beam.getForceVector().getY() + ", node1 x:" + node1.getPosition().getX() + " y:" + node1.getPosition().getY() + ", node2 x:" + node2.getPosition().getX() + " y:" + node2.getPosition().getY() + "\n");
        
        node1.calculateNewState();
        node2.calculateNewState();

        System.out.print("beam force: x:" + beam.getForceVector().getX() + ", y:" + beam.getForceVector().getY() + ", node1 x:" + node1.getPosition().getX() + " y:" + node1.getPosition().getY() + ", node2 x:" + node2.getPosition().getX() + " y:" + node2.getPosition().getY() + "\n");
        

        
        
    }
}
