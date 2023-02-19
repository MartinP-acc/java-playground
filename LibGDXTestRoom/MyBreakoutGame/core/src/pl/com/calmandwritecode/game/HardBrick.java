package pl.com.calmandwritecode.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

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
    public void collision(Array<Shot> shots, boolean isPowerBall){
        for (int i=0; i<shots.size; i++){
            Shot shot = shots.get(i);
            if (shot.overlaps(this)){
                hits++;
                if (hits>2) destroyed=true;
                else texture = textures[hits];

                if (isPowerBall) destroyed=true;
                else shots.removeIndex(i);
            }
        }
    }

    @Override
    public void collision(Ball ball){
        bounce(ball);

        hits++;
        if (hits>2) destroyed=true;
        else texture = textures[hits];

        if (ball.powerBall) destroyed=true;

        stuckCounter = 0;

        ball.update();
    }

}
