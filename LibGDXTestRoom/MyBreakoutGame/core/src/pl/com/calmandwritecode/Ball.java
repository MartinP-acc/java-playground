package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Circle {

    private final Sprite ballTexture;
    private final float widthScreen;
    private final float heightScreen;
    public float xSpeed;
    public float ySpeed;
    public Vector2 position;
    public Vector2 futurePos;
    private  final Sound ballBounceSound;

    public Ball(float widthScreen, float heightScreen, TextureAtlas atlas){
        set(widthScreen/2,heightScreen/2-100,11);
        ballTexture = atlas.createSprite("ball");
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        xSpeed = 2;
        ySpeed = 3;
        position = new Vector2(x,y);
        futurePos = new Vector2(x+xSpeed*10,y+ySpeed*10);
        ballBounceSound = Gdx.audio.newSound(Gdx.files.internal("ball_bounce.ogg"));
    }

    public void draw(SpriteBatch batch){
        batch.draw(ballTexture,x-radius,y-radius);
    }

    public void dispose(){

    }

    public void update(){
        x+=xSpeed;
        y+=ySpeed;
        if (x<=0 && xSpeed<0){
            xSpeed = -xSpeed;
            playBounce();
        }
        if (x>=widthScreen-radius && xSpeed>0){
            xSpeed = -xSpeed;
            playBounce();
        }
        if (y>=heightScreen-radius && ySpeed>0){
            ySpeed = -ySpeed;
            playBounce();
        }
        updateVectors();
    }

    public void updateVectors(){
        position.set(x,y);
        futurePos.set(x+xSpeed*5,y+ySpeed*5);
    }

    public void accelerateBall(){
        xSpeed = xSpeed * 1.05f;
        ySpeed = ySpeed * 1.05f;
    }

    public void playBounce(){
        ballBounceSound.play();
    }
}
