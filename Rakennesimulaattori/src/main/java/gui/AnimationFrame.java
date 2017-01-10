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
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import physics.Node;
import javax.swing.Timer;
import logic.Builder;
import physics.Beam;
import physics.Space;

/**
 *      2D  2D  2D
 * 
 *
 * @author janne
 */
public class AnimationFrame extends JPanel {

    double speedMultiplier = 1;

    ArrayList<GraphicNode> graphicNodes;
    ArrayList<GraphicBeam> graphicBeams;

    private int xOffset;
    private int yOffset;

    String filePath;

    Timer timer;

    Space space;

    public AnimationFrame() {
        this.filePath = "";

        this.space = new Space();

        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();

        xOffset = 10;
        yOffset = -10;
        
        timer = newTimer();
    }

    public void restart() throws IOException {
        load(filePath);
    }

    public void load(String filePath) throws IOException {

        this.filePath = filePath;

        deleteComponents();
        
        Builder builder = new Builder(space);
        space = builder.buildFromFile(filePath);

        for (Node node : space.getNodes()) {
            addNode(node);
        }

        for (Beam beam : space.getBeams()) {
            addBeam(beam);
        }
        
        timer.stop();
        repaint();

    }

    public void addNode(Node node) {
        graphicNodes.add(new GraphicNode(node, xOffset, yOffset));
    }

    private void addBeam(Beam beam) {
        graphicBeams.add(new GraphicBeam(beam, xOffset, yOffset));
    }

    private void deleteComponents() {

        graphicNodes = new ArrayList();
        graphicBeams = new ArrayList();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 500);
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

    private Timer newTimer() {

        int frameRate = 50;

        int delay = 1000 / frameRate;
        final int calculationsPerFrame = (int) (speedMultiplier / space.getUpdateInterval() * delay / 1000);

        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

//                space.stepFor(calculationsPerFrame);
                
//                repaint();
            }
        };

        Timer timer1 = new Timer(delay, taskPerformer);
        return timer1;
    }

    void stepFor(int steps) {
        space.stepFor(steps);
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
