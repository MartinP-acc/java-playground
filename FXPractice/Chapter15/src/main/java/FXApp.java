import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();
        Button b1 = new Button("Regał");
        GridPane.setConstraints(b1,0,0,1,2);
        Button b2 = new Button("Książka");
        GridPane.setConstraints(b2,1,0,3,1);
        Button b3 = new Button("Tytuł");
        GridPane.setConstraints(b3,1,1,1,1);
        Button b4 = new Button("Autor");
        GridPane.setConstraints(b4,2,1,1,1);
        Button b5 = new Button("Rok");
        GridPane.setConstraints(b5,3,1,1,1);
        Button r1c1 = new Button("regał 1");
        GridPane.setConstraints(r1c1,0,2,1,1);
        Button r1c2 = new Button("tytuł 1");
        GridPane.setConstraints(r1c2,1,2,1,1);
        Button r1c3 = new Button("autor 1");
        GridPane.setConstraints(r1c3,2,2,1,1);
        Button r1c4 = new Button("rok 1");
        GridPane.setConstraints(r1c4,3,2,1,1);
        Button r2c1 = new Button("regał 1");
        GridPane.setConstraints(r2c1,0,3,1,1);
        Button r2c2 = new Button("tytuł 1");
        GridPane.setConstraints(r2c2,1,3,1,1);
        Button r2c3 = new Button("autor 1");
        GridPane.setConstraints(r2c3,2,3,1,1);
        Button r2c4 = new Button("rok 1");
        GridPane.setConstraints(r2c4,3,3,1,1);
        b1.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        b2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        b3.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        b4.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        b5.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r1c1.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r1c2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r1c3.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r1c4.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r2c1.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r2c2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r2c3.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        r2c4.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        gridPane.getChildren().addAll(b1,b2,b3,b4,b5,r1c1,r1c2,r1c3,r1c4,r2c4,r2c1,r2c2,r2c3);
        Scene scene = new Scene(gridPane,500,300);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
