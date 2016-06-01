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


/**
 *
 * @author janne
 */
public class App {
    ArrayList<Node> nodes = new ArrayList(); 
    Scanner scanner;
    Boolean keepGoing;
    
    public App() {
        keepGoing = true;
        
        nodes.add(new Node(new Vector(0, 0), 10f, 0.1f));
        nodes.add(new Node(new Vector(100, 0), 10f, 0.1f));
        Beam beam = new Beam(nodes.get(0), nodes.get(1), 120, 200, 20, 200);
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
    
    
    public void stepFor(int until) {
               
        
        for (int i = 0 ; i < until; i++)
            {
                step();
            }
        printStatus();
    }
 
    
    
    public void step() {
        for (Node node : nodes) {

            node.calculateNewState();
        }
        
        

    }
    
    public void printStatus() {
        
        for (int i = 0 ; i  < nodes.size() ; i++) {
            Node node = nodes.get(i);
            System.out.print("__  Node "+ (i+1) + ": ");
            printNode(node);
            
        }
        System.out.print("\n");
    }    

    private void printNode(Node node) {
        System.out.print("position: " + node.getPosition().toString() + "  speed: " + node.getVelocityV().toString() + " acc: " + node.accelerationVector().toString() + " ");
    }
    
}
