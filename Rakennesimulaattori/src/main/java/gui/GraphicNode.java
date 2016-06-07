/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import physics.Node;

/**
 *
 * @author janne
 */
public class GraphicNode {
    

    
    Node node;
    
    int radius;
    int diameter;
    int x;
    int y;
    
    int xOffset;
    int yOffset;
        
    
    public GraphicNode(Node node, int xOffset, int yOffset) {

        this.xOffset = xOffset;
        this.yOffset = yOffset;
        
        this.node = node;
        
        this.radius = 5;
        this.diameter = radius * 2;
        
        
        
    }
            
    public void paintNode(Graphics g) {
        setCoordinates();
        g.setColor(Color.BLACK);
        g.fillOval(x + xOffset - radius, -y + yOffset - radius, diameter, diameter);
        
    }

    public void setCoordinates() {
       
        x = (int) node.getPosition().getX();
        y = (int) node.getPosition().getY();        
    }
    
    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

}
