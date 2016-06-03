/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import physics.Beam;
import physics.Node;
import physics.Space;
import physics.Vector;

/**
 *
 * @author janne
 */
public class Builder {
    
    Space space;    
    
    public Builder(Space space) {
        this.space = space;
    }
    
    public void build(String string) {
        
        String[] components;
        components = string.split("-");
        for (String component : components) {
            component = component.trim();
            buildComponent(component);            
        }

        
        float gravity = 0.00f;
        
        space.addNode(new Node(new Vector(400, 0), gravity, 0.1f)); 
        space.addNode(new Node(new Vector(400, 400), gravity, 0.1f));
 //       space.addNode(new Node(new Vector(0, 400), gravity, 0.1f));
        
        
        float length = 480;
        float stiffness = 800;
        int mass = 20;
        int strength = 200000;
        
        Beam beam = new Beam(space.getNode(0), space.getNode(1), length, stiffness, mass, strength); //tästä eroon
        space.addBeam(beam);
        space.addBeam(new Beam(space.getNode(0), space.getNode(3), length, stiffness, mass, strength));
        space.addBeam(new Beam(space.getNode(1), space.getNode(2), length, stiffness, mass, strength));
        space.addBeam(new Beam(space.getNode(2), space.getNode(3), length, stiffness, mass, strength));
        space.addBeam(new Beam(space.getNode(2), space.getNode(0), length, stiffness, mass, strength));
        space.addBeam(new Beam(space.getNode(3), space.getNode(1), length, stiffness, mass, strength));
    }
    
    public void buildNode(String[] valueStrings) {
        double x;
        double y;
        float gravity;
        float updateInterval;
        Vector position;
        
        
        x = 100;
        y = 150;
        gravity = space.getGravity();
    
        position = new Vector(x, y);
        
        x = Double.parseDouble(getValue("x", valueStrings));
        y = Double.parseDouble(getValue("y", valueStrings));
        gravity = space.getGravity();
        updateInterval = space.getUpdateInterval();
        position = new Vector(x, y);        
        
        space.addNode(new Node(position, gravity, updateInterval));
    }

    private String getValue(String tag, String[] valueStrings) {
        for (String valueString : valueStrings) {
            String[] tagValue = valueString.trim().split("\\s");
            if (tagValue[0].trim().equalsIgnoreCase(tag)) {
                return tagValue[1];
            }
            
            
        }
        return null;
    }

    private void buildComponent(String component) {
        
        
        if (component.trim().startsWith("node")) {
            buildNode(component.split(";"));
        }
    }
    
    
    
    
}
