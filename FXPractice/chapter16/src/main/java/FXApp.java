import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Popup popup = new Popup();
        Button hideButton = new Button("hide popup window");
        hideButton.setOnAction(e -> popup.hide());
        Tooltip tooltip = new Tooltip("press to open popup window with button");
        popup.getContent().add(hideButton);

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(new MenuItem("Help"), new MenuItem("About us"), new MenuItem("Contact"));

        Button showPopup = new Button("show popup window");
        showPopup.setOnAction(e -> popup.show(stage));
        showPopup.setTooltip(tooltip);
        showPopup.setContextMenu(contextMenu);
        Pane pane = new Pane(showPopup);
        Scene scene = new Scene(pane, 300,300);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
