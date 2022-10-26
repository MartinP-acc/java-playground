import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle rect = new Rectangle(250, 250, 200, 100);
        rect.setOnZoom(e -> {
            rect.setScaleX(e.getTotalZoomFactor());
            rect.setScaleY(e.getTotalZoomFactor());
        });
        rect.setOnRotate(e ->
            rect.rotateProperty().set(e.getTotalAngle())
        );

        rect.setFill(Color.BLUEVIOLET);
        rect.setEffect(new InnerShadow(10,10,10,Color.BLUE));
        rect.setEffect(new DropShadow(15,20,20,Color.BLACK));


        Pane pane = new Pane();
        pane.getChildren().add(rect);
        Scene scene = new Scene(pane,700,700);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
