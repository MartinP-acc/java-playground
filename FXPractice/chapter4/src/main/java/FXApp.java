import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) {
        Rectangle rect = new Rectangle(200,200);
        rect.setFill(Color.AQUAMARINE);
        Circle circle = new Circle(40);
        circle.setFill(Color.RED);
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(rect,circle);
        Scene scene = new Scene(hBox, 400, 400);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
