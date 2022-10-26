import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Rectangle rectangle = new Rectangle(50,50,200,100);

        Dialog<ButtonType> dialogTest = new Dialog<>();

        ButtonType ok = ButtonType.OK;
        ButtonType cancel = ButtonType.CANCEL;

        dialogTest.getDialogPane().getButtonTypes().addAll(ok,cancel);
        dialogTest.setTitle("Dialog test");
        dialogTest.setHeaderText("Fill rectangle");
        dialogTest.setContentText("Press OK to fill rectangle green color or press cancel to fill it red");
        dialogTest.setGraphic(new Rectangle(30,10));

        Alert testAlert = new Alert(Alert.AlertType.INFORMATION);

        Platform.runLater( ()->{
            Optional<ButtonType> r = dialogTest.showAndWait();
            r.ifPresent(b -> {
                if (b == ButtonType.OK) rectangle.setFill(Color.GREEN);
                if (b == ButtonType.CANCEL) rectangle.setFill(Color.RED);
            });
            testAlert.setContentText(r.toString());
            testAlert.showAndWait();
        });



        Pane root = new Pane(rectangle);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
