package physics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class VectorTest {
    
    public VectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void vectorLength() {
        Vector vector = new Vector(3, 4);
        assertEquals(5, vector.length(), 0.0001);
    }

    @Test
    public void vectorDistance() {
        Vector vector1 = new Vector(3, 3);
        Vector vector2 = new Vector(10, 12);
        assertEquals(11.4017, vector1.distance(vector2), 0.001);
    }    
    
    @Test
    public void vectorMultiplication() {
        Vector vector = new Vector(3, 4);
        vector = vector.multiply(5);
        assertEquals(25, vector.length(), 0.0001);
    }

    @Test
    public void vectorAddition() {
        Vector vector1 = new Vector(3, 3);
        Vector vector2 = new Vector(10, 12);
        
        Vector vector3 = vector1.add(vector2);
        
        assertEquals(13, vector3.getX(), 0.0001);
        assertEquals(15, vector3.getY(), 0.0001);        
    }

    @Test
    public void vectorSubtraction() {
        Vector vector1 = new Vector(3, 3);
        Vector vector2 = new Vector(10, 12);
        
        Vector vector3 = vector1.subtract(vector2);
        
        assertEquals(-7, vector3.getX(), 0.0001);
        assertEquals(-9, vector3.getY(), 0.0001);        
    }    
    @Test
    public void vectorToString() {
        Vector vector1 = new Vector(3, 3);
        
        assertEquals("x: 03,0000; y: 03,0000; z: 00,0000", vector1.toString());
    }    
    
}
