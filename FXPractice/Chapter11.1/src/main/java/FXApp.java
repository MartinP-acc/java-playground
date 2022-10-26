import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class FXApp extends Application {

    private DoubleProperty angleY = new SimpleDoubleProperty();

    @Override
    public void start(Stage stage) throws Exception {
        if (Platform.isSupported(ConditionalFeature.SCENE3D)){
            Group group = new Group();
            Sphere sphere = new Sphere(75);
            sphere.getTransforms().add(new Translate(250,250,0));
            Rotate yRotate = new Rotate(0,0,0,0,Rotate.Y_AXIS);
            sphere.getTransforms().add(yRotate);
            yRotate.angleProperty().bind(angleY);
            PhongMaterial material = new PhongMaterial(Color.CADETBLUE);
            PointLight light = new PointLight(Color.WHITE);
            FileInputStream file = new FileInputStream("src/main/resources/earth1.jpg");
            Image image = new Image(file);
            material.setSelfIlluminationMap(image);
            sphere.setMaterial(material);
            light.setTranslateY(150);
            light.setTranslateZ(50);
            group.getChildren().addAll(sphere,light);
            Scene scene = new Scene(group,500,500,true, SceneAntialiasing.BALANCED);
            scene.setFill(Color.BLACK);
            scene.setOnMouseDragged(e -> angleY.set(-e.getSceneX()));
            PerspectiveCamera camera = new PerspectiveCamera();
            camera.setNearClip(0.1);
            camera.setFarClip(500);
            camera.setFieldOfView(30);
            scene.setCamera(camera);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        }else{
            System.out.println("3D not supported");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
