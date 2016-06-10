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
import gui.Gui;
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
import java.io.IOException;
import java.util.ArrayList;
import logic.App;
import physics.Node;
import javax.swing.Timer;
import physics.Beam;
import physics.Space;


public class DrawPanel extends JPanel {
    int initialDelay = 1000; //milliseconds
    float speedMultiplier = 5;

    
    ArrayList<GraphicNode> graphicNodes;
    ArrayList<GraphicBeam> graphicBeams;    
    
    private int x = 0;
    private int y = 0;
    private int diameter = 20;
    
    private int xOffset = 600;
    private int yOffset = 000;
    
    String filePath;
    
    Timer timer;
    
    App app;

    public DrawPanel() {
        this.filePath = "";
        
        this.app = new App();
        
        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();
        
        timer = setTimer();
    
        //load("");
        

        

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
    public void restart() throws IOException {
        load(filePath);
    }
    public void load(String filePath) throws IOException {   
        
        this.filePath = filePath;
        

        
        
        
        deleteComponents();
        
        app.load(filePath);
        
        
        
    
        
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
        
        
    }

    public void addNode(Node node) {
        graphicNodes.add(new GraphicNode(node , xOffset, yOffset));
    }
    
    private void addBeam(Beam beam) {
        graphicBeams.add(new GraphicBeam(beam, xOffset, yOffset));
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
        return new Dimension(1000, 900);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       

        if (!graphicNodes.isEmpty()) {
            for (GraphicNode node : graphicNodes) {
                node.paintNode(g);
            }
        }
        if (!graphicBeams.isEmpty()) {

            for (GraphicBeam beam : graphicBeams) {
                beam.paintBeam(g);
            }
        }

        
    }  

    private Timer setTimer() {
        
        int frameRate = 20;
        

        int delay = 1000 / frameRate;
        final int calculationsPerFrame = (int) (speedMultiplier / app.getSpace().getUpdateInterval() * delay / 1000);
        
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

        Timer timer = new Timer(delay, taskPerformer);
        //timer.setInitialDelay(initialDelay);
        
        return timer;
        
    }

    void stepFor(int steps) {
        app.stepFor(steps);
    }

    void pause() {
        timer.stop();
    }

    void play() {
        timer.start();
    }
    
    void playPause() {
        if (timer.isRunning()) {
            pause();    
        } else {
            play();
        }
    }


}