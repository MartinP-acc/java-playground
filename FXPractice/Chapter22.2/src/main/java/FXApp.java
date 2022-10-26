import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        TreeItem<String> heroes = new TreeItem<>("Heroes");

        TreeItem<String> dc = new TreeItem<>("DC");
        TreeItem<String> marvel = new TreeItem<>("Marvel");

        TreeItem<String> batman = new TreeItem<>("Batman");
        TreeItem<String> superman = new TreeItem<>("Superman");
        TreeItem<String> flash = new TreeItem<>("Flash");
        TreeItem<String> aquaman = new TreeItem<>("Aquaman");

        TreeItem<String> thor = new TreeItem<>("Thor");
        TreeItem<String> hulk = new TreeItem<>("Hulk");
        TreeItem<String> ironman = new TreeItem<>("Ironman");
        TreeItem<String> spiderman = new TreeItem<>("Spiderman");

        heroes.getChildren().addAll(dc,marvel);
        dc.getChildren().addAll(batman,superman,flash,aquaman);
        marvel.getChildren().addAll(thor,hulk,ironman,spiderman);

        TreeView<String> tree = new TreeView<>(heroes);

        tree.setOnMouseClicked(mouseEvent -> System.out.println(tree.getSelectionModel().getSelectedItem().getValue()));

        tree.setEditable(true);
        tree.setCellFactory(TextFieldTreeCell.forTreeView());

        Pane root = new Pane(tree);
        Scene scene = new Scene(root,500,500);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
