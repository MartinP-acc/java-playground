import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle rect = new Rectangle(200,100);
        rect.setFill(Color.BLUEVIOLET);
        Circle circle = new Circle(50);
        circle.setFill(Color.RED);
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        hBox.getChildren().addAll(rect,circle);
        Scene scene = new Scene(hBox,400,400);
        stage.setScene(scene);
        stage.sizeToScene();

        EventHandler<WindowEvent> windowEvent = e ->{
            System.out.print("Handler : ");
            IPrint.print(e);
        };
        EventHandler<MouseEvent> mouseFilter = e ->{
            System.out.print("Handler : ");
            IPrint.print(e);
        };

        rect.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseFilter);
        stage.addEventHandler(WindowEvent.ANY, windowEvent);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
