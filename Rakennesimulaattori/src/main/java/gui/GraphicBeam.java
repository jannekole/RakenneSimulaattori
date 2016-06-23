/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import physics.Beam;
import physics.Node;

/**
 *
 * @author janne
 */
public class GraphicBeam {
    Beam beam;
    
    boolean isBroken = false;
    
    int diameter;
    int x1;
    int y1;
    
    int x2;
    int y2;
    
    int xOffset;
    int yOffset;
     
    
    public GraphicBeam(Beam beam, int xOffset, int yOffset) {

        this.beam = beam;
        
        this.xOffset = xOffset;
        this.yOffset = yOffset;
         
        
    }
            
    public void paintBeam(Graphics g) {
        setCoordinates();
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2D.setColor(Color.BLACK);
        if (isBroken) {
            g2D.setColor(Color.PINK);
            g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
        }
        g2D.drawLine(x1 + xOffset, -y1 - yOffset, x2 + xOffset, -y2 - yOffset);
        
    }

    public void setCoordinates() {
       
        x1 = (int) beam.getNodes().get(0).getPositionV().getX();
        y1 = (int) beam.getNodes().get(0).getPositionV().getY();
        
        
        x2 = (int) beam.getNodes().get(1).getPositionV().getX();
        y2 = (int) beam.getNodes().get(1).getPositionV().getY();
        
        isBroken = beam.isBroken();
        
        
    }
    


}
