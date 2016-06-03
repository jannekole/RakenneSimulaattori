/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author janne
 */
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import logic.App;
import physics.Node;
import javax.swing.Timer;
import physics.Beam;


public class DrawPanel extends JPanel {
    //int delay = 1000; //milliseconds
    
    ArrayList<graphicNode> graphicNodes;
    ArrayList<graphicBeam> graphicBeams;    
    
    private int x = 0;
    private int y = 0;
    private int diameter = 20;
    
    private int xOffset = 400;
    private int yOffset = 300;
    

    
    App app;

    public DrawPanel(App app) {
        
        this.app = app;
        
        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();
        
        
        ArrayList<Node> nodes = app.getSpace().getNodes();
        
        for (Node node : nodes) {
            addNode(node);
        }
        
        ArrayList<Beam> beams = app.getSpace().getBeams();
        
        for (Beam beam : beams) {
            addBeam(beam);
        }
  
        

        

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
               // moveSquare(e.getX(),e.getY());
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                //moveSquare(e.getX(),e.getY());
            }
        });
        

    }

    public void addNode(Node node) {
        graphicNodes.add(new graphicNode(node , xOffset, yOffset));
    }
    
    private void addBeam(Beam beam) {
        graphicBeams.add(new graphicBeam(beam, xOffset, yOffset));
    }    

    
   // public void refresh() {
     //   System.out.print(".resh ");

        


        
//        int x = (int) node.getPosition().getX();
//        int y = (int) node.getPosition().getY();
//        this.x = x;
//        this.y = y;
 //       repaint();
   // }
    
    
    

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,800);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        if (!graphicNodes.isEmpty()) {
            System.out.print(" not empty ");
            for (graphicNode node : graphicNodes) {
                node.paintNode(g);
            }
        }
        if (!graphicBeams.isEmpty()) {

            for (graphicBeam beam : graphicBeams) {
                beam.paintBeam(g);
            }
        }

        
    }  


}