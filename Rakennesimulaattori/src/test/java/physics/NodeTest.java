package physics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import physics.Node;
import physics.Vector;
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
public class NodeTest {
    double updateInterval;
    
    Node node1;
    Node node2;
    Node node3;
    Node node4;
    
    Beam beam1;
    Beam beam2;
    
    public NodeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        updateInterval = 0.1;
        
        node1 = new Node(new Vector(24,28), 10, updateInterval);
        node2 = new Node(new Vector(0,0), 0, updateInterval);
        node3 = new Node(new Vector(100,0), 10, updateInterval);
        node4 = new Node(new Vector(0,100), 10, updateInterval);
        
        
        

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    @Test
    public void positionIsCorrectX() {

        assertEquals(24, node1.getPosition().getX(), 0.001);
        
    }
    
    @Test
    public void positionIsCorrectY() {

        assertEquals(28, node1.getPosition().getY(), 0.001);
        
    }    
    
    @Test
    public void positionIsCorrectAftersetPositionX() {
        Vector newPosition = new Vector(48 , 77);
        node1.setPosition(newPosition);
        assertEquals(48, node1.getPosition().getX(), 0.001);
        
    }
    @Test
    public void positionIsCorrectAftersetPositionY() {
        Vector newPosition = new Vector(48 , 77);
        node1.setPosition(newPosition);
        assertEquals(77, node1.getPosition().getY(), 0.001);
        
    }
    @Test
    public void massIsCorrect() {
        float length = 100;
        float stiffness = 100;
        int mass = 100;
        int strength = 30;
        
        
        beam1 = new Beam(node1, node2, stiffness, mass, strength, length);
        beam2 = new Beam(node1, node3, 50       , 30  , strength, length);
        
        assertEquals(node1.massSum(), 65, 0.001);
        
    }
    @Test
    public void forceSumIsCorrect() {
        float length = 100;
        float stiffness = 100;
        int mass = 100;
        int strength = 30;
        
        Vector position3 = new Vector(120, 0);
        node3.setPosition(position3);
 
        Vector position4 = new Vector(0, 120);
        node4.setPosition(position4);
        
        beam1 = new Beam(node2, node3, stiffness, mass, strength, length);
        beam2 = new Beam(node2, node4, 50       , 30  , strength, length);
        
        beam1.calculateNewState();
        beam2.calculateNewState();
      
        assertEquals(20, node2.forceSum().x, 0.01);
        assertEquals(10, node2.forceSum().y, 0.01);
    }
    @Test
    public void testAccelerationIsCorrect() {
        float length = 100;
        float stiffness = 100;
        int mass = 100;
        int strength = 30;
        
        Vector position3 = new Vector(120, 0);
        node3.setPosition(position3);
 
        Vector position4 = new Vector(0, 120);
        node4.setPosition(position4);
        
        beam1 = new Beam(node2, node3, stiffness, mass, strength, length);
        beam2 = new Beam(node2, node4, 50       , 30  , strength, length);
        
        beam1.calculateNewState();
        beam2.calculateNewState();
        
        node2.calculateNewState();
        node3.calculateNewState();
        node4.calculateNewState();
        
        
        assertEquals(20 / 65f * updateInterval, node2.getVelocityV().x, 0.0001);
        assertEquals(10 / 65f * updateInterval, node2.getVelocityV().y, 0.0001);
        
    }
    @Test(expected = IllegalArgumentException.class)
    public void nullPositionVectorReturnsException() {
        Node node9 = new Node(null, 10, 0.1);

    }
    @Test(expected = IllegalArgumentException.class)
    public void negativeUpdateIntervalReturnsException() {
        Node node9 = new Node(new Vector(10, 10), 10, -0.1);

    }
    @Test(expected = IllegalArgumentException.class)
    public void zeroUpdateIntervalReturnsException() {
        Node node9 = new Node(new Vector(10, 10), 10, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setNullVelocityVectorReturnsException() {
        Node node9 = new Node(new Vector(10, 10), 10, 0.1);
        node9.setInitialVelocity(null);

    }
    
        
}
