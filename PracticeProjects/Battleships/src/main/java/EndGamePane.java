import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ResourceBundle;


public class EndGamePane extends GlassPane implements LanguageObserver {

    private static EndGamePane instance;
    private Label label;
    private ResourceBundle language;

    private EndGamePane(Pane backgroundPane) {
        super(backgroundPane);
        setDimension(295,200,270,100);
        label = new Label();
        label.setTextFill(Color.YELLOW);
        label.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 30));
        label.setLayoutX(10);
        label.setLayoutY(30);
        label.setEffect(new Glow());
        getChildren().add(label);
        setVisible(false);
    }

    public static void init(Pane backgroundPane){
        instance = new EndGamePane(backgroundPane);
    }

    public void showWinner(String playerName){
        label.setText(playerName+language.getString("won"));
        setVisible(true);
    }

    public static EndGamePane getInstance() {
        return instance;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        language = languageBundle;
    }
}
