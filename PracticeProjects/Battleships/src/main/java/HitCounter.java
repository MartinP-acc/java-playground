import helper.GridSetup;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ResourceBundle;

import static helper.GameConst.FIELD;

public class HitCounter extends Canvas implements LanguageObserver{

    private static final double WIDTH = FIELD*6;
    private static final double HEIGHT = FIELD*2;
    private final GraphicsContext brush = this.getGraphicsContext2D();
    private final GridSetup gridSetup;
    private String player;
    private Label headerLabel;
    public HitCounter(GridSetup gridSetup){
        super(WIDTH+20, HEIGHT+20);
        this.player = "";
        this.gridSetup = gridSetup;
        setLayoutX(gridSetup.getBoardStartX()+FIELD*3);
        setLayoutY(gridSetup.getBoardStartY()+FIELD*11.7);
        drawHitCounter();
        headerLabel = new Label();
        headerLabel.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 18));
        headerLabel.setTextFill(Color.BLACK);
        headerLabel.setLayoutX(gridSetup.getBoardStartX()+FIELD*3.1);
        headerLabel.setLayoutY(gridSetup.getBoardStartY()+FIELD*11.8);
    }

    private void drawHitCounter(){
        DropShadow shadow = new DropShadow(4,Color.BLACK);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setRadius(8);
        brush.setGlobalAlpha(0.7);
        brush.setFill(Color.DEEPSKYBLUE);
        brush.setStroke(Color.BLACK);
        brush.setLineWidth(4);
        brush.setEffect(shadow);
        brush.fillRoundRect(4,4,WIDTH,HEIGHT,0,0);
        brush.setGlobalAlpha(1);
        brush.drawImage(new Image("assets/Hit.png"),FIELD*4.5,HEIGHT*0.4);
        brush.setFill(Color.BLACK);
        brush.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 20));
        brush.fillText(player, FIELD*0.2, HEIGHT*0.3);
        brush.setEffect(null);
        brush.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 30));
        brush.fillText(" / 20", FIELD*1.7,HEIGHT*0.8);
    }

    public Label getCounterLabel(){
        Label label = new Label();
        label.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 30));
        label.setLayoutX(gridSetup.getBoardStartX()+FIELD*4);
        label.setLayoutY(gridSetup.getBoardStartY()+FIELD*12.58);
        return label;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        player += languageBundle.getString("points");
        headerLabel.setText(player);
    }

    public Label getHeaderLabel() {
        return headerLabel;
    }
}
