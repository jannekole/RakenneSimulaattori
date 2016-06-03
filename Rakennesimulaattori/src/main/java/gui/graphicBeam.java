/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import physics.Beam;
import physics.Node;

/**
 *
 * @author janne
 */
public class graphicBeam {
    Beam beam;
    
    int diameter;
    int x1;
    int y1;
    
    int x2;
    int y2;
    
    int xOffset;
    int yOffset;
     
    
    public graphicBeam(Beam beam, int xOffset, int yOffset) {

        this.beam = beam;
        
        this.xOffset = xOffset;
        this.yOffset = yOffset;
         
        
    }
            
    public void paintBeam(Graphics g) {
        setCoordinates();
        g.setColor(Color.BLACK);
        g.drawLine(x1 + xOffset, -y1 + yOffset, x2 + xOffset, -y2 + yOffset);
        
    }

    public void setCoordinates() {
       
        x1 = (int) beam.getNodes().get(0).getPosition().getX();
        y1 = (int) beam.getNodes().get(0).getPosition().getY();
        
        x2 = (int) beam.getNodes().get(1).getPosition().getX();
        y2 = (int) beam.getNodes().get(1).getPosition().getY();
        
        
    }
    


}
