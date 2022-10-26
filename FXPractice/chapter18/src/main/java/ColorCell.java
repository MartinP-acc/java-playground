import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorCell extends ListCell<Color> {

    private Rectangle rectangle;

    @Override
    protected void updateItem(Color color, boolean b) {
        super.updateItem(color, b);
        rectangle = new Rectangle(30,10);
        if (color != null){
            setText(color.toString());
            rectangle.setFill(color);
        }else {
            setText("unknown");
            rectangle.setFill(Color.WHITE);
        }
        setGraphic(rectangle);
    }
}
