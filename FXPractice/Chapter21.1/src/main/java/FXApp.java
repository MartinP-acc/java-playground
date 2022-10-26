import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

//        ObservableList<String> fruits = FXCollections.observableArrayList("apple","strawberry","bananas","pear","watermelon");
//
//        ChoiceDialog<String> chDialog = new ChoiceDialog<>(fruits.get(0),fruits);
//
//        Platform.runLater(chDialog::showAndWait);
//
//        System.out.println(chDialog.getSelectedItem());
//
//        TextInputDialog tiDialog = new TextInputDialog("something");

//        Platform.runLater(()->{
//            Optional<String> response = tiDialog.showAndWait();
//            System.out.println(response.orElse(tiDialog.getDefaultValue()));
//        });

        Rectangle rectangle = new Rectangle(0,0,900, 600);

        FileChooser fileChooser = new FileChooser();

        EventHandler<MouseEvent> click = mouseEvent -> {
            if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                try {
                    File file = fileChooser.showOpenDialog(stage);
                    Image image = new Image(new FileInputStream(file));
                    rectangle.setFill(new ImagePattern(image));
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        };
        rectangle.addEventHandler(MouseEvent.MOUSE_CLICKED,click);

        Pane root = new Pane(rectangle);
        Scene scene = new Scene(root, 900,600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
