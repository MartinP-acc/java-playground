import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static helper.GameConst.FIELD;

public class RectButton extends Rectangle {

    public static final Image MISS = new Image("assets/Miss.png");
    public static final Image HIT = new Image("assets/Hit.png");
    private boolean disabled;
    private boolean disabledAll;

    public RectButton(double x, double y, String id){
        super(x,y,FIELD,FIELD);
        this.disabled = false;
        this.disabledAll = false;
        this.setOpacity(0);
        this.setId(id);
        this.setFill(Color.YELLOW);
        handleEvents();
    }

    private void handleEvents(){
        this.setOnMouseEntered(mouseEvent ->{
            if (!disabled & !disabledAll) this.setOpacity(0.6);
        });
        this.setOnMouseExited(mouseEvent -> {
            if (!disabled & !disabledAll) this.setOpacity(0);
        });
    }

    public boolean isDisabledHandler() {
        return disabled | disabledAll;
    }

    public void setDisabledHandlers(boolean status){
        this.disabledAll = status;
    }

    public void setImage(Image rectButtonImage){
        ImagePattern imagePattern = new ImagePattern(rectButtonImage);
        this.setFill(imagePattern);
        this.disabled = true;
        this.setOpacity(1);
    }
}
