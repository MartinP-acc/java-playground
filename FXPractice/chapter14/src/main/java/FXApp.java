import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Rectangle rectangle = new Rectangle(200,200,100,50);
        KeyValue val1 = new KeyValue(rectangle.rotateProperty(),0, Interpolator.EASE_BOTH);
        KeyValue val2 = new KeyValue(rectangle.rotateProperty(), 360,Interpolator.EASE_BOTH);
        KeyFrame frame1 = new KeyFrame(Duration.millis(0),val1);
        KeyFrame frame2 = new KeyFrame(Duration.millis(4000),val2);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1,frame2);
        timeline.setCycleCount(4);

        Pane pane = new Pane();
        pane.getChildren().add(rectangle);
        Scene scene = new Scene(pane,400,400);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

        timeline.play();
    }
}
