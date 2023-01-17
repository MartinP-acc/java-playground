package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Circle {

    private Texture ballTexture;
    private float widthScreen;
    private float heightScreen;
    public float xSpeed;
    public float ySpeed;
    public Vector2 position;

    public Ball(float widthScreen, float heightScreen){
        set(widthScreen/2,heightScreen/2-100,11);
        ballTexture = new Texture(Gdx.files.internal("ball.png"));
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        xSpeed = 2;
        ySpeed = 4;
        position = new Vector2(x,y);
    }

    public void draw(SpriteBatch batch){
        batch.draw(ballTexture,this.x-11,this.y-11);
    }

    public void dispose(){
        ballTexture.dispose();
    }

    public void update(){
        x+=xSpeed;
        y+=ySpeed;
        if (x<=0 && xSpeed<0) xSpeed = -xSpeed;
        if (x>=widthScreen-radius && xSpeed>0) xSpeed = -xSpeed;
        if (y>=heightScreen-radius && ySpeed>0) ySpeed = -ySpeed;
    }
}
