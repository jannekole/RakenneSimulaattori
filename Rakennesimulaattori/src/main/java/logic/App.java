/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import physics.Beam;
import physics.Node;
import physics.Vector;

import java.util.ArrayList;

import java.util.Scanner;
import physics.Space;




/**
 *
 * @author janne
 */
public class App {

    Scanner scanner;
    Boolean keepGoing;
    Space space;
    
    public App() {
        space = new Space();
        
        setObjects();
        
        scanner = new Scanner( System.in );
        
        
        
        int lines;
        int calculationsPerLine;
        

      /*  
        lines = scanner.nextInt();
        calculationsPerLine = scanner.nextInt();
        
        calculationsPerLine = 10;
        
        int i = 0;
        
        
        printStatus();
        
        while (i < lines) {
            
            if (lines < 0) {
                break;
            }
            
            
            stepFor(calculationsPerLine);
            i ++;
        }
        */  

 
         
        
          
    }

    public Space getSpace() {
        return space;
    }
    
    public void addNode(Node node) {
        space.addNode(node);
    }
    
    
    public void stepFor(int steps) {
               
        
        for (int i = 0 ; i < steps; i++)
            {
                space.step();
            }
        printStatus();
    }
 
    
    
   
    public void printStatus() {
        
        for (int i = 0 ; i  < space.getNodes().size() ; i++) {
            Node node = space.getNode(i);
            System.out.print("__  Node "+ (i+1) + ": ");
            printNode(node);
            
        }
        System.out.print("\n");
    }    

    private void printNode(Node node) {
        System.out.print("position: " + node.getPosition().toString() + "  speed: " + node.getVelocityV().toString() + " acc: " + node.accelerationVector().toString() + " ");
    }

    private void setObjects() { 
        Builder builder = new Builder(space);
        builder.build("node 1;x 0;y 20;-\n node 2;x 120;  y 70;-");
        
    }
    
}
