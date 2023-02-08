package pl.com.calmandwritecode.game;


import com.badlogic.gdx.graphics.g2d.Sprite;

public class HardBrick extends Brick{

    private int hits;
    private final Sprite[] textures;

    public HardBrick(float x, float y, Sprite... textures) {
        super(x, y, textures[0]);
        hits = 0;
        this.textures = textures;
        pointsWorth = 30;

    }

    @Override
    public void collision(Ball ball){
        bounce(ball);

        hits++;
        if (hits>2) destroyed=true;
        else texture = textures[hits];
        ball.update();
    }

}
