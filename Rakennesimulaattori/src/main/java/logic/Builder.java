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
    static final String VALUESEPARATOR = "\\s+";    // white space
    private Vector offset;

    /**
     * Constructs a new Builder with the given Space.
     * @param space The Space to build the components into
     */
    public Builder(Space space) {
        this.space = space;
        this.nodes = new HashMap();
        this.offset = new Vector(0, 0);
    }


    private void buildNode(String[] valueStrings) {
        double x;
        double y;
        double z = 0;
        
        Vector position;

        x = Double.parseDouble(getValue("x", valueStrings));
        y = Double.parseDouble(getValue("y", valueStrings));
        
        String zString = getValue("z", valueStrings);
        
        if (!zString.equals("")) {
            z = Double.parseDouble(zString);
        }

        position = new Vector(x, y, z).add(offset);

        String nodeName = getValue("node", valueStrings);
        Node node = new Node(position, space);

        setNodeConstanSpeed(node, valueStrings);

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

        materialStiffness = Double.parseDouble(getValue("stiffness", valueStrings));
        mass = Integer.parseInt(getValue("mass", valueStrings));
        strength = Integer.parseInt(getValue("strength", valueStrings));
        length = Double.parseDouble(getValue("length", valueStrings));

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
                if (splitText.length >= 2) {
                    return splitText[1];
                }
            }
        }
        return "";
    }

    private void buildComponent(String component) throws IOException {

        component = component.trim();
        String[] componentFields = component.split(FIELDSEPARATOR);
        String componentType = componentFields[0].trim();

        if (componentType.startsWith("node")) {
            buildNode(componentFields);
        } else if (componentType.startsWith("beam")) {
            buildBeam(componentFields);
        } else if (componentType.startsWith("offset")) {
            setOffset(componentFields);
        } 
    }

    /**
     * Builds the components given in the file.
     * @param filePath The path of the file that contains the data on the Nodes and Beams
     * @return The Space with Nodes and Beams loaded from file.
     * @throws java.io.IOException If there is a problem reading the specified file or the format of the file is not correct
     */
    public Space buildFromFile(String filePath) throws IOException {

        String data = stringFromFile(filePath);
        buildFromString(data);
        return space;
    }

    private void buildFromString(String string) throws IOException {

        space.setEmpty();

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
    }

    private String stringFromFile(String filePath) throws IOException {

        FileReader in;

        String data = "";

        in = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(in);
        String line;
        
        while ((line = bufferedReader.readLine()) != null) {
            data = data + line + "\n";
        }

        return data;
    }

    private void setNodeConstanSpeed(Node node, String[] valueStrings) {
        boolean isConstantVelocityX = getValue("constantx", valueStrings).equalsIgnoreCase("true");
        node.setXConstantVelocity(isConstantVelocityX);

        boolean isConstantVelocityY = getValue("constanty", valueStrings).equalsIgnoreCase("true");
        node.setYConstantVelocity(isConstantVelocityY);
        
        boolean isConstantVelocityZ= getValue("constantz", valueStrings).equalsIgnoreCase("true");
        node.setZConstantVelocity(isConstantVelocityZ);
    }

    private void setOffset(String[] componentFields) {
        int xOffset = Integer.parseInt(getValue("xoffset", componentFields));
        int yOffset = Integer.parseInt(getValue("yoffset", componentFields));
        offset = new Vector(xOffset, yOffset);
    }
}
