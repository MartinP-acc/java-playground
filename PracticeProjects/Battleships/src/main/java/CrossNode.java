import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class CrossNode extends ImageView {

    public CrossNode(double x, double y){
        Image crossImage = new Image(Objects.requireNonNull(getClass().getResource("assets/cross.png")).toExternalForm());
        setImage(crossImage);
        setLayoutX(x);
        setLayoutY(y);
        setOnMouseEntered(mouseEvent -> setEffect(new Glow(0.6)));
        setOnMouseExited(mouseEvent -> setEffect(null));
    }

    public void setRotation(double sceneX, double sceneY){
            this.setRotate(sceneX+sceneY);
    }
}
