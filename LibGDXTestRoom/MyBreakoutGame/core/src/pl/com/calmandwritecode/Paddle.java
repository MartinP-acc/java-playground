package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends Rectangle {

    private float widthScreen;
    private Texture paddleTexture;

    public Paddle(float widthScreen){
        this. widthScreen = widthScreen;
        paddleTexture = new Texture(Gdx.files.internal("paddle.png"));
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

    public void collision(Ball ball){
        Vector2 start = new Vector2(x,y+height);
        Vector2 end = new Vector2(x+width,y+height);
        Vector2 center = new Vector2(ball.x+ball.xSpeed, ball.y+ball.ySpeed);

        if (Intersector.intersectSegmentCircle(start,end,center,25)){
            ball.ySpeed = -ball.ySpeed;
            if (ball.x<x+60) ball.xSpeed -= 2;
            if (ball.x>x+width-60) ball.xSpeed +=2;
        }else
        if (Intersector.intersectSegmentCircle(new Vector2(x,y),new Vector2(x,y+height),center,25) ||
                Intersector.intersectSegmentCircle(new Vector2(x+width,y),new Vector2(x+width,y+height),center,25)) {
            ball.xSpeed = -ball.xSpeed;
            ball.ySpeed = -ball.ySpeed;

        }
    }

    public void dispose(){
        paddleTexture.dispose();
    }


}
