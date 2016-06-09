/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import physics.Beam;
import physics.Node;
import physics.Space;
import physics.Vector;

/**
 * A class to build structures based on user generated text documents.
 * 
 * @author janne
 */
public class Builder {
    
    Space space;    
    HashMap<String, Node> nodes;
    
    
    
    static final String COMPONENTSEPARATOR = "\n"; // new line
    static final String FIELDSEPARATOR = ";";
    static final String VALUESEPARATOR = "\\s";    // white space
    
    public Builder(Space space) {
        this.space = space;
        this.nodes = new HashMap();
    }
    

    
    public void buildNode(String[] valueStrings) {
        double x;
        double y;
        double gravity;
        double updateInterval;
        Vector position;
        
        

        gravity = space.getGravity();
    
        
        x = Double.parseDouble(getValue("x", valueStrings));
        y = Double.parseDouble(getValue("y", valueStrings));

        updateInterval = space.getUpdateInterval();
        position = new Vector(x, y);       
        
        String nodeName = getValue("node", valueStrings);
        Node node = new Node(position, gravity, updateInterval);
        
        nodes.put(nodeName, node);
        
        space.addNode(node);
    }
    private void buildBeam(String[] valueStrings) throws IOException {
        Node node1;
        Node node2;

        String nodeName1;
        String nodeName2;

        double length;
        double materialStiffness;
        int mass;
        int strength;

        materialStiffness = Double.parseDouble(getValue("sf", valueStrings));
        mass = Integer.parseInt(getValue("m", valueStrings));
        strength = Integer.parseInt(getValue("sr", valueStrings));
        length = Double.parseDouble(getValue("l", valueStrings));

        nodeName1 = getValue("a", valueStrings);
        nodeName2 = getValue("b", valueStrings);

        String beamRow = "";
        for (String string : valueStrings) {
            beamRow = beamRow + string;
        }

        if ((node1 = nodes.get(nodeName1)) == null) {
            throw new IOException("Node " + nodeName1 + " not found.");
        } else if ((node2 = nodes.get(nodeName2)) == null) {
            throw new IOException("Node " + nodeName2 + " not found.");
        }

//        node2 = nodes.get(nodeName2);
        try {
            space.addBeam(new Beam(node1, node2, materialStiffness, mass, strength, length));
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }

    }

    private String getValue(String tag, String[] valueStrings) {
        for (String valueString : valueStrings) {
            valueString = valueString.trim();
            String[] splitText = valueString.split(VALUESEPARATOR);
            if (splitText[0].equalsIgnoreCase(tag)) {
                return splitText[1];
            }

            
        }
        return null;
    }

    private void buildComponent(String component) throws IOException {
        
        component = component.trim();
        String [] componentFields = component.split(FIELDSEPARATOR);
        String componentType = componentFields[0].trim();
        
        
        if (componentType.startsWith("node")) {
            buildNode(componentFields);
        } else if (componentType.startsWith("beam")) {
            buildBeam(componentFields);
        }
        
        

    }

    void buildFromFile(String filename) throws IOException {

        String data = stringFromFile(filename);
        buildFromString(data);

    }
    
    public void buildFromString(String string) throws IOException {
        
        space.zeroComponents();
        
        String[] components;
        components = string.split(COMPONENTSEPARATOR);
        for (int rowIndex = 0; rowIndex < components.length; rowIndex++) {
            String component = components[rowIndex].trim();
            try {
                buildComponent(component);
            } catch (IOException e) {
                throw new IOException("Error in file on row " + (rowIndex + 1) + ": " + e.getMessage());
            }

        }
        //väliaikainen:
        space.getNode(0).setXConstantVelocity(true); 
        space.getNode(0).setYConstantVelocity(true);
        space.getNode(1).setXConstantVelocity(true);
        space.getNode(1).setYConstantVelocity(true);

    }    

    private String stringFromFile(String filename) throws IOException {

        FileReader in;

        String data = "";

        in = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(in);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            data = data + line + "\n";

            // System.out.println(data);
        }

        return data;
    }
}
