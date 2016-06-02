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


import static gui.Gui.app;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.Button;

public class GuiTest {
    static GuiTest gui;
    static JFrame frame = new JFrame("Swing Paint Demo");
    private static Button button;    
    
    public static void main(String[] args) {
        
        gui = new GuiTest();
        button = new Button();
        button.setLabel("Refresh");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gui.button1ActionPerformed(evt);
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.createAndShowGUI(); 
            }
            
        });
    }

    private  void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame frame = new JFrame("Swing Paint Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.add(new DrawPanel());

        frame.pack();
        frame.setVisible(true);
    } 
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
//        app.stepFor(Integer.parseInt(jTextField1.getText()));
        app.printStatus();

    }      
          
}
        
                

