package pl.com.calmandwritecode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class LifeCounter {

    private Sprite paddleSprite;
    private int lives;
    private BitmapFont font;

    public LifeCounter(TextureAtlas atlas){
        paddleSprite = atlas.createSprite("small_paddle");
        lives = 3;
        font = new BitmapFont();
    }

    public void draw(SpriteBatch batch){
        batch.draw(paddleSprite, 10, Gdx.graphics.getHeight()-20);
        font.draw(batch," x "+lives,paddleSprite.getX()+paddleSprite.getWidth()+10,Gdx.graphics.getHeight()-10);
    }

    public void ballOut(){
        lives -= 1;
    }

    public void extraLife(){
        lives += 1;
    }

    public boolean isGameOver(){
        return lives < 0;
    }

}
