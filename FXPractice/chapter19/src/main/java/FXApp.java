import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save as");
        MenuItem print = new MenuItem("Print");
        MenuItem exit = new MenuItem("Exit");

        Menu file = new Menu("File");
        file.getItems().addAll(save,saveAs,print,new SeparatorMenuItem(),exit);

        MenuItem copy = new MenuItem("Copy");
        MenuItem cut = new MenuItem("Cut");
        MenuItem paste = new MenuItem("Paste");
        RadioMenuItem red = new RadioMenuItem("Red");
        RadioMenuItem blue = new RadioMenuItem("Blue");
        RadioMenuItem green = new RadioMenuItem("Green");
        CheckMenuItem stroke = new CheckMenuItem("Stroke on/off");

        ToggleGroup colors = new ToggleGroup();
        colors.getToggles().addAll(red,green,blue);



        Menu edit = new Menu("Edit");
        edit.getItems().addAll(copy,cut,paste,new SeparatorMenuItem(), red, green, blue, new SeparatorMenuItem(), stroke);

        MenuItem aboutUs = new MenuItem("About Us");
        MenuItem version = new MenuItem("Version");

        Menu help = new Menu("Help");
        help.getItems().addAll(aboutUs,version);

        MenuBar menuBar = new MenuBar(file,edit,help);
        menuBar.setMinSize(300,10);

        Rectangle rectangle = new Rectangle(75,50,150,50);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(0);

        red.setOnAction(actionEvent -> rectangle.setFill(Color.RED));
        blue.setOnAction(actionEvent -> rectangle.setFill(Color.BLUE));
        green.setOnAction(actionEvent -> rectangle.setFill(Color.GREEN));

        stroke.setOnAction(actionEvent -> {
            if (stroke.isSelected()) rectangle.setStrokeWidth(2);
            else rectangle.setStrokeWidth(0);
        });

        MenuButton menuButton = new MenuButton("Set Color");
        menuButton.getItems().addAll(red,green,blue);
        menuButton.setLayoutX(100);
        menuButton.setLayoutY(110);

        Pane pane = new Pane(menuBar,rectangle,menuButton);
        Scene scene = new Scene(pane,300,200);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();




    }
}
