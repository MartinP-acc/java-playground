package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends Rectangle {

    private final float widthScreen;
    private final Sprite paddleTexture;

    public Paddle(float widthScreen, TextureAtlas atlas){
        this. widthScreen = widthScreen;
        paddleTexture = atlas.createSprite("paddle");
        x = widthScreen/2;
        y = 40;
        width = paddleTexture.getWidth();
        height = paddleTexture.getHeight();
    }

    public void draw(SpriteBatch batch){
        batch.draw(paddleTexture,x,y);
    }

    public void update(){
        x=Gdx.input.getX()-120;
        if (x>widthScreen-240) x= widthScreen-240;
        if (x<0) x=0;
    }

    public void collision(Ball ball) {
        Vector2 start = new Vector2(x, y + height);
        Vector2 end = new Vector2(x + width, y + height);
        Vector2 center = new Vector2(ball.x+ball.xSpeed, ball.y+ball.ySpeed);
        boolean wasCollision = false;

        if (Intersector.intersectSegmentCircle(start, end, center, ball.radius*ball.radius)||
                Intersector.intersectSegmentCircle(new Vector2(x,y), new Vector2(x+width,y), center, ball.radius*ball.radius)) {
            if (ball.ySpeed<0)ball.ySpeed = -ball.ySpeed;
            ball.y += ball.ySpeed;
            if (ball.x < x + 60) {
                ball.xSpeed -= 1;
                if (ball.x < x + 30) ball.xSpeed -= 1;
            } else if (ball.x > x + width - 60) {
                ball.xSpeed += 1;
                if (ball.x < x + width - 30) ball.xSpeed -= 1;
            }
            wasCollision = true;
        }else

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

    public void dispose(){
    }
}
