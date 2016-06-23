/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physics;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janne
 */
public class SpaceTest {
    private Space space;
    private double gravity;
    private double updateInterval;
    private Node node1;
    private Node node2;
    private Node node3;
    private double length;
    private double stiffness;
    private int mass;
    private int strength;
    
    public SpaceTest() {
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
        
        space.setGravity(10);
        space.setUpdateInterval(0.1);
        
        
        node1 = new Node(new Vector(20, 0), space);
        node2 = new Node(new Vector(100, 0), space);
        node3 = new Node(new Vector(180, 0), space);
        
        length = 100.0;
        stiffness = 100.0;
        mass = 100;
        strength = 1000;
        
        Beam beam1 = new Beam(node1, node2, stiffness, mass, strength, length);
        Beam beam2 = new Beam(node2, node3, stiffness, mass, strength, length);
        
        

        space.addBeam(beam1);
        space.addBeam(beam2);

        space.addNode(node1);
        space.addNode(node2);
        space.addNode(node3);
        
        
        
        double yPos = -10.0*0.1/2*0.1;
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testNode1Position() {
        
        space.step();
        
        double yPos = -10.0 * 0.1 / 2 * 0.1;
        
        double force = stiffness / length * (100 - 80);
        double massOnNode = mass / 2;
        double acceleration = force / massOnNode;
        double averageVelocity = acceleration * 0.1 / 2;
        
        assertEquals(20 - averageVelocity * 0.1, node1.getPositionV().getX(), 0.0001);
        assertEquals(yPos, node1.getPositionV().getY(), 0.0001);
        
    }
    @Test
    public void testNode2Position() {
            
        space.step();
        
        double yPos = -10.0 * 0.1 / 2 * 0.1;
        
        assertEquals(100, node2.getPositionV().getX(), 0.0001);
        assertEquals(yPos, node2.getPositionV().getY(), 0.0001);
    }
}
