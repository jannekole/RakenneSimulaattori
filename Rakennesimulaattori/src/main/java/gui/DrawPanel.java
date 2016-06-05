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
import static gui.Gui2.app;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import physics.Space;


public class DrawPanel extends JPanel {
    int initialDelay = 1000; //milliseconds
    
    Timer timer;
    
    ArrayList<graphicNode> graphicNodes;
    ArrayList<graphicBeam> graphicBeams;    
    
    private int x = 0;
    private int y = 0;
    private int diameter = 20;
    
    private int xOffset = 900;
    private int yOffset = 000;
    

    
    App app;

    public DrawPanel(App app) {
        

        
        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();
        
        setTimer();
    
        load(app);
        

        

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
    public void load(App app) {   
        
        this.app = app;
        
        timer.stop();
        timer.start();
        
        deleteComponents();
        
        ArrayList<Node> nodes = app.getSpace().getNodes();
        
        for (Node node : nodes) {
            addNode(node);
        }
        
        ArrayList<Beam> beams = app.getSpace().getBeams();
        
        for (Beam beam : beams) {
            addBeam(beam);
        }
        
        timer.stop();
        repaint();
        timer.start();
        
    }

    public void addNode(Node node) {
        graphicNodes.add(new graphicNode(node , xOffset, yOffset));
    }
    
    private void addBeam(Beam beam) {
        graphicBeams.add(new graphicBeam(beam, xOffset, yOffset));
    }    
    
    private void deleteComponents() {

        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();
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
        return new Dimension(1800,800);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       

        if (!graphicNodes.isEmpty()) {
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

    private void setTimer() {
        
        int frameRate = 50;
        float speedMultiplier = 1f;

        int delay = 1000 / frameRate;
        int calculationsPerFrame = (int) (speedMultiplier / Space.updateInterval * delay / 1000);

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                        //...Perform a task...

                app.stepFor(calculationsPerFrame);

                        //long millis = System.currentTimeMillis() % 100000;
                //System.out.print("\nmillis: (before paint): " + millis + " ");
                repaint();

                        //long millis2 = System.currentTimeMillis() % 100000;
                //System.out.print("millis: (after  paint): " + millis2 + " dif: " + (millis2 - millis) + "  ");
            }
        };

        timer = new Timer(delay, taskPerformer);
        timer.setInitialDelay(initialDelay);
        
    }


}