import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class LogTextArea extends TextArea {

    static SimpleStringProperty text;

    public LogTextArea() {
        setLayoutX(355);
        setLayoutY(440);
        setMaxSize(200,70);
        setBackground(new Background(new BackgroundFill(Color.BLACK,new CornerRadii(10), new Insets(10))));
        setEditable(false);
        text = new SimpleStringProperty();
        text.addListener(observable -> Platform.runLater(this::addLog));
        setWrapText(true);
        setOpacity(0.7);
    }

    private void addLog(){
        setText(text.getValue()+"\n"+getText());
    }

    public static SimpleStringProperty getTextLog(){
        return text;
    }
}
