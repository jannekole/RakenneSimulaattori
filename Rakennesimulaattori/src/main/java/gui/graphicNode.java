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
public class graphicNode {
    

    
    Node node;
    
    int diameter;
    int x;
    int y;
    
    
    public graphicNode(Node node) {

        this.node = node;
        
        this.diameter = 5;
        
    }
            
    public void paintNode(Graphics g) {
        setCoordinates();
        g.setColor(Color.BLACK);
        g.fillOval(x + 300, -y + 400,diameter,diameter);
        System.out.print("pöö");
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
