import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.media.*;
import javafx.stage.Stage;


public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
            Media silverForMonster = new Media("");
            MediaPlayer thatsAll = new MediaPlayer(silverForMonster);
            Button status = new Button("status");
            Button start = new Button("Play");
            start.setOnAction(actionEvent -> thatsAll.play());
            status.setOnAction(actionEvent -> System.out.println(thatsAll.getStatus()));

            thatsAll.setOnError(()-> System.out.println(thatsAll.getError().toString()));

            HBox root = new HBox(start,status);
            Scene scene = new Scene(root, 200,100);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();

    }
}
