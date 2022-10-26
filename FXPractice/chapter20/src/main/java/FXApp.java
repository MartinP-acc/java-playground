import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.WritableDoubleValue;
import javafx.beans.value.WritableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class FXApp extends Application{

    private static final String PASSWORD_TEST = "javafx_passfield";

    @Override
    public void start(Stage stage) throws Exception {

        TextField anyLogin = new TextField();
        anyLogin.setPrefColumnCount(20);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefColumnCount(20);

        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(4);
        textArea.setWrapText(true);

        passwordField.setOnAction(e->{
            String someone = anyLogin.getText();
            if (passwordField.getText().equals(PASSWORD_TEST)){
                textArea.setText("Congratulation "+ (someone.isEmpty()?"John Doe":someone) +", you just write in the Password Field correct password." +
                        " but nothing special will happen");
            } else textArea.setText("Wrong password dushbag");
        });

        Slider slider = new Slider(10,50,12);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(10);
        slider.setMinorTickCount(9);
        slider.setSnapToTicks(true);

        Label lab = new Label("Val = "+slider.getValue());
        slider.setOnMouseReleased(mouseEvent -> lab.setText("Val = "+slider.getValue()));

        VBox root = new VBox(anyLogin,passwordField,textArea,slider,lab);
        Scene scene = new Scene(root, 600,600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
