/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threeD;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import physics.Beam;

/**
 *
 * @author janne
 */
public class Beam3D {
    Beam beam;
    Cylinder beamCylinder;
    
    public Beam3D(Beam beam, Xform world) {
        this.beam = beam;

        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);
        beamCylinder = new Cylinder(1, 0);
        world.getChildren().add(beamCylinder);
    }

    public void refresh() {
        

        double node1X = beam.getNodes().get(0).getPositionV().getX();
        double node1Y = beam.getNodes().get(0).getPositionV().getY();
        double node1Z = beam.getNodes().get(0).getPositionV().getZ();
        
        
        double node2X = beam.getNodes().get(1).getPositionV().getX();
        double node2Y = beam.getNodes().get(1).getPositionV().getY();
        double node2Z = beam.getNodes().get(1).getPositionV().getZ();
        
        Point3D origin = new Point3D(node1X, node1Y, node1Z);
        Point3D target = new Point3D(node2X, node2Y, node2Z);        
        
        createConnection(origin, target);
        
        
    }
    
    public void createConnection(Point3D origin, Point3D target) {
        
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        
        
        beamCylinder.setHeight(height);
        beamCylinder.getTransforms().clear();
        beamCylinder.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);


}
    
}
