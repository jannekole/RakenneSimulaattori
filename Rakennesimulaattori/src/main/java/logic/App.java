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

import gui.Gui;


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
        
       
        System.out.print("--- O H J E E T ---\n"
                + "Ohjelma simuloi tällä hetkellä yhtä palkkia, tai oikeastaan\n"
                + "jousen päissä olevia painoja (node). Ohjelman käynnistyessä jousi on \n"
                + "jännittynyt ja se alkaa oskilloida sekä tippua vapaassa pudotuksessa.\n"
                + "Ohjelma laskee painojen sijainnin, nopeuden ja kiihtyvyyden ajan edetessä. \n"
                + "Jousi-paino yhdistelmä alkaa 'vaeltaa' muutaman oskilloinnin jälkeen,\n"
                + "mikä todennäköisesti johtuu Double:lla tehtyjen laskujen epätarkkuudesta\n"
                + "----\n"
                + "Anna kaksi lukua. Ensimmäinen luku kertoo montako riviä ohjelma\n"
                + "tulostaa. Toinen luku kertoo kuinka monta kertaa ohjelma laskee palkin\n"
                + "tilan rivien välissä. Esimerkiksi 15 ja 10 antaa järkevän tuloksen\n");
        
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
            

 
         
        
          
    }

    public Space getSpace() {
        return space;
    }
    
    public void addNode(Node node) {
        space.addNode(node);
    }
    
    
    public void stepFor(int until) {
               
        
        for (int i = 0 ; i < until; i++)
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

    private void setObjects() { //Väliaikainen ratkaisu
        float gravity = 0.00f;
        space.addNode(new Node(new Vector(0, 0), 0.004f, 0.1f));   //GRAVITY
        space.addNode(new Node(new Vector(400, 0), gravity, 0.1f)); 
        float length = 480;
        float stiffness = 800;
        int mass = 20;
        int strength = 200000;
        
        
        Beam beam = new Beam(space.getNode(0), space.getNode(1), length, stiffness, mass, strength); //tästä eroon
        space.addBeam(beam);
    }
    
}
