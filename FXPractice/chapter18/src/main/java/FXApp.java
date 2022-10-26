import javafx.application.Application;
import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Label tbState = new Label("OFF");
        ToggleButton tb = new ToggleButton("ToggleButton");
        tb.setOnAction(e ->{
            if (tb.isSelected()) tbState.setText("ON");
            else tbState.setText("OFF");
        });

        ToggleButton blueTg = new ToggleButton("blue");
        ToggleButton redTg = new ToggleButton("red");
        ToggleButton greenTg = new ToggleButton("green");
        ToggleGroup colorTg = new ToggleGroup();
        colorTg.getToggles().addAll(blueTg,redTg,greenTg);
        ReadOnlyProperty<Toggle> tgselect = colorTg.selectedToggleProperty();

        ObservableList<Color> colors = FXCollections.observableArrayList(Color.RED,Color.AQUA,Color.BISQUE);
        ChoiceBox<Color> chBox = new ChoiceBox<>(colors);
        Label monthLab = new Label("Colors");
        chBox.setOnAction(actionEvent -> monthLab.setText(chBox.getValue().toString().substring(2,8)));

        ListView<Color> lv = new ListView<>(colors);
        lv.setCellFactory(colorListView -> new ColorCell());
        lv.setPrefSize(70,120);

        ColorPicker colorPicker = new ColorPicker();
        DatePicker datePicker = new DatePicker();

        FlowPane root = new FlowPane(tb, tbState, redTg,greenTg,blueTg,monthLab,chBox,lv,colorPicker,datePicker);
        Scene scene = new Scene(root, 300,300);

        tgselect.addListener((observableValue, toggle, t1) -> {
            if (t1 != null){
                String text = ((ToggleButton) t1).getText();
                System.out.println(text);
                switch (text){
                    case "blue" -> root.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                    case "red" -> root.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                    case "green" -> root.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            }
            else root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        });

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
