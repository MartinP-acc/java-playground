package pl.com.calmandwritecode.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends Rectangle {

    private final float widthScreen;
    private final Sprite paddleTexture;
    private boolean readyToThrow;

    public Paddle(float widthScreen, TextureAtlas atlas){
        this. widthScreen = widthScreen;
        paddleTexture = atlas.createSprite("paddle120");
        x = widthScreen/2;
        y = 40;
        width = paddleTexture.getWidth();
        height = paddleTexture.getHeight();
    }

    public void draw(SpriteBatch batch){
        batch.draw(paddleTexture,x,y);
    }

    public void update(){
        x=Gdx.input.getX()-width/2;
        if (x>widthScreen-width) x= widthScreen-width;
        if (x<0) x=0;
    }

    public void collision(Ball ball) {

        if (readyToThrow){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                readyToThrow = false;
                ball.ySpeed=4;
                ball.xSpeed=2;
            }
        }

        Vector2 start = new Vector2(x, y + height);
        Vector2 end = new Vector2(x + width, y + height);
        Vector2 center = new Vector2(ball.x, ball.y);
        boolean wasCollision = false;

        if (Intersector.intersectSegmentCircle(start, end, center, ball.radius*ball.radius)||
                Intersector.intersectSegmentCircle(new Vector2(x,y), new Vector2(x+width,y), center, ball.radius*ball.radius)) {
            if (ball.ySpeed<0)ball.ySpeed = -ball.ySpeed;
            ball.y += ball.ySpeed;
            if (ball.x < x + width/4) {
                ball.xSpeed -= 1;
                if (ball.x < x + width/8) ball.xSpeed -= 1;
            } else if (ball.x > x + width - width/4) {
                ball.xSpeed += 1;
                if (ball.x < x + width - width/8) ball.xSpeed -= 1;
            }
            wasCollision = true;
        }

        if (Intersector.intersectSegmentCircle(new Vector2(x, y), new Vector2(x, y + height), center, ball.radius*ball.radius)){
            ball.x = x-ball.radius-5;
            ball.xSpeed = -5;
            wasCollision = true;
        }else
        if (Intersector.intersectSegmentCircle(new Vector2(x + width, y), new Vector2(x + width, y + height), center, ball.radius*ball.radius)) {
            ball.x = x+width+ball.radius+5;
            ball.xSpeed = 5;
            wasCollision = true;
        }

        if (wasCollision)ball.playBounce();
    }

    public void setReadyToThrow(){
        readyToThrow = true;
    }

    public boolean isReadyToThrow() {
        return readyToThrow;
    }

}