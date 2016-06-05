/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<String, Node> nodes;
    
    public Builder(Space space) {
        this.space = space;
        this.nodes = new HashMap();
    }
    
    public void build(String string) {
        
        String[] components;
        components = string.split("\n");
        for (String component : components) {
            component = component.trim();
            buildComponent(component);            
        }

        
        float gravity = space.getGravity();
        float updateInterval = space.getUpdateInterval();
        
 //       space.addNode(new Node(new Vector(400, 0), gravity, updateInterval)); 
 //       space.addNode(new Node(new Vector(400, 400), gravity, updateInterval));
 //       space.addNode(new Node(new Vector(0, 400), gravity, updateInterval));
        
        space.getNode(0).setXStationary(true);
        space.getNode(0).setYStationary(true);
        space.getNode(1).setXStationary(true);
        space.getNode(1).setYStationary(true);
        
        
//        float length = 200;
  //      float stiffness = 25000;
    //    int mass = 5;
      //  int strength = 15000;
        
        //Beam beam = new Beam(space.getNode(0), space.getNode(1), length, stiffness, mass, strength); //tästä eroon
       // space.addBeam(beam);
 //       space.addBeam(new Beam(space.getNode(0), space.getNode(3), stiffness, mass, strength));
  //      space.addBeam(new Beam(space.getNode(1), space.getNode(2), stiffness, mass, strength));
    //    space.addBeam(new Beam(space.getNode(2), space.getNode(3), stiffness, mass, strength));
      //  space.addBeam(new Beam(space.getNode(2), space.getNode(0), stiffness, mass, strength));
        //space.addBeam(new Beam(space.getNode(3), space.getNode(1), stiffness, mass, strength));
        
 
        
        
    }
    
    public void buildNode(String[] valueStrings) {
        double x;
        double y;
        float gravity;
        float updateInterval;
        Vector position;
        
        

        gravity = space.getGravity();
    
        
        x = Double.parseDouble(getValue("x", valueStrings));
        y = Double.parseDouble(getValue("y", valueStrings));
        gravity = space.getGravity();
        updateInterval = space.getUpdateInterval();
        position = new Vector(x, y);       
        
        String nodeName = getValue("node", valueStrings);
        Node node = new Node(position, gravity, updateInterval);
        
        nodes.put(nodeName, node);
        
        space.addNode(node);
    }
    private void buildBeam(String[] valueStrings) {
        Node node1;
        Node node2;
        double length;
        double materialStiffness;
        int mass;
        int strength;
        


        
        length = 200;
        materialStiffness = 25000;
        mass = 5;
        strength = 15000;
        
        materialStiffness   = Double.parseDouble(getValue("sf", valueStrings));
        mass                = Integer.parseInt(getValue("m", valueStrings));
        strength            = Integer.parseInt(getValue("sr", valueStrings));
        length              = Double.parseDouble(getValue("l", valueStrings));
        
        String nodeName1  = getValue("a", valueStrings);
        String nodeName2  = getValue("b", valueStrings);
        
        
        String beamRow = "";
        for (String string : valueStrings) {
            beamRow = beamRow + string;
        }
        if ((node1 = nodes.get(nodeName1)) == null) {
            
            System.out.print(beamRow);
        }
        if ((node2 = nodes.get(nodeName2)) == null) {
            System.out.print(beamRow);
        }
//        node2 = nodes.get(nodeName2);
        
        
        
        space.addBeam(new Beam(node1, node2, materialStiffness, mass, strength, length));
        
    
    }
     

    private String getValue(String tag, String[] valueStrings) {
        for (String valueString : valueStrings) {
            valueString = valueString.trim();
            String tagFromText = valueString.split("\\s")[0];
            if (tagFromText.equalsIgnoreCase(tag)) {
                valueString = valueString.replaceAll("[^0-9-]*", "");
                return valueString;
            }
            
            
        }
        return null;
    }

    private void buildComponent(String component) {
        
        
        if (component.trim().startsWith("node")) {
            buildNode(component.split(";"));
        }
        if (component.trim().startsWith("beam")) {
            buildBeam(component.split(";"));
        }        
        
    }

    void buildFromFile(String filename) {
        String data = stringFromFile(filename);
        build(data);
    }

    private String stringFromFile(String filename) {
        
      FileReader in;
      
      String data = "";
      try {
         in = new FileReader(filename);
         BufferedReader bufferedReader = new BufferedReader(in);
         String line;
         while ((line = bufferedReader.readLine()) != null) {
                data = data + line + "\n";

               // System.out.println(data);
            }
        }
      catch (IOException e) { 
                 
                 }
      
      return data;
    }

   
    
    
    
}
