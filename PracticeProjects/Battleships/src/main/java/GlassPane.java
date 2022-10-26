import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

abstract class GlassPane extends Pane {

    private final Pane backgroundPane;
    private WritableImage image;
    private Rectangle rectangle;

    public GlassPane(Pane backgroundPane){
        this.backgroundPane = backgroundPane;
    }

    public void setDimension(double x, double y, double width, double height){
        setLayoutX(x);
        setLayoutY(y);
        setMinSize(width,height);
        Rectangle clip = new Rectangle(5,5,width-10,height-10);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        int blurInit = 15;
        BoxBlur blur = new BoxBlur(blurInit, blurInit, 3);
        blur.setInput(new ColorAdjust(0,0,-0.7,0));
        rectangle = new Rectangle(0,0,width,height);
        rectangle.setEffect(blur);
        rectangle.setClip(clip);
        getChildren().add(rectangle);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (isVisible() && getWidth()>0) updateBackground();
            }
        };
        animationTimer.start();
    }

    private void updateBackground(){
        if (image == null ||
        getHeight() != image.getHeight() ||
        getWidth() != image.getWidth()){
            image = new WritableImage((int) getWidth(), (int) getHeight());
            rectangle.setFill(new ImagePattern(image));
        }

        double x = getLayoutX();
        double width = getWidth();
        if (getLayoutX()<0){
            x = 0;
            width = getWidth() + getLayoutX();
        }
        double y = getLayoutY() < 0 ? 0 : getLayoutY();
        SnapshotParameters snparam = new SnapshotParameters();
        Rectangle2D rect = new Rectangle2D(x,y,width,getHeight());
        snparam.setViewport(rect);
        backgroundPane.snapshot(snparam,image);
    }


}
