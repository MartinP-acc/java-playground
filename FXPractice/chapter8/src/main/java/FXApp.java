import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Label label1 = new Label("linia");
        label1.setLayoutX(10);
        label1.setLayoutY(0);
        Line line = new Line(10,30,30,50);

        Label label2 = new Label("przerywana");
        label2.setLayoutX(50);
        label2.setLayoutY(10);
        Line dashLine = new Line(50,50,90,35);
        dashLine.getStrokeDashArray().addAll(5.0,3.0,5.0,2.0);

        Label label3 = new Label("łamana");
        label3.setLayoutX(105);
        label3.setLayoutY(0);
        Polyline poly = new Polyline();
        poly.getPoints().addAll(100.0,50.0,130.0,25.0,130.0,50.0,145.0,35.0);

        Label label4 = new Label("prostokąt");
        label4.setLayoutX(150);
        label4.setLayoutY(10);
        Rectangle rect = new Rectangle(150,25,50,25);

        Label label5 = new Label("Koło");
        label5.setLayoutX(210);
        label5.setLayoutY(0);
        Circle circle = new Circle(225,35, 12);

        Label label6 = new Label("Elipsa");
        label6.setLayoutX(260);
        label6.setLayoutY(0);
        Ellipse ellipse = new Ellipse(275,35,20,12);

        Label label7 = new Label("Łuk");
        label7.setLayoutX(320);
        label7.setLayoutY(0);
        Arc arc = new Arc(325,50,25,20,0,120);

        Label label8 = new Label("Wielokąt");
        label8.setLayoutX(370);
        label8.setLayoutY(10);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(370.0,25.0,420.0,50.0,420.0,25.0,370.0,50.0);

        Label label9 = new Label("Krzywa Beziera");
        label9.setLayoutX(10);
        label9.setLayoutY(60);
        QuadCurve curve1 = new QuadCurve(10,80,40,100,100,85);
        curve1.setFill(Color.TRANSPARENT);
        curve1.setStrokeWidth(5);
        curve1.setStroke(Color.RED);

        Label label10 = new Label("Sześcienna Beziera");
        label10.setLayoutX(150);
        label10.setLayoutY(60);
        CubicCurve curve2 = new CubicCurve(150,80,180,150,210,30,240,100);
        curve2.setFill(Color.TRANSPARENT);
        curve2.setStrokeWidth(5);
        curve2.setStroke(Color.RED);

        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(300,400),
                new LineTo(200,250),
                new ArcTo(50,50,180,300,250,false,true),
                new ArcTo(50,50,180,400,250,false,true),
                new ClosePath()
        );
        path.setFill(Color.TRANSPARENT);
        path.setStroke(Color.RED);
        path.setStrokeWidth(2);

        SVGPath svgPath = new SVGPath();
        svgPath.setContent("M350,450 C325,380,250,325,250,300 A50,50,180,0,1,350,300 A50,50,180,0,1,450,300" +
                " C450,350,325,400,350,450");
        svgPath.setFill(Color.BLUE);
        svgPath.setStroke(Color.BLUE);
        svgPath.setStrokeWidth(3);

        Pane pane = new Pane();
        pane.getChildren().addAll(label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,
                line,dashLine,poly,rect,circle,ellipse,arc,polygon,curve1,curve2,path,svgPath);
        Scene scene = new Scene(pane,600,600);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
