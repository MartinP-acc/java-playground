import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ResourceBundle;

public class ShipSetupPane extends GlassPane implements LanguageObserver{

    private static ShipSetupPane instance;
    public Label manually;
    public Label generate;
    private Label header;

    private ShipSetupPane(Pane backgroundPane) {
        super(backgroundPane);
        setDimension(20,150,840,100);
        header = new Label("Set ship position");
        header.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 30));
        header.setTextFill(Color.WHITE);
        header.setLayoutX(230);
        header.setLayoutY(10);
        getChildren().add(header);
        showButtons();
        setVisible(false);
    }

    public void showButtons(){
        manually = initItem("Manually");
        manually.setLayoutX(100);
        manually.setLayoutY(40);
        getChildren().add(manually);
        generate = initItem("Generate");
        generate.setLayoutX(530);
        generate.setLayoutY(40);
        getChildren().add(generate);
    }

    public static void init(Pane backgroundPane) {
        instance = new ShipSetupPane(backgroundPane);
    }

    private Label initItem (String text){
        Sounds sounds = Sounds.getInstance();
        Label label = new Label(text);
        label.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 30));
        label.setTextFill(Color.GREY);
        label.setOnMouseEntered(mouseEvent -> {
            label.setEffect(new Glow());
            label.setTextFill(Color.YELLOWGREEN);
            sounds.playMenu();
        });
        label.setOnMouseExited(mouseEvent -> {
            label.setEffect(null);
            label.setTextFill(Color.GREY);
        });
        return label;
    }

    public static ShipSetupPane get() {
        return instance;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        manually.setText(languageBundle.getString("manual"));
        generate.setText(languageBundle.getString("generate"));
        header.setText(languageBundle.getString("header"));
    }
}
