import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class FXApp extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        double canvasSize = 400;
        Canvas canvas = new Canvas(canvasSize,canvasSize);

        Point2D start = new Point2D(3.0*canvasSize/8.0, 3.0*canvasSize/8.0);
        Point2D end = new Point2D(6.0*canvasSize/8.0, 3.0*canvasSize/8.0);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        //drawChessboard(gc, 40, 300);

        //drawDragon(start,end,17,Color.BLUE, gc);

        drawJingJang(gc,300,50);

        Pane pane = new Pane(canvas);
        Scene  scene = new Scene(pane, 400,400);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private void drawDragon(Point2D start, Point2D end, int steps, Color strokeColor, GraphicsContext gc) {
        gc.save();
        if (steps==0){
            gc.beginPath();
            gc.setStroke(strokeColor);
            gc.moveTo(start.getX(),start.getY());
            gc.lineTo(end.getX(),end.getY());
            gc.stroke();
        } else {
            Point2D point1 = new Point2D((start.getX() + end.getX()) / 2, (start.getY()+end.getY())/2);
            Point2D point2 = new Point2D(point1.getX() + end.getY() - point1.getY(), point1.getY()+start.getX()-point1.getX());
            gc.setStroke(strokeColor);
            drawDragon(end,point2,steps-1,null, gc);
            drawDragon(start,point2,steps-1,null, gc);
        }
        gc.stroke();
        gc.restore();
    }

    private void drawChessboard(GraphicsContext gc, double xy, double size){
        for (int row=0; row<8; row++){
            for (int col=0; col<8; col++){
                if ((row+col) % 2 != 0){
                    gc.fillRect(xy,xy,size/8.0,size/8.0);
                }
                gc.translate(size/8.0,0);
            }
            gc.translate(-size,size/8.0);
        }
        gc.strokeRect(xy,xy-size,size,size);
    }

    private void drawJingJang(GraphicsContext gc, double r, double t){
        gc.setFill(Color.BLACK);
        gc.fillArc(t,t,r,r,270,180,ArcType.ROUND);
        gc.fillOval(r/4+t,r/2+t,r/2,r/2);
        gc.setFill(Color.WHITE);
        gc.fillOval(r/4+r/6+t,r/2+r/6+t,r/6,r/6);
        gc.fillOval(r/4+t,t,r/2,r/2);
        gc.setFill(Color.BLACK);
        gc.fillOval(r/4+r/6+t,r/6+t,r/6,r/6);
        gc.strokeOval(t,t,r,r);
    }
}
