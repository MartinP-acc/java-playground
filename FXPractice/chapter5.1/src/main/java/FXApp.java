import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXApp extends Application {

    private double newX, newY, oldX, oldY, offX, offY;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label = new Label("Try drag rectangle");
        Rectangle rect = new Rectangle(50,50,100,60);
        oldX = rect.getX();
        oldY = rect.getY();
        rect.setFill(Color.BLUE);
        EventHandler<MouseEvent> mouseHandler2 = e -> {
            rect.setX(e.getX());
            rect.setY(e.getY());
        };
        rect.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseHandler2);

        Pane pane = new Pane();
        pane.getChildren().addAll(label,rect);

        Scene scene = new Scene(pane,500,500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();

    }
}
