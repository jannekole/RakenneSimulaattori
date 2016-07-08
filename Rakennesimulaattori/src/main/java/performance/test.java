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
            System.out.print("failed ");
        }

        space.stepFor(5000);
    }
    
}
