/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import physics.Beam;
import physics.Node;
import physics.Space;

/**
 *
 * @author janne
 */
public class BuilderTest {
    Space space;
    Builder builder;
    String filePath;
    
    double xPos;
    double yPos;
    boolean isConstantVelocityX;
    boolean isConstantVelocityY;

    double node0X;
    double node0Y;
    
    double node1X;
    double node1Y;
    
    Beam beam;
    
        
    public BuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        space = new Space();
        builder = new Builder(space);
        filePath = "src/test/resources/testFile.txt";
        
        node0X = -100;
        node0Y = 987;
                
        node1X = -80;
        node1Y = 0;
    

    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void readFile() {
        readTestFile(filePath);
    }
    
    @Test
    public void testNode0() {
        
        xPos = node0X;
        yPos = node0Y;
        
        isConstantVelocityX = true;
        isConstantVelocityY = true;
        
        testNode(0);        
    }
    @Test
    public void testNode8() {
               
        xPos = 60;
        yPos = 4321;
        
        isConstantVelocityX = false;
        isConstantVelocityY = true;
        
        testNode(8);
    }

    @Test
    public void testNode3() {
               
        xPos = -40;
        yPos = 0;
        
        isConstantVelocityX = false;
        isConstantVelocityY = false;
        
        testNode(3);

    }    
    
    @Test
    public void testBeam0() {
        readTestFile(filePath);
        beam = space.getBeams().get(0);
        
        Node node0 = beam.getNodes().get(0);
        Node node1 = beam.getNodes().get(1);
        
        assertEquals(node0X, node0.getPositionV().getX(), 0.0001);
        assertEquals(node0Y, node0.getPositionV().getY(), 0.0001);
        
        assertEquals(node1X, node1.getPositionV().getX(), 0.0001);
        assertEquals(node1Y, node1.getPositionV().getY(), 0.0001);
        
        assertEquals(4321, beam.getLength(), 0.001);
        assertEquals(12345, beam.getMaterialStiffness(), 0.001);
        assertEquals(54, beam.getMass(), 0.001);  
        assertEquals(789 , beam.getStrength(), 0.001);        

    }        
    
    @Test
    public void loadingTwiceDeletesFirst() {
        readTestFile(filePath);
        readTestFile(filePath);
        assertEquals(10, space.getNodes().size());
    }

    private void readTestFile(String filePath) {
        try {
            File file = new File(filePath);
            String absoluteFilePath = file.getAbsolutePath();
            builder.buildFromFile(absoluteFilePath);
        } catch (IOException ex) {
            fail("problem reading test file");
        }
    }
    
    private void testNode(int nodeNumber) {
        readTestFile(filePath);
        
        Node node = space.getNodes().get(nodeNumber);
        
        assertEquals(xPos, node.getPositionV().getX(), 0.0001);
        assertEquals(yPos, node.getPositionV().getY(), 0.0001);
        
        assertEquals(isConstantVelocityX, node.isXConstantVelocity());
        assertEquals(isConstantVelocityY, node.isYConstantVelocity());   

    }
    
}
