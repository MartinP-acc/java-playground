import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.ResourceBundle;

public class OppTurnInfo extends GlassPane implements LanguageObserver{

    private static OppTurnInfo instance;
    private Label label;

    private OppTurnInfo(Pane backgroundPane) {
        super(backgroundPane);
        setDimension(550,200,220,100);
        label = new Label();
        label.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        label.setTextFill(Color.WHITE);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setLayoutY(30);
        label.setLayoutX(20);
        getChildren().add(label);
        setVisible(false);
    }

    public static void init(Pane backgroundPane){
        instance = new OppTurnInfo(backgroundPane);
    }

    public static OppTurnInfo getInstance() {
        return instance;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        label.setText(languageBundle.getString("wait"));
    }
}
