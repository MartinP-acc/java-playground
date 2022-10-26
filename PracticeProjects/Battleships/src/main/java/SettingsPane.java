import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ResourceBundle;

public class SettingsPane extends GlassPane implements LanguageObserver {

    private static SettingsPane instance;
    private final Sounds sounds = Sounds.getInstance();
    private Label volumeLabel;
    private Label languageLabel;
    private CheckBox soundCB;
    public ComboBox<String> languageBox;

    private SettingsPane(Pane backgroundPane) {
        super(backgroundPane);
        setDimension(500,200,300,200);
        initSoundSetting();
        setVisible(false);
    }

    public static void init(Pane backgroundPane){
        instance = new SettingsPane(backgroundPane);
    }

    private void initSoundSetting() {
        volumeLabel = new Label("Adjust volume");
        volumeLabel.setLayoutX(20);
        volumeLabel.setLayoutY(20);
        volumeLabel.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR, 16));
        volumeLabel.setTextFill(Color.WHITE);
        Slider volumeSlider = new Slider(0,1,sounds.getVolume());
        volumeSlider.setLayoutX(15);
        volumeSlider.setLayoutY(30);
        volumeSlider.setPrefSize(260,50);
        volumeSlider.valueProperty().addListener(observable -> sounds.setVolume(volumeSlider.getValue()));
        soundCB = new CheckBox("On/Off sounds");
        soundCB.setLayoutX(20);
        soundCB.setLayoutY(70);
        soundCB.setSelected(true);
        soundCB.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR,16));
        soundCB.setTextFill(Color.WHITE);
        soundCB.selectedProperty().addListener(observable ->{
                    sounds.setEnable(soundCB.isSelected());
                    volumeSlider.setDisable(!volumeSlider.isDisable());
        });
        languageLabel = new Label("Language");
        languageLabel.setFont(Font.font("Courier New", FontWeight.BOLD, FontPosture.REGULAR,16));
        languageLabel.setTextFill(Color.WHITE);
        languageLabel.setLayoutX(20);
        languageLabel.setLayoutY(110);
        languageBox = new ComboBox<>();
        languageBox.setPlaceholder(new Text("POLSKI"));
        languageBox.getItems().addAll("POLSKI","ENGLISH");
        languageBox.setLayoutX(100);
        languageBox.setLayoutY(110);
        getChildren().addAll(soundCB,volumeLabel,volumeSlider,languageLabel,languageBox);
    }

    public static SettingsPane get(){
        return instance;
    }

    @Override
    public void update(ResourceBundle languageBundle) {
        volumeLabel.setText(languageBundle.getString("volume"));
        soundCB.setText(languageBundle.getString("on/off"));
        languageLabel.setText(languageBundle.getString("language"));
    }
}
