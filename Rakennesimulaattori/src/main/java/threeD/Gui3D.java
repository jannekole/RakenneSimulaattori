/*
 * Copyright (c) 2013, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package threeD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import static javafx.application.Platform.setImplicitExit;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.Timer;

import physics.Space;
import logic.Builder;
import physics.Beam;

/**
 *
 * @author cmcastil
 */
public class Gui3D extends Application {

    final Group root = new Group();
    final Xform axisGroup = new Xform();
    final Xform moleculeGroup = new Xform();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    private static final double CAMERA_INITIAL_DISTANCE = -1500;
    private static final double CAMERA_INITIAL_X_ANGLE = 0.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 0.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
    private static final double AXIS_LENGTH = 250.0;
    private static final double HYDROGEN_ANGLE = 104.5;
    private static final double CONTROL_MULTIPLIER = 0.1;
    private static final double SHIFT_MULTIPLIER = 10.0;
    private static final double MOUSE_SPEED = 0.1;
    private static final double ROTATION_SPEED = 2.0;
    private static final double TRACK_SPEED = 4;

    Space space;
    Builder builder;
    Timer timer;
    ArrayList<Beam3D> beam3Ds;

    String filePath;
            
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    private RunnableSpace runnableSpace;
    private Stage primaryStage;

    //   private void buildScene() {
    //       root.getChildren().add(world);
    //   }
    private void buildCamera() {
        System.out.println("buildCamera()");
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);

    }

    private void buildAxes() {
        System.out.println("buildAxes()");
        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        final Box xAxis = new Box(AXIS_LENGTH, 1, 1);
        final Box yAxis = new Box(1, AXIS_LENGTH, 1);
        final Box zAxis = new Box(1, 1, AXIS_LENGTH);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);

        axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
        axisGroup.setVisible(true);
        world.getChildren().addAll(axisGroup);
    }

    private void handleMouse(Scene scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);

                double modifier = 1.0;

                if (me.isAltDown()) {
                    modifier = CONTROL_MULTIPLIER;
                }
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                }
                if (me.isControlDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * MOUSE_SPEED * modifier * ROTATION_SPEED);
                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * MOUSE_SPEED * modifier * ROTATION_SPEED);
                } else if (me.isPrimaryButtonDown()) {
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
                } else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaY * MOUSE_SPEED * modifier * 20;
                    camera.setTranslateZ(newZ);
                }

            }
        });
    }

    private void handleKeyboard(Scene scene, final Node root) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        runnableSpace.playPause();
                        break;
                    case O:
                        FileChooser fileChooser = new FileChooser();

                        File file = fileChooser.showOpenDialog(primaryStage);
                        filePath = file.getAbsolutePath();
                        openFile(filePath);
                    case R:

                        openFile(filePath);

                    case Z:
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
                        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
                        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
                        break;
                    case X:
                        axisGroup.setVisible(!axisGroup.isVisible());
                        break;

                    case V:
                        moleculeGroup.setVisible(!moleculeGroup.isVisible());
                        break;
                }
            }
        });
    }
/*
    private void buildBeam(Beam beam) { //not used
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);

        double node1X = beam.getNodes().get(0).getPositionV().getX();
        double node1Y = beam.getNodes().get(0).getPositionV().getY();
        double node1Z = 0;

        double length = 200;

        double node2X = beam.getNodes().get(1).getPositionV().getX();
        double node2Y = beam.getNodes().get(1).getPositionV().getY();
        double node2Z = 0;

        Point3D origin = new Point3D(node1X, node1Y, node1Z);
        Point3D target = new Point3D(node2X, node2Y, node2Z);

        Rectangle beamRect = createConnection(origin, target);

        world.getChildren().addAll(beamRect);

    }

    public Rectangle createConnection(Point3D origin, Point3D target) {
        Point3D yAxis = new Point3D(0, 1, 0);
        Point3D diff = target.subtract(origin);
        double height = diff.magnitude();

        Point3D mid = target.midpoint(origin);
        Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());

        Point3D axisOfRotation = diff.crossProduct(yAxis);
        double angle = Math.acos(diff.normalize().dotProduct(yAxis));
        Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);

        Rectangle rect = new Rectangle(100, 1);
        Cylinder line = new Cylinder(100, 1);

        rect.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);

        return rect;
    }
*/

    @Override
    public void start(Stage primaryStage) {
        
        this.primaryStage = primaryStage;
        // setUserAgentStylesheet(STYLESHEET_MODENA);
        System.out.println("start()");

        root.getChildren().add(world);
        root.setDepthTest(DepthTest.ENABLE);

        // buildScene();
        buildCamera();

        space = new Space();
        space.setUpdateInterval(0.0001);
        runnableSpace = new RunnableSpace(space);

        Thread spaceThread = new Thread(runnableSpace);
        spaceThread.setDaemon(true);
        spaceThread.start();

        builder = new Builder(space);
        filePath = "C:\\Users\\janne\\rakenneSimulaattori\\Rakennesimulaattori\\valmitRakenteet\\pendulum.txt";
        openFile(filePath);


        Scene scene = new Scene(root, 1024, 900, true);
        scene.setFill(Color.GREY);
        handleKeyboard(scene, world);
        handleMouse(scene, world);
        
        
        
        primaryStage.setTitle("Molecule Sample Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setCamera(camera);
        /*
         Platform.runLater(new Runnable() {
         @Override
         public void run() {
         int i = 0;
         while (i < 10) {
         i++;
         space.stepFor(2000);
         refresh();
         }
         }
         });

         */
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame moveBall = new KeyFrame(Duration.millis(50),
                new EventHandler<ActionEvent>() {
                    private double time = 0;

                    @Override
                    public void handle(ActionEvent event) {
                        runnableSpace.pause();
                     
                        //if (space.getTime() > time) {
                        refresh();
                        //}
                        time = space.getTime();
                        runnableSpace.play();
                    }
                });
        timeline.getKeyFrames().add(moveBall);
        timeline.play();

//        timer = newTimer();
//        timer.start();
    }

    private void refresh() {
        for (Beam3D beam3d : beam3Ds) {
            beam3d.refresh();
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void openFile(String filePath) {
        
        try {
            builder.buildFromFile(filePath);
        } catch (Exception e) {
            System.out.print("failed ");
        }

        beam3Ds = new ArrayList();

        ArrayList<Beam> beams = space.getBeams();
        world.getChildren().clear();
        buildAxes();
        for (Beam beam : beams) {
            beam3Ds.add(new Beam3D(beam, world));

        }
    }

}
