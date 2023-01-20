package pl.com.calmandwritecode;

import com.badlogic.gdx.graphics.Texture;

public class WallBrick extends Brick {

    public WallBrick(float x, float y, Texture texture) {
        super(x, y, texture);
    }

    @Override
    public void collision(Ball ball) {
        bounce(ball);
        ball.update();
    }
}
