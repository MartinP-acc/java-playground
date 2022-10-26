import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        if (Platform.isSupported(ConditionalFeature.SCENE3D)){
            Group group = new Group();
            Sphere sphere = new Sphere(50);
            sphere.getTransforms().add(new Translate(100,100,0));
            Cylinder cylinder = new Cylinder(30,100);
            cylinder.getTransforms().add(new Translate(250,100,0));
            Box box = new Box(100,100,100);
            box.getTransforms().add(new Translate(400,100,0));
            PointLight pointLight = new PointLight(Color.RED);
            group.getChildren().addAll(sphere,cylinder,box,pointLight);
            pointLight.setTranslateX(350);
            pointLight.setTranslateZ(-100);
            PerspectiveCamera pc = new PerspectiveCamera(true);
            pc.setTranslateZ(-300);
            pc.setNearClip(0.1);
            pc.setFarClip(400);
            pc.setFieldOfView(30);
            group.getTransforms().add(new Translate(-250,-75,100));
            Scene scene = new Scene(group,500,200,true, SceneAntialiasing.BALANCED);
            scene.setCamera(pc);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        }else {
            System.out.println("System is not support 3D");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
