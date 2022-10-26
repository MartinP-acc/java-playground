import helper.GridSetup;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static helper.GameConst.FIELD;

public class BoardDrawing extends Canvas {

    private static final double BOARD_DIMENSION = FIELD*11.5;
    private final GraphicsContext brush;

    public BoardDrawing(GridSetup gridSetup){
        super(BOARD_DIMENSION,BOARD_DIMENSION);
        this.brush = this.getGraphicsContext2D();
        this.setLayoutX(gridSetup.getBoardStartX());
        this.setLayoutY(gridSetup.getBoardStartY());
    }

    public void initBoard(Color backgroundColor){
        drawBackground(backgroundColor);
        drawGrid();
        drawGridHeaders();
    }

    private void drawBackground(Color backgroundColor) {
        double dimensions = BOARD_DIMENSION;
        double arc = 30;
        brush.setGlobalAlpha(0.7);
        brush.setFill(backgroundColor);
        brush.fillRoundRect(0,0,dimensions,dimensions,arc,arc);
    }

    private void drawGrid() {
        final double GRID_DIMENSION = FIELD*10;
        double x = FIELD;
        double y;
        brush.setStroke(Color.WHITE);
        brush.setLineWidth(2);
        for (int i = 1; i<=10; i++){
            y = FIELD*i;
            brush.strokeRect(x,y,GRID_DIMENSION,FIELD);
            brush.strokeRect(y,x,FIELD,GRID_DIMENSION);
        }
    }

    private void drawGridHeaders() {
        final double OFFSET = 10;
        double x, y;
        brush.setGlobalAlpha(1);
        brush.setFill(Color.WHITE);
        brush.setFont(Font.font("Courier New",20));
        for (int i = 1; i<=10 ; i++){
            char letter = (char)(i+96);
            x = FIELD*i + OFFSET;
            y = FIELD - OFFSET;
            brush.fillText(letter+"",x,y);
            x = OFFSET;
            y = FIELD + FIELD*i - OFFSET;
            brush.fillText(i+"",x,y);
        }
    }
}
