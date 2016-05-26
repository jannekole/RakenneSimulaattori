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
    Node node1;
    
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
        node1 = new Node(new Vector(24,28), 10, 0.1f);
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
}
