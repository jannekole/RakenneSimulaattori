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
        
        start();

        
        
    }
 
         
    public void textUI() {
        scanner = new Scanner( System.in );
        
        
        
        int lines = 100;
        int calculationsPerLine = 100;
        

        
//        lines = scanner.nextInt();
  //      calculationsPerLine = scanner.nextInt();
        
        calculationsPerLine = 10;
        
        int i = 0;
        
        
        printStatus();
        
        while (i < lines) {
            
            if (lines < 0) {
                break;
            }
            
            
            stepFor(calculationsPerLine);
            printStatus();
            i ++;
        }
          
    }    
          
    

    public Space getSpace() {
        return space;
    }
    
    public void addNode(Node node) {
        space.addNode(node);
    }
    
    
    public void stepFor(int steps) {
               
        space.stepFor(steps);
     
    }
 
    
    
   
    public void printStatus() {
        space.printStatus();
    }    

    private void printNode(Node node) {
        space.printNode(node);
    }

    private void setObjects() { 
        Builder builder = new Builder(space);
 /*       builder.build(
); */
        String filename = "test.txt";
        builder.buildFromFile(filename);
        
    }

    public void start() {
        
        space = new Space();
        
        setObjects();
        
    }
    

    
}
