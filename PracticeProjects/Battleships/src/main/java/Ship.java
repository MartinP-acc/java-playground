import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

import static helper.GameConst.FIELD;

public class Ship extends ImageView{

    private String alignment;
    private int size;
    private Coordinate[] shipCoords;

    private Ship(double x, double y, String imagePath, String alignment){
        super(imagePath);
        setX(x);
        setY(y);
        this.alignment = alignment;
        if (alignment.equals("V")) rotateImage();
    }

    public static Ship createEmptyShip(int size){
        Ship ship = new Ship(0,0, "assets/Battleship"+size+".png","H");
        ship.size = size;
        ship.shipCoords = new Coordinate[size];
        return ship;
    }

    public static Ship createGenerated(Generator gen, int size){
        Ship genShip = new Ship(gen.getPlayerX(), gen.getPlayerY(), "assets/Battleship"+size+".png", gen.getAlignment());
        genShip.size = size;
        genShip.shipCoords = new Coordinate[size];
        genShip.shipCoords[0] = gen.getCoordinate();
        return genShip;
    }

    private void rotateImage() {
        double pivotX = getX() + FIELD/2;
        double pivotY = getY() + FIELD/2;
        getTransforms().add(new Rotate(90,pivotX,pivotY));
    }

    public int getSize() {
        return size;
    }

    public Coordinate[] getShipCoords() {
        return shipCoords;
    }

    public void setFirstCoordinate(String id){
        shipCoords[0] = Coordinate.parseCoordinate(id);
    }

    public String getAlignment() {
        return alignment;
    }

    public void changeAlignment(){
        if (alignment.equals("H")){
            alignment = "V";
            rotateImage();
        } else {
            alignment = "H";
            getTransforms().remove(0);
        }
    }

}
