import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.Objects;
import java.util.ResourceBundle;

public class GameMenu extends GlassPane implements LanguageObserver{

    private static final double MARGIN = 40;
    private final double width = 400;
    private final double height = 550;
    private static GameMenu instance;
    public Label resume;
    public Label playWithCpu;
    public Label settings;
    public Label quit;

    private GameMenu(Pane backgroundPane){
        super(backgroundPane);
        showTitle();
        showMainMenu();
    }

    public static void init(Pane backgroundPane){
        instance = new GameMenu(backgroundPane);
    }

    private void showTitle() {
        setDimension(-width,0,width,height);
        Image titleImage = new Image(Objects.requireNonNull(getClass().getResource("assets/title.png")).toExternalForm());
        ImageView titleView = new ImageView(titleImage);
        InnerShadow innerShadow = new InnerShadow();
        titleView.setEffect(innerShadow);
        titleView.setX(MARGIN);
        titleView.setY(MARGIN);
        getChildren().add(titleView);
    }

    private void showMainMenu(){
        resume = initMenuItem();
        resume.setLayoutX(MARGIN);
        resume.setLayoutY(putAt(0));
        resume.setVisible(false);
        getChildren().add(resume);
        playWithCpu = initMenuItem();
        playWithCpu.setLayoutX(MARGIN);
        playWithCpu.setLayoutY(putAt(1));
        getChildren().add(playWithCpu);
        settings = initMenuItem();
        settings.setLayoutX(MARGIN);
        settings.setLayoutY(putAt(2));
        getChildren().add(settings);
        quit = initMenuItem();
        quit.setLayoutX(MARGIN);
        quit.setLayoutY(putAt(3));
        getChildren().add(quit);
    }

    private double putAt(int line){
        final double firstLine = 150;
        final double offset = 45;
        return firstLine+offset*line;
    }

    private Label initMenuItem (){
        Sounds sounds = Sounds.getInstance();
        Label label = new Label();
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

    public void show(){
        TranslateTransition showMenu = new TranslateTransition();
        showMenu.setByX(width);
        showMenu.setDuration(Duration.millis(500));
        showMenu.setNode(this);
        showMenu.setCycleCount(1);
        showMenu.play();
    }

    public void hide(){
        TranslateTransition showMenu = new TranslateTransition();
        SettingsPane.get().setVisible(false);
        showMenu.setByX(-width);
        showMenu.setDuration(Duration.millis(500));
        showMenu.setNode(this);
        showMenu.setCycleCount(1);
        showMenu.play();
    }

    public static GameMenu get(){
        return instance;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        resume.setText(languageBundle.getString("resume"));
        playWithCpu.setText(languageBundle.getString("newGameCPU"));
        settings.setText(languageBundle.getString("settings"));
        quit.setText(languageBundle.getString("quit"));
    }
}
