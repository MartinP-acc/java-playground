import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class FXApp extends Application{

    private Label setLabel(String text, double x, double y){
        Label label = new Label(text);
        label.setLayoutX(x);
        label.setLayoutY(y);
        return label;
    }

    private Rectangle setRectangle(double x, double y){
        Rectangle rectangle = new Rectangle(x,y,100,50);
        rectangle.setFill(Color.BLUE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        return rectangle;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Label dropShadowLabel = setLabel("DropShadow effect",0,0);
        Rectangle rectDropShadow = setRectangle(10,20);
        rectDropShadow.setEffect(new DropShadow(BlurType.GAUSSIAN,Color.GREY,15,0.5,10,10));

        Label innerShadowLabel = setLabel("InnerShadow effect",150,0);
        Rectangle rectInnerShadow = setRectangle(160,20);
        rectInnerShadow.setEffect(new InnerShadow(BlurType.GAUSSIAN,Color.GREY,10,0.1,10,10));

        Label shadowLabel = setLabel("Shadow effect", 300,0);
        Rectangle rectShadow = setRectangle(310,20);
        rectShadow.setEffect(new Shadow(BlurType.GAUSSIAN,Color.GREY,15));

        Label boxBlurLabel = setLabel("BoxBlur effect", 450,0);
        Rectangle rectBoxBlur = setRectangle(460,20);
        rectBoxBlur.setEffect(new BoxBlur(10,10,3));

        Label gaussianBlurLabel = setLabel("GaussianBlur effect",0,100);
        Rectangle rectGaussianBlur = setRectangle(10,120);
        rectGaussianBlur.setEffect(new GaussianBlur(10));

        Label motionBlurLabel = setLabel("MotionBlur effect",150,100);
        Rectangle rectMotionBlur = setRectangle(160,120);
        rectMotionBlur.setEffect(new MotionBlur(45,15));

        Label bloomLabel = setLabel("Bloom effect",300,100);
        Rectangle rectBloom = setRectangle(310,120);
        rectBloom.setFill(new LinearGradient(0,0,1,1,true, CycleMethod.NO_CYCLE,
                new Stop(0,Color.ORANGE),new Stop(1,Color.BLUE)));
        rectBloom.setEffect(new Bloom(0.4));

        Label glowLabel = setLabel("Glow effect",450,100);
        Rectangle rectGlow = setRectangle(460,120);
        Image image = new Image(new FileInputStream("src/main/resources/fire.jpg"));
        rectGlow.setFill(new ImagePattern(image));
        rectGlow.setEffect(new Glow(0.7));

        Label reflectionLabel = setLabel("Reflection effect",0,200);
        Rectangle rectReflection = setRectangle(10,220);
        rectReflection.setFill(new ImagePattern(image));
        rectReflection.setEffect(new Reflection());

        Label sepiaToneLabel = setLabel("SepiaTone effect",150,200);
        Rectangle rectSepiaTone = setRectangle(160,220);
        rectSepiaTone.setFill(new ImagePattern(image));
        rectSepiaTone.setEffect(new SepiaTone(0.7));

        Label displacementMapLabel = setLabel("DisplacementMap effect ??",300,200);
        Rectangle rectDisplacementMap = setRectangle(310,220);
        rectDisplacementMap.setEffect(new DisplacementMap());

        Label colorAdjustLabel = setLabel("ColorAdjust effect",450,200);
        Rectangle rectColorAdjust = setRectangle(460,220);
        rectColorAdjust.setFill(new ImagePattern(image));
        rectColorAdjust.setEffect(new ColorAdjust(0.3,0.5,0.7,0.9));

        Label perspectiveTransformLabel = setLabel("PerspectiveTransform effect", 0, 300);
        Rectangle rectPerspectiveTransform = setRectangle(10,320);
        rectPerspectiveTransform.setFill(new ImagePattern(image));
        rectPerspectiveTransform.setEffect(new PerspectiveTransform(20,320,100,320,115,370,5,370));

        Label blendLabel = setLabel("Blend effect",0,400);
        Rectangle rectBlend = new Rectangle(150,150);
        ColorInput input1 = new ColorInput(10,420,100,100,Color.YELLOW);
        ColorInput input2 = new ColorInput(60,470,100,100,Color.LIGHTBLUE);
        rectBlend.setEffect(new Blend(BlendMode.MULTIPLY,input1,input2));

        Image image2 = new Image(new FileInputStream("src/main/resources/kwiatek.jpg"));
        ImageInput imageInput1 = new ImageInput(image,210,420);
        ImageInput imageInput2 = new ImageInput(image2,260,470);
        Rectangle rectBlendImage = new Rectangle(150,150);
        rectBlendImage.setEffect(new Blend(BlendMode.DIFFERENCE,imageInput1,imageInput2));

        Pane pane = new Pane();
        pane.getChildren().addAll(dropShadowLabel,rectDropShadow,
                innerShadowLabel,rectInnerShadow,
                shadowLabel,rectShadow,
                boxBlurLabel,rectBoxBlur,
                gaussianBlurLabel,rectGaussianBlur,
                motionBlurLabel,rectMotionBlur,
                bloomLabel,rectBloom,
                glowLabel,rectGlow,
                reflectionLabel,rectReflection,
                sepiaToneLabel,rectSepiaTone,
                displacementMapLabel,rectDisplacementMap,
                colorAdjustLabel,rectColorAdjust,
                perspectiveTransformLabel, rectPerspectiveTransform,
                blendLabel,rectBlend, rectBlendImage);
        Scene scene = new Scene(pane, 600,700);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
