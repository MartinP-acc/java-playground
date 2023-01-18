package pl.com.calmandwritecode;

import com.badlogic.gdx.graphics.Texture;

public class WallBrick extends Brick {

    public WallBrick(float x, float y, Texture texture) {
        super(x, y, texture);
    }

    @Override
    public void collision(Ball ball) {
        if (ball.y<y || ball.y>y+height) ball.ySpeed =-ball.ySpeed;
        if (ball.x>x+width && ball.xSpeed<0) ball.xSpeed =-ball.xSpeed;
        if (ball.x<x && ball.xSpeed>0) ball.xSpeed =-ball.xSpeed;
        ball.update();
    }
}
