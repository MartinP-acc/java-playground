package pl.com.calmandwritecode.game;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WallBrick extends Brick {

    public WallBrick(float x, float y, Sprite texture) {
        super(x, y, texture);
        destroyable = false;
        pointsWorth = 50;
    }

    @Override
    public void collision(Ball ball) {
        bounce(ball);
        ball.update();
        if (ball.powerBall) destroyed=true;
        else stuckCounter++;
    }
}
