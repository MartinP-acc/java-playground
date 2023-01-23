package pl.com.calmandwritecode;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WallBrick extends Brick {

    public WallBrick(float x, float y, Sprite texture) {
        super(x, y, texture);
    }

    @Override
    public void collision(Ball ball) {
        bounce(ball);
        ball.update();
    }
}