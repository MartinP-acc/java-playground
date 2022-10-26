package helper;

import static helper.GameConst.FIELD;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y){
        this.x = x * FIELD;
        this.y = y * FIELD;
    }

    public void setX(double x) {
        this.x = x * FIELD;
    }

    public void setY(double y) {
        this.y = y * FIELD;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
