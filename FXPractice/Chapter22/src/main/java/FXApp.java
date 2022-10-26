import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Pagination pagination = new Pagination(5);

        pagination.setPageFactory(new TextPageFactory("src/main/resources/zasady_poprawnego_pisania_testów_jednostkowych.txt"));
        pagination.setPrefWidth(500);

        VBox vBox = new VBox(pagination);
        Scene scene = new Scene(vBox, 520, 500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();


    }
}
