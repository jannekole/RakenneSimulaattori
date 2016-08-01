/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package performance;

import logic.Builder;
import physics.Space;

/**
 *
 * @author janne
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Space space = new Space();
        Builder builder = new Builder(space);
        
        try {
            builder.buildFromFile("C:\\Users\\janne\\rakenneSimulaattori\\Rakennesimulaattori\\valmitRakenteet\\highBeam.txt");
        } catch (Exception e) {
            System.out.println("failed ");
        }
        long millis1 = java.lang.System.currentTimeMillis();
        space.stepFor(5000);
        long millis2 = java.lang.System.currentTimeMillis();        
        space.stepFor(5000);
        long millis3 = java.lang.System.currentTimeMillis();
        long first = millis2 - millis1;
        System.out.println("first 5000: " + first);
        long second = millis3 - millis2;
        System.out.println("first 5000: " + second);        
        
        
    }
    
}
