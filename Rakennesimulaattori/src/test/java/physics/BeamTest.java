package physics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import physics.Node;
import physics.Vector;
import physics.Beam;
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
public class BeamTest {
    Beam beam;
    Node node1;
    Node node2;
    
    
    public BeamTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        node1 = new Node(new Vector(0,0), 10, 0.1f);
        node2 = new Node(new Vector(100,0), 10, 0.1f);
        float length = 100;
        float stiffness = 100;
        int mass = 100;
        int strength = 30;
        
        
        beam = new Beam(node1, node2, stiffness, mass, strength, length);
    }
    
    @After
    public void tearDown() {
    }

    @Test // Tests if the beam at rest produces a force of 0
    public void testBeamForce1() { 
        assertEquals(0f, beam.getForce(), 0.01);
        
    }
    
    @Test // Tests to see if stretching the beam causes an expected amount of force 
    public void testBeamForce2() { 
        
        Vector newPosition = new Vector(120, 0);
        node2.setPosition(newPosition);
        beam.calculateNewState();
        beam.calculateNewState();
        assertEquals(-20, beam.getForce(), 0.01);
        
    }
    @Test //Tests to see if stretching the beam too much causes the beam to break (force = 0)
    public void testBeamForceOverLimitNegative() {
        
        Vector newPosition = new Vector(150, 0);
        node2.setPosition(newPosition);
        assertEquals(0, beam.getForce(), 0.01);
        
    }
    @Test //Tests to see if compressing the beam too much causes the beam to break (force = 0)
    public void testBeamForceOverLimitPositive() {
        
        Vector newPosition = new Vector(50, 0);
        node2.setPosition(newPosition);
        assertEquals(0, beam.getForce(), 0.01);
        
    }
    
    
}
