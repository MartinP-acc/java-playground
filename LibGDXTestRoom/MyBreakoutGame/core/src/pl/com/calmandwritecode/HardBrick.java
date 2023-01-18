package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class HardBrick extends Brick{

    private int hits;
    private static Texture[] textures = new Texture[]{
            new Texture(Gdx.files.internal("hard_brick0.png")),
            new Texture(Gdx.files.internal("hard_brick1.png")),
            new Texture(Gdx.files.internal("hard_brick2.png"))
    };

    public HardBrick(float x, float y, Texture texture) {
        super(x, y, textures[0]);
        hits = 0;
    }

    @Override
    public void collision(Ball ball){
        if (ball.y<y || ball.y>y+height) ball.ySpeed =-ball.ySpeed;
        if (ball.x>x+width && ball.xSpeed<0) ball.xSpeed =-ball.xSpeed;
        if (ball.x<x && ball.xSpeed>0) ball.xSpeed =-ball.xSpeed;

        hits++;
        if (hits>2) destroyed=true;
        else texture = textures[hits];
        ball.update();
    }

}
