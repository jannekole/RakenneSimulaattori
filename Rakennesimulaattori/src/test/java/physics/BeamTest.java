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
        node1 = new Node(new Vector(0,0), 10, 0.1);
        node2 = new Node(new Vector(100,0), 10, 0.1);
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
    public void testBeamForceIsZero() { 
        assertEquals(0f, beam.getForce(), 0.01);
        
    }
    
    @Test // Tests to see if stretching the beam causes an expected amount of force 
    public void testBeamForce() { 
        
        Vector newPosition = new Vector(120, 0);
        node2.setPosition(newPosition);
        beam.calculateNewState();
        
       
        assertEquals(-20, beam.getForce(), 0.01);
        
    }
    
    
    
    @Test //Tests to see if stretching the beam too much causes the beam to break (force = 0)
    public void testBeamForceOverLimitNegative() {
        
        Vector newPosition = new Vector(150, 0);
        node2.setPosition(newPosition);
        beam.calculateNewState();
        assertEquals(0, beam.getForce(), 0.01);
        
    }
    @Test //Tests to see if compressing the beam too much causes the beam to break (force = 0)
    public void testBeamForceOverLimitPositive() {
        
        Vector newPosition = new Vector(50, 0);
        node2.setPosition(newPosition);
        beam.calculateNewState();
        assertEquals(0, beam.getForce(), 0.01);
        
    }
    
    @Test //Tests to see if stretching the beam too much causes the beam to break (force = 0)
    public void testBrokenBeamStillBroken() {
        
        Vector newPosition = new Vector(150, 0);
        node2.setPosition(newPosition);
        beam.calculateNewState();
        assertEquals(0, beam.getForce(), 0.01);
    }
    
    @Test 
    public void testForceDampensWhenForceDecreasing() { 
        
        beam.setDampeningFactor(0.1);
        node2.setPosition(new Vector(120, 0));
        beam.calculateNewState();
        node2.setPosition(new Vector(110, 0));
        beam.calculateNewState();
        
        
        assertEquals(-9, beam.getForce(), 0.01);
        
    }
    
    @Test // Tests to see if stretching the beam causes an expected amount of force 
    public void testNoDampeningWhenForceDecreasingButChangesSign() { 
        
        beam.setDampeningFactor(0.1);
        node2.setPosition(new Vector(120, 0));
        beam.calculateNewState();
        node2.setPosition(new Vector(90, 0));
        beam.calculateNewState();
        
        
        assertEquals(10, beam.getForce(), 0.01);
        
    }
        
    
    
    
     @Test 
    public void testNoDampeningWhenForceStaysSame() { 
        
        beam.setDampeningFactor(0.1);
        node2.setPosition(new Vector(120, 0));
        beam.calculateNewState();
        
        beam.calculateNewState();
        
        
        assertEquals(-20, beam.getForce(), 0.01);
        
    }
       

    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStiffnessThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, -50, 100, 341, 23);
        
        
    }    
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeMassThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 50, -100, 341, 23);
        
        
    }        
        
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStrengthThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 50, 100, -1, 23);
        
        
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeLengthThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 50, 100, 23, -1);
        
        
    }    
    
    //Tests Beam with 0 as an argument
    @Test(expected = IllegalArgumentException.class)
    public void testZeroStiffnessThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 0, 100, 341, 23);
        
        
    }    
    @Test(expected = IllegalArgumentException.class)
    public void testZeroeMassThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 50, 0, 341, 23);
        
        
    }        
        
    @Test(expected = IllegalArgumentException.class)
    public void testZeroStrengthThrowsException() {
        
        
        Beam beam3 = beam = new Beam(node1, node2, 50, 100, 0, 23);
        
        
    }
    @Test(expected = IllegalArgumentException.class)
    public void testNodesInsamePlaceWhenInitializingWithoutLengthThrowsException() {
        
        node1.setPosition(new Vector(10, 10));
        node2.setPosition(new Vector(10, 10));
        
        Beam beam3 = new Beam(node1, node2, 50, 100, 23); //This attempts to create a beam with a length of 0
        
        
        
    }    
    @Test(expected = IllegalArgumentException.class)
    public void testNodesInsamePlaceWhenSettingLengthToNodeDistanceThrowsException() {
        
        node1.setPosition(new Vector(10, 10));
        node2.setPosition(new Vector(10, 20));
        
        Beam beam3 = new Beam(node1, node2, 50, 100, 23); //This attempts to create a beam with a length of 0
        
        node2.setPosition(new Vector(10, 10));
        beam3.setLengthToNodeDistance();
        
        
    }    
    @Test(expected = IllegalArgumentException.class)
    public void testNodesAreTheSameThrowsException() {

        
        Beam beam3 = new Beam(node1, node1, 50, 100, 23, 50); //This attempts to create a beam with a length of 0
        

        
        
    }    
        
    
        
}
