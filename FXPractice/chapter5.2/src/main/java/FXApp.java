import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Rectangle rectangle = new Rectangle(10,10,200,100);
        Circle circle = new Circle(60,60, 50);

        rectangle.setFill(Color.BLUEVIOLET);
        circle.setFill(Color.RED);

        Pane leftPane = new Pane();
        leftPane.setMinSize(400,400);
        leftPane.setLayoutX(0);
        leftPane.setLayoutY(0);
        leftPane.getChildren().add(rectangle);
        leftPane.setBorder(new Border(new BorderStroke(Color.AQUAMARINE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        EventHandler<MouseEvent> mdraged = e-> {
            if (e.getTarget().equals(rectangle)) {
                rectangle.setX(e.getX());
                rectangle.setY(e.getY());
            }
            if (e.getTarget().equals(circle)){
                circle.setCenterX(e.getX());
                circle.setCenterY(e.getY());
            }
        };
        EventHandler<MouseEvent> mrelease = e-> {
            if (e.getTarget().equals(rectangle)) {
                rectangle.setMouseTransparent(false);
            }
            if (e.getTarget().equals(circle)){
                circle.setMouseTransparent(false);
            }
        };
        EventHandler<MouseEvent> pressed = e-> {
            if (e.getTarget().equals(rectangle) || e.getTarget().equals(circle))
                ((Shape)e.getTarget()).setMouseTransparent(true);
        };

        EventHandler<MouseEvent> dragDet = e->{
            if (e.getTarget().equals(rectangle) || e.getTarget().equals(circle))
                ((Shape) e.getTarget()).startFullDrag();
        };



        Pane rightPane = new Pane();
        rightPane.setMinSize(400,400);
        rightPane.setLayoutX(500);
        rightPane.setLayoutY(0);
        rightPane.getChildren().add(circle);
        rightPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        EventHandler<MouseDragEvent> mde = e -> {
            if (e.getTarget().equals(rectangle)) {
                rectangle.setX(e.getX());
                rectangle.setY(e.getY());
                if (!((Pane) e.getSource()).getChildren().contains(rectangle))
                    ((Pane) e.getSource()).getChildren().add(rectangle);
                if ( e.getSource().equals(rightPane)) rightPane.toFront();
                else leftPane.toFront();
            }
        };

        leftPane.addEventHandler(MouseEvent.MOUSE_RELEASED,mrelease);
        leftPane.addEventHandler(MouseEvent.MOUSE_PRESSED,pressed);
        leftPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,mdraged);
        leftPane.addEventHandler(MouseEvent.DRAG_DETECTED,dragDet);
        rightPane.addEventHandler(MouseEvent.MOUSE_RELEASED,mrelease);
        rightPane.addEventHandler(MouseEvent.MOUSE_PRESSED,pressed);
        rightPane.addEventHandler(MouseEvent.MOUSE_DRAGGED,mdraged);
        rightPane.addEventHandler(MouseEvent.DRAG_DETECTED,dragDet);
        rightPane.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, mde);
        leftPane.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, mde);

        Pane pane = new Pane();
        pane.getChildren().addAll(leftPane,rightPane);
        pane.setMinSize(900,400);

        Scene scene = new Scene(pane,950,600);
        stage.setScene(scene);
        stage.show();

    }
}
