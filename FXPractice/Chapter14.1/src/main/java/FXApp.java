import javafx.animation.FillTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.StrokeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Text strokeTransitionText = new Text("Stroke Transition");
        strokeTransitionText.setX(20);
        strokeTransitionText.setY(60);
        strokeTransitionText.setStrokeWidth(2);
        strokeTransitionText.setFont(Font.font(null, FontWeight.BOLD,50));
        StrokeTransition strokeTransition = new StrokeTransition(Duration.millis(3000),strokeTransitionText,
                Color.RED,Color.BLUE);

        Rectangle rectangle = new Rectangle(20,100,150,50);
        FillTransition fillTransition = new FillTransition(Duration.seconds(3), rectangle,
                Color.YELLOW, Color.BLUEVIOLET);

        Circle circle = new Circle(60,260,50,Color.AQUAMARINE);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(3),circle);
        scaleTransition.setByX(-1);
        scaleTransition.setByY(-1);

        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(10.0,400.0,40.0,400.0,40.0,370.0,50.0,370.0,50.0,400.0,80.0,400.0,80.0,410.0,50.0,410.0,50.0,440.0,40.0,440.0,40.0,410.0,10.0,410.0);
        polygon.setFill(Color.ORANGE);
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3),polygon);
        rotateTransition.setByAngle(360);

        Pane pane = new Pane();
        pane.getChildren().addAll(strokeTransitionText,rectangle,circle,polygon);
        Scene scene = new Scene(pane,600,600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        strokeTransition.setAutoReverse(true);
        strokeTransition.setCycleCount(2);
        strokeTransition.play();

        fillTransition.setAutoReverse(true);
        fillTransition.setCycleCount(2);
        fillTransition.play();

        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2);
        scaleTransition.play();

        rotateTransition.setCycleCount(2);
        rotateTransition.play();
    }
}
