/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.IOException;
import physics.Node;
import java.util.Scanner;
import physics.Space;

/**
 *This class handles the operation of the application
 * @author janne
 */
public class App {

    Scanner scanner;
    Boolean keepGoing;
    Space space;

    /**
     *
     */
    public App() {
        space = new Space();
    }

    public void textUI() {
        scanner = new Scanner(System.in);

        int lines = 100;
        int calculationsPerLine = 100;

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
            printStatus();
            i++;
        }
    }

    public Space getSpace() {
        return space;
    }

    public void addNode(Node node) {
        space.addNode(node);
    }

    /**
     *Calculates the new state of the Space for the given number of times.
     * 
     * @param steps
     */
    public void stepFor(int steps) {

        space.stepFor(steps);
    }

    public void printStatus() {
        System.out.println(space.toString());
    }
    
    /**
     *Loads a new Space from the given file.
     * @param filePath The path of the file
     * @throws IOException
     */
    public void load(String filePath) throws IOException {

        Builder builder = new Builder(space);

        builder.buildFromFile(filePath);
    }



}
